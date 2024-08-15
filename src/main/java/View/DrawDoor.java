package View;

import Controller.Main;
import Model.Database;
import Model.GenerateMaze;
import Model.MazeSolver;
import Model.Question;


/**
 * The type Draw door.
 */
public class DrawDoor {
    /**
     * The constant DOOR_NUMBERS.
     */
    private static int DOOR_NUMBERS = 0;
    /**
     * the database instance
     */
    private final Database myDatabase;
    /**
     * the question instance
     */
    private final Question myQuestion;
    /**
     * the draw question instance
     */

    private final DrawQuestion myDrawQuestion;
    /**
     * the draw maze instance
     */

    private final DrawMaze myMV;
    /**
     * the maze solver instance
     */
    private final MazeSolver myMazeSolver;
    /**
     * the generate maze instance
     */
    private final GenerateMaze myMaze;


    /**
     * Instantiates a new Draw door.
     *
     * @param theDrawMaze the draw maze
     * @param theMaze     the maze
     */
    public DrawDoor(final DrawMaze theDrawMaze, GenerateMaze theMaze) {
        myMaze = theMaze;
        myMV = theDrawMaze;
        myDatabase = Main.getDatabase();
        myQuestion = myDatabase.getMyQuestions()[DOOR_NUMBERS];
        DOOR_NUMBERS++;
        Main.setQuestion(myQuestion);
        myDrawQuestion = new DrawQuestion(myQuestion);
        myMazeSolver = new MazeSolver(theMaze.getMazeMatrix());


    }

    /**
     * Update maze boolean.
     *
     * @param theX the x
     * @param theY the y
     * @return the boolean
     */
    public boolean updateMaze(final int theX, final int theY) {

        if (myDatabase.getUserAnswer() != null) {
            if (myQuestion.verify(myDatabase.getUserAnswer())) {
                myMaze.changeValue(theX, theY, 5);


                myMV.updateMaze(theX, theY, 5);
                myDatabase.setAnswer(null);

                myDrawQuestion.setVisibility(false);
                return true;
            } else {
                myMaze.changeValue(theX, theY, 1);

                myMV.updateMaze(theX, theY, 1);

                myDatabase.setAnswer(null);

                if (!myMazeSolver.isSolvable()) {
                    myDrawQuestion.setVisibility(false);
                    return false;

                }


            }
            myDrawQuestion.setVisibility(false);
        }
        return true;

    }


}
