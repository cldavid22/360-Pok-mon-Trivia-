package View;

import Controller.Main;
import Model.Question;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * The type Draw question.
 */
public class DrawQuestion {
    /**
     * the image view instance
     */
    private final ImageView myImageView;
    /**
     * the text field instances
     */
    private final TextField myTextField1;
    private final TextField myTextField2;
    private final TextField myTextField3;
    private final TextField myTextField4;


    /**
     * Instantiates a new Draw question.
     *
     * @param theQuestion the question
     */
    public DrawQuestion(final Question theQuestion) {
        AnchorPane myPane = (AnchorPane) Main.getRoot();
        Image myImage = new Image(theQuestion.getQuestion());

        myImageView = new ImageView();
        myImageView.setImage(myImage);
        myImageView.setFitHeight(242);
        myImageView.setFitWidth(293);
        myImageView.setLayoutX(347);
        myImageView.setLayoutY(157);


        myTextField1 = new TextField();
        myTextField1.setLayoutX(194);
        myTextField1.setLayoutY(369);
        myTextField1.setMaxWidth(88);
        myTextField1.setMaxHeight(25);
        myTextField2 = new TextField();
        myTextField2.setLayoutX(194);
        myTextField2.setLayoutY(399);
        myTextField2.setMaxWidth(88);
        myTextField2.setMaxHeight(25);
        myTextField3 = new TextField();
        myTextField3.setLayoutX(194);
        myTextField3.setLayoutY(428);
        myTextField3.setMaxWidth(88);
        myTextField3.setMaxHeight(25);
        myTextField4 = new TextField();
        myTextField4.setLayoutX(194);
        myTextField4.setLayoutY(455);
        myTextField4.setMaxWidth(88);
        myTextField4.setMaxHeight(25);


        myTextField1.setText(theQuestion.getOption1());
        myTextField2.setText(theQuestion.getOption2());
        myTextField3.setText(theQuestion.getOption4());
        myTextField4.setText(theQuestion.getOption3());

        //myImageView = new ImageView(myImage);
        myPane.getChildren().add(myTextField1);
        myPane.getChildren().add(myTextField2);
        myPane.getChildren().add(myTextField3);
        myPane.getChildren().add(myTextField4);
        myPane.getChildren().add(myImageView);

        try {
            InputStream audioStream = getClass().getResourceAsStream("/POKEMON.wav");
            if (audioStream == null) {
                throw new IOException("Audio file not found");
            }

            byte[] audioData = audioStream.readAllBytes();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(audioData);

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(byteArrayInputStream);

            AudioFormat format = audioInputStream.getFormat();
            SourceDataLine line = AudioSystem.getSourceDataLine(format);
            line.open(format);
            line.start();

            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesRead;

            while ((bytesRead = audioInputStream.read(buffer, 0, buffer.length)) != -1) {
                line.write(buffer, 0, bytesRead);
            }

            line.drain();
            line.close();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }


    }

    /**
     * Sets visibility.
     *
     * @param theVisible the visible
     */
    public void setVisibility(final boolean theVisible) {
        myImageView.setVisible(theVisible);
        myTextField1.setVisible(theVisible);
        myTextField2.setVisible(theVisible);
        myTextField3.setVisible(theVisible);
        myTextField4.setVisible(theVisible);

    }
}
