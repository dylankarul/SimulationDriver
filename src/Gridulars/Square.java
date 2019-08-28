package Gridulars;

import javafx.scene.shape.Polygon;
/***
 * Authors: Dylan
 * Purpose: allows for cells to be visualized as squares
 * Assumptions: None
 * Dependencies: Shapes, CNM and Grid
 * How to use it: for each cell, call getVertices in order to have it placed in the proper spot on the screen
 */

public class Square extends Shapes {

    public Square(){super();}

    /***
     * This creates the sqaure that will be visualized on the screen, and does so by calculating where
     * each vertex should be and creating the square with those vertices
     * @param x x position in the grid
     * @param y y position in the grid
     * @param screenWidth
     * @param screenHeight
     * @param gridWidth
     * @param gridHeight
     * @return the new sqaure that was created
     */
    @Override
    public Polygon getVertices(int x, int y, double screenWidth, double screenHeight, int gridWidth, int gridHeight){
        double cellWidth = (double) screenWidth / gridWidth;
        double cellHeight = (double) screenHeight / gridHeight;
        double xLeft = x * cellHeight;
        double yTop =  y * cellWidth;
//                myGrid[x][y] = new Cell(new Rectangle(y*cellSideLength, x*cellSideLength, cellSideLength, cellSideLength), simType, myConfigGrid[x][y]);
        Polygon rect = new Polygon();
        rect.getPoints().addAll(new Double[] {
                yTop                 , xLeft,
                yTop + cellWidth, xLeft,
                yTop + cellWidth, xLeft + cellHeight,
                yTop                 , xLeft + cellHeight
        });
        return rect;
    }


    @Override
    protected void makeCompleteNeighborList(CellNeighborManager CNM, Grid myCellGrid, int x, int y){
        CNM.addToNeighborList(myCellGrid, x-1, y-1);
        CNM.addToNeighborList(myCellGrid, x-1, y);
        CNM.addToNeighborList(myCellGrid, x-1, y+1);
        CNM.addToNeighborList(myCellGrid, x, y-1);
        CNM.addToNeighborList(myCellGrid, x, y+1);
        CNM.addToNeighborList(myCellGrid, x+1, y-1);
        CNM.addToNeighborList(myCellGrid, x+1, y);
        CNM.addToNeighborList(myCellGrid, x+1, y+1);
    }

    @Override
    protected void makeCardinalNeighborList(CellNeighborManager CNM, Grid myCellGrid, int x, int y){
        CNM.addToNeighborList(myCellGrid, x-1, y);
        CNM.addToNeighborList(myCellGrid, x, y-1);
        CNM.addToNeighborList(myCellGrid, x, y+1);
        CNM.addToNeighborList(myCellGrid, x+1, y);
    }

    @Override
    protected void makeVertexNeighborList(CellNeighborManager CNM, Grid myCellGrid, int x, int y){
        CNM.addToNeighborList(myCellGrid, x+1, y+1);
        CNM.addToNeighborList(myCellGrid, x+1, y-1);
        CNM.addToNeighborList(myCellGrid, x-1, y+1);
        CNM.addToNeighborList(myCellGrid, x-1, y-1);
    }
}
