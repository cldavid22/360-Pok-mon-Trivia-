package Model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    private static Database myDatabase1;
    private static Database myDatabase2;

    @BeforeAll
    static void setUpBeforeClass() throws SQLException {
        myDatabase1 = new Database();
        myDatabase2 = new Database();
    }

    @Test
    void getMyQuestionArray() {
        Question[] myQuestions = myDatabase1.getMyQuestionArray();
        assertEquals(myQuestions, myDatabase1.getMyQuestionArray());
    }

    @Test
    void fillArray() {
        Question[] myQuestions = new Question[25];
        assertNotEquals(myQuestions, myDatabase1.getMyQuestionArray());
    }

    @Test
    void setAnswer() {
        myDatabase1.setAnswer("pikachu");
        assertEquals("pikachu", myDatabase1.getUserAnswer());
    }

    @Test
    void getUserAnswer() {
        myDatabase1.setAnswer("pikachu");
        assertEquals("pikachu", myDatabase1.getUserAnswer());
    }

    @Test
    void shuffle() {
        Question[] myQuestions = myDatabase1.getMyQuestionArray();
        Question question1 = myQuestions[0];
        System.out.println(question1);
        myDatabase1.shuffle();
        Question[] myQuestions2 = myDatabase1.getMyQuestionArray();
        Question question2 = myQuestions2[0];
        System.out.println(question2);
        assertNotEquals(question1, question2);
    }
}