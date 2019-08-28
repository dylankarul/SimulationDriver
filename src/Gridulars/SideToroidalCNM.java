package Gridulars;
/***
 * Authors: Dylan
 * Purpose: adds neighbors only if they are side-Toroidal neighbors, meaning the top and bottom aren't toroidal
 * but the sides are. Checks whether a cell is another cell's neighbor or not based on the rules of being sideToroidal
 * and then adds it to the neighbor list if so
 * Assumptions: All cells being given are neighbors, not just random cells in the Grid
 * Dependencies: Cell, Grid
 * How to use it: call addToNeighbor list for all of the cells surrounding your desired cell, and it will check whether
 * that cell is inBounds (for top and bottom) or not or get its toroidal value (if it is on the side) and add it accordingly.
 */
public class SideToroidalCNM extends CellNeighborManager{



    public SideToroidalCNM(){super();}

    /***
     * checks if a cell is inBounds or not and also gets its toroidal value if applicable, and then adds it to the specific
     * cell's CNM list of neighbors
     * @param grid the Grid class
     * @param xIndex xIndex of the cell that we want to add
     * @param yIndex yIndex of the cell that we want to add
     */
    @Override
    public void addToNeighborList(Grid grid, int xIndex, int yIndex){
        if(inBounds(grid, xIndex) && !getNeighbors().contains(getElementAt(grid, xIndex, yIndex))){
            getNeighbors().add(getElementAt(grid, xIndex, yIndex));
        }
    }

    private boolean inBounds(Grid grid, int xSpot) {
        return (!(xSpot < 0 || xSpot >= grid.getGridHeight()));
    }

    private Cell getElementAt(Grid grid, int Xindex, int Yindex){
        Yindex=getToroidalval(Yindex, grid);
        return grid.getCellAt(Xindex, Yindex);
    }
}
