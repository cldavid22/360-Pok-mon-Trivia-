package Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MazeSolverTest {

    @Test
    public void testIsSolvable() {
        int[][] maze = {
                {3, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 4}
        };

        MazeSolver mazeSolver = new MazeSolver(maze);
        assertTrue(mazeSolver.isSolvable());
    }

    @Test
    public void testUnsolvableMaze() {
        int[][] maze = {
                {3, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 1, 1, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 1, 1, 4}
        };

        MazeSolver mazeSolver = new MazeSolver(maze);
        assertFalse(mazeSolver.isSolvable());
    }

    @Test
    public void testInvalidMaze() {
        int[][] maze = {
                {0, 1, 0},
                {0, 1, 0},
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };

        assertThrows(IllegalArgumentException.class, () -> new MazeSolver(maze).isSolvable());
    }
}

