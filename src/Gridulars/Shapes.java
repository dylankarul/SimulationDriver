package Gridulars;

import javafx.scene.shape.Polygon;
/***
 * Authors: Dylan
 * Purpose: allows for cells to be visualized as any specific shape that a subclass specifies
 * Assumptions: That subclasses will be made
 * Dependencies: Shapes, CNM and Grid
 * How to use it: for each cell create a new type of shape, call getVertices in order to have it placed in the proper spot on the screen
 */
abstract public class Shapes {

    private String Complete = "Complete";
    private String Cardinal = "Cardinal";
    private String Vertex = "Vertex";

    public Shapes() {}

    /***
     * essentially creates the shape and where it will be put on the screen
     * @param x x index in grid
     * @param y y index in grid
     * @param screenWidth
     * @param screenHeight
     * @param gridWidth
     * @param gridHeight
     * @return the shape that was created
     */
    abstract public Polygon getVertices(int x, int y, double screenWidth, double screenHeight, int gridWidth, int gridHeight);

    /***
     * Makes the specific list of neighbors for the cell
     * @param CNM the cellNeighborManager for that cell
     * @param myCellGrid the Grid class for the simulaiton
     * @param x x position in the grid
     * @param y y position in the grid
     * @param neighPolicy the neighbor policy being used (Cardinal, complete...)
     */
    public void makeNeighborList(CellNeighborManager CNM, Grid myCellGrid, int x, int y, String neighPolicy){
        if(neighPolicy.equals(Complete)){
            makeCompleteNeighborList(CNM, myCellGrid, x, y);
        }
        else if(neighPolicy.equals(Cardinal)){
            makeCardinalNeighborList(CNM, myCellGrid, x, y);
        }
        else if(neighPolicy.equals(Vertex)){
            makeVertexNeighborList(CNM, myCellGrid, x, y);
        }
    }

    abstract protected void makeCompleteNeighborList(CellNeighborManager CNM, Grid myCellGrid, int x, int y);

    abstract protected void makeCardinalNeighborList(CellNeighborManager CNM, Grid myCellGrid, int x, int y);

    abstract protected void makeVertexNeighborList(CellNeighborManager CNM, Grid myCellGrid, int x, int y);

}
