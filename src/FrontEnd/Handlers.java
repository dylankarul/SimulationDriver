package FrontEnd;

import Engine.CASimulator;
import javafx.stage.Stage;
import Gridulars.Shapes;
import java.util.List;


public class Handlers {

    protected void handleButton(Stage stage, String CSVFile, Shapes shape, String neighborPolicy, String edgePolicy, List<Double> infoList) {
        CASimulator ca = new CASimulator();
        for(Double d : infoList){
            System.out.println("info" + d);
        }
        try {
            ca.setupSim(stage, CSVFile, shape, neighborPolicy, edgePolicy, infoList);
        } catch(Exception e) {
            throw new IllegalArgumentException("Your CSV File does not fit the requirements for this Simulation!");
        }
    }

    protected void handleBackToMainButton(Stage stage) {
        stage.close();
        CASimulator ca = new CASimulator();
        ca.start(stage);
    }
}
