package Automaton;

import Gridulars.Cell;
import Gridulars.Grid;




import java.util.List;

/***
 * Authors: Dylan and Sam
 * Purpose: this is the specific Conway simulation class
 * Assumptions: None
 * Dependencies: AbstractAutomaton, Cell, CellNeighborManager, ConwayView class
 * How to use it: Call update for every step that you want it to update a cell in the grid, and call getView to get the
 * JavaFx aspect
 * when you want to choose which color to fill a cell with
 */

public class ConwayAutomaton extends AbstractAutomaton {

    /***
     * Contains the States for this simulation and allows for certain colors to be associated
     * with each state type
     */
    public enum States {
        DEAD (0),
        ALIVE(1);

        private int stateNumber;

        States(int n) {
            this.stateNumber = n;

        }
    }

    private static final int LONELINESS = 1;
    private static final int STABILITY = 2;
    private static final int REPRODUCTION = 3;
    private static final int OVERCROWDED = 4;

    /**
     * Default Constructor
     */
    public ConwayAutomaton(List<Double> infoArray) {
        super(States.values().length);
    }

    public ConwayAutomaton(){
        super(States.values().length);
    }

    /***
     * Function that implements the specific Conway rules
     * @param c the cell being updated, grid the Grid class of the simulation
     */
    public void update(Cell c, Grid grid) {
                List<Cell> neighbors = c.getCNM().getNeighbors();
                int aliveNeighbors = countNeighbors(neighbors, States.ALIVE.stateNumber);

                if (aliveNeighbors <= LONELINESS) { // LONELINESS
                    c.setNextCellState(States.DEAD.stateNumber);
                }

                if (aliveNeighbors == STABILITY) { // STABILITY
                    c.setNextCellState(c.getMyCellState());
                }

                if (aliveNeighbors == REPRODUCTION) { // REPRODUCTION
                    c.setNextCellState(States.ALIVE.stateNumber);
                }

                if (aliveNeighbors >= OVERCROWDED) { // overcrowding
                    c.setNextCellState(States.DEAD.stateNumber);
                }



        // update all Cells' current states

    }

    /***
     * returns the javafx portion of this simulation
     */
    @Override
    public AbstractAutomatonViewClass getAutomatonView(){
        return new ConwayViewClass();
    }



}
