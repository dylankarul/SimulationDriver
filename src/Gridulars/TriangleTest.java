
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

class TriangleTest {

    Shapes tri;
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
        tri = new Triangle();
        helper = new ShapeTestHelper();



        SimConfig config = new SimConfig(new File("RPSTest.csv"));

        myGrid = new Grid(config.getGridArr(), Perc, 400, 400, new Triangle(), "Complete", "Non-Toroidal");
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
    void getVerticesNormal() {
        Polygon rect = tri.getVertices(0,0,100,100,10,10);
        Double[] realpoints = new Double[]{0.0,10.0,18.0,10.0, 9.0,0.0};

        assertEquals(helper.verticesMatch(rect, realpoints), true) ;
    }

    @Test
    void getVerticesInverted() {
        Polygon rect = tri.getVertices(1, 3,100,100,10,10);
        Double[] realpoints = new Double[]{27.0,20.0,45.0,20.0, 36.0,10.0};
        assertEquals(helper.verticesMatch(rect, realpoints), true) ;
    }

    @Test
    void makeCompleteNeighborListNonToroidalNormal() {
        CellNeighborManager CNM = new RegularCNM();
        List<Cell> realNeighs = List.of(n2, n3, n5, n4, n6);
        boolean truthVal = helper.neighborsMatch(CNM, tri, myGrid, "Complete", 0, 0, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeCompleteNeighborListNonToroidalInverted() {
        CellNeighborManager CNM = new RegularCNM();
        List<Cell> realNeighs = List.of(n1, n3, n6, n5, n4);
        boolean truthVal = helper.neighborsMatch(CNM, tri, myGrid, "Complete", 0, 1, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeCompleteNeighborListToroidalNormal() {
        CellNeighborManager CNM = new ToroidalCNM();
        List<Cell> realNeighs = List.of(n2, n1, n9, n8, n7, n4, n6, n5);
        boolean truthVal = helper.neighborsMatch(CNM, tri, myGrid, "Complete", 0, 2, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeCompleteNeighborListToroidalInverted() {
        CellNeighborManager CNM = new ToroidalCNM();
        List<Cell> realNeighs = List.of(n6, n5, n1, n3, n2, n8, n7, n9);
        boolean truthVal = helper.neighborsMatch(CNM, tri, myGrid, "Complete", 1, 0, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeCompleteNeighborListSideToroidalNormal() {
        CellNeighborManager CNM = new SideToroidalCNM();
        List<Cell> realNeighs = List.of(n3, n2, n5, n4, n6);
        boolean truthVal = helper.neighborsMatch(CNM, tri, myGrid, "Complete", 0, 0, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeCompleteNeighborListSideToroidalInverted() {
        CellNeighborManager CNM = new SideToroidalCNM();
        List<Cell> realNeighs = List.of(n7, n9, n5, n4, n6);
        boolean truthVal = helper.neighborsMatch(CNM, tri, myGrid, "Complete", 2, 1, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeCardinalNeighborListNonToroidalNormal() {
        CellNeighborManager CNM = new RegularCNM();
        List<Cell> realNeighs = List.of(n2, n6);
        boolean truthVal = helper.neighborsMatch(CNM, tri, myGrid, "Cardinal", 0, 2, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeCardinalNeighborListNonToroidalInverted() {
        CellNeighborManager CNM = new RegularCNM();
        List<Cell> realNeighs = List.of(n1, n3);
        boolean truthVal = helper.neighborsMatch(CNM, tri, myGrid, "Cardinal", 0, 1, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeCardinalNeighborListToroidalNormal() {
        CellNeighborManager CNM = new ToroidalCNM();
        List<Cell> realNeighs = List.of(n3, n2, n4);
        boolean truthVal = helper.neighborsMatch(CNM, tri, myGrid, "Cardinal", 0, 0, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeCardinalNeighborListToroidalInverted() {
        CellNeighborManager CNM = new ToroidalCNM();
        List<Cell> realNeighs = List.of(n5, n4, n3);
        boolean truthVal = helper.neighborsMatch(CNM, tri, myGrid, "Cardinal", 1, 2, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeCardinalNeighborListSideToroidalNormal() {
        CellNeighborManager CNM = new SideToroidalCNM();
        List<Cell> realNeighs = List.of(n9, n8);
        boolean truthVal = helper.neighborsMatch(CNM, tri, myGrid, "Cardinal", 2, 0, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeCardinalNeighborListSideToroidalInverted() {
        CellNeighborManager CNM = new SideToroidalCNM();
        List<Cell> realNeighs = List.of(n1, n3);
        boolean truthVal = helper.neighborsMatch(CNM, tri, myGrid, "Cardinal", 0, 1, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeVertexNeighborListNonToroidalNormal() {
        CellNeighborManager CNM = new RegularCNM();
        List<Cell> realNeighs = List.of(n1, n3, n9, n7, n2);
        boolean truthVal = helper.neighborsMatch(CNM, tri, myGrid, "Vertex", 1, 1, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeVertexNeighborListNonToroidalInverted() {
        CellNeighborManager CNM = new RegularCNM();
        List<Cell> realNeighs = List.of(n2, n4, n8, n9, n1);
        boolean truthVal = helper.neighborsMatch(CNM, tri, myGrid, "Vertex", 1, 2, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeVertexNeighborListToroidalNormal() {
        CellNeighborManager CNM = new ToroidalCNM();
        List<Cell> realNeighs = List.of(n4, n1, n3, n9, n6, n7, n2);
        boolean truthVal = helper.neighborsMatch(CNM, tri, myGrid, "Vertex", 1, 1, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeVertexNeighborListToroidalInverted() {
        CellNeighborManager CNM = new ToroidalCNM();
        List<Cell> realNeighs = List.of(n7, n4, n6, n3, n9, n1, n2);
        boolean truthVal = helper.neighborsMatch(CNM, tri, myGrid, "Vertex", 2, 1, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeVertexNeighborListSideToroidalNormal() {
        CellNeighborManager CNM = new SideToroidalCNM();
        List<Cell> realNeighs = List.of(n9, n6, n5, n8, n4);
        boolean truthVal = helper.neighborsMatch(CNM, tri, myGrid, "Vertex", 2, 0, realNeighs);
        assertEquals(truthVal, true);
    }

    @Test
    void makeVertexNeighborListSideToroidalInverted() {
        CellNeighborManager CNM = new SideToroidalCNM();
        List<Cell> realNeighs = List.of(n1, n6, n3, n4, n5);
        boolean truthVal = helper.neighborsMatch(CNM, tri, myGrid, "Vertex", 0, 1, realNeighs);
        assertEquals(truthVal, true);
    }
}
