
package Gridulars;

import Automaton.PercolationAutomaton;
import Configuration.SimConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    SimConfig s1;
    Integer[][] gArr;
    Grid g1;
    Cell[][] cellGrid;

    @BeforeEach
    void setUp() {
        //SimConfig s1 = new SimConfig(new File("resources/percTest.csv"));
        //s1.getGridArr();
        s1 = new SimConfig(new File("resources/percTest.csv"));
        gArr = s1.getGridArr();
        g1 = new Grid(gArr, new PercolationAutomaton(), 300, 300, new Square(), "Complete", "Toroidal");
        //cellGrid = g1.getMyCellGrid();


    }



    @Test
    void getGridWidth() {
        var cellGridCols = g1.getGridWidth();
        assertEquals(3, cellGridCols);
    }

    @Test
    void getGridHeight() {
        var cellGridCols = g1.getGridHeight();
        assertEquals(4, cellGridCols);
    }

    @Test
    void getCellAt(){
        Cell c = g1.getCellAt(0,0);
        assertEquals(1, c.getMyCellState());
    }
}
