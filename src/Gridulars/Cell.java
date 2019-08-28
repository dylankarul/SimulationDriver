package Gridulars;

import Automaton.AbstractAutomaton;
/***
 * Authors: Dylan and Sam
 * Purpose: this sets up cells for the simulation, along with thier properties
 * Assumptions: None
 * Dependencies: Abstract Automaton and CellNeighborManager
 * How to use it: create a new cell and provide the specific simulation type, and
 * its initial state. Also can use updateCell and setNextState in order to change a cells state
 */

public class Cell {


    private int myCellState;
    private int myNextCellState;
    //private Shape myCellShape;
    private int myCellEnergy;
    private int myCellTime=0;
    private AbstractAutomaton mySimType;
    private CellNeighborManager myCNM;
    //private Point myCoordinates;
    //private Color myCellColor;
    private CellVisualization cellVis;

    public Cell(AbstractAutomaton ca, int stateValue) {
        cellVis = new CellVisualization();
        mySimType = ca;
        myCellState = stateValue;
        myNextCellState = myCellState;
        //cellVis.setCellColor(mySimType, stateValue);
        //myCellColor = ca.getAutomatonView().getColor(stateValue);
        //myCellShape.setFill(myCellColor);
    }

//    public void update() {
//        simType.update();
//    }

//    public abstract simulationChange(Neighbors neighbors, SimulationRules rules) {
//
//    }

    /***
     * Takes the cell and updates it to whatever its nextState was set to
     */
    public void updateCell() {
        myCellState = myNextCellState;
        getCellVis().setCellColor(mySimType, myNextCellState);
    }

    public int getMyCellState() {
        return myCellState;
    }

    /***
     * Makes it to that when one calls updateCell, the cells state is updated to the parameter labeled
     * next in this function
     */
    public void setNextCellState(int next){
        myNextCellState = next;
    }

    public int getNextCellState(){
        return myNextCellState;
    }

    public CellVisualization getCellVis(){
        return cellVis;
    }

    public void setCNM(CellNeighborManager cnm){
        myCNM = cnm;
    }

    public CellNeighborManager getCNM(){
        return myCNM;
    }


    public void setMyCellEnergy(int energy){ myCellEnergy = energy; }

    public int getMyCellEnergy(){ return myCellEnergy; }

    public void setMyCellTime(int time){myCellTime=time;}

    public int getMyCellTime(){ return myCellTime;}


//    public abstract Cell emptyCell;
//    public abstract Color getColor;
}
