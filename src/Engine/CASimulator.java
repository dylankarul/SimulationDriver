package Engine;

import Automaton.AbstractAutomaton;
import Configuration.SimConfig;
import FrontEnd.Screens.*;
import Gridulars.Grid;
import Gridulars.Cell;
import FrontEnd.Screens.ConwayScreen;
import FrontEnd.Screens.AbstractScreen;
import FrontEnd.Screens.MainMenuScreen;
import FrontEnd.Screens.PercolationScreen;
import Gridulars.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.List;
import java.lang.reflect.Constructor;

public class CASimulator extends Application {

    private static String TITLE = "Cellular Automaton Simulator";
    private static final int SCREEN_WIDTH = 600;
    private static final int SCREEN_HEIGHT = 400;
    private static final int SCREEN_HEIGHT_FULL = 600;
    private double FRAMES_PER_SECOND = 3;
    private double MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final Paint BACKGROUND = Color.rgb(120, 144, 156); // material-ui blue grey 400
    private Group root;
    private Stage myStage;
    private Scene myScene;
    private List<Double> autoInfoList;

    private Timeline animation;

    private Grid myGrid;
    private Integer[][] myConfigGrid;
    private SimConfig myConfig;
    private AbstractAutomaton myAutomaton;
    private String myAutomatonName;

    private VBox myStatusBox;

    @Override
    public void start (Stage stage) {
        myStage = stage;
//        myScene = setupSim(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND);
        makeIntroScreen(myStage);
//        animateSim();
    }

    private void makeIntroScreen(Stage stage) {
        MainMenuScreen mainMenuScreen = new MainMenuScreen(SCREEN_WIDTH, SCREEN_HEIGHT_FULL, stage);
        mainMenuScreen.makeScreen();
        stage.setScene(mainMenuScreen.getMyScene());
        stage.setTitle(TITLE);
        stage.show();
    }

    public void setupSim (Stage stage, String csvPath, Shapes shape, String neighborPolicy, String edgePolicy, List<Double> infoList) {
        myStage = stage;
        root = new Group();
        myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT_FULL, BACKGROUND);

        // add all the cells
        autoInfoList = infoList;
        myConfig = new SimConfig(new File(csvPath));
        myConfigGrid = myConfig.getGridArr();
        myAutomatonName = myConfig.getSimType();
        myAutomaton = parseAutomatonClass();
        myGrid = new Grid(myConfigGrid, myAutomaton, SCREEN_WIDTH, SCREEN_HEIGHT, shape, neighborPolicy, edgePolicy); //complete cardinal or vertex for neighpol, non-toroidaltoroidal side-toroidal

        autoInfoList = infoList;
        for(int rowIndex=0; rowIndex<myGrid.getGridHeight(); rowIndex++){
            for(int iter=0; iter<myGrid.getGridWidth(); iter++){
                Cell cell = myGrid.getCellAt(rowIndex, iter);
                cell.getCellVis().getShape().setStroke(Color.BLACK); //INSERT PROPERTY FOR TRANSPARENT
                root.getChildren().add(cell.getCellVis().getShape());
            }
        }

        /*for (Cell[] cRow : myGrid.getMyCellGrid()) {
            for (Cell c : cRow) {
                c.getShape().setStroke(Color.BLACK); //INSERT PROPERTY FOR TRANSPARENT
                root.getChildren().add(c.getShape());
            }
        }*/

        myStage.close();
        myStage.setScene(myScene);
        myStage.show();

        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));

        animateSim();
    }

    private void animateSim() {
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private void step () {
        //call function that updates each cell

        for(int rowIndex=0; rowIndex<myGrid.getGridHeight(); rowIndex++){
            for(int iter=0; iter<myGrid.getGridWidth(); iter++){
                myAutomaton.update(myGrid.getCellAt(rowIndex, iter), myGrid);
            }
        }

        updateWholeGrid();

        //Cell[][] temp =  myAutomaton.update(myGrid.getMyCellGrid());

        for(int rowIndex=0; rowIndex<myGrid.getGridHeight(); rowIndex++){
            for(int iter=0; iter<myGrid.getGridWidth(); iter++){
                Cell c = myGrid.getCellAt(rowIndex, iter);
                root.getChildren().remove(c.getCellVis().getShape());
                c.getCellVis().getShape().setStroke(Color.BLACK);
                root.getChildren().add(c.getCellVis().getShape());
            }
        }

        /*for (Cell[] row : temp) {
            for (Cell c : row) {
                root.getChildren().remove(c.getShape());
                c.getShape().setStroke(Color.BLACK);
                root.getChildren().add(c.getShape());
            }
        }*/
    }

    private void updateWholeGrid(){
        for(int rowIndex=0; rowIndex<myGrid.getGridHeight(); rowIndex++){
            for(int iter=0; iter<myGrid.getGridWidth(); iter++){
                Cell c = myGrid.getCellAt(rowIndex, iter);
                c.updateCell();
            }
        }
    }

    private AbstractAutomaton parseAutomatonClass() {
        AbstractAutomaton automatonClass;
        Constructor<?> automatonConstructor;
        try {
            automatonClass = (AbstractAutomaton) Class.forName("Automaton." + myAutomatonName + "Automaton").getConstructor(List.class).newInstance(autoInfoList);
            return automatonClass;
        } catch (Exception e) {
            // Potential ClassNotFoundException (forName) or NoSuchMethodException (constructor)
            e.printStackTrace();
            return null;
        }
    }

//    private AbstractScreen parseScreenClass() {
//        Class<?> screenClass;
//        Constructor<?> screenConstructor;
//        try {
//            screenClass = Class.forName("FrontEnd.Screens." + myAutomatonName + "Screen");
//            screenConstructor = screenClass.getConstructor(Integer.class, Integer.class, Stage.class);
//            return (AbstractScreen) screenConstructor.newInstance(SCREEN_WIDTH, SCREEN_HEIGHT, myStage);
//        } catch (Exception e) {
//            // Potential ClassNotFoundException (forName) or NoSuchMethodException (constructor)
//            e.printStackTrace();
//            return null;
//        }
//    }

    private void handleKeyInput(KeyCode code) {
        if (code == KeyCode.Q) {
            handleQuit(myStage);
        }
        if (code == KeyCode.SPACE) {
            handlePause();
        }
        if(code == KeyCode.RIGHT){
            handleNext();
        }
        if(code == KeyCode.F){
            handleSpeedUp();
        }
        if(code == KeyCode.S){
            handleSlowDown();
        }
    }

    private void handleNext(){
        if(animation.getStatus()!=Animation.Status.RUNNING){
            step();
        }
    }

    private void handlePause() {
        if (animation.getStatus() == Animation.Status.RUNNING)
            animation.pause();
        else animation.play();
    }

    private void handleSpeedUp(){
        //FRAMES_PER_SECOND*=10;
        animateSim();
    }

    private void handleSlowDown(){
        //FRAMES_PER_SECOND = FRAMES_PER_SECOND/10;
        animateSim();
    }

    private void handleQuit(Stage stage) {
        stage.close();
        animation.stop();
        AbstractScreen autoScreen = null;

        switch(myAutomatonName) {
            case "Conway":
                autoScreen = new ConwayScreen(SCREEN_WIDTH, SCREEN_HEIGHT, stage); break;
            case "Percolation":
                autoScreen = new PercolationScreen(SCREEN_WIDTH, SCREEN_HEIGHT, stage); break;
            case "Fire":
                autoScreen = new FireScreen(SCREEN_WIDTH, SCREEN_HEIGHT, stage); break;
            case "RPS":
                autoScreen = new RPSScreen(SCREEN_WIDTH, SCREEN_HEIGHT, stage); break;

            default: // make main menu screen
                autoScreen = new MainMenuScreen(SCREEN_WIDTH, SCREEN_HEIGHT, stage); break;
        }

        autoScreen.makeScreen();

        stage.setScene(autoScreen.getMyScene());
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
