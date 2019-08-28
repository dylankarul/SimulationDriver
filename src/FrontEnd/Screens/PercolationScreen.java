package FrontEnd.Screens;

import Configuration.ReadPropertyFile;
import javafx.stage.Stage;

public class PercolationScreen extends AbstractScreen {

    public PercolationScreen(int sWidth, int sHeight, Stage stage) {
        super(sWidth, sHeight, stage,
              new ReadPropertyFile("PercolationVis.properties"),
              new ReadPropertyFile("PercolationConfig.properties")
        );
    }
}
