package Automaton;

import Gridulars.Cell;
import Gridulars.Grid;
import java.util.List;


/***
 * Authors: Dylan
 * Purpose: this is the specific Percolation simulation class
 * Assumptions: None
 * Dependencies: AbstractAutomaton, Cell, CellNeighborManager, PercolationViewClass
 * How to use it: Call update for every step that you want it to update a cell in the grid, and call getView
 * when you want to choose which color to fill a cell with
 */

public class PercolationAutomaton extends AbstractAutomaton {

    /***
     * Contains the States for this simulation and allows for certain colors to be associated
     * with each state type
     */
    public enum States {
        BLOCKED(0),
        OPEN(1),
        FILLED(2);

        private int stateNumber;

        States(int n) {
            this.stateNumber = n;
        }
    }

    //private boolean isToroidal;

    public PercolationAutomaton(){
        super(States.values().length);
    }

    public PercolationAutomaton(List<Double> infoArray) {
        super(States.values().length);
    }

    /***
     * Function that implements the specific Percolation rules
     * @param c the cell being updated, grid the Grid class for the simulation
     */
    public void update(Cell c, Grid grid) {
                if (c.getMyCellState() == States.OPEN.stateNumber) {
                    List<Cell> neighbors = c.getCNM().getNeighbors();
                    int percolatedNeighbors = countNeighbors(neighbors, States.FILLED.stateNumber);

                    if (percolatedNeighbors > 0) {
                        //c.setType(2);
                        c.setNextCellState(States.FILLED.stateNumber);
                    }
                }



    }

    /*private void updateCNM(Cell cell){
        //cell.getCNM().update();
    }*/

    /***
     * gets the View class for Percolation
     * @return
     */
    @Override
    public AbstractAutomatonViewClass getAutomatonView(){
        return new PercolationViewClass();
    }

}
