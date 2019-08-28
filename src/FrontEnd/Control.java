package FrontEnd;

import javafx.scene.control.ToolBar;
import javafx.scene.Group;

public class Control {

    private ToolBar toolBar;
    public Control(){
        toolBar = new ToolBar();

    }
    public void addBar(Group root) {
        root.getChildren().add(toolBar);
    }
}
