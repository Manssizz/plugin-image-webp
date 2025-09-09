package run.halo.starter;

import com.github.gotson.webp.WebP;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Service for processing images: converting to WebP and adding watermarks.
 */
@Service
public class ImageProcessor {

    /**
     * Processes an image: adds watermark and converts to WebP.
     *
     * @param inputImagePath Path to the input image
     * @param outputImagePath Path to save the processed image
     * @param settings Plugin settings
     * @throws IOException If processing fails
     */
    public void processImage(String inputImagePath, String outputImagePath, PluginSettings settings) throws IOException {
        BufferedImage originalImage = ImageIO.read(new File(inputImagePath));
        BufferedImage processedImage = originalImage;

        // Add watermark
        if ("text".equals(settings.getWatermarkType())) {
            processedImage = addTextWatermark(originalImage, settings);
        } else if ("image".equals(settings.getWatermarkType())) {
            processedImage = addImageWatermark(originalImage, settings);
        }

        // Convert to WebP
        saveAsWebP(processedImage, outputImagePath, settings.getWebpQuality());
    }

    private BufferedImage addTextWatermark(BufferedImage image, PluginSettings settings) {
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(new Font("Arial", Font.BOLD, settings.getTextSize()));
        g2d.setColor(Color.decode(settings.getTextColor()));

        FontMetrics fm = g2d.getFontMetrics();
        int x = calculateX(settings.getTextPosition(), image.getWidth(), fm.stringWidth(settings.getTextContent()));
        int y = calculateY(settings.getTextPosition(), image.getHeight(), fm.getHeight());

        g2d.drawString(settings.getTextContent(), x, y);
        g2d.dispose();
        return image;
    }

    private BufferedImage addImageWatermark(BufferedImage image, PluginSettings settings) throws IOException {
        BufferedImage watermark = ImageIO.read(new File(settings.getImagePath()));
        // Resize watermark
        int watermarkWidth = (int) (image.getWidth() * settings.getImageSize() / 100.0);
        int watermarkHeight = (int) (watermark.getHeight() * (double) watermarkWidth / watermark.getWidth());
        watermark = Thumbnails.of(watermark).size(watermarkWidth, watermarkHeight).asBufferedImage();

        // Make transparent
        BufferedImage transparentWatermark = new BufferedImage(watermark.getWidth(), watermark.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = transparentWatermark.createGraphics();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, settings.getImageTransparency()));
        g2d.drawImage(watermark, 0, 0, null);
        g2d.dispose();

        // Position
        Positions position = getPosition(settings.getImagePosition());

        return Thumbnails.of(image).watermark(position, transparentWatermark, 1.0f).asBufferedImage();
    }

    private void saveAsWebP(BufferedImage image, String outputPath, float quality) throws IOException {
        // Use WebP writer with quality
        WebP.write(image, new File(outputPath), quality);
    }

    private int calculateX(String position, int imageWidth, int textWidth) {
        switch (position) {
            case "top-left":
            case "bottom-left":
                return 10;
            case "top-right":
            case "bottom-right":
                return imageWidth - textWidth - 10;
            case "center":
                return (imageWidth - textWidth) / 2;
            default:
                return 10;
        }
    }

    private int calculateY(String position, int imageHeight, int textHeight) {
        switch (position) {
            case "top-left":
            case "top-right":
                return textHeight + 10;
            case "bottom-left":
            case "bottom-right":
                return imageHeight - 10;
            case "center":
                return (imageHeight + textHeight) / 2;
            default:
                return textHeight + 10;
        }
    }

    private Positions getPosition(String position) {
        switch (position) {
            case "top-left":
                return Positions.TOP_LEFT;
            case "top-right":
                return Positions.TOP_RIGHT;
            case "bottom-left":
                return Positions.BOTTOM_LEFT;
            case "bottom-right":
                return Positions.BOTTOM_RIGHT;
            case "center":
                return Positions.CENTER;
            default:
                return Positions.BOTTOM_RIGHT;
        }
    }
}
