
package Automaton;

import Gridulars.Grid;
import Tests.UpdateGridHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SegregationAutomatonTest {

    int DUKE = 0;
    int UNC = 1;
    int EMPTY = 2;
    double threshold = .5;
    Grid testGrid;
    UpdateGridHelper updater = new UpdateGridHelper();

    List<Double> infoList = List.of(threshold);
    SegregationAutomaton Seg = new SegregationAutomaton(infoList);


    @BeforeEach
    void setUp() {
        //SimConfig config = new SimConfig(new File("segregationTest.csv"));

        //testGrid = new Grid(config.getGridArr(), Seg, 400, 400, new Square(), "Complete", "Non-Toroidal");
        testGrid = updater.setCSVToUse("segregationTest.csv", Seg, "Complete", "Non-Toroidal");

    }

    @Test
    void testSatisfiedCellDoesntMove() {
        updater.updateEverything(testGrid, Seg);
        assertEquals(DUKE, testGrid.getCellAt(0, 0).getMyCellState());
    }



    @Test
    void testEmptyCellIsntANeighbor(){
        updater.updateEverything(testGrid, Seg);
        assertEquals(UNC, testGrid.getCellAt(1, 2).getMyCellState());
    }



    @Test
    void testUnsatisfiedCellMoves(){
        updater.updateEverything(testGrid, Seg);
        assertEquals(EMPTY, testGrid.getCellAt(0, 1).getMyCellState());
        assertEquals(UNC, testGrid.getCellAt(2, 2).getMyCellState());
    }


    @Test
    void testUnsatisfiedCellDoesntMoveBecauseNoOpenSpots(){
        testGrid = updater.setCSVToUse("segregationTest2.csv", Seg, "Complete", "Non-Toroidal");

        /*n9.setNextCellState(DUKE);
        n9.updateCell();*/
        updater.updateEverything(testGrid, Seg);
        assertEquals(UNC, testGrid.getCellAt(1,0).getMyCellState());
    }

    @Test
    void testThirdFile(){
        testGrid = updater.setCSVToUse("segregationTest3.csv", Seg, "Complete", "Non-Toroidal");
        updater.updateEverything(testGrid, Seg);
        assertEquals(EMPTY, testGrid.getCellAt(0,0).getMyCellState());

        assertEquals(DUKE, testGrid.getCellAt(0,1).getMyCellState());
    }

}
