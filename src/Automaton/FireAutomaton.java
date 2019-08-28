

package Automaton;
import Gridulars.Cell;
import Gridulars.Grid;

import java.lang.reflect.Array;
import java.util.List;

/***
 * Authors: Dylan
 * Purpose: this is the specific Fire simulation class
 * Assumptions: None
 * Dependencies: AbstractAutomaton, Cell, CellNeighborManager, FireViewClass
 * How to use it: Call update for every step that you want it to update a Cell, and call getView to get the JavaFx aspect
 * when you want to choose which color to fill a cell with
 */

public class FireAutomaton extends AbstractAutomaton{

    /***
     * Contains the States for this simulation and allows for certain colors to be associated
     * with each state type
     */
    public enum States {
        EMPTY(0),
        TREE(1),
        BURNING(2);

        private int stateNumber;

        States(int n) {
            this.stateNumber = n;

        }
    }

    private double myProbCatch;

    /***
     * Constructor, sets probCatch, which is the probability of a tree catching on fire
     */
    public FireAutomaton(List<Double> infoArray){
        super(States.values().length);
        List<Double> test = infoArray;
        infoArray.add(8.0);
        myProbCatch = infoArray.get(0);
    }

    /***
     * Function that implements the specific Fire rules
     * @param grid the Grid class for the simulation, c the specific cell being updated
     */
    public void update(Cell c, Grid grid){
                if(c.getMyCellState()== States.TREE.stateNumber){
                    List<Cell> directNeighbors = c.getCNM().getNeighbors();
                    if(checkNeighbors(directNeighbors) && randomFireGenerator()){
                        c.setNextCellState(States.BURNING.stateNumber);
                    }
                }
                else if(c.getMyCellState()==States.BURNING.stateNumber){
                    c.setNextCellState(States.EMPTY.stateNumber);
                }



    }

    /***
     * checks neighbors for if there is a burning tree next to the current cell
     * @param neighbors the list of neighbors
     * @return true if there is a burning neighbor, false if not
     */
    private boolean checkNeighbors(List<Cell> neighbors){
        for(Cell c: neighbors){
            System.out.println(c.getMyCellState());
            if (c.getMyCellState() == States.BURNING.stateNumber){
                return true;
            }
        }
        System.out.println("no neighs");
        return false;
    }

    /***
     * generates a random number between 0 and 1. Returns whether that number is less than the probability of catching on fire
     * It is used because if it returns true, then that cell will be set to burning in the implementation
     * @return see above
     */
    private boolean randomFireGenerator(){
        double randomNum = Math.random();
        System.out.println(randomNum);
        return (randomNum<myProbCatch);
    }

    /***
     * returns the javafx view class for Fire
     */
    @Override
    public AbstractAutomatonViewClass getAutomatonView(){
        return new FireViewClass();
    }
}


