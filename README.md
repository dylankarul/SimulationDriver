simulation
====

This project implements a cellular automata simulator. It displays an understanding of OO principles such as abstraction, encapsulation, and polymorphism. This allows for different types of simulations, cell shapes, and rules
for neighbors, edges, and how they interact. 

Names: Luis Fornes, Dylan Karul, Sam Zhang


### Primary Roles
* Dylan : Back End, Gridulars & Engine - Create Simulations, Cells, Shapes of cells, and CellNeighborManager and endure they run (standalone)
* Sam : Front End & Engine - Create the GUI and handle javafx related programming\
* Luis : Configuration & Screens- Properties files, refactoring/abstraction, and integration of back & front ends

### Resources Used
[Creating object instance from String name of the class](https://stackoverflow.com/questions/6094575/creating-an-instance-using-the-class-name-and-calling-constructor)

[Material-UI Color Schemes](https://material-ui.com/style/color/)

[Learned to Use Reflection](https://docs.oracle.com/javase/6/docs/technotes/guides/reflection/index.html)

    

### Running the Program

Main class: 
Engine.CASimulator

Data files needed: 
* data (resource directory) -> all CSV files
* resources (resource directory) -> all .properties files

What needs to be done to add a new kind of simulation to the project:
    * One would have to make a new Automaton class that extends AbstractAutomaton, implement the rules in the 
          update method, and also create a new AutomatonViewClass that contains the colors associated
          with the states for that class. In terms of configuration, one would just need to make it
          so that the config aspect knows that if a properties file says "SimName" then it should
          use that SimName as the Simulation type moving forward. Then in the front end, they would just need to add buttons and a screen for it in the front end so that
          someone could actually run it. Then one must add the Config and Vis properties files. It is in these files that you can also change the shape of the cell, the neighbor policy, and the edge policy.

Assumptions or Simplifications:
* Config files are in correct folder

