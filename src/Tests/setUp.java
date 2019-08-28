/*package Tests;

import Automaton.AbstractAutomaton;
import Automaton.ConwayAutomaton;
import Gridulars.*;
import javafx.scene.shape.Polygon;

import java.util.Arrays;
import java.util.List;

public class setUp {

    public List<Cell> createNeighList(AbstractAutomaton simType, List<Integer> states){
        Cell n1 = new Cell(new Polygon(), simType, states.get(0));
        Cell n2 = new Cell(new Polygon(), simType, states.get(1));
        Cell n3 = new Cell(new Polygon(), simType, states.get(2));
        Cell n4 = new Cell(new Polygon(), simType, states.get(3));
        Cell n5 = new Cell(new Polygon(), simType, states.get(4));
        Cell n6 = new Cell(new Polygon(), simType, states.get(5));
        Cell n7 = new Cell(new Polygon(), simType, states.get(6));
        Cell n8 = new Cell(new Polygon(), simType, states.get(7));
        Cell n9 = new Cell(new Polygon(), simType, states.get(8));
        List<Cell> neighs = List.of(n1, n2, n3, n4, n5, n6, n7, n8, n9);

        return neighs;
    }

    public static Cell[][] fillGrid(List<Cell> neighs, CellNeighborManager CNM, String neighPolicy){
        Cell[][] myGrid = new Cell[3][3];
        int count = 0;
        for (int x = 0; x < myGrid.length; x++) {
            for (int y = 0; y < myGrid[0].length; y++) {
                myGrid[x][y] = neighs.get(count);
                count++;
            }
        }

        int x = 0;
        for(Cell[] row:myGrid){
            int y =0;
            for(Cell cell:row){
                Shapes myShape = new Square();
                cell.setCNM(CNM);
                myShape.makeNeighborList(cell.getCNM(), myGrid, x, y, neighPolicy);
                y++;
            }
            x++;
        }

        return myGrid;
    }

    static String printGrid (Cell[][] grid) {
        String result = "";
        for (Cell[] row : grid) {
            for (Cell c : row) {
                result += c.getMyCellState() + " ";
            }
            result += "\n";
        }
        return result;
    }
}*/
