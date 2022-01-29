package fi.utu.tech.gui.javafx;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;

/**
 * This is a black box that can be used to get demo images
 */

public class ImageBlackBox {

    private int cursor = 0;
    private final int width = 300;
    private final int height = 200;

    private Image generateImage(int seed) {
        Random pixelGenerator = new Random(seed);
        int[] buffer = new int[width*height];
        for (int i = 0; i<buffer.length; i++) {
            buffer[i] = pixelGenerator.nextInt();
        }
        WritableImage w = new WritableImage(width, height);
        w.getPixelWriter().setPixels(0, 0, width, height, PixelFormat.getIntArgbPreInstance(), buffer, 0, width);
        return w;

    }

    public int getCurrentSeed() {
        return cursor;
    }

    public Image current(){
        return generateImage(cursor);
    }

    public Image next() {
        cursor++;
        return generateImage(cursor);
    }

    public Image previous() {
        cursor--;
        return generateImage(cursor);

    }
    
}
