package Gridulars;

import Automaton.AbstractAutomaton;



/***
 * Authors: Dylan
 * Purpose: an object that allows for all the cells to be contained in one grid-like structure
 * Assumptions: None
 * Dependencies: Abstract Automaton, Cell, CNM and all that extend it, Shapes
 * How to use it: call new grid with the integer[][] representation of it, the specific type of
 * simulation, the screen width and height, the shape of the cells, the neighbor policy being used,
 * and the edgepolicy being used
 */
public class Grid {
    private Cell[][] myCellGrid;
    private Integer[][] myConfigGrid;
    private AbstractAutomaton simType;

    private int screenWidth;
    private int screenHeight;
    private String myNeighPolicy;
    private String myEdgePolicy;

    private String Toroidal = "Toroidal";
    private String NonToroidal = "Non-Toroidal";
    private String SideToroidal = "Side-Toroidal";


//    private int animationSize = 400;

    /***
     * public constructor that sets all the params
     * @param configGrid the integer representation of the original grid
     * @param ca the simulation type
     * @param sw screen width
     * @param sh screen height
     * @param myShape the shape being used for cells
     * @param neighPolicy the neighbor policy
     * @param edgePolicy the edge policy
     */
    public Grid(Integer[][] configGrid, AbstractAutomaton ca, int sw, int sh, Shapes myShape, String neighPolicy, String edgePolicy) {
        myConfigGrid = configGrid;
        simType = ca;
        screenWidth = sw;
        screenHeight = sh;
        //myPropPath=propPath;
        myNeighPolicy = neighPolicy;
        myEdgePolicy = edgePolicy;
        initialize(configGrid.length, configGrid[0].length, myShape);
    }

    private void initialize(int gridHeight, int gridWidth, Shapes myShape) {
//        double cellSideLength = (double) screenWidth / Math.max(gridHeight, gridWidth);


        myCellGrid = new Cell[gridHeight][gridWidth];

        for (int x = 0; x < gridHeight; x++) {
            for (int y = 0; y < gridWidth; y++) {
                Cell newCell = new Cell(simType, myConfigGrid[x][y]);
                myCellGrid[x][y] = newCell;
                newCell.getCellVis().setShape(myShape.getVertices(x, y, screenWidth, screenHeight, gridWidth, gridHeight));
                newCell.getCellVis().setCellColor(simType, myConfigGrid[x][y]);
            }
        }

        // add/update neighbors
        addCellNeighborManager(myShape);
    }

    private void addCellNeighborManager(Shapes myShape) {
        for (int x = 0; x < myCellGrid.length; x++) {
            for (int y = 0; y < myCellGrid[0].length; y++) {

                if(myEdgePolicy.equals(Toroidal)){
                    myCellGrid[x][y].setCNM(new ToroidalCNM());
                }
                else if(myEdgePolicy.equals(NonToroidal)){
                    myCellGrid[x][y].setCNM(new RegularCNM());
                }
                else if(myEdgePolicy.equals(SideToroidal)){
                    myCellGrid[x][y].setCNM(new SideToroidalCNM());
                }

                myShape.makeNeighborList(myCellGrid[x][y].getCNM(), this, x, y, myNeighPolicy);
            }
        }
    }


    //public Cell[][] getMyCellGrid() {
        //return myCellGrid;
    //}

    public int getGridWidth(){
        return myCellGrid[0].length;
    }

    public int getGridHeight(){
        return myCellGrid.length;
    }

    /***
     * gets the specific cell at that index of the grid
     * @param x x spot in grid
     * @param y y spot in the grid
     * @return the cell at that spot
     */
    public Cell getCellAt(int x, int y){
        return myCellGrid[x][y];
    }


}