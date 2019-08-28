package Gridulars;

import Automaton.AbstractAutomaton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
/***
 * Authors: Dylan
 * Purpose: a class that allows for the front end to set each cell to the color associated with its state and also sets the cell
 * to be the specific shape in the front end such as hexagon or square
 * Assumptions: None
 * Dependencies: Abstract automaton
 * How to use it: extend this class and implement any specific neighbor rules you want,
 * such as cardinal, complete, etc when you are adding to the neighborsList.
 */
public class CellVisualization {

    private Shape myCellShape;
    //private Color myCellColor;


    public Shape getShape(){
        return myCellShape;
    }

    /***
     * sets the cell to be shape p in the visualization
     * @param p the given type of polygon
     */
    public void setShape(Polygon p){

        myCellShape = p;

    }

    /***
     * retrieves the color associated with the cell's next state and sets it so the front end will display this color
     * @param mySimType the specific simulation being run
     * @param myNextCellState the cell state that the cell is being updated to
     */
    public void setCellColor(AbstractAutomaton mySimType, int myNextCellState){
        Color myCellColor = mySimType.getAutomatonView().getColor(myNextCellState);
        myCellShape.setFill(myCellColor);
    }
}
