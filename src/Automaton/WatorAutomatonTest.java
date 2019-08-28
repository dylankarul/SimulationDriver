
package Automaton;

import Gridulars.Grid;
import Gridulars.Cell;
import Tests.UpdateGridHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WatorAutomatonTest {

    int FISH = 0;
    int SHARK = 1;
    int WATER = 2;
    Grid testGrid;
    UpdateGridHelper updater = new UpdateGridHelper();

    List<Double> infoList = List.of(3.0, 4.0, 2.0);
    WatorAutomaton Wator = new WatorAutomaton(infoList);


    @BeforeEach
    void setUp() {
        testGrid = updater.setCSVToUse("watorTest.csv", Wator, "Cardinal", "Toroidal");

    }




    void sharkEatsFish() {
        updater.updateEverything(testGrid, Wator);
    }

    @Test
    void sharkEatsTheFish() {
        sharkEatsFish();
        assertEquals(SHARK, testGrid.getCellAt(1, 0).getMyCellState());
        assertEquals(WATER, testGrid.getCellAt(0, 0).getMyCellState());
    }


    @Test
    void sharkEatsTheFishToroidal() {
        //List<Integer> newStates = List.of(SHARK, WATER, WATER, WATER, WATER, WATER, FISH, WATER, WATER);
        //changeCellStates(newStates);
        testGrid = updater.setCSVToUse("watorTest2.csv", Wator, "Cardinal", "Toroidal");
        sharkEatsFish();
        assertEquals(SHARK ,testGrid.getCellAt(2, 0).getMyCellState());
        assertEquals(WATER, testGrid.getCellAt(0, 0).getMyCellState());
    }


    @Test
    void sharkMoves() {
        //List<Integer> newStates = List.of(SHARK, WATER, SHARK, SHARK, SHARK, SHARK, SHARK, SHARK, SHARK);
        //changeCellStates(newStates);
        testGrid = updater.setCSVToUse("watorTest3.csv", Wator, "Cardinal", "Toroidal");
        updater.updateEverything(testGrid, Wator);
        assertEquals(SHARK, testGrid.getCellAt(0, 1).getMyCellState());
    }


    @Test
    void sharkGainsEnergy() {
        sharkEatsFish();
        assertEquals(5, testGrid.getCellAt(1, 0).getMyCellEnergy());
    }

    @Test
    void sharkHasBaby() {
        //List<Integer> newStates = List.of(SHARK, WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER);
        //changeCellStates(newStates);
        testGrid = updater.setCSVToUse("watorTest4.csv", Wator, "Cardinal", "Toroidal");
        updater.updateEverything(testGrid, Wator);
        updater.updateEverything(testGrid, Wator);
        updater.updateEverything(testGrid, Wator);
        int sharkcount = 0;
        for(int rowIndex=0; rowIndex<testGrid.getGridHeight(); rowIndex++){
            for(int iter=0; iter<testGrid.getGridWidth(); iter++){
                Cell c = testGrid.getCellAt(rowIndex, iter);
                if(c.getMyCellState()==SHARK){
                    sharkcount++;
                }
            }
        }

        assertEquals(2, sharkcount);
    }

    @Test
    void sharkWaitsToHaveBaby() {
        //List<Integer> newStates = List.of(SHARK, SHARK, SHARK, SHARK, SHARK, SHARK, SHARK, SHARK, SHARK);
        //changeCellStates(newStates);
        testGrid = updater.setCSVToUse("watorTest5.csv", Wator, "Cardinal", "Toroidal");
        updater.updateEverything(testGrid, Wator);
        updater.updateEverything(testGrid, Wator);
        updater.updateEverything(testGrid, Wator);

        testGrid.getCellAt(0,0).setNextCellState(WATER);
        testGrid.getCellAt(0,0).updateCell();
        //List<Integer> newStates2 = List.of(WATER);
        //changeCellStates(newStates2);
        updater.updateEverything(testGrid, Wator);
        int sharkcount=0;
        for(int rowIndex=0; rowIndex<testGrid.getGridHeight(); rowIndex++){
            for(int iter=0; iter<testGrid.getGridWidth(); iter++){
                Cell c = testGrid.getCellAt(rowIndex, iter);
                if(c.getMyCellState()==SHARK){
                    sharkcount++;
                }
            }
        }
        assertEquals(9, sharkcount);

    }

    @Test
    void sharkDies() {
        //List<Integer> newStates = List.of(SHARK, WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER);
        //changeCellStates(newStates);
        testGrid = updater.setCSVToUse("watorTest6.csv", Wator, "Cardinal", "Toroidal");
        updater.updateEverything(testGrid, Wator);
        updater.updateEverything(testGrid, Wator);
        updater.updateEverything(testGrid, Wator);
        updater.updateEverything(testGrid, Wator);
        updater.updateEverything(testGrid, Wator);
        int sharkcount = 0;
        for(int rowIndex=0; rowIndex<testGrid.getGridHeight(); rowIndex++){
            for(int iter=0; iter<testGrid.getGridWidth(); iter++){
                Cell c = testGrid.getCellAt(rowIndex, iter);
                if(c.getMyCellState()==SHARK){
                    sharkcount++;
                }
            }
        }
        assertEquals(1, sharkcount);
    }

    @Test
    void fishMoves() {
        //List<Integer> newStates = List.of(FISH, WATER, 4, 4, 4, 4, 4, 4, 4);
        //changeCellStates(newStates);
        testGrid = updater.setCSVToUse("watorTest7.csv", Wator, "Cardinal", "Toroidal");
        updater.updateEverything(testGrid, Wator);
        assertEquals(FISH, testGrid.getCellAt(0, 1).getMyCellState());
        assertEquals(WATER, testGrid.getCellAt(0, 0).getMyCellState());
    }

    @Test
    void fishMovesToroidal() {
        testGrid = updater.setCSVToUse("watorTest8.csv", Wator, "Cardinal", "Toroidal");
        updater.updateEverything(testGrid, Wator);
        assertEquals(FISH, testGrid.getCellAt(2, 0).getMyCellState());
        assertEquals(WATER, testGrid.getCellAt(0, 0).getMyCellState());
    }

    @Test
    void fishHasBaby() {
        testGrid = updater.setCSVToUse("watorTest9.csv", Wator, "Cardinal", "Toroidal");
        updater.updateEverything(testGrid, Wator);
        updater.updateEverything(testGrid, Wator);
        updater.updateEverything(testGrid, Wator);
        int fishcount = 0;
        for(int rowIndex=0; rowIndex<testGrid.getGridHeight(); rowIndex++){
            for(int iter=0; iter<testGrid.getGridWidth(); iter++){
                Cell c = testGrid.getCellAt(rowIndex, iter);
                if(c.getMyCellState()==FISH){
                    fishcount++;
                }
            }
        }
        assertEquals(2, fishcount);
    }

    @Test
    void fishWaitsToHaveBaby() {
        //List<Integer> newStates = List.of(FISH, FISH, FISH, FISH, FISH, FISH, FISH, FISH, FISH);
        //changeCellStates((newStates));
        testGrid = updater.setCSVToUse("watorTest10.csv", Wator, "Cardinal", "Toroidal");
        updater.updateEverything(testGrid, Wator);
        updater.updateEverything(testGrid, Wator);
        updater.updateEverything(testGrid, Wator);
        testGrid.getCellAt(0,0).setNextCellState(WATER);
        testGrid.getCellAt(0,0).updateCell();
        updater.updateEverything(testGrid, Wator);
        int fishcount = 0;
        for(int rowIndex=0; rowIndex<testGrid.getGridHeight(); rowIndex++){
            for(int iter=0; iter<testGrid.getGridWidth(); iter++){
                Cell c = testGrid.getCellAt(rowIndex, iter);
                if(c.getMyCellState()==FISH){
                    fishcount++;
                }
            }
        }
        assertEquals(9, fishcount);
    }
}
