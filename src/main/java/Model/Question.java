package Model;

/**
 * The type Question.
 */
//Question class, will be used to create an array of questions
public class Question implements java.io.Serializable {
    /**
     * The constant myCorrect.
     */
    private static double myCorrect;
    /**
     * The constant myTotal.
     */
    private static double myTotal;
    /**
     * The constant my Question.
     */
    private final String myQuestion;
    /**
     * The constant option 1.
     */
    private final String myOption1;
    /**
     * The constant option 2.
     */
    private final String myOption2;
    /**
     * The constant option 3.
     */
    private final String myOption3;
    /**
     * The constant option 4.
     */
    private final String myOption4;
    /**
     * The constant correct answer.
     */
    private final String myCorrectAnswer;

    /**
     * Instantiates a new Question.
     *
     * @param theQuestion      the question
     * @param theOption1       the option 1
     * @param theOption2       the option 2
     * @param theOption3       the option 3
     * @param theOption4       the option 4
     * @param theCorrectAnswer the correct answer
     */
    public Question(final String theQuestion, final String theOption1, final String theOption2,
                    final String theOption3, final String theOption4, final String theCorrectAnswer) {
        myCorrect = 0;
        myQuestion = theQuestion;
        myOption1 = theOption1;
        myOption2 = theOption2;
        myOption3 = theOption3;
        myOption4 = theOption4;
        myCorrectAnswer = theCorrectAnswer;
    }

    /**
     * Gets option 1.
     *
     * @return the option 1
     */
    public String getOption1() {
        return myOption1;
    }

    /**
     * Gets option 2.
     *
     * @return the option 2
     */
    public String getOption2() {
        return myOption2;
    }

    /**
     * Gets option 3.
     *
     * @return the option 3
     */
    public String getOption3() {
        return myOption3;
    }

    /**
     * Gets option 4.
     *
     * @return the option 4
     */
    public String getOption4() {
        return myOption4;
    }

    /**
     * Gets correct answer.
     *
     * @return the correct answer
     */
    public String getCorrectAnswer() {
        return myCorrectAnswer;
    }

    /**
     * Gets question.
     *
     * @return the question
     */
    public String getQuestion() {
        return myQuestion;
    }

    /**
     * Verify boolean.
     *
     * @param theInput the input
     * @return the boolean
     */
    public boolean verify(final String theInput) {
        if (theInput.equalsIgnoreCase(myCorrectAnswer)) {
            myCorrect = myCorrect + 0.5;
        }
        myTotal = myTotal + 0.5;
        return theInput.equalsIgnoreCase(myCorrectAnswer);
    }

    /**
     * the toString method
     * @return the string
     */
    @Override
    public String toString() {
        return myQuestion + " " + myOption1 + " " + myOption2 + " " + myOption3 + " " + myOption4 + " " + myCorrectAnswer;
    }


}



