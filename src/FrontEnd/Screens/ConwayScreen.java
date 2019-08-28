package FrontEnd.Screens;
import javafx.scene.Scene;

import Configuration.ReadPropertyFile;
import javafx.stage.Stage;

public class ConwayScreen extends AbstractScreen {

    private Scene myScene;

    public ConwayScreen(int sWidth, int sHeight, Stage stage) {
        super(sWidth, sHeight, stage,
                new ReadPropertyFile("ConwayVis.properties"),
                new ReadPropertyFile("ConwayConfig.properties")
        );

        //myScene = new Scene(bPane, getScreenWidth(), getScreenHeight());

    }
}
