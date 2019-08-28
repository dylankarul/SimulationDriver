package FrontEnd.Screens;

import Configuration.ReadPropertyFile;
import FrontEnd.Handlers;
import Gridulars.Hexagon;
import Gridulars.Triangle;
import Gridulars.Square;
import Gridulars.Shapes;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractScreen extends Handlers {

    private int        screenWidth;
    private int        screenHeight;
    private Stage      myStage;
    private Scene      myScene;

    private ReadPropertyFile myVisProperties;
    private ReadPropertyFile myConfigProperties;

    private String myScreenTitle;

    private String csv1;
    private String csv2;
    private String csv3;

    private String startButtonText;
    private String backButtonText;

    private String option1ButtonText;
    private String option2ButtonText;
    private String option3ButtonText;

    private Shapes shape;
    private String neighborPolicy;
    private String edgePolicy;

    public AbstractScreen(int sWidth, int sHeight, Stage stage, ReadPropertyFile visProps, ReadPropertyFile configProps) {
        screenWidth = sWidth;
        screenHeight = sHeight;
        myStage = stage;
        if (! (visProps == null && configProps == null)) {
            myVisProperties = visProps;
            myConfigProperties = configProps;
            setPropertyVariables();
        }
    }

    public AbstractScreen(int sWidth, int sHeight, Stage stage) { // for the case of MainMenuScreen
        this(sWidth, sHeight, stage, null, null);
    }

    private void setPropertyVariables() {
        myScreenTitle = myVisProperties.getProp("ScreenTitle");

        startButtonText = myVisProperties.getProp("StartButton");
        backButtonText = myVisProperties.getProp("BackButton");

        option1ButtonText = myVisProperties.getProp("BUTTON1");
        option2ButtonText = myVisProperties.getProp("BUTTON2");
        option3ButtonText = myVisProperties.getProp("BUTTON3");

        csv1 = myConfigProperties.getProp("CSV1");
        csv2 = myConfigProperties.getProp("CSV2");
        csv3 = myConfigProperties.getProp("CSV3");
    }

    public Text getScreenText() {
        Text screenText = new Text(getScreenTitle());
        screenText.setFont(Font.font("verdana", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 25));
        screenText.setFill(Color.BLANCHEDALMOND);

        return screenText;
    }

    public BorderPane setBorderPane(int sWidth, int sHeight, GridPane gPane) {
        BorderPane bPane = new BorderPane();

        ImageView backgroundImg = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("background.png")));
        backgroundImg.setFitWidth(sWidth);
        backgroundImg.setFitHeight(sHeight);

        bPane.getChildren().add(backgroundImg);
        bPane.setCenter(getScreenText());
        bPane.setBottom(gPane);

        bPane.setMargin(gPane, new Insets(0,0, 75, 0));

        return bPane;
    }

    public void makeScreen() {
        //ReadPropertyFile neighType = new ReadPropertyFile("CompleteNeighbor.properties");
        //ReadPropertyFile neighType = new ReadPropertyFile("CardinalNeighbor.properties");
        //ReadPropertyFile neighType = new ReadPropertyFile("VertexNeighbor.properties");
        //ReadPropertyFile edgeType = new ReadPropertyFile("NonToroidal.properties");
        //ReadPropertyFile edgeType = new ReadPropertyFile("Toroidal.properties");
        //ReadPropertyFile edgeType = new ReadPropertyFile("SideToroidal.properties");
        shape= new Square();
        try{
            shape = (Shapes) Class.forName("Gridulars." + myConfigProperties.getProp("Shape")).getConstructor().newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //shape = new Triangle();
        //shape = new Hexagon();
        neighborPolicy = myConfigProperties.getProp("Neighbor");
        //////neighborPolicy="Complete";
        //neighborPolicy="Cardinal";
        //neighborPolicy="Vertex";
        edgePolicy = myConfigProperties.getProp("Edge");
        List<Double> infoList = new ArrayList<>();
        Double info1 = Double.valueOf(myConfigProperties.getProp("info1"));
        Double info2 = Double.valueOf(myConfigProperties.getProp("info2"));
        Double info3 = Double.valueOf(myConfigProperties.getProp("info3"));
        infoList.add(info1);
        infoList.add(info2);
        infoList.add(info3);
        for(Double d : infoList){
            System.out.println("info" + d);
        }




        //////edgePolicy="Non-Toroidal";
        //edgePolicy="Toroidal";
        //edgePolicy="Side-Toroidal";

        Text screenText = getScreenText();
        try {
            MenuButton simMenu = new MenuButton(startButtonText);

            MenuItem m1 = new MenuItem(option1ButtonText);
            MenuItem m2 = new MenuItem(option2ButtonText);
            MenuItem m3 = new MenuItem(option3ButtonText);

            Collection<MenuItem> menuItems = new ArrayList<>();
            menuItems.add(m1);
            menuItems.add(m2);
            menuItems.add(m3);

            simMenu.getItems().addAll(menuItems);
//
//            m1.setOnAction(f -> handleButton(getMyStage(), csv1));
//            m2.setOnAction(f -> handleButton(getMyStage(), csv2));
//            m3.setOnAction(f -> handleButton(getMyStage(), csv3));

            m1.setOnAction(f -> handleButton(getMyStage(), csv1, shape, neighborPolicy, edgePolicy, infoList));
            m2.setOnAction(f -> handleButton(getMyStage(), csv2, shape, neighborPolicy, edgePolicy, infoList));
            m3.setOnAction(f -> handleButton(getMyStage(), csv3, shape, neighborPolicy, edgePolicy, infoList));

            Button backToMainButton = new Button(backButtonText);
            backToMainButton.setOnAction(f -> handleBackToMainButton(getMyStage()));

            GridPane gridPane = new GridPane();
            gridPane.setHgap(20);
            gridPane.setVgap(15);

            gridPane.addRow(0, backToMainButton);
            gridPane.addRow(1, simMenu);

            gridPane.setHalignment(backToMainButton, HPos.CENTER);
            gridPane.setHalignment(simMenu, HPos.CENTER);
            gridPane.setAlignment(Pos.BOTTOM_CENTER);

            BorderPane bPane = setBorderPane(
                    getScreenWidth(),
                    getScreenHeight(),
                    gridPane
            );

            myScene = new Scene(bPane, getScreenWidth(), getScreenHeight());
        } catch(Exception e){
            throw new IllegalArgumentException("One or all of the parameters in your properties file does not fit simulation requirements!");
        }
    }

    public Scene getMyScene() { return myScene; }

    public String getScreenTitle() { return myScreenTitle; }

    Stage getMyStage() { return myStage; }
    int getScreenWidth() { return screenWidth; }
    int getScreenHeight() { return screenHeight; }
}
