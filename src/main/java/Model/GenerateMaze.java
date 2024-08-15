package Model;

import java.io.File;
import java.util.Scanner;

/**
 * The type Generate maze.
 */
public class GenerateMaze {
    private static int[][] MAZE_MATRIX;
    private final String myFileName;

    /**
     * Instantiates a new Generate maze.
     *
     * @param theFileName the file name
     */
    public GenerateMaze(String theFileName) {
        myFileName = theFileName;
        readArrayFromFile();

    }

    /**
     * Change value.
     *
     * @param theX     the x
     * @param theY     the y
     * @param theValue the value
     */
//change a value in the matrix
    public void changeValue(int theX, int theY, int theValue) {
        MAZE_MATRIX[theX][theY] = theValue;
    }

    /**
     * Read array from file.
     */
    public void readArrayFromFile() {
        try {
            Scanner scan = new Scanner(new File(myFileName));
            int row = scan.nextInt();
            int col = scan.nextInt();
            int[][] temp = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    temp[i][j] = scan.nextInt();
                }
            }
            MAZE_MATRIX = temp;
        } catch (Exception e) {
            System.out.println("File not found or error reading file");
            MAZE_MATRIX = new int[0][0]; // Initialize with a default empty matrix
        }
    }


    /**
     * Get maze matrix int [ ] [ ].
     *
     * @return the int [ ] [ ]
     */
    public int[][] getMazeMatrix() {
        return MAZE_MATRIX;
    }

    /**
     * Sets maze matrix.
     *
     * @param mazeMatrix the maze matrix
     */
    public void setMazeMatrix(int[][] mazeMatrix) {
        MAZE_MATRIX = mazeMatrix;
    }

    /**
     * To string method.
     *
     * @return the string
     */
    @Override
    public String toString() {
        //display matrix
        StringBuilder result = new StringBuilder();
        for (int[] mazeMatrix : MAZE_MATRIX) {
            for (int j = 0; j < MAZE_MATRIX[0].length; j++) {
                result.append(mazeMatrix[j]).append(" ");
            }
            result.append("\n");
        }
        result.append("\n");
        return result.toString();
    }
}
