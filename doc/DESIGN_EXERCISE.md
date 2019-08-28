Design Exercise
===============

Name: Dylan Karul, Luis Fornes, Sam Zhang

### Questions to Consider
    How does a Gridulars.Cell know what rules to apply for its simulation?
        The rules would be supplied by whichever CA is run and the respective classes associated with that CA
        For each of those classes, logic pertaining to that CA would be given.
    
    How does a Gridulars.Cell know about its neighbors? How can it update itself without affecting its neighbors update?
        For each step, we're traversing the Gridulars.Grid and examines it's direct neighbors and potentially runs a getState() method
        for those neighbors. At each step, we would maintain a copy of the current state, update cells to a new state, then
        set the current state to the new state once all cells are updated.
    
    What behaviors does the Gridulars.Grid itself have? How can it update all of the Cells it contains?
        Behaviors the Gridulars.Grid has: Ability to initialize first configuration, ability to traverse the grid cell by cell
        with each step, the ability to update the each cells' state(s). The Gridulars.Grid can update all of its cells by
        traversing using a technique like bfs or dfs.
    
    What information about a simulation could be given in a configuration file?
        It could have the number of possible states for each cell, followed by integer representations of the Gridulars.Grid
        before simulation begins (i.e. 1's and 0's for ConwayAutomaton's representing a dead/alive cell)
    
    How is the GUI updated after all the cells have been updated?
        Each cell's color fill or potentially an image could just update based on our given logic for a particular CA
        at each step.
    
### High-Level Design

We envision an overarching Gridulars.Grid class that just represents a 2D system of cells.

Each of these cells could be its own class called Gridulars.Cell that has properties such as state (dead, alive, empty, etc.)
and functionality to check its neighbors.

Finally, each


