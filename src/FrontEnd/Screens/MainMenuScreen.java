package FrontEnd.Screens;
import Automaton.AbstractAutomaton;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.lang.reflect.Constructor;

public class MainMenuScreen extends AbstractScreen {

    private static final String SCREEN_TITLE = "Call Automaton Simulator";
    private Scene myIntro;

    public MainMenuScreen(int sWidth, int sHeight, Stage stage) {
        super(sWidth, sHeight, stage);
    }

    /**
     * Creates and sets all the features of the intro screen, including the title text and the buttons.
     */
    @Override
    public void makeScreen() {
        Text simText = getScreenText();

        Text quitText = new Text("* Press Q within Simulations to Return to this Menu \n* Press SPACE to Pause/Resume Simulation\n* Press the Right Arrow to Step Through");
        quitText.setFont(Font.font("verdana", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 8));
        quitText.setFill(Color.rgb(236, 239, 241));

        Button directToPercolationButton = new Button("PERCOLATION");
        directToPercolationButton.setOnAction(f -> handlePercolationRedirect(getMyStage()));

        Button directToConwayButton = new Button("CONWAY'S GAME OF LIFE");
        directToConwayButton.setOnAction(f -> handleConwayRedirect(getMyStage()));

        Button directToFireButton = new Button("FIRE");
        directToFireButton.setOnAction(f -> handleFireRedirect(getMyStage()));

        Button directToRPSButton = new Button("ROCK, PAPER, SCISSORS");
        directToRPSButton.setOnAction(f -> handleRPSRedirect(getMyStage()));

        Button directToSegregationButton = new Button("SEGREGATION");
        directToSegregationButton.setOnAction(f -> handleSegregationRedirect(getMyStage()));

        Button directToWatorButton = new Button("WA-TOR");
        directToWatorButton.setOnAction(f -> handleWatorRedirect(getMyStage()));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(15);

        gridPane.add(directToPercolationButton, 0,0);
        gridPane.add(directToConwayButton, 1,0);
        gridPane.add(directToFireButton, 2,0);

        gridPane.add(directToRPSButton, 2, 2);
        gridPane.add(directToSegregationButton, 1, 2);
        gridPane.add(directToWatorButton,0, 2);

        BorderPane bPane = setBorderPane(
                getScreenWidth(),
                getScreenHeight(),
                gridPane
        );

        bPane.setTop(quitText);

        gridPane.setAlignment(Pos.BOTTOM_CENTER);

        bPane.setMargin(quitText, new Insets(3,0, 0, 7));

        myIntro = new Scene(bPane, getScreenWidth(), getScreenHeight());
    }

    //* * * * * * LUIS:  we hould definitely make all these into one handle redirect and have them take the screen as a paramter that weve intialized up top!!!
    private void handleConwayRedirect(Stage stage) {
        AbstractScreen conwayMenu = new ConwayScreen(
                getScreenWidth(),
                getScreenHeight(),
                stage
        );
        completeStage(stage, conwayMenu);
    }

    private void handlePercolationRedirect(Stage stage) {
        AbstractScreen percMenu = new PercolationScreen(
                getScreenWidth(),
                getScreenHeight(),
                stage
        );
        completeStage(stage, percMenu);
    }

    private void handleFireRedirect(Stage stage) {
        AbstractScreen fireMenu = new FireScreen(
                getScreenWidth(),
                getScreenHeight(),
                stage
        );
        completeStage(stage, fireMenu);
    }

    private void handleRPSRedirect(Stage stage) {
        AbstractScreen rpsMenu = new RPSScreen(
                getScreenWidth(),
                getScreenHeight(),
                stage
        );
        completeStage(stage, rpsMenu);
    }

    private void handleSegregationRedirect(Stage stage) {
        AbstractScreen segregationMenu = new SegregationScreen(
                getScreenWidth(),
                getScreenHeight(),
                stage
        );
        completeStage(stage, segregationMenu);
    }

    private void handleWatorRedirect(Stage stage) {
        AbstractScreen watorMenu = new WatorScreen(
                getScreenWidth(),
                getScreenHeight(),
                stage
        );
        completeStage(stage, watorMenu);
    }

    private void completeStage(Stage stage, AbstractScreen menu) {
        menu.makeScreen();
        stage.close();
        stage.setScene((menu.getMyScene()));
        stage.show();
    }

    public String getScreenTitle() { return SCREEN_TITLE; }

    public Scene getMyScene() { return myIntro; }
}
