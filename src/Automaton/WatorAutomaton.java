package Automaton;

import Gridulars.Cell;
import Gridulars.Grid;
import java.util.List;
import java.util.ArrayList;

/***
 * Authors: Dylan
 * Purpose: this is the specific Wator simulation class
 * Assumptions: None
 * Dependencies: AbstractAutomaton, Cell, CellNeighborManager, WatorViewClass
 * How to use it: Call update for every step that you want it to update a cell in the grid, and call getViewClass
 * when you want to choose which color to fill a cell with
 */
public class WatorAutomaton extends AbstractAutomaton {

    /***
     * Contains the States for this simulation and allows for certain colors to be associated
     * with each state type
     */
    public enum States {
        FISH(0),
        SHARK(1),
        WATER(2);

        private int stateNumber;


        States(int n) {
            this.stateNumber = n;
        }
    }

    private int reproductionTime=3;
    private int startingEnergy=3;
    private int energyBoostAmount=2;
    private int numUpdates = 0;

    public WatorAutomaton(List<Double> infoArray){
        super(States.values().length);
        reproductionTime=(int) Math.round(infoArray.get(0));
        startingEnergy = (int) Math.round(infoArray.get(1));
        energyBoostAmount = (int) Math.round(infoArray.get(2));
    }

    /***
     * Function that implements the specific Wator rules
     * @param grid the Grid class for the simulation, c the cell being updated
     * @return the updated cell grid
     */
    public void update(Cell c, Grid grid){
        if(numUpdates==0){
            Cell[][] tempGrid = new Cell[grid.getGridHeight()][grid.getGridWidth()];
            for(int rowIndex=0; rowIndex<grid.getGridHeight(); rowIndex++){
                for(int iter=0; iter<grid.getGridWidth(); iter++){
                    tempGrid[rowIndex][iter] = grid.getCellAt(rowIndex, iter);
                }
            }
            setInitialEnergy(tempGrid);
        }
                if(c.getMyCellState()==States.FISH.stateNumber && c.getNextCellState()==States.FISH.stateNumber){
                    updateFish(c);
                }
                else if(c.getMyCellState()==States.SHARK.stateNumber){
                    updateShark(c);


        }
        numUpdates++;

    }

    private void setInitialEnergy(Cell[][] cellGrid){
        for(Cell[] row:cellGrid){
            for(Cell c:row){
                c.setMyCellEnergy(startingEnergy);
            }
        }
    }

    private void updateFish(Cell c){
        boolean reproduce = checkForReproduce(c);

        List<Cell> neighbors =  c.getCNM().getNeighbors();
        List<Cell> openSpots = new ArrayList<>();
        for(Cell neigh:neighbors) {
            if (neigh.getNextCellState() == States.WATER.stateNumber) {
                openSpots.add(neigh);
            }
        }

        if(!openSpots.isEmpty()){
            changeCells(openSpots, c, reproduce, 0, States.FISH.stateNumber);
        }
    }

    private void updateShark(Cell c){
        boolean reproduce;
        if(c.getMyCellEnergy()<=0){
            c.setNextCellState(States.WATER.stateNumber);
            c.setMyCellEnergy(startingEnergy);
            c.setMyCellTime(0);
        }
        else{

            reproduce = checkForReproduce(c);

            List<Cell> neighbors = c.getCNM().getNeighbors();
            List<Cell> fishNeighbors = new ArrayList<>();
            List<Cell> openSpots = new ArrayList<>();
            for(Cell neigh:neighbors){
                if(neigh.getNextCellState()==States.FISH.stateNumber){
                    fishNeighbors.add(neigh);
                }
                else if(neigh.getNextCellState()==States.WATER.stateNumber){
                    openSpots.add(neigh);
                }
            }
            if(!fishNeighbors.isEmpty()){
                changeCells(fishNeighbors, c, reproduce, energyBoostAmount, States.SHARK.stateNumber);
            }
            else if(!openSpots.isEmpty()){
                changeCells(openSpots, c, reproduce, 0, States.SHARK.stateNumber);
            }
            else{ //nowhere to go
                c.setMyCellEnergy(c.getMyCellEnergy()-1);
            }
        }
    }

    private void changeCells(List<Cell> myList, Cell c, boolean reproduce, int boost, int stateNum){
        int randomSpot = (int)(Math.random() * myList.size());
        Cell newCell = myList.get(randomSpot);
        newCell.setNextCellState(stateNum);
        newCell.setMyCellTime(c.getMyCellTime());
        newCell.setMyCellEnergy(c.getMyCellEnergy()-1+boost);
        c.setMyCellEnergy(startingEnergy);
        c.setMyCellTime(0);
        if(!reproduce){
            c.setNextCellState(States.WATER.stateNumber);
        }
        else{
            newCell.setMyCellTime(0);
        }
    }

    private boolean checkForReproduce(Cell c){
        c.setMyCellTime(c.getMyCellTime()+1);
        return(c.getMyCellTime()>=reproductionTime);
    }

    /***
     * gets the view class for Wator
     */
    @Override
    public AbstractAutomatonViewClass getAutomatonView(){
        return new WatorViewClass();
    }

}
