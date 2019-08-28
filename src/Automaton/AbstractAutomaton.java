package Automaton;

import Gridulars.Cell;
import Gridulars.Grid;
import java.util.List;

/***
 * Authors: Dylan and Sam
 * Purpose: this is the abstract class for any type of simulation.
 * You would use it to implement a new type of simulation
 * Assumptions: None
 * Dependencies: Cell class
 * How to use it: extend this class and implement the abstract functions
 */

public abstract class AbstractAutomaton {


    private int numStates;

    AbstractAutomaton(int nStates) {
        numStates = nStates;
    }

    AbstractAutomaton(List<Double> infoList){};


    /***
     * Function that implements the specific update rules of a simulation
     * @param grid the Grid class (not the actual grid) for the simulation, cell the specific cell being altered
     */
    public abstract void update(Cell cell, Grid grid);


    /***
     * returns the view class for the specific simulation
     * @return
     */
    public abstract AbstractAutomatonViewClass getAutomatonView();

    /*protected Cell[][] updateWholeGrid(Cell[][] cellGrid){
        for (Cell[] row : cellGrid)
            for (Cell c : row)
                c.updateCell();

        return cellGrid;
    }*/


    /***
     * a general function that examines a cells neighbors and returns the number of neighbors of a
     * specific state
     * @param neighbors a list of the cell's neighbors
     * @param desiredState the specific state that the user wants a count of
     * @return
     */
    protected int countNeighbors(List<Cell> neighbors, int desiredState){
        int neighborSatisfy = 0;
        for (Cell neighborCell : neighbors) {
            if (neighborCell.getMyCellState() == desiredState)
                neighborSatisfy++;
        }

        return neighborSatisfy;
    }



}
