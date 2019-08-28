package Gridulars;


import Automaton.PercolationAutomaton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    Cell c1;
    CellNeighborManager CNM;
    Cell n1 = new Cell(new PercolationAutomaton(), 1);
    Cell n2 = new Cell(new PercolationAutomaton(), 1);
    Cell n3 = new Cell(new PercolationAutomaton(), 0);
    Cell n4 = new Cell(new PercolationAutomaton(), 2);
    Cell n5 = new Cell(new PercolationAutomaton(), 0);
    Cell n6 = new Cell(new PercolationAutomaton(), 0);
    Cell n7 = new Cell(new PercolationAutomaton(), 1);
    Cell n8 = new Cell(new PercolationAutomaton(), 1);
    Cell n9 = new Cell(new PercolationAutomaton(), 1);
    List<Cell> neighs = List.of(n1, n2, n3, n4, n5, n6, n7, n8, n9);

    @BeforeEach
    void setUp() {
        //SimConfig s1 = new SimConfig(new File("resources/percTest.csv"));
        //s1.getGridArr();
        c1 = new Cell(new PercolationAutomaton(), 1);


    }

    @Test
    void getType() {
        var testType = c1.getMyCellState();
        var realType = 1;
        assertEquals(testType, realType);
    }

    @Test
    void getTypeAfterUpdate() {
        c1.setNextCellState(2);
        c1.updateCell();
        var testType = c1.getMyCellState();
        var realType = 2;
        assertEquals(testType, realType);
    }

    @Test
    void setNextTypeDoesntChangeType() {
        c1.setNextCellState(0);
        var realType = c1.getMyCellState();
        assertEquals(realType, 1);
    }

    @Test
    void setNextType() {
        c1.setNextCellState(1);
        c1.setNextCellState(2);
        c1.setNextCellState(3);
        c1.updateCell();
        var realType = c1.getMyCellState();
        assertEquals(realType, 3);
    }

    @Test
    void testSetTypeWithoutChoosingANextType() {
        c1.updateCell();
        var realType = c1.getMyCellState();
        assertEquals(realType, 1);
    }


    @Test
    void setCNMandGetCNM() {
        //CellNeighborManager newCNM = new CellNeighborManager(neighs);
        CellNeighborManager newCNM = new ToroidalCNM();
        c1.setCNM(newCNM);
        var realCNM = c1.getCNM();
        assertEquals(newCNM, realCNM);
    }

    @Test
    void getCNM() {
        assertEquals(null, c1.getCNM());
    }

    @Test
    void setAndGetCellEnergy(){
        c1.setMyCellEnergy(4);
        var cellEng = c1.getMyCellEnergy();
        assertEquals(4, cellEng);
    }
}