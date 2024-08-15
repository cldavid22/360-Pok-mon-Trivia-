package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GenerateMazeTest {
    private GenerateMaze generateMaze;

    @BeforeEach
    public void setUp() {

        generateMaze = new GenerateMaze("Maze1.0.txt");

    }

    @Test
    public void testChangeValue() {
        generateMaze.changeValue(0, 1, 2);
        int[][] mazeMatrix = generateMaze.getMazeMatrix();
        assertEquals(2, mazeMatrix[0][1]);
    }

    @Test
    public void testReadArrayFromFile() {
        int[][] mazeMatrix = generateMaze.getMazeMatrix();
        assertNotNull(mazeMatrix);
        assertEquals(9, mazeMatrix.length);
        assertEquals(9, mazeMatrix[0].length);
        assertEquals(1, mazeMatrix[0][0]);
        assertEquals(0, mazeMatrix[1][1]);
        assertEquals(3, mazeMatrix[2][2]);
    }

}
