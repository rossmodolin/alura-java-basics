import java.awt.*;

public interface StickerGenerator {
    /**
     * Creates stickers from resized images and puts a short text below them.
     * @param g2d image where input image will be written on
     * @param scaled resized input image
     * @param width width of g2d
     * @param height height of g2d 
     * @param phrase message printed at the bottom of image
     */
    void generate(Graphics2D g2d, Image scaled, int width, int height, String phrase);
  
}
