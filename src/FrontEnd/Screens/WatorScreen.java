package FrontEnd.Screens;

import Configuration.ReadPropertyFile;
import javafx.stage.Stage;

public class WatorScreen extends AbstractScreen {

    public WatorScreen(int sWidth, int sHeight, Stage stage) {
        super(sWidth, sHeight, stage,
                new ReadPropertyFile("WatorVis.properties"),
                new ReadPropertyFile("WatorConfig.properties")
        );
    }
}
