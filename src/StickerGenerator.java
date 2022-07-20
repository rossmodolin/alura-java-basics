import java.awt.*;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class StickerGenerator {

    public void create(InputStream inputStream, String filename, int rating) throws Exception {

        // Read image
        BufferedImage originalImage = ImageIO.read(inputStream);

        // Create new local resized image with transparent background
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        double ratio = (double) height/width;
        int smallHeight = heightCalculator(400, ratio);
        final Image scaled = originalImage.getScaledInstance(400, smallHeight, Image.SCALE_SMOOTH);

        // Create output image
        BufferedImage outputImage = new BufferedImage(400, smallHeight + 100, BufferedImage.TRANSLUCENT);

        // Scale the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(scaled, 0, 0, 400, smallHeight, null);

        // // Set font
        final Font font = new Font(Font.SANS_SERIF, Font.BOLD, 36);
        g2d.setColor(Color.YELLOW);
        g2d.setFont(font);

        // // Write a phrase in the new image
        g2d.drawString("Rating: " + rating + starGenerator(rating), 50, smallHeight + 50);
        g2d.dispose();

        // Write new image to a file
        ImageIO.write(outputImage, "png", new File(filename));

    }

    private String starGenerator(int desired) {
        String stars = "";
        int totalStars = desired / 2;
        for (int i = 0; i < totalStars; i++) {
            stars += "⭐";
        }

        return stars;
    }

    private int heightCalculator(int width, double ratio) {
        double height = ratio * width;
        return (int) height;
    }
    
}
