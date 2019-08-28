package FrontEnd.Screens;

import Configuration.ReadPropertyFile;
import javafx.stage.Stage;

public class FireScreen extends AbstractScreen {

    public FireScreen(int sWidth, int sHeight, Stage stage) {
        super(sWidth, sHeight, stage,
                new ReadPropertyFile("FireVis.properties"),
                new ReadPropertyFile("FireConfig.properties")
        );
    }
}
