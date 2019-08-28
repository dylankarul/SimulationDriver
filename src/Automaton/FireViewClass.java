package Automaton;

import javafx.scene.paint.Color;

import java.util.List;

public class FireViewClass extends AbstractAutomatonViewClass {

    public FireViewClass(){
        super();
    }

    /***
     * returns the color list for the Fire class, which is indexed by statenumber
     * @returnn see above
     */
    @Override
    protected List<Color> getColorList(){
        return List.of(Color.WHITE,
                Color.GREEN,
                Color.ORANGE);
    }

}
