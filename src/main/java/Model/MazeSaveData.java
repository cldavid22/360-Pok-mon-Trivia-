package Model;

import java.io.*;

/**
 * The type Maze save data.
 */
public record MazeSaveData(int[][] theMaze, int theSpriteX, int theSpriteY, Question[] theQuestions, double theCameraH,
                           double theCameraV) implements Serializable {

    /**
     * Load from file maze save data.
     *
     * @param fileName the file name
     * @return the maze save data
     */
    public static MazeSaveData loadFromFile(final String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (MazeSaveData) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Save to file.
     *
     * @param filename the filename
     */
    public void saveToFile(final String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
