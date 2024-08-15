package View;

import Model.GenerateMaze;
import javafx.scene.Group;
import javafx.scene.image.Image;

/**
 * The type Draw maze.
 */
public class DrawMaze {
    /**
     * group instance
     */
    private final Group myGroup;
    /**
     * the draw wall array
     */
    private final DrawWall[][] myWalls;

    /**
     * Instantiates a new Draw maze.
     *
     * @param theGroup the group
     * @param theMaze  the maze
     */
    public DrawMaze(final Group theGroup, GenerateMaze theMaze) {
        myGroup = theGroup;


        myWalls = new DrawWall[theMaze.getMazeMatrix().length][theMaze.getMazeMatrix().length];
        generateMaze(theMaze.getMazeMatrix());
    }

    /**
     * Generate maze.
     *
     * @param theArray the array
     */
//generate maze form matrix
    public void generateMaze(int[][] theArray) {
        for (int i = 0; i < theArray.length; i++) {
            for (int j = 0; j < theArray[0].length; j++) {
                if (theArray[i][j] == 1) {
                    myWalls[i][j] = new DrawWall(myGroup, i * DrawWall.DEFAULT_SPACE, j * DrawWall.DEFAULT_SPACE, DrawWall.WALL_WIDTH, DrawWall.WALL_HEIGHT, "wallSprite.png");
                } else if (theArray[i][j] == 2) {
                    myWalls[i][j] = new DrawWall(myGroup, i * DrawWall.DEFAULT_SPACE, j * DrawWall.DEFAULT_SPACE, DrawWall.WALL_WIDTH, DrawWall.WALL_HEIGHT, "doorClose.png");
                } else if (theArray[i][j] == 4) {
                    myWalls[i][j] = new DrawWall(myGroup, i * DrawWall.DEFAULT_SPACE, j * DrawWall.DEFAULT_SPACE, DrawWall.WALL_WIDTH, DrawWall.WALL_HEIGHT, "escapeRope.png");
                } else if (theArray[i][j] == 5) {
                    myWalls[i][j] = new DrawWall(myGroup, i * DrawWall.DEFAULT_SPACE, j * DrawWall.DEFAULT_SPACE, DrawWall.WALL_WIDTH, DrawWall.WALL_HEIGHT, "doorOpen.png");
                }


            }
        }
    }


    /**
     * Update maze.
     *
     * @param theX     the x
     * @param theY     the y
     * @param theIndex the index
     */
    public void updateMaze(int theX, int theY, int theIndex) {
        if (theIndex == 5) {
            myWalls[theX][theY].getWall().setImage(new Image("doorOpen.png"));
            myWalls[theX][theY].getWall().setVisible(true);
        } else if (theIndex == 1) {
            myWalls[theX][theY].getWall().setImage(new Image("wallSprite.png"));
            myWalls[theX][theY].getWall().setVisible(true);
        }
    }
}
