package Gridulars;

import java.util.List;
import java.util.ArrayList;
/***
 * Authors: Dylan
 * Purpose: an abstract class that holds and updates the neighbors of any specific cell
 * Assumptions: None
 * Dependencies: Cell, Grid
 * How to use it: extend this class and implement any specific neighbor rules you want,
 * such as Toroidal, nonToroidal, etc when you are adding to the neighborsList.
 */
public abstract class CellNeighborManager {

    private List<Cell> neighborsList;

    public CellNeighborManager() {
        neighborsList = new ArrayList<>();
    }

//    public void update(){
//        List<Cell> temp = new ArrayList<>();
//        for(Cell c:neighborsList){
//            temp.add(c);
//        }
//        neighborsList = temp;
//    }
//    public void setNeighbors(Cell c, Grid g) {
//        this.numNeighbors = num;
//    }


    //public abstract List<Cell> makeNeighborList(Cell[][] myGrid, int x, int y);

    protected int getToroidalval(int Yindex, Grid grid){
        Yindex = Yindex % grid.getGridWidth();
        Yindex = Yindex + grid.getGridWidth(); // If index is negative, modulus division gives us negative result, so this makes it positive.
        Yindex = Yindex % grid.getGridWidth(); // In case the previous step made index >= n

        return Yindex;
    }

    abstract public void addToNeighborList(Grid grid, int Xindex, int Yindex);

    /***
     *
     * @return the neighbor list for the specific cell being managed
     */
    public List<Cell> getNeighbors(){
        return neighborsList;
    }
}
