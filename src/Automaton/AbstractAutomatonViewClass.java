package Automaton;

import javafx.scene.paint.Color;
import java.util.List;
/***
 * Authors: Dylan
 * Purpose: this is the abstract class for any type of simulation's view specific aspect, which is the colors of the cells
 * You would use it when implementing  a new type of simulation
 * Assumptions: None
 * Dependencies: None
 * How to use it: extend this class and implement color list portion
 */
public abstract class AbstractAutomatonViewClass {

    private List<Color> colorList;

    public AbstractAutomatonViewClass(){
        colorList = getColorList();
    }

    /***
     * gets the color of the specific state number
     * @param n the state number
     * @return the color
     */
    public Color getColor(int n){
        if(n<colorList.size()){
            return colorList.get(n);
        }
        else{
            return Color.BLACK;
        }
    }

    /***
     * gets the list of colors for that automaton, which is indexed by state number
     * @return the List of colors
     */
    abstract protected List<Color> getColorList();
}
