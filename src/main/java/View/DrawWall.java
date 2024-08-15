package View;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The type Draw wall.
 */
public class DrawWall {
    /**
     * The constant WALL_WIDTH.
     */
    public static final int WALL_WIDTH = 50;
    /**
     * The constant WALL_HEIGHT.
     */
    public static final int WALL_HEIGHT = 50;
    /**
     * The constant DEFAULT_SPACE.
     */
    public static final int DEFAULT_SPACE = 50;
    /**
     * The Wall image.
     */
    private final ImageView wallImage;

    /**
     * Instantiates a new Draw wall.
     *
     * @param theRoot      the root
     * @param theX         the x
     * @param theY         the y
     * @param theWidth     the width
     * @param theHeight    the height
     * @param theImagePath the image path
     */
    DrawWall(final Group theRoot, final int theX, int theY, int theWidth, int theHeight, String theImagePath) {

        Image image = new Image(theImagePath);
        wallImage = new ImageView(image); // Initialize wallImage with the new ImageView instance
        wallImage.setFitWidth(theWidth);
        wallImage.setFitHeight(theHeight);
        wallImage.setTranslateX(theX);
        wallImage.setTranslateY(theY);

        theRoot.getChildren().add(wallImage);
    }

    /**
     * Gets wall.
     *
     * @return the wall
     */
    public ImageView getWall() {
        return wallImage;
    }

}
