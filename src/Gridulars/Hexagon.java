package Gridulars;

import javafx.scene.shape.Polygon;
/***
 * Authors: Dylan
 * Purpose: this sets the vertices on the display screen for a specific cell that will be represented
 * as a hexagon
 * Assumptions: None
 * Dependencies: Grid, CellNeighborManager, Shapes
 * How to use it: create a new Hexagon and then call getVertices with the specific Cell's
 * position in the grid, the displays width and height, and the grids sizes as well, and then
 * it will accurately make its vertices
 */
public class Hexagon extends Shapes{

    public Hexagon(){super();}



    private static final double TOP_HEX = (1.0/5.0);
    private static final double HALF =.5;
    private static final double BOT_HEX = (4.0/5.0);
    private static final double HEX_RATIO = (8.0/5.0);
    private static final double TWO = 2.0;


    /***
     *
     * @param x the xpos of the cell in the grid
     * @param y the ypos of the cell in the grid
     * @param screenWidth
     * @param screenHeight
     * @param gridWidth
     * @param gridHeight
     * @return a hexagon with the proper vertices in the display
     */
    @Override
    public Polygon getVertices(int x, int y, double screenWidth, double screenHeight, int gridWidth, int gridHeight){
        Polygon rect;
        double cellWidth = (double) (screenWidth-((screenWidth/gridWidth)/2)) / gridWidth;
        double cellHeight = (double) screenHeight / gridHeight;
        if(x%2==0){
            rect = evenVerts(x, y, cellWidth, cellHeight);
        }
        else{
            rect = oddVerts(x, y, cellWidth, cellHeight);
        }

        return rect;
    }

    private Polygon evenVerts(int x, int y, double cellWidth, double cellHeight){
        Polygon rect = new Polygon();
        rect.getPoints().addAll(new Double[] {

                (y+HALF)*cellWidth , (HEX_RATIO)*cellHeight*x/TWO,
                (y+1.0)*cellWidth,     (TOP_HEX)*cellHeight+(HEX_RATIO)*cellHeight*x/TWO,
                (y+1.0)*cellWidth, (BOT_HEX)*cellHeight+(HEX_RATIO)*cellHeight*x/TWO,
                (y+HALF)*cellWidth, (1.0)*cellHeight+(HEX_RATIO)*cellHeight*x/TWO,
                1.0*y*cellWidth, (BOT_HEX)*cellHeight+(HEX_RATIO)*cellHeight*x/TWO,
                1.0*y*cellWidth, (TOP_HEX)*cellHeight+(HEX_RATIO)*cellHeight*x/TWO




        });
        return rect;
    }

    private Polygon oddVerts(int x, int y, double cellWidth, double cellHeight){

        Polygon rect = new Polygon();
        rect.getPoints().addAll((new Double[] {

                (y+HALF)*cellWidth +HALF*cellWidth , (HEX_RATIO)*cellHeight*x/TWO,
                (y+1.0)*cellWidth+HALF*cellWidth,     (TOP_HEX)*cellHeight+(HEX_RATIO)*cellHeight*x/TWO,
                (y+1.0)*cellWidth+HALF*cellWidth, (BOT_HEX)*cellHeight+(HEX_RATIO)*cellHeight*x/TWO,
                (y+HALF)*cellWidth+HALF*cellWidth, (1.0)*cellHeight+(HEX_RATIO)*cellHeight*x/TWO,
                1.0*y*cellWidth+HALF*cellWidth, (BOT_HEX)*cellHeight+(HEX_RATIO)*cellHeight*x/TWO,
                1.0*y*cellWidth+HALF*cellWidth, (TOP_HEX)*cellHeight+(HEX_RATIO)*cellHeight*x/TWO

        }));
        return rect;
    }

    @Override
    protected void makeCompleteNeighborList(CellNeighborManager CNM, Grid myCellGrid, int x, int y) {
        CNM.addToNeighborList(myCellGrid, x, y - 1); //left
        CNM.addToNeighborList(myCellGrid, x, y + 1); //right
        CNM.addToNeighborList(myCellGrid, x - 1, y);
        CNM.addToNeighborList(myCellGrid, x + 1, y);

        if (x % 2 != 0) {
            CNM.addToNeighborList(myCellGrid, x - 1, y + 1); //topright
            CNM.addToNeighborList(myCellGrid, x + 1, y + 1); //bottomright
        }
        else{
            CNM.addToNeighborList(myCellGrid, x - 1, y - 1); //topright
            CNM.addToNeighborList(myCellGrid, x + 1, y - 1);
        }
    }


    @Override
    protected void makeCardinalNeighborList(CellNeighborManager CNM, Grid myCellGrid, int x, int y){
        makeCompleteNeighborList(CNM, myCellGrid, x, y);
    }

    @Override
    protected void makeVertexNeighborList(CellNeighborManager CNM, Grid myCellGrid, int x, int y){
        makeCompleteNeighborList(CNM, myCellGrid, x, y);
    }
}
