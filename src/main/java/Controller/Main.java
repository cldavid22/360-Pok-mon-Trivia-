package Controller;

import Model.Database;
import Model.GenerateMaze;
import Model.MazeSaveData;
import Model.Question;
import View.DrawSprite;
import View.DrawMaze;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * The type Main.
 */
public class Main extends Application {

    /**
     * The constant myRoot.
     */
    private static Parent myRoot;
    /**
     * The constant Database.
     */
    private static Database myDatabase;
    /**
     * The myQuestion instance.
     */
    private static Question myQuestion;
    /**
     * The scrollPane view.
     */
    private static ScrollPane myScrollPane;
    /**
     * The user input instance.
     */

    private static UserInput myUserInput;
    /**
     * The draw maze instance.
     */
    private final DrawMaze myDrawMaze;
    /**
     * The draw sprite instance.
     */
    private final DrawSprite mySprite;
    /**
     * the maze instance.
     */
    private final GenerateMaze myMaze;
    /**
     * The anchor pane.
     */
    @FXML
    public AnchorPane myAnchorPane;
    /**
     * The background image view.
     */
    private ImageView myBackgroundImageView;
    /**
     * the load button
     */
    private Button myLoadPreviousSaveButton;
    /**
     * the reset button
     */
    private Button myRestartGameButton;
    /**
     * the exit button
     */
    private Button myExitGameButton;
    /**
     * the save data instance
     */
    private MazeSaveData myData;

    /**
     * Instantiates a new Main.
     * initialise all elements that need to be universally accessed by the program.
     */
    public Main() {

        mySprite = new DrawSprite(100, 100, 40, 40);
        myMaze = new GenerateMaze("Maze4x42.0.txt");
        Group myGroup = new Group();
        myGroup.getChildren().add(mySprite.getMySprite());
        myScrollPane = new ScrollPane();
        myScrollPane.setLayoutX(347);
        myScrollPane.setLayoutY(157);
        myScrollPane.setPrefSize(293, 242);
        myScrollPane.setContent(myGroup);
        myDrawMaze = new DrawMaze(myGroup, myMaze);
        myUserInput = new UserInput(mySprite, myScrollPane, myDrawMaze, myMaze);
    }

    /**
     * Sets question.
     *
     * @param theQuestion the question
     */
    public static void setQuestion(final Question theQuestion) {
        myQuestion = theQuestion;
    }

    /**
     * Gets database.
     *
     * @return the database
     */
    public static Database getDatabase() {
        return myDatabase;
    }

    /**
     * Gets root.
     *
     * @return the root
     */
    public static Parent getRoot() {
        return myRoot;
    }

    /**
     * Main.
     *
     * @param theArgs the args
     */
    public static void main(final String[] theArgs) {
        launch(theArgs);

    }

    /**
     * starter method for javafx.
     *
     * @throws IOException if there is error on javafx generation
     */
    @Override
    public void start(final Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("Question.fxml"));
        myRoot = loader.load();
        myDatabase = new Database();
        myAnchorPane = (AnchorPane) myRoot;

        myAnchorPane.getChildren().add(myScrollPane);
        Scene myScene = new Scene(myAnchorPane);

        myScene.setOnKeyPressed(event -> {
            try {
                int win = myUserInput.handleKeyPress(event.getCode());
                if (win == 5){
                    won();
                }
            } catch (SQLException | IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                throw new RuntimeException(e);
            }
        });
        myScene.setOnMouseClicked(event -> myUserInput.handleMouseClick());
        myDatabase.shuffle();
        stage.setScene(myScene);
        stage.show();
    }

    /**
     * Up button action method
     *
     * @throws IOException the io exception
     */
    public void up() throws IOException { //option 1
        myDatabase.setAnswer(myQuestion.getOption1());
        int result = myUserInput.handleMouseClick();

        if (result == 1) {
            won();

        } else if (result == 2) {
            loss();
        }

    }

    /**
     * Right button action method.
     *
     * @throws IOException the io exception
     */
    public void right() throws IOException { //option 2
        myDatabase.setAnswer(myQuestion.getOption2());
        int result = myUserInput.handleMouseClick();

        if (result == 1) {
            won();

        } else if (result == 2) {
            loss();
        }

    }

    /**
     * Down button action method.
     *
     * @throws IOException the io exception
     */
    public void down() throws IOException { //option 3
        myDatabase.setAnswer(myQuestion.getOption3());
        int result = myUserInput.handleMouseClick();

        if (result == 1) {
            won();

        } else if (result == 2) {
            loss();
        }
    }

    /**
     * Left button action method.
     *
     * @throws IOException the io exception
     */
    public void left() throws IOException { //option 4
        myDatabase.setAnswer(myQuestion.getOption4());
        int result = myUserInput.handleMouseClick();

        if (result == 1) {
            won();

        } else if (result == 2) {
            loss();
        }

    }
    /**
     * loss procedure.
     *
     */
    private void loss() {
        myAnchorPane.setPrefSize(1000, 650);


        myBackgroundImageView = new ImageView(new Image("loss.jpg"));
        myBackgroundImageView.setFitWidth(1000);
        myBackgroundImageView.setFitHeight(650);
        myAnchorPane.getChildren().add(myBackgroundImageView);


        myLoadPreviousSaveButton = new Button("Load Previous Save");
        myLoadPreviousSaveButton.setLayoutX(150);
        myLoadPreviousSaveButton.setLayoutY(500);
        myLoadPreviousSaveButton.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        myLoadPreviousSaveButton.setOnAction(event -> {
            load();
            myBackgroundImageView.setVisible(false);
            myLoadPreviousSaveButton.setVisible(false);
            myRestartGameButton.setVisible(false);
            myExitGameButton.setVisible(false);
        });
        myAnchorPane.getChildren().add(myLoadPreviousSaveButton);
        myRestartGameButton = new Button("Restart Game");
        myRestartGameButton.setLayoutX(420);
        myRestartGameButton.setLayoutY(500);
        myRestartGameButton.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        myRestartGameButton.setOnAction(event -> {
            try {
                resetGame();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
        myAnchorPane.getChildren().add(myRestartGameButton);


        myExitGameButton = new Button("Quit");
        myExitGameButton.setLayoutX(630);
        myExitGameButton.setLayoutY(500);
        myExitGameButton.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        myExitGameButton.setOnAction(event -> exitGame());
        myAnchorPane.getChildren().add(myExitGameButton);

    }

    /**
     * Show about dialog.
     */
    public void showAboutDialog() {
        Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);
        aboutAlert.setTitle("About");
        aboutAlert.setHeaderText(null);
        aboutAlert.setContentText("""
                Welcome to the captivating world of Pokemon Maze Trivia!\040
                In this game, you're going to navigate through the maze and test your\040
                Pokemon knowledge to see if you truly have what it takes to become\040
                the Pokemon trivia champion.\040
                Good Luck and Have Fun!""");
        aboutAlert.showAndWait();
    }

    /**
     * Show instruction dialog.
     */
    public void showInstructionDialog() {
        Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);
        aboutAlert.setTitle("About");
        aboutAlert.setHeaderText(null);
        aboutAlert.setContentText("""
                WASD is to move character position
                When prompted with a question use the keypad to pick which option
                The options are listed below.
                To save please choose the A button
                To load please choose the B button
                Or use the menu to do theses functionalities""");
        aboutAlert.showAndWait();
    }

    /**
     * Exit game.
     */
    public void exitGame() {
        Platform.exit();
    }

    /**
     * Reset game.
     *
     * @throws Exception the exception
     */
    public void resetGame() throws Exception {
        Stage currentStage = (Stage) myAnchorPane.getScene().getWindow();
        currentStage.close();
        Stage newStage = new Stage();
        start(newStage);
    }
    /**
     * Won procedure.
     *
     * @throws IOException if there is an error during javafx generation
     */
    private void won() throws IOException {
        myAnchorPane.setPrefSize(1000, 650);

        myBackgroundImageView = new ImageView(new Image("Won.jpg"));
        myBackgroundImageView.setFitWidth(1000);
        myBackgroundImageView.setFitHeight(650);
        myAnchorPane.getChildren().add(myBackgroundImageView);


        myRestartGameButton = new Button("Restart Game");
        myRestartGameButton.setLayoutX(350);
        myRestartGameButton.setLayoutY(550);
        myRestartGameButton.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        myRestartGameButton.setOnAction(event -> {
            try {
                resetGame();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
        myAnchorPane.getChildren().add(myRestartGameButton);
        myExitGameButton = new Button("Quit");
        myExitGameButton.setLayoutX(570);
        myExitGameButton.setLayoutY(550);
        myExitGameButton.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        myExitGameButton.setOnAction(event -> exitGame());
        myAnchorPane.getChildren().add(myExitGameButton);
    }

    /**
     * Save.
     */
    public void save() {
        int[][] copiedMaze = new int[myMaze.getMazeMatrix().length][];
        for (int i = 0; i < myMaze.getMazeMatrix().length; i++) {
            copiedMaze[i] = Arrays.copyOf(myMaze.getMazeMatrix()[i], myMaze.getMazeMatrix()[i].length);
        }
        myData = new MazeSaveData(copiedMaze, (int) mySprite.getMyX(), (int) mySprite.getMyY(), myDatabase.getMyQuestionArray(), myScrollPane.getHvalue(), myScrollPane.getVvalue());
        myData.saveToFile("saveData.txt");
    }

    /**
     * Load.
     */
    public void load() {
        if (myData != null) {
            MazeSaveData.loadFromFile("saveData.txt");

        } else {
            myData = MazeSaveData.loadFromFile("saveData.txt");
        }
        if (myData != null) {
            myMaze.setMazeMatrix(myData.theMaze());
        }
        myDrawMaze.generateMaze(myMaze.getMazeMatrix());
        mySprite.updateSpritePosition(myData.theSpriteX(), myData.theSpriteY());
        myScrollPane.setHvalue(myData.theCameraH());
        myScrollPane.setVvalue(myData.theCameraV());
        save();
    }


}
