package Gridulars;

/***
 * Authors: Dylan
 * Purpose: adds neighbors only if they are nonToroidal neighbors. Checks whether a cell is another cell's neighbor
 * or not based on the rules of being nonToroidal and then adds it to the neighbor list if so
 * Assumptions: All cells being given are neighbors, not just random cells in the Grid
 * Dependencies: Cell, Grid
 * How to use it: call addToNeighbor list for all of the cells surrounding your desired cell, and it will check whether
 * that cell is inBounds or not and add it accordingly.
 */

public class RegularCNM extends CellNeighborManager{


    public RegularCNM(){
        super();
    }

    @Override
    public void addToNeighborList(Grid grid, int xSpot, int ySpot){
        if(inBounds(grid, xSpot, ySpot) && !getNeighbors().contains(grid.getCellAt(xSpot, ySpot))){
            getNeighbors().add(grid.getCellAt(xSpot, ySpot));
        }
    }

    private boolean inBounds(Grid grid, int xSpot, int ySpot){
        if(xSpot>-1 && xSpot<grid.getGridHeight()){
            return (ySpot>-1 && ySpot<grid.getGridWidth());
        }
        return false;
    }
}
