package Automaton;

import javafx.scene.paint.Color;

import java.util.List;

public class PercolationViewClass extends AbstractAutomatonViewClass {

    public PercolationViewClass(){
        super();
    }

    /***
     *
     * @return the list of colors associated with the percolation simulation
     */
    @Override
    protected List<Color> getColorList(){
        return List.of(Color.LIGHTGRAY,
                Color.WHITE,
                Color.BLUE);
    }
}
