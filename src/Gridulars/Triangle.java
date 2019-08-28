package Gridulars;

import javafx.scene.shape.Polygon;
/***
 * Authors: Dylan
 * Purpose: allows for cells to be visualized as triangles
 * Assumptions: None
 * Dependencies: Shapes, CNM and Grid
 * How to use it: for each cell, call getVertices in order to have it placed in the proper spot on the screen
 */
public class Triangle extends Shapes{

    public Triangle(){super();}

    private static final double TWO = 2.0;
    private boolean upsideDown;

    /***
     * This creates the triangle that will be visualized on the screen, and does so by calculating where
     * each vertex should be and creating the triangle with those vertices
     * @param x x position in the grid
     * @param y y position in the grid
     * @param screenWidth
     * @param screenHeight
     * @param gridWidth
     * @param gridHeight
     * @return the new triangle that was created
     */
    @Override
    public Polygon getVertices(int x, int y, double screenWidth, double screenHeight, int gridWidth, int gridHeight){
        double cellWidth = (screenWidth-(screenWidth/gridWidth)) / (gridWidth) * TWO;
        double cellHeight = screenHeight / gridHeight;
//                myGrid[x][y] = new Cell(new Rectangle(y*cellSideLength, x*cellSideLength, cellSideLength, cellSideLength), simType, myConfigGrid[x][y]);
        Polygon rect;
        checkUpsideDown(x, y);
        if(upsideDown){
            rect = upsideDownVertices(x, y, cellWidth, cellHeight);
        }
        else{
            rect = normalVertices(x, y, cellWidth, cellHeight);
        }
        return rect;
    }

    private Polygon upsideDownVertices(int x, int y, double cellWidth, double cellHeight){
        Polygon rect = new Polygon();
        rect.getPoints().addAll(new Double[] {
                (y/ TWO)*cellWidth, x*cellHeight,
                (y/ TWO)*cellWidth+cellWidth, (x)*cellHeight,
                (y/ TWO)*cellWidth+cellWidth/ TWO, x*cellHeight+cellHeight
        });
        return rect;
    }

    private Polygon normalVertices(int x, int y, double cellWidth, double cellHeight){
        Polygon rect = new Polygon();
        rect.getPoints().addAll(new Double[] {
                (y/ TWO)*cellWidth, (x)*cellHeight+cellHeight,
                (y/ TWO)*cellWidth+cellWidth, (x)*cellHeight+cellHeight,
                (y/ TWO)*cellWidth+cellWidth/ TWO, x*cellHeight
        });
        return rect;
    }

    protected boolean checkUpsideDown(int x, int y){
        if(x%2 == 0){

            upsideDown = !(y%2==0);

        }
        else{

            upsideDown = (y%2==0);


        }
        return upsideDown;
    }

    @Override
    protected void makeCompleteNeighborList(CellNeighborManager CNM, Grid myCellGrid, int x, int y){
        checkUpsideDown(x, y);

        CNM.addToNeighborList(myCellGrid, x, y-1);
        CNM.addToNeighborList(myCellGrid, x, y+1);
        CNM.addToNeighborList(myCellGrid, x-1, y);
        CNM.addToNeighborList(myCellGrid, x, y-2);
        CNM.addToNeighborList(myCellGrid, x-1, y-1);
        CNM.addToNeighborList(myCellGrid, x-1, y+1);
        CNM.addToNeighborList(myCellGrid, x, y+2);
        CNM.addToNeighborList(myCellGrid, x+1, y+1);
        CNM.addToNeighborList(myCellGrid, x+1, y);
        CNM.addToNeighborList(myCellGrid, x+1, y-1);

        if(upsideDown){
            CNM.addToNeighborList(myCellGrid, x-1, y-2);
            CNM.addToNeighborList(myCellGrid, x-1, y+2);
        }
        else{
            CNM.addToNeighborList(myCellGrid, x+1, y-2);
            CNM.addToNeighborList(myCellGrid, x+1, y+2);
        }
    }


    @Override
    protected void makeCardinalNeighborList(CellNeighborManager CNM, Grid myCellGrid, int x, int y){
        checkUpsideDown(x, y);

        CNM.addToNeighborList(myCellGrid, x, y-1);
        CNM.addToNeighborList(myCellGrid, x, y+1);
        if(upsideDown){
            CNM.addToNeighborList(myCellGrid, x-1, y);
        }
        else{
            CNM.addToNeighborList(myCellGrid, x+1, y);
        }

    }

    @Override
    protected void makeVertexNeighborList(CellNeighborManager CNM, Grid myCellGrid, int x, int y){
        checkUpsideDown(x, y);

        CNM.addToNeighborList(myCellGrid, x, y+2);
        CNM.addToNeighborList(myCellGrid, x-1, y-1);
        CNM.addToNeighborList(myCellGrid, x-1, y+1);
        CNM.addToNeighborList(myCellGrid, x+1, y+1);
        CNM.addToNeighborList(myCellGrid, x, y-2);
        CNM.addToNeighborList(myCellGrid, x+1, y-1);

        if(upsideDown){
            CNM.addToNeighborList(myCellGrid, x+1, y);
            CNM.addToNeighborList(myCellGrid, x-1, y-2);
            CNM.addToNeighborList(myCellGrid, x-1, y+2);
        }
        else{
            CNM.addToNeighborList(myCellGrid, x-1, y);
            CNM.addToNeighborList(myCellGrid, x+1, y-2);
            CNM.addToNeighborList(myCellGrid, x+1, y+2);
        }
    }
}
