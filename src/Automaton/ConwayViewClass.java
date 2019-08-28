package Automaton;

import javafx.scene.paint.Color;
import java.util.List;

public class ConwayViewClass extends AbstractAutomatonViewClass {

    public ConwayViewClass(){
        super();
    }

    /***
     * @return the conway color list which is indexed by state number
     */
    @Override
    protected List<Color> getColorList(){
        return List.of(Color.WHITE,
                Color.BLACK);
    }

}
