package Controller;

import View.DrawDoor;
import Model.GenerateMaze;
import View.DrawSprite;
import View.DrawWall;
import View.DrawMaze;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * The type User input.
 */
public class UserInput {
    /**
     * The constant CAMARA_MOVEMENT_SPEED.
     */
    private static final double CAMARA_MOVEMENT_SPEED = 0.0175;
    /**
     * the Draw sprite instance
     */
    private final DrawSprite mySprite;
    /**
     * the Scroll pane instance
     */
    private final ScrollPane myScrollPane;
    /**
     * the Draw maze instance
     */
    private final DrawMaze myDrawMaze;
    /**
     * the Generate maze instance
     */
    private final GenerateMaze myMaze;
    /**
     * the grid position of the sprite x and y
     */
    private int myGridX;
    private int myGridY;
    /**
     * the boolean values for the sprite movement
     */
    private boolean myNorth;
    private boolean mySouth;
    private boolean myEast;
    private boolean myWest;
    private boolean mySpriteCanMove;
    /**
     * the Draw door instance
     */
    private DrawDoor myDrawDoor;
    /**
     * the boolean value for if the user won
     */
    private boolean myWon;

    /**
     * Instantiates a new User input.
     *
     * @param theSprite   the sprite
     * @param theMazeView the maze view
     * @param theDrawMaze the draw maze
     * @param theMaze     the maze
     */
    UserInput(DrawSprite theSprite, ScrollPane theMazeView, DrawMaze theDrawMaze, GenerateMaze theMaze) {
        myMaze = theMaze;
        myWon = false;
        myDrawMaze = theDrawMaze;
        mySprite = theSprite;
        myNorth = true;
        myEast = true;
        myWest = true;
        mySouth = true;
        myScrollPane = theMazeView;
        mySpriteCanMove = true; // Set sprite movement to true by default
        myDrawDoor = null;
        myGridX = 0;
        myGridY = 0;
    }

    /**
     * Handle key press.
     *
     * @param keyCode the key code
     * @throws SQLException                  the sql exception
     * @throws IOException                   the io exception
     * @throws UnsupportedAudioFileException the unsupported audio file exception
     * @throws LineUnavailableException      the line unavailable exception
     */
    int handleKeyPress(final KeyCode keyCode) throws SQLException, IOException, UnsupportedAudioFileException, LineUnavailableException {
        if (!mySpriteCanMove) {
            return 1; // If sprite cannot move, exit early
        }

        double newSpriteX = mySprite.getMyX();
        double newSpriteY = mySprite.getMyY();


        switch (keyCode) {
            case W -> {
                newSpriteY = mySprite.getMyY() - 10;
                int r = isCollision(newSpriteX, newSpriteY);

                if (r==1) {
                    myScrollPane.setVvalue(myScrollPane.getVvalue() - CAMARA_MOVEMENT_SPEED);
                    mySprite.startAnimation(mySprite.getMyAnimationPathsUp());
                    mySprite.stopAnimation();
                }
                if (r==5) {
                    return 5;
                }

            }
            case A -> {
                newSpriteX = mySprite.getMyX() - 10;
                int r = isCollision(newSpriteX, newSpriteY);

                if (r==1) {
                    myScrollPane.setHvalue(myScrollPane.getHvalue() - CAMARA_MOVEMENT_SPEED);
                    mySprite.startAnimation(mySprite.getMyAnimationPathsLeft());
                    mySprite.stopAnimation();
                }if (r==5) {
                    return 5;
                }
            }
            case S -> {
                newSpriteY = mySprite.getMyY() + 10;
                int r = isCollision(newSpriteX, newSpriteY);

                if (r==1) {
                    myScrollPane.setVvalue(myScrollPane.getVvalue() + CAMARA_MOVEMENT_SPEED);
                    mySprite.startAnimation(mySprite.getMyAnimationPathsDown());
                    mySprite.stopAnimation();
                }if (r==5) {
                    return 5;
                }
            }
            case D -> {
                newSpriteX = mySprite.getMyX() + 10;
                int r = isCollision(newSpriteX, newSpriteY);

                if (r==1) {
                    myScrollPane.setHvalue(myScrollPane.getHvalue() + CAMARA_MOVEMENT_SPEED);
                    mySprite.startAnimation(mySprite.getMyAnimationPathsRight());
                    mySprite.stopAnimation();
                }if (r==5) {
                    return 5;
                }
            }

            default -> {

            }
        }
        if (myNorth && myEast && myWest && mySouth) {
            mySprite.updateSpritePosition(newSpriteX, newSpriteY);
        } else {
            mySprite.stopAnimation();

        }
        return 2;
    }

    /**
     * Is collision boolean.
     *
     * @param theSpriteX the sprite x
     * @param theSpriteY the sprite y
     * @return the boolean
     */
    public int isCollision(final double theSpriteX, final double theSpriteY) {
        myGridX = (int) (theSpriteX / DrawWall.DEFAULT_SPACE);
        myGridY = (int) (theSpriteY / DrawWall.DEFAULT_SPACE);

        if (myGridX >= 0 && myGridX < myMaze.getMazeMatrix().length &&
                myGridY >= 0 && myGridY < myMaze.getMazeMatrix().length) {
            int cellValue = myMaze.getMazeMatrix()[myGridX][myGridY];
            if (cellValue == 1) {

                myNorth = false;
                myEast = false;
                mySouth = false;
                myWest = false;

            } else if (cellValue == 2) {

                mySpriteCanMove = false;
                myDrawDoor = new DrawDoor(myDrawMaze, myMaze);


            } else if (cellValue == 4) {


                mySpriteCanMove = false;
                myWon = true;
                return 5;

            } else {
                // No collision with walls, allow movement in all directions
                myNorth = true;
                myEast = true;
                mySouth = true;
                myWest = true;
            }
        }

        // Allow movement only if no collision in all directions
        if (myNorth && myEast && mySouth && myWest) {
            return 1;
        }
        return 2;
    }

    /**
     * Handle mouse click int.
     *
     * @return the int
     */
    public int handleMouseClick() {
        if (myDrawDoor != null) {
            mySpriteCanMove = true;
            boolean isMazeSolvable = myDrawDoor.updateMaze(myGridX, myGridY);

            // 0 maze is solvable and not won
            // 1 maze is solvable and won
            // 2 maze is not solvable and not won
            if (isMazeSolvable && !myWon) {
                return 0;
            } else if (!isMazeSolvable && !myWon) {
                return 2;
            } else if (isMazeSolvable) {
                return 1;
            }
        }
        return 0;
    }

}