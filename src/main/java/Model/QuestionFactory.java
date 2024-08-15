package Model;

/**
 * The type Question factory.
 */
public final class QuestionFactory {
    /**
     * Instantiates a new Question factory.
     */
    private QuestionFactory() {
    }

    /**
     * Create question.
     *
     * @param theQuestion      the question
     * @param theOption1       the option 1
     * @param theOption2       the option 2
     * @param theOption3       the option 3
     * @param theOption4       the option 4
     * @param theCorrectAnswer the correct answer
     * @return the question
     */
    public static Question createQuestion(final String theQuestion, final String theOption1,
                                          final String theOption2, final String theOption3,
                                          final String theOption4, final String theCorrectAnswer) {
        return new Question(theQuestion, theOption1, theOption2, theOption3, theOption4, theCorrectAnswer);
    }


}
