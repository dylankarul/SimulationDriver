package Automaton;
import Gridulars.Cell;
import Gridulars.Grid;

import java.util.List;

/***
 * Authors: Dylan and Sam
 * Purpose: this is the specific RPS simulation class
 * Assumptions: None
 * Dependencies: AbstractAutomaton, Cell, CellNeighborManager, RPSViewClass
 * How to use it: Call update for every step that you want it to update a cell in the grid, and call getViewClass
 * when you want to choose which color to fill a cell with
 */
public class RPSAutomaton extends AbstractAutomaton {

    /***
     * Contains the States for this simulation and allows for certain colors to be associated
     * with each state type
     */
    public enum States {
        ROCK(0),
        PAPER(1),
        SCISSORS(2);

        private int stateNumber;

        States(int n) {
            this.stateNumber = n;
        }
    }

    private double myThreshold;

    public RPSAutomaton(List<Double> infoArray){
        super(States.values().length);
        myThreshold = infoArray.get(0);
    }

    /***
     * Function that implements the specific RPS rules
     * @param c the cell being updated for the simulation, grid is the Grid class for the simulation
     * @return the updated cell grid
     */
    public void update(Cell c, Grid grid){

                List<Cell> neighbors = c.getCNM().getNeighbors();
                if(c.getMyCellState()==States.ROCK.stateNumber){
                    rules(c, neighbors, States.PAPER.stateNumber);
                }
                else if(c.getMyCellState()==States.PAPER.stateNumber){
                    rules(c, neighbors, States.SCISSORS.stateNumber);
                }
                else if(c.getMyCellState()==States.SCISSORS.stateNumber){
                    rules(c, neighbors, States.ROCK.stateNumber);
                }

    }



    private void rules(Cell c, List<Cell> neighbors, int opposite){
        int oppositeCount = 0;
        for(Cell neigh:neighbors){
            if(neigh.getMyCellState()==opposite){
                oppositeCount++;
            }
        }
        if(oppositeCount>myThreshold){
            c.setNextCellState(opposite);
        }
    }

    /***
     * gets the RPS view class which has front end color information
     * @return
     */
    @Override
    public AbstractAutomatonViewClass getAutomatonView(){
        return new RPSViewClass();
    }
}
