package Automaton;

import javafx.scene.paint.Color;

import java.util.List;

public class RPSViewClass extends AbstractAutomatonViewClass {

    public RPSViewClass(){
        super();
    }

    /***
     * gets the list of colors associated with each state for RPS
     * @return
     */
    @Override
    protected List<Color> getColorList(){
        return List.of(Color.RED,
                Color.GREEN,
                Color.BLUE);
    }
}
