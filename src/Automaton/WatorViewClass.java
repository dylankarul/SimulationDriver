package Automaton;

import javafx.scene.paint.Color;

import java.util.List;

public class WatorViewClass extends AbstractAutomatonViewClass {

    public WatorViewClass(){
        super();
    }

    /***
     * returns the list that has the colors associated with each state for Wator
     * @return
     */
    @Override
    protected List<Color> getColorList(){
        return List.of(Color.GREEN,
                Color.DARKGRAY,
                Color.BLUE);
    }

}
