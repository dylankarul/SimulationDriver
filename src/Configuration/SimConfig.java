package Configuration;

import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.InputStream;

/***
 * Authors: Dylan
 * Purpose: this reads a CSV file and turns it into an Array of arrays
 * Assumptions: None
 * Dependencies: File and input stream
 * How to use it: Simply call SimConfig with the CSV file name and it will create
 * a matrix using that CSV file as a double array of integers
 */

public class SimConfig {

    private String fileName;
    private Scanner input;
    private String simType;
    private Integer[][] gridArr;


    public SimConfig(File file) {
        initialize(file);
    }

    private void initialize(File file) {
        fileName = file.getName();

        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        if (stream != null) {
            input = new Scanner(stream);
        }

        simType = input.nextLine().trim();
        String[] widthNHeight = input.next().split(",");
        int gridWidth  = Integer.valueOf(widthNHeight[0]);
        int gridHeight = Integer.valueOf(widthNHeight[1]);

        gridArr = new Integer[gridHeight][gridWidth];
        readData();
    }

    private void readData() {
        int count = 0;

        while (input.hasNext()) {
            String[] row = input.next().split(",");
            ArrayList<Integer> rowOfInts = new ArrayList<>();
            for (String value : row) {
                rowOfInts.add(Integer.valueOf(value));
            }

            Integer[] tempRow = rowOfInts.toArray(new Integer[0]);

            gridArr[count] = tempRow;
            count++;
        }
    }

    public String getSimType() { return simType; }

    public String getFileName() { return fileName; }

    public Integer[][] getGridArr() { return gridArr; }
}
