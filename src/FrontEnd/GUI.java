package FrontEnd;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


public class GUI {

    private Group root;
    private int sceneWidth;
    private int sceneHeight;
    private Rectangle grid[][];

    public GUI(int sceneWidth, int sceneHeight) {
        this.sceneHeight = sceneHeight;
        this.sceneWidth = sceneWidth;
        root = new Group();
    }

    private Scene setupSim (int width, int height, Paint background) {
        root = new Group();
        var scene = new Scene(root, width, height, background);
        //add all the cells
        return scene;
    }

    public void showGrid(Color[][] units) {
        for(int x=0; x<units.length; x++) {
            for (int y = 0; y < units[0].length; y++) {
                grid[x][y].setFill(units[x][y]);
            }
        }
    }
}