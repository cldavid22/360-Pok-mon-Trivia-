package View;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * The type Draw sprite.
 */
public class DrawSprite {
    /**
     * the image view instance .
     */

    private final ImageView mySprite;
    /**
     * the animation paths for the sprite.
     */
    private final String[] myAnimationPathsUp;
    private final String[] myAnimationPathsDown;
    private final String[] myAnimationPathsRight;
    private final String[] myAnimationPathsLeft;
    /**
     * the x and y coordinates of the sprite.
     */
    private double myX;
    private double myY;
    /**
     * the current frame index of the sprite.
     */
    private int myCurrentFrameIndex;
    /**
     * the timeline for the animation.
     */
    private boolean myIsAnimating;

    /**
     * Instantiates a new Draw sprite.
     *
     * @param theX      the x
     * @param theY      the y
     * @param theWidth  the width
     * @param theHeight the height
     */
    public DrawSprite(final double theX, final double theY, final double theWidth, final double theHeight) {
        myX = theX;
        myY = theY;

        String[] animationPathsDown = {
                "pokeDown2.png",
                "pokeDown1.png",
                "pokeDown2.png",
                "pokeDown3.png"
        };
        String[] animationPathsUp = {
                "pokeUp1.png",
                "pokeUp2.png",
                "pokeUp3.png",
                "pokeUp2.png"

        };
        String[] animationPathsRight = {
                "pokeRight1.png",
                "pokeRight2.png",
                "pokeRight3.png",
                "pokeRight2.png"
        };
        String[] animationPathsLeft = {
                "pokeLeft1.png",
                "pokeLeft2.png",
                "pokeLeft3.png",
                "pokeLeft2.png"
        };

        myAnimationPathsDown = animationPathsDown;
        myAnimationPathsUp = animationPathsUp;
        myAnimationPathsRight = animationPathsRight;
        myAnimationPathsLeft = animationPathsLeft;

        myCurrentFrameIndex = 0;
        Image firstFrame = new Image(animationPathsDown[myCurrentFrameIndex]);
        mySprite = new ImageView(firstFrame);
        mySprite.setFitWidth(theWidth);
        mySprite.setFitHeight(theHeight);
        mySprite.setTranslateX(myX);
        mySprite.setTranslateY(myY);

    }

    /**
     * Get my animation paths up string [ ].
     *
     * @return the string [ ]
     */
    public String[] getMyAnimationPathsUp() {
        return myAnimationPathsUp;
    }

    /**
     * Get my animation paths down string [ ].
     *
     * @return the string [ ]
     */
    public String[] getMyAnimationPathsDown() {
        return myAnimationPathsDown;
    }

    /**
     * Get my animation paths right string [ ].
     *
     * @return the string [ ]
     */
    public String[] getMyAnimationPathsRight() {
        return myAnimationPathsRight;
    }

    /**
     * Get my animation paths left string [ ].
     *
     * @return the string [ ]
     */
    public String[] getMyAnimationPathsLeft() {
        return myAnimationPathsLeft;
    }

    /**
     * Gets my sprite.
     *
     * @return the sprite
     */
    public ImageView getMySprite() {
        return mySprite;
    }

    /**
     * Gets my x.
     *
     * @return the x
     */
    public double getMyX() {
        return myX;
    }

    /**
     * Gets my y.
     *
     * @return the y
     */
    public double getMyY() {

        return myY;
    }


    /**
     * Update sprite position.
     *
     * @param x the x
     * @param y the y
     */
    public void updateSpritePosition(final double x, final double y) {
        myX = x;
        myY = y;
        mySprite.setTranslateX(x);
        mySprite.setTranslateY(y);
    }

    /**
     * Start animation.
     *
     * @param animationPaths the animation paths
     */
    public void startAnimation(final String[] animationPaths) {
        myIsAnimating = true;
        animate(animationPaths);
    }

    /**
     * Stop animation.
     */
    public void stopAnimation() {
        myIsAnimating = false;
    }

    private void animate(final String[] theAnimationPaths) {
        if (myIsAnimating) {
            myCurrentFrameIndex = (myCurrentFrameIndex + 1) % theAnimationPaths.length;
            Image nextFrame = new Image(theAnimationPaths[myCurrentFrameIndex]);
            mySprite.setImage(nextFrame);
            Duration frameDuration = Duration.millis(100);
            KeyFrame keyFrame = new KeyFrame(frameDuration, event -> animate(theAnimationPaths));
            Timeline timeline = new Timeline(keyFrame);
            timeline.play();
        }
    }
}