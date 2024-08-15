package Model;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;


/**
 * The type Database.
 */
public class Database {
    private final Question[] myQuestions = new Question[25]; //array of Questions
    private String myUserAnswer;

    /**
     * Instantiates a new Database.
     *
     * @throws SQLException the sql exception
     */
    public Database() throws SQLException {
        fillArray();
    }

    /**
     * Get my questions question [ ].
     *
     * @return the question [ ]
     */
    public Question[] getMyQuestions() {
        return myQuestions;
    }

    /**
     * Get my question array question [ ].
     *
     * @return the question [ ]
     */
    public Question[] getMyQuestionArray() {
        return myQuestions;
    }



    //fill the array with data from the database
    /**
     * Fill array.
     *
     * @throws SQLException the sql exception
     */
    private void fillArray() throws SQLException {
        int myIndex = 0;

        SQLiteDataSource myDS = new SQLiteDataSource();
        myDS.setUrl("jdbc:sqlite:questions.sqlite");
        //establish connection
        Connection myConnection = myDS.getConnection();
        Statement myStatement = myConnection.createStatement();
        String myQuery = "select * from questions";
        ResultSet myResultSet = myStatement.executeQuery(myQuery);
        //read in all questions from database and add them to the array of Questions
        while (myResultSet.next()) {
            String question = myResultSet.getString("question");
            String option1 = myResultSet.getString("option1");
            String option2 = myResultSet.getString("option2");
            String option3 = myResultSet.getString("option3");
            String option4 = myResultSet.getString("option4");
            String answer = myResultSet.getString("answer");
            myQuestions[myIndex] = QuestionFactory.createQuestion(question, option1, option2, option3, option4, answer);
            myIndex++;
        }
    }

    /**
     * Sets answer.
     *
     * @param theText the the text
     */
//setting the answer (when user presses up)
    public void setAnswer(final String theText) {
        myUserAnswer = theText;
    }

    /**
     * Gets user answer.
     *
     * @return the user answer
     */
    public String getUserAnswer() {
        return myUserAnswer;
    }

    /**
     * Shuffle.
     */
//randomize the array of questions
    public void shuffle() {
        Random random = new Random();
        int n = myQuestions.length;
        for (int i = n - 1; i > 0; i--) {
            int j = random.nextInt(i);
            Question temp = myQuestions[i];
            myQuestions[i] = myQuestions[j];
            myQuestions[j] = temp;
        }
    }
}
