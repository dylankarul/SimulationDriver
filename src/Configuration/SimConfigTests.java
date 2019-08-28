package Configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.io.File;

class SimConfigTests {
    private SimConfig s1;

    @BeforeEach
    void setUp(){
        s1 = new SimConfig(new File("resources/percTest.csv"));
    }

    @Test
    void testCorrectSimulationName(){
        //s1.readData();
        assertEquals(s1.getSimType(), "Percolation");
    }

    @Test
    void testGrid(){
        var realGrid = s1.getGridArr();

        var desiredGrid = new Integer[][] {{1,0,1},{0,3,1},{2,1,0},{0,0,0}};
        boolean flag = false;
        for(int x=0; x<realGrid.length; x++){
            if(Arrays.equals(realGrid[x], desiredGrid[x])){
                flag=true;
            }
            else{
                flag=false;
            }
        }
        assertTrue(flag);
    }

    @Test
    void testGridSize(){
        var realGrid = s1.getGridArr();
        assertEquals(realGrid.length, 4);
        assertEquals(realGrid[0].length, 3);
    }
}

