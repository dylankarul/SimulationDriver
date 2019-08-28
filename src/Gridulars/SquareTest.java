
package Gridulars;

import Automaton.PercolationAutomaton;
import Configuration.SimConfig;
import Tests.ShapeTestHelper;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.scene.shape.Polygon;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {

    Shapes squ;
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
    Grid myGrid;
    PercolationAutomaton Perc = new PercolationAutomaton();

    @BeforeEach
    void setUp(){
        squ = new Square();
        helper = new ShapeTestHelper();



        SimConfig config = new SimConfig(new File("RPSTest.csv"));

        myGrid = new Grid(config.getGridArr(), Perc, 400, 400, new Square(), "Complete", "Non-Toroidal");
        n1 = myGrid.getCellAt(0,0);
        n2 = myGrid.getCellAt(0,1);
        n3 = myGrid.getCellAt(0,2);
        n4 = myGrid.getCellAt(1,0);
        n5 = myGrid.getCellAt(1,1);
        n6 = myGrid.getCellAt(1,2);
        n7 = myGrid.getCellAt(2,0);
        n8 = myGrid.getCellAt(2,1);
        n9 = myGrid.getCellAt(2,2);
    }

    @Test
    void TestgetVertices() {
        Polygon rect = squ.getVertices(0,0,100,100,10,10);
        Double[] realpoints = new Double[]{0.0,0.0,10.0,0.0, 10.0,10.0, 0.0,10.0};
        ObservableList<Double> theList = rect.getPoints();
        assertEquals(helper.verticesMatch(rect, realpoints), true) ;
    }

    @Test
    void TestgetVerticesTwo() {
        Polygon rect = squ.getVertices(1,1,100,100,10,10);
        Double[] realpoints = new Double[]{10.0,10.0,20.0,10.0, 20.0,20.0, 10.0,20.0};
        assertEquals(helper.verticesMatch(rect, realpoints), true) ;
    }

    @Test
    void makeCompleteNeighborListNonToroidal() {
        //Cell[][] myGrid = helper.createGrid();
        CellNeighborManager CNM = new RegularCNM();
        List<Cell> realNeighs = List.of(n2, n4, n5);
        boolean truthVal = helper.neighborsMatch(CNM, squ, myGrid, "Complete", 0, 0, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeCompleteNeighborListToroidal() {
        //Cell[][] myGrid = helper.createGrid();
        CellNeighborManager CNM = new ToroidalCNM();
        List<Cell> realNeighs = List.of(n7, n8, n9, n1, n3, n4, n5, n6);
        boolean truthVal = helper.neighborsMatch(CNM, squ, myGrid, "Complete", 0, 1, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeCompleteNeighborListSideToroidal() {
        CellNeighborManager CNM = new SideToroidalCNM();
        List<Cell> realNeighs = List.of(n6, n4, n5, n9, n8);
        boolean truthVal = helper.neighborsMatch(CNM, squ, myGrid, "Complete", 2, 0, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeCardinalNeighborListNonToroidal() {
        CellNeighborManager CNM = new RegularCNM();
        List<Cell> realNeighs = List.of(n2, n4);
        boolean truthVal = helper.neighborsMatch(CNM, squ, myGrid, "Cardinal", 0, 0, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeCardinalNeighborListToroidal() {
        CellNeighborManager CNM = new ToroidalCNM();
        List<Cell> realNeighs = List.of(n5, n7, n9, n2);
        boolean truthVal = helper.neighborsMatch(CNM, squ, myGrid, "Cardinal", 2, 1, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeCardinalNeighborSideToroidal() {
        CellNeighborManager CNM = new SideToroidalCNM();
        List<Cell> realNeighs = List.of(n6, n8, n7);
        boolean truthVal = helper.neighborsMatch(CNM, squ, myGrid, "Cardinal", 2, 2, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeVertexNeighborListNonToroidal() {
        CellNeighborManager CNM = new RegularCNM();
        List<Cell> realNeighs = List.of(n6, n4);
        boolean truthVal = helper.neighborsMatch(CNM, squ, myGrid, "Vertex", 0, 1, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeVertexNeighborListToroidal() {
        CellNeighborManager CNM = new ToroidalCNM();
        List<Cell> realNeighs = List.of(n3, n1, n6, n4);
        boolean truthVal = helper.neighborsMatch(CNM, squ, myGrid, "Vertex", 2, 1, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeVertexNeighborSideToroidal() {
        CellNeighborManager CNM = new SideToroidalCNM();
        List<Cell> realNeighs = List.of(n4, n5);
        boolean truthVal = helper.neighborsMatch(CNM, squ, myGrid, "Vertex", 0, 2, realNeighs);
        assertEquals(truthVal, true);
    }
}
