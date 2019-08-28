package FrontEnd.Screens;

import Configuration.ReadPropertyFile;
import javafx.stage.Stage;

public class RPSScreen extends AbstractScreen {

    public RPSScreen(int sWidth, int sHeight, Stage stage) {
        super(sWidth, sHeight, stage,
                new ReadPropertyFile("RPSVis.properties"),
                new ReadPropertyFile("RPSConfig.properties")
        );
    }
}

