
package Gridulars;

import Automaton.PercolationAutomaton;
import Automaton.RPSAutomaton;
import Configuration.SimConfig;
import Tests.UpdateGridHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegularCNMTest {

    double threshold = 2.0;
    Grid testGrid;
    UpdateGridHelper updater = new UpdateGridHelper();
    List<Double> infoList = List.of(threshold);
    RPSAutomaton RPS = new RPSAutomaton(infoList);
    Cell n1 = new Cell(new PercolationAutomaton(), 1);
    Cell n2 = new Cell(new PercolationAutomaton(), 1);
    Cell n3 = new Cell(new PercolationAutomaton(), 0);
    Cell n4 = new Cell(new PercolationAutomaton(), 2);
    Cell n5 = new Cell(new PercolationAutomaton(), 0);
    Cell n6 = new Cell(new PercolationAutomaton(), 0);
    Cell n7 = new Cell(new PercolationAutomaton(), 1);
    Cell n8 = new Cell(new PercolationAutomaton(), 1);
    Cell n9 = new Cell(new PercolationAutomaton(), 0);
    Cell n10 = new Cell(new PercolationAutomaton(), 1);
    Cell n11 = new Cell(new PercolationAutomaton(), 1);
    List<Cell> neighs = List.of(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11);

    @BeforeEach
    void setUp() {


        SimConfig config = new SimConfig(new File("RPSTest.csv"));

        testGrid = new Grid(config.getGridArr(), RPS, 400, 400, new Square(), "Complete", "Non-Toroidal");
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
    void addToNeighborListOutOfBounds() {
        n1.setCNM(new RegularCNM());
        n1.getCNM().addToNeighborList(testGrid, -1, 1);
        List<Cell> myNeighbors = n1.getCNM().getNeighbors();
        int realNumNeighbors = 0;
        assertEquals(realNumNeighbors, n1.getCNM().getNeighbors().size());
        //assertEquals(myNeighbors.size(), 3);
    }


    @Test
    void addToNeighborListInBounds() {
        n5.setCNM(new RegularCNM());
        n5.getCNM().addToNeighborList(testGrid, 0, 0);
        n5.getCNM().addToNeighborList(testGrid, 0, 1);
        n5.getCNM().addToNeighborList(testGrid, 0, 2);
        n5.getCNM().addToNeighborList(testGrid, 1, 0);
        n5.getCNM().addToNeighborList(testGrid, 1, 2);
        n5.getCNM().addToNeighborList(testGrid, 2, 0);
        n5.getCNM().addToNeighborList(testGrid, 2, 1);
        n5.getCNM().addToNeighborList(testGrid, 2, 2);
        List<Cell> myNeighbors = n5.getCNM().getNeighbors();
        List<Cell> realNeighbors = List.of(n1, n2, n3, n4, n6, n7, n8, n9);
        int counter = 0;
        assertEquals(myNeighbors.get(0).getMyCellState(), realNeighbors.get(0).getMyCellState());
        for(Cell cell:myNeighbors){
            assertEquals(cell, realNeighbors.get(counter));
            counter++;
        }
    }
}
