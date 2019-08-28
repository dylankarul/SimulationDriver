package Automaton;
import Gridulars.Cell;
import Gridulars.Grid;
import java.util.ArrayList;
import java.util.List;

/***
 * Authors: Dylan
 * Purpose: this is the specific Segregation simulation class
 * Assumptions: None
 * Dependencies: AbstractAutomaton, Cell, CellNeighborManager, SegregationViewClass
 * How to use it: Call update for every step that you want it to update a cell in the grid, and call getViewClass
 * when you want to choose which color to fill a cell with
 */
public class SegregationAutomaton extends AbstractAutomaton{

    /***
     * Contains the States for this simulation and allows for certain colors to be associated
     * with each state type
     */
    public enum States {
        DUKE(0),
        UNC(1),
        EMPTY(2);

        private int stateNumber;

        States(int n) {
            this.stateNumber = n;
        }
    }

    private double myThreshold;

    public SegregationAutomaton(List<Double> infoArray){
        super(States.values().length);
        myThreshold = infoArray.get(0);
    }

    /***
     * Function that implements the specific Segregation rules
     * @param grid the Grid class for the simulation, c is the cell being updated
     */
    public void update(Cell c, Grid grid){
        List<Cell> openSpots = new ArrayList<>();

        for(int rowIndex=0; rowIndex<grid.getGridHeight(); rowIndex++){
            for(int iter=0; iter<grid.getGridWidth(); iter++){
                Cell currCell = grid.getCellAt(rowIndex, iter);
                if(currCell.getMyCellState()==States.EMPTY.stateNumber && currCell.getNextCellState()==States.EMPTY.stateNumber){
                    openSpots.add(currCell);
                }
            }
        }

        if(c.getMyCellState() != States.EMPTY.stateNumber && !isSatisfied(c)){
                moveCells(c, openSpots);

        }


    }


    private void moveCells(Cell cellToMove, List<Cell> openSpots){
            if(!openSpots.isEmpty()){
                System.out.println("hi");
                int randomSpot = (int)(Math.random() * openSpots.size());
                Cell newCell = openSpots.get(randomSpot);
                newCell.setNextCellState(cellToMove.getMyCellState());
                cellToMove.setNextCellState(States.EMPTY.stateNumber);
                openSpots.remove(randomSpot);
            }

    }

    private boolean isSatisfied(Cell c){
        int myState = c.getMyCellState();
        List<Cell> neighbors = c.getCNM().getNeighbors();
        double totalNeighbors = 0.0;
        double neighborsLikeMe = 0.0;
        for(Cell neigh:neighbors){
            if(neigh.getMyCellState()==myState){
                totalNeighbors+=1;
                neighborsLikeMe+=1;
            }
            else if(neigh.getMyCellState()!=myState){
                totalNeighbors+=1;
            }
        }
        return(neighborsLikeMe/totalNeighbors >= myThreshold);
    }

    /***
     * gets the view class for the Segregation class
     */
    @Override
    public AbstractAutomatonViewClass getAutomatonView(){
        return new SegregationViewClass();
    }

}
