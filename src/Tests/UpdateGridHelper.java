package Tests;

import Automaton.AbstractAutomaton;
import Configuration.SimConfig;
import Gridulars.Cell;
import Gridulars.Grid;
import Gridulars.Square;

import java.io.File;

public class UpdateGridHelper {

    private static final int SCREENSIZE = 400;

    public void updateWholeGrid(Grid testGrid){
        for(int rowIndex=0; rowIndex<testGrid.getGridHeight(); rowIndex++){
            for(int iter=0; iter<testGrid.getGridWidth(); iter++){
                Cell c = testGrid.getCellAt(rowIndex, iter);
                c.updateCell();
            }
        }
    }

    public void updateEverything(Grid testGrid, AbstractAutomaton auto){
        for(int rowIndex=0; rowIndex<testGrid.getGridHeight(); rowIndex++){
            for(int iter=0; iter<testGrid.getGridWidth(); iter++){
                Cell c = testGrid.getCellAt(rowIndex, iter);
                auto.update(c, testGrid);
            }
        }
        updateWholeGrid(testGrid);
    }

    public Grid setCSVToUse(String name, AbstractAutomaton auto, String neighpolicy, String edgepolicy){
        SimConfig config = new SimConfig(new File(name));

        return new Grid(config.getGridArr(), auto, SCREENSIZE, SCREENSIZE, new Square(), neighpolicy, edgepolicy);

    }
}
