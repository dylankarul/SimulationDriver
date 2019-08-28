package Tests;
import Gridulars.Grid;
import Gridulars.Shapes;
import Gridulars.CellNeighborManager;
import Gridulars.Cell;
import javafx.collections.ObservableList;
import javafx.scene.shape.Polygon;

import java.util.List;


public class ShapeTestHelper {

    public boolean verticesMatch(Polygon shape, Double[] realpoints){
        ObservableList<Double> points = shape.getPoints();
        for(int index = 0; index<points.size(); index++){
            Double point = points.get(index);
            if(!realpoints[index].equals(point)){
                return false;
            }
        }
        return true;
    }

    public boolean neighborsMatch(CellNeighborManager CNM, Shapes shape, Grid myGrid, String neighPolicy, int x, int y, List<Cell> realNeighs){
        shape.makeNeighborList(CNM, myGrid, x, y, neighPolicy);
        List<Cell> neighbors = CNM.getNeighbors();
        for(int index =0; index<neighbors.size(); index++){
            if(!neighbors.get(index).equals(realNeighs.get(index))){
                return false;
            }
        }
        return true;
    }

}
