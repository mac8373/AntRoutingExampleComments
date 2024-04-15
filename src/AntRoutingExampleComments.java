import java.util.ArrayList; //Import the ArrayList class from the package java.util
import java.util.List; //Import the List class from the package java.util
import java.util.Random; //Import the Random class from the package java.util

/*
simulate ants finding the shortest path between two points on a grid.
The Ant class represents an ant which moves around the grid.
The move() method determines the next position for the ant based on its current position and the grid.
The getNeighbors() method returns a list of neighboring positions around the ant's current position.
The pickNextPosition() method randomly selects one of the neighboring positions for the ant to move to.
The AntRoutingExample class is the main class where the simulation is run. It initializes the grid, creates an ant, and moves it for a certain number of steps.
 */

class Ant { //Defines the Ant class
    // 3 attributes of Ant class
    private int x; // x coordinate of ants current position in environment
    private int y; // y coordinate of ants current position in environment
    private int[][] grid; // The grid/environment that the ant moves on

    public Ant(int x, int y, int[][] grid) { // Constructor for ant class
        this.x = x; // Initializes x coordinate for ants position
        this.y = y; // Initialized y coordinate for ants position
        this.grid = grid; // Sets the grid/environment that the ant moves on
    }
    // Method that allows ant to change from previous to current to future position
    public void move() {
        // Gets a list of neighbor positions around the ants current position using the x,y coordinates, environments length, and the index of 0 from the first row
        List<int[]> neighbors = getNeighbors(x, y, grid.length, grid[0].length);
        // Selects a neighbor position randomly as ants next position
        int[] nextPosition = pickNextPosition(neighbors);
        // Updates ants coordinate position to new one being (0,1)
        this.x = nextPosition[0]; // Updates x coordinate
        this.y = nextPosition[1]; // Updates y coordinate
    }
    /*
    This method is to get a list of all of the neighbor positions around a certain coordinate using the given boundaries of the grid of maxX and maxY.
    It not only checks for all possible neighbors, but also filters out positions that are not within the boundaries.
    It also filters out its current position so that it knows it must make a move.
     */
    // Gets all neighbor positions around certain position using the x,y coordinates as well as the maxX and maxY
    private List<int[]> getNeighbors(int x, int y, int maxX, int maxY) {
        // Initializes a brand new list for storing neighbor positions
        List<int[]> neighbors = new ArrayList<>();
        for (int dx = -1; dx <= 1; dx++) { // Loops through every possible neighbor position. dx referring to the horizontal position starting initially at -1, continuing until value reaches greater than 1. dx incremented by 1.
            for (int dy = -1; dy <= 1; dy++) { // Loops through every possible neighbor position. dy referring ot the vertical position starting initially at -1, continuing until value reaches greater than 1. dy incremented by 1.
                int newX = x + dx; // Calculates neighbor horizontal position (hence newX variable) by adding current position (x) and dx.
                int newY = y + dy; // Calculates neighbor vertical position (hence newY variable) by adding current position (y) and dy.
                if (newX >= 0 && newX < maxX && newY >= 0 && newY < maxY && !(dx == 0 && dy == 0)) { // To check if neighbor position is within the boundaries set. If every single condition is met, the code in the if block will execute.
                    // Adds on the calculated neighbor position to the list in order to keep track of neighbor positions.
                    neighbors.add(new int[]{newX, newY});
                }
            }
        }
        return neighbors;
    }
    // Method that randomly picks one of the neighbor positions from the list.
    // Declares private method pickNextPosition which returns an int[] array.
    private int[] pickNextPosition(List<int[]> neighbors) {
        Random rand = new Random(); // Creates new instance of Random class, assigning it to the variable rand. To find random numbers.
        int index = rand.nextInt(neighbors.size()); // Uses method nextInt from Random class in order to produce a random int between 0 and neighbors.size().
        return neighbors.get(index); // Returns neighbor position with randomly chosen index from neighbors list.
    }
    // Method used to get x coordinate of ants current horizontal position in environment.
    public int getX() {
        return x;
    }
    // Method used to get y coordinate of ants current vertical position in environment.
    public int getY() {
        return y;
    }
}
/*
The AntRoutingExampleComments class is the programs point of entry. It first initializes the grid/environment for the ant. Then creates the ant, then moves the ant given a number of steps.
The environment is a 10x10 grid, and there is an obstacle at (3,3). The ant is given an initial position of (0,0) on the grid. And the amount of steps is set at 20. The for loop is then initiated so that the ant will move a certain number of steps.
Each instance that the ant moves, its position is then updated. After the update, the code will print out the updated position that the ant moved to.
 */
public class AntRoutingExampleComments {
    // Point of entry into program using main method. Accepting array String[] args as argument, allowing arguments to pass through the command line during execution of program.
    public static void main(String[] args) {
        int[][] grid = new int[10][10]; // Creates environment known as grid with a size of 10x10.
        // Set obstacle at (3,3)
        grid[3][3] = 1;

        Ant ant = new Ant(0, 0, grid); // Creates initial position of (0,0) on the grid for ant to start on.
        int steps = 20; // Amount of steps that ant takes is set to 20.
        for (int i = 0; i < steps; i++) { // For loop that declares variable i to have an initial value of 0. Checking if i is less than value of steps, being true, loop continues. After each time, i is incremented by 1.
            ant.move(); // Calls move method for ant, moving ant to next position.
            // After ant moves, code prints output: "Ant moved to" which is the ants new position on the grid. Adding together new x point and new y point to output coordinate.
            System.out.println("Ant moved to: (" + ant.getX() + ", " + ant.getY() + ")");
        }
    }
}