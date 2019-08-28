package Automaton;

import Tests.UpdateGridHelper;
import Gridulars.Grid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConwayAutomatonTest {


    int DEAD = 0;
    int ALIVE = 1;
    Grid testGrid;
    ConwayAutomaton Con = new ConwayAutomaton();
    UpdateGridHelper updater = new UpdateGridHelper();


    @BeforeEach
    void setUp() {
        //SimConfig config = new SimConfig(new File("conwayTest.csv"));

        //testGrid = new Grid(config.getGridArr(), Con, 400, 400, new Square(), "Complete", "Toroidal");
        testGrid = updater.setCSVToUse("conwayTest.csv", Con, "Complete", "Toroidal");
    }



    @Test
    void updateAllLonely() {
        testGrid = updater.setCSVToUse("conwayTest2.csv", Con, "Complete", "Toroidal");

        updater.updateEverything(testGrid, Con);
        assertEquals(DEAD, testGrid.getCellAt(0,0).getMyCellState());
    }

    @Test
    void updateAllOvercrowded() {
        testGrid = updater.setCSVToUse("conwayTest3.csv", Con, "Complete", "Toroidal");

        updater.updateEverything(testGrid, Con);
        assertEquals(DEAD, testGrid.getCellAt(0,0).getMyCellState());
    }

    @Test
    void updateStable() {
        updater.updateEverything(testGrid, Con);
        assertEquals(DEAD, testGrid.getCellAt(1,1).getMyCellState());
    }

    @Test
    void updateReproduce() {
        testGrid.getCellAt(1,2).setNextCellState(1);
        testGrid.getCellAt(1,2).updateCell();
        updater.updateEverything(testGrid, Con);
        assertEquals(ALIVE, testGrid.getCellAt(1,1).getMyCellState());
    }

    @Test
    void testToad(){
        testGrid = updater.setCSVToUse("conwayToad.csv", Con, "Complete", "Toroidal");
        updater.updateEverything(testGrid, Con);
    }


}