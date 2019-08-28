
package Automaton;

import Gridulars.Grid;
import java.util.List;
import Tests.UpdateGridHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class RPSAutomatonTest {

    int ROCK = 0;
    int PAPER = 1;
    int SCISSORS = 2;
    int threshold = 2;
    Grid testGrid;
    UpdateGridHelper updater = new UpdateGridHelper();
    List<Double> infoList = List.of(2.0);
    RPSAutomaton RPS = new RPSAutomaton(infoList);

    @BeforeEach
    void setUp() {
        //SimConfig config = new SimConfig(new File("RPSTest.csv"));

        //testGrid = new Grid(config.getGridArr(), RPS, 400, 400, new Square(), "Complete", "Non-Toroidal");

        testGrid = updater.setCSVToUse("RPSTest.csv", RPS, "Complete", "Non-Toroidal");

    }

    @Test
    void updatePaperBeatsRock() {
        updater.updateEverything(testGrid, RPS);
        assertEquals(PAPER, testGrid.getCellAt(0, 0).getMyCellState());
    }

    void helperUpdate(){
        testGrid.getCellAt(0,1).setNextCellState(SCISSORS);
        testGrid.getCellAt(1,0).setNextCellState(SCISSORS);
        testGrid.getCellAt(1,1).setNextCellState(SCISSORS);
        testGrid.getCellAt(2,1).setNextCellState(SCISSORS);
        testGrid.getCellAt(0,1).updateCell();
        testGrid.getCellAt(1,0).updateCell();
        testGrid.getCellAt(1,1).updateCell();
        testGrid.getCellAt(2,1).updateCell();
        /*n2.setNextCellState(SCISSORS);
        n4.setNextCellState(SCISSORS);
        n5.setNextCellState(SCISSORS);
        n8.setNextCellState(SCISSORS);
        n8.updateCell();
        n5.updateCell();
        n4.updateCell();
        n2.updateCell();*/
    }

    @Test
    void updateScissorsDoesntBeatRock(){
        helperUpdate();
        updater.updateEverything(testGrid, RPS);
        assertEquals(ROCK, testGrid.getCellAt(0, 0).getMyCellState());
    }


    @Test
    void updateScissorsBeatsPaper(){
        helperUpdate();
        testGrid = updater.setCSVToUse("RPSTest2.csv", RPS, "Complete", "Non-Toroidal");

        /*n1.setNextCellState(PAPER);
        n1.updateCell();*/
        updater.updateEverything(testGrid, RPS);
        assertEquals(SCISSORS, testGrid.getCellAt(1, 0).getMyCellState());
    }


    @Test
    void updateRockDoesntBeatPaper(){
        updater.updateEverything(testGrid, RPS);
        assertEquals(PAPER, testGrid.getCellAt(1, 1).getMyCellState());
    }


    @Test
    void updateRockBeatsScissors(){
        testGrid = updater.setCSVToUse("RPSTest3.csv", RPS, "Complete", "Non-Toroidal");

        /*n2.setNextCellState(ROCK);
        n2.updateCell();
        n5.setNextCellState(ROCK);
        n5.updateCell();*/
        updater.updateEverything(testGrid, RPS);
        assertEquals(ROCK, testGrid.getCellAt(0,2).getMyCellState());
    }


    @Test
    void updatePaperDoesntBeatScissors(){
        testGrid.getCellAt(1,2).setNextCellState(PAPER);
        testGrid.getCellAt(1,2).updateCell();
        /*n6.setNextCellState(PAPER);
        n6.updateCell();*/
        updater.updateEverything(testGrid, RPS);
        assertEquals(SCISSORS, testGrid.getCellAt(0,2).getMyCellState());
    }


    @Test
    void notAboveThresholdWontUpdate(){
        updater.updateEverything(testGrid, RPS);
        assertEquals(ROCK, testGrid.getCellAt(2,0).getMyCellState());
    }


}
