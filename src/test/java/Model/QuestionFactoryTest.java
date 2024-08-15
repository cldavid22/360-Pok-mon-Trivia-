package Model;

import static org.junit.jupiter.api.Assertions.*;

class QuestionFactoryTest {

    @org.junit.jupiter.api.Test
    void createQuestion() {
        Question question = QuestionFactory.createQuestion("pikachu.jpeg", "pikachu", "ralts", "infernape", "chamander", "pikachu");
        assertEquals("pikachu.jpeg", question.getQuestion());
        assertEquals("pikachu", question.getOption1());
        assertEquals("ralts", question.getOption2());
        assertEquals("infernape", question.getOption3());
        assertEquals("chamander", question.getOption4());
        assertEquals("pikachu", question.getCorrectAnswer());
    }

}