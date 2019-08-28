package Automaton;

import javafx.scene.paint.Color;

import java.util.List;

public class SegregationViewClass extends AbstractAutomatonViewClass {

    public SegregationViewClass(){
        super();
    }

    /***
     * gets the color list for Segregation class
     */
    @Override
    protected List<Color> getColorList(){
        return List.of(Color.GREEN,
                Color.RED,
                Color.BLACK);
    }
}
