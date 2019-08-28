
package Automaton;

import Gridulars.Grid;
import Tests.UpdateGridHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class FireAutomatonTest {

    int EMPTY = 0;
    int TREE = 1;
    int BURNING = 2;
    Grid testGrid;
    UpdateGridHelper updater = new UpdateGridHelper();
    List<Double> infoList = List.of(.25);
    FireAutomaton fire = new FireAutomaton(infoList);


    @BeforeEach
    void setUp() {
        //SimConfig config = new SimConfig(new File("fireTest.csv"));

        //testGrid = new Grid(config.getGridArr(), fire, 400, 400, new Square(), "Cardinal", "Non-Toroidal");

        testGrid = updater.setCSVToUse("fireTest.csv", fire, "Cardinal", "Non-Toroidal");


    }


    @Test
    void updateFiretoEmpty() {
        updater.updateEverything(testGrid, fire);
        //fire.update(myGrid);
        //assertEquals(0, n1.getMyCellState());
        assertEquals(0, testGrid.getCellAt(0, 0).getMyCellState());
    }


    @Test
    void updateEmpty(){
        updater.updateEverything(testGrid, fire);
        assertEquals(0, testGrid.getCellAt(0, 2).getMyCellState());
    }

    @Test
    void updateCatchFire(){
        List<Double> infoList = List.of(1.0);
        FireAutomaton fire100 = new FireAutomaton(infoList);
        updater.updateEverything(testGrid, fire100);
        assertEquals(2, testGrid.getCellAt(0, 1).getMyCellState());

    }

    @Test
    void emptyWallTest(){
        testGrid = updater.setCSVToUse("fireTest2.csv", fire, "Cardinal", "Non-Toroidal");
        updater.updateEverything(testGrid, fire);
        assertEquals(0, testGrid.getCellAt(0, 1).getMyCellState());
    }

    @Test
    void middleCatchesFireTest(){
        List<Double> infoList = List.of(1.0);
        FireAutomaton fire100 = new FireAutomaton(infoList);
        testGrid = updater.setCSVToUse("fireTest3.csv", fire100, "Cardinal", "Non-Toroidal");
        updater.updateEverything(testGrid, fire100);
        assertEquals(2, testGrid.getCellAt(2, 2).getMyCellState());
    }




}
