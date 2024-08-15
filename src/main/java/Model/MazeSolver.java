package Model;

import java.util.ArrayDeque;
import java.util.Queue;


/**
 * The type Maze solver.
 */
public class MazeSolver {
    /**
     * The maze matrix
     */
    private final int[][] myMaze;
    /**
     * The number of rows in the maze
     */
    private final int myRows;
    /**
     * The number of columns in the maze
     */
    private final int myCols;
    /**
     * The visited matrix
     */
    private final boolean[][] myVisited;
    /**
     * The start point
     */
    private int[] myStart;
    /**
     * The end point
     */
    private int[] myEnd;

    /**
     * Instantiates a new Maze solver.
     *
     * @param theMaze the maze
     */
    public MazeSolver(final int[][] theMaze) {
        this.myMaze = theMaze;
        this.myRows = theMaze.length;
        this.myCols = theMaze[0].length;
        this.myVisited = new boolean[myRows][myCols];
        this.myStart = null;
        this.myEnd = null;

        for (int i = 0; i < myRows; i++) {
            for (int j = 0; j < myCols; j++) {
                if (theMaze[i][j] == 3) {
                    myStart = new int[]{i, j};
                } else if (theMaze[i][j] == 4) {
                    myEnd = new int[]{i, j};
                }
            }
        }
    }
    /**
     * Solve maze.
     *
     * @return the boolean
     */
    private boolean isValidMove(final int theRow, int theCol) {
        return theRow >= 0 && theRow < myRows && theCol >= 0 && theCol < myCols && myMaze[theRow][theCol] != 1 && !myVisited[theRow][theCol];
    }

    /**
     * Is solvable boolean.
     *
     * @return the boolean
     */
    public boolean isSolvable() {
        if (myStart == null || myEnd == null) {
            throw new IllegalArgumentException("Start and/or end points not found in the maze.");
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(myStart);
        myVisited[myStart[0]][myStart[1]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            if (row == myEnd[0] && col == myEnd[1]) {
                return true;
            }

            int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                if (isValidMove(newRow, newCol)) {
                    myVisited[newRow][newCol] = true;
                    queue.add(new int[]{newRow, newCol});
                }
            }
        }

        return false; // No path found
    }

}

