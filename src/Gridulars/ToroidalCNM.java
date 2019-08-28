package Gridulars;
/***
 * Authors: Dylan
 * Purpose: adds the cells neighbors (in their toroidal form) to the list of neighbors
 * Assumptions: All cells being given are neighbors, not just random cells in the Grid
 * Dependencies: Cell, Grid
 * How to use it: call addToNeighbor list for all of the cells surrounding your desired cell, and it will get its toroidal
 * value and add it accordingly.
 */

public class ToroidalCNM extends CellNeighborManager {

    public ToroidalCNM(){
        super();
    }

    /***
     *  gets the given cells toroidal value in the grid if applicable, and then adds it to the specific
     * cell's CNM list of neighbors
     * @param grid the Grid class
     * @param Xindex xIndex of the cell that we want to add
     * @param Yindex yIndex of the cell that we want to add
     */
    @Override
    public void addToNeighborList(Grid grid, int Xindex, int Yindex){
        if(!getNeighbors().contains(getElementAt(grid, Xindex, Yindex))){
            getNeighbors().add(getElementAt(grid, Xindex, Yindex));
        }

    }

    private Cell getElementAt(Grid grid, int Xindex, int Yindex) {
        Xindex = Xindex % grid.getGridHeight();
        Xindex = Xindex + grid.getGridHeight(); // If index is negative, modulus division gives us negative result, so this makes it positive.
        Xindex = Xindex % grid.getGridHeight(); // In case the previous step made index >= n

        Yindex = getToroidalval(Yindex, grid);

        return grid.getCellAt(Xindex, Yindex);
    }

}
