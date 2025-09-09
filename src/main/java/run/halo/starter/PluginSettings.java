package run.halo.starter;

/**
 * Class to hold plugin settings.
 */
public class PluginSettings {
    private String watermarkType;
    private String textContent;
    private String textColor;
    private int textSize;
    private String textPosition;
    private String imagePath;
    private float imageTransparency;
    private int imageSize;
    private String imagePosition;
    private float webpQuality;
    private boolean webpCompression;
    private boolean convertHeaderImage;

    // Getters and setters
    public String getWatermarkType() { return watermarkType; }
    public void setWatermarkType(String watermarkType) { this.watermarkType = watermarkType; }

    public String getTextContent() { return textContent; }
    public void setTextContent(String textContent) { this.textContent = textContent; }

    public String getTextColor() { return textColor; }
    public void setTextColor(String textColor) { this.textColor = textColor; }

    public int getTextSize() { return textSize; }
    public void setTextSize(int textSize) { this.textSize = textSize; }

    public String getTextPosition() { return textPosition; }
    public void setTextPosition(String textPosition) { this.textPosition = textPosition; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public float getImageTransparency() { return imageTransparency; }
    public void setImageTransparency(float imageTransparency) { this.imageTransparency = imageTransparency; }

    public int getImageSize() { return imageSize; }
    public void setImageSize(int imageSize) { this.imageSize = imageSize; }

    public String getImagePosition() { return imagePosition; }
    public void setImagePosition(String imagePosition) { this.imagePosition = imagePosition; }

    public float getWebpQuality() { return webpQuality; }
    public void setWebpQuality(float webpQuality) { this.webpQuality = webpQuality; }

    public boolean isWebpCompression() { return webpCompression; }
    public void setWebpCompression(boolean webpCompression) { this.webpCompression = webpCompression; }

    public boolean isConvertHeaderImage() { return convertHeaderImage; }
    public void setConvertHeaderImage(boolean convertHeaderImage) { this.convertHeaderImage = convertHeaderImage; }
}
