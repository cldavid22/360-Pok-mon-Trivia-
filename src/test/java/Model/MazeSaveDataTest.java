package Model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MazeSaveDataTest {

    private static MazeSaveData mazeSaveData;
    private static int[][] maze;
    private static int spriteX;
    private static int spriteY;
    private static Question[] questions;
    private static double cameraH;
    private static double cameraV;

    @BeforeAll
    public static void setUp() {
        maze = new int[][]{{1, 0, 1}, {0, 1, 0}, {1, 0, 1}};
        spriteX = 1;
        spriteY = 1;
        questions = new Question[]{
                new Question("Question 1", "Option 1", "Option 2", "Option 3", "Option 4", "Correct 1"),
                new Question("Question 2", "Option 1", "Option 2", "Option 3", "Option 4", "Correct 2")
        };
        cameraH = 45.0;
        cameraV = 30.0;

        mazeSaveData = new MazeSaveData(maze, spriteX, spriteY, questions, cameraH, cameraV);
    }

    @Test
    public void testGetMaze() {

        int[][] retrievedMaze = mazeSaveData.theMaze();
        assertArrayEquals(maze, retrievedMaze);
    }

    @Test
    public void testGetSpriteX() {

        int retrievedSpriteX = mazeSaveData.theSpriteX();
        assertEquals(spriteX, retrievedSpriteX);
    }

    @Test
    public void testGetSpriteY() {

        int retrievedSpriteY = mazeSaveData.theSpriteY();
        assertEquals(spriteY, retrievedSpriteY);
    }

    @Test
    public void testGetQuestions() {

        Question[] retrievedQuestions = mazeSaveData.theQuestions();
        assertArrayEquals(questions, retrievedQuestions);
    }

    @Test
    public void testGetCameraH() {

        double retrievedCameraH = mazeSaveData.theCameraH();
        assertEquals(cameraH, retrievedCameraH, 0.001); // Using delta for double comparison
    }

    @Test
    public void testGetCameraV() {

        double retrievedCameraV = mazeSaveData.theCameraV();
        assertEquals(cameraV, retrievedCameraV, 0.001); // Using delta for double comparison
    }

    // You can also write tests for saveToFile and loadFromFile methods, but they would involve file I/O which is more complex.

    // Don't forget to test edge cases and potential exceptions as well.
}
