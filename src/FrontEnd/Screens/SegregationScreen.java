package FrontEnd.Screens;

import Configuration.ReadPropertyFile;
import javafx.stage.Stage;

public class SegregationScreen extends AbstractScreen {

    public SegregationScreen(int sWidth, int sHeight, Stage stage) {
        super(sWidth, sHeight, stage,
                new ReadPropertyFile("SegregationVis.properties"),
                new ReadPropertyFile("SegregationConfig.properties")
        );
    }
}
