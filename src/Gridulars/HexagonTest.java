
package Gridulars;

import Automaton.PercolationAutomaton;
import Configuration.SimConfig;
import Tests.ShapeTestHelper;
import javafx.scene.shape.Polygon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HexagonTest {
    Grid testGrid;
    PercolationAutomaton Perc = new PercolationAutomaton();
    Shapes hex;
    ShapeTestHelper helper;
    Cell n1;
    Cell n2;
    Cell n3;
    Cell n4;
    Cell n5;
    Cell n6;
    Cell n7;
    Cell n8;
    Cell n9;

    @BeforeEach
    void setUp(){
        hex = new Hexagon();
        helper = new ShapeTestHelper();


        SimConfig config = new SimConfig(new File("RPSTest.csv"));

        testGrid = new Grid(config.getGridArr(), Perc, 400, 400, new Hexagon(), "Complete", "Non-Toroidal");
        n1 = testGrid.getCellAt(0,0);
        n2 = testGrid.getCellAt(0,1);
        n3 = testGrid.getCellAt(0,2);
        n4 = testGrid.getCellAt(1,0);
        n5 = testGrid.getCellAt(1,1);
        n6 = testGrid.getCellAt(1,2);
        n7 = testGrid.getCellAt(2,0);
        n8 = testGrid.getCellAt(2,1);
        n9 = testGrid.getCellAt(2,2);
    }

    @Test
    void getVertices() {
        Polygon rect = hex.getVertices(0,0,100,100,10,10);
        Double[] realpoints = new Double[]{4.75,0.0,9.5,2.0, 9.5,8.0, 4.75,10.0,0.0,8.0,0.0,2.0};

        assertEquals(helper.verticesMatch(rect, realpoints), true) ;
    }

    @Test
    void getVerticesIndent() {
        Polygon rect = hex.getVertices(1,3,100,100,10,10);
        Double[] realpoints = new Double[]{38.0,8.0,42.75,10.0, 42.75,16.0, 38.0,18.0,33.25,16.0,33.25,10.0};

        assertEquals(helper.verticesMatch(rect, realpoints), true) ;
    }

    @Test
    void makeCompleteNeighborListNonToroidalEven() {
        CellNeighborManager CNM = new RegularCNM();
        List<Cell> realNeighs = List.of(n1, n3, n5, n4);
        boolean truthVal = helper.neighborsMatch(CNM, hex, testGrid, "Complete", 0, 1, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeCompleteNeighborListNonToroidalOdd() {
        CellNeighborManager CNM = new RegularCNM();
        List<Cell> realNeighs = List.of(n4, n6, n2, n8, n3, n9);
        boolean truthVal = helper.neighborsMatch(CNM, hex, testGrid, "Complete", 1, 1, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeCompleteNeighborListToroidalEven() {
        CellNeighborManager CNM = new ToroidalCNM();
        List<Cell> realNeighs = List.of(n3, n2, n7, n4, n9, n6);
        boolean truthVal = helper.neighborsMatch(CNM, hex, testGrid, "Complete", 0, 0, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeCompleteNeighborListToroidalOdd() {
        CellNeighborManager CNM = new ToroidalCNM();
        List<Cell> realNeighs = List.of(n6, n5, n1, n7, n2, n8);
        boolean truthVal = helper.neighborsMatch(CNM, hex, testGrid, "Complete", 1, 0, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeCompleteNeighborListSideToroidalEven() {
        CellNeighborManager CNM = new SideToroidalCNM();
        List<Cell> realNeighs = List.of(n2, n1, n6, n5);
        boolean truthVal = helper.neighborsMatch(CNM, hex, testGrid, "Complete", 0, 2, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeCompleteNeighborListSideToroidalOdd() {
        CellNeighborManager CNM = new SideToroidalCNM();
        List<Cell> realNeighs = List.of(n5, n4, n3, n9, n1, n7);
        boolean truthVal = helper.neighborsMatch(CNM, hex, testGrid, "Complete", 1, 2, realNeighs);
        assertEquals(truthVal, true);
    }
}
