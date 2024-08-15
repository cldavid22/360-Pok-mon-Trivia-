package Model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {


    private static Question myQuestion;

    @BeforeAll
    static void setUpBeforeClass() {
        myQuestion = new Question("pikachu.jpeg", "pikachu", "ralts", "infernape", "chamander", "pikachu");
    }


    @Test
    void getOption1() {
        assertEquals("pikachu", myQuestion.getOption1());
    }

    @Test
    void getOption2() {
        assertEquals("ralts", myQuestion.getOption2());
    }

    @Test
    void getOption3() {
        assertEquals("infernape", myQuestion.getOption3());
    }

    @Test
    void getOption4() {
        assertEquals("chamander", myQuestion.getOption4());
    }

    @Test
    void getCorrectAnswer() {
        assertEquals("pikachu", myQuestion.getCorrectAnswer());
    }

    @Test
    void getQuestion() {
        assertEquals("pikachu.jpeg", myQuestion.getQuestion());
    }

    @Test
    void verify() {
        assertTrue(myQuestion.verify("pikachu"));
        assertFalse(myQuestion.verify("ralts"));
    }

    @Test
    void testToString() {
        assertEquals("pikachu.jpeg pikachu ralts infernape chamander pikachu", myQuestion.toString());
    }
}