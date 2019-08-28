
package Automaton;

import Gridulars.Grid;
import Tests.UpdateGridHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercolationAutomatonTest {

    int BLOCKED = 0;
    int OPEN = 1;
    int FILLED = 2;

    Grid testGrid;
    UpdateGridHelper updater = new UpdateGridHelper();

    PercolationAutomaton Perc = new PercolationAutomaton();

    @BeforeEach
    void setUp() {
        testGrid = updater.setCSVToUse("percTest2.csv", Perc, "Complete", "Non-Toroidal");

    }

    @Test
    void updatePercolatesDiagonal() {
        updater.updateEverything(testGrid, Perc);
        //Perc.update(myGrid);
        //assertEquals(FILLED, n5.getMyCellState());
        assertEquals(FILLED, testGrid.getCellAt(1, 1).getMyCellState());
    }


    @Test
    void updateDoesntPercolateFill() {
        updater.updateEverything(testGrid, Perc);
        assertEquals(BLOCKED, testGrid.getCellAt(0, 1).getMyCellState());
    }


    @Test
    void updateFilledStaysFilled() {
        updater.updateEverything(testGrid, Perc);
        assertEquals(FILLED, testGrid.getCellAt(0, 0).getMyCellState());
    }


    @Test
    void updatePercolatesToDirectNeighbor(){
        updater.updateEverything(testGrid, Perc);
        assertEquals(OPEN, testGrid.getCellAt(1, 2).getMyCellState());
        updater.updateEverything(testGrid, Perc);
        assertEquals(FILLED, testGrid.getCellAt(1, 2).getMyCellState());
    }

    @Test
    void testDoesntPercolate(){
        testGrid = updater.setCSVToUse("percNoPercolates.csv", Perc, "Complete", "Non-Toroidal");
        updater.updateEverything(testGrid, Perc);
        updater.updateEverything(testGrid, Perc);
        updater.updateEverything(testGrid, Perc);
        updater.updateEverything(testGrid, Perc);
        updater.updateEverything(testGrid, Perc);
        updater.updateEverything(testGrid, Perc);
        updater.updateEverything(testGrid, Perc);
        updater.updateEverything(testGrid, Perc);
        assertEquals(OPEN, testGrid.getCellAt(6, 3).getMyCellState());
    }

    @Test
    void testCornerPercolates(){
        testGrid = updater.setCSVToUse("percTest3.csv", Perc, "Complete", "Non-Toroidal");
        updater.updateEverything(testGrid, Perc);
        updater.updateEverything(testGrid, Perc);
        assertEquals(FILLED, testGrid.getCellAt(2, 0).getMyCellState());
    }
}
