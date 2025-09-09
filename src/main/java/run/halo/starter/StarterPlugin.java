package run.halo.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import run.halo.app.plugin.BasePlugin;
import run.halo.app.plugin.PluginContext;

/**
 * <p>Plugin main class to manage the lifecycle of the plugin.</p>
 * <p>This class must be public and have a public constructor.</p>
 * <p>Only one main class extending {@link BasePlugin} is allowed per plugin.</p>
 *
 * @author guqing
 * @since 1.0.0
 */
@Component
public class StarterPlugin extends BasePlugin {

    @Autowired
    private ImageProcessor imageProcessor;

    public StarterPlugin(PluginContext pluginContext) {
        super(pluginContext);
    }

    @Override
    public void start() {
        System.out.println("Image WebP Converter Plugin started!");
        // Register event listener for image uploads
        // Note: In a real implementation, hook into Halo's attachment upload events
        // For now, placeholder for integration
    }

    @Override
    public void stop() {
        System.out.println("Image WebP Converter Plugin stopped!");
    }

    /**
     * Method to process uploaded image (to be called from event listener).
     *
     * @param inputPath Path to uploaded image
     * @param outputPath Path to save processed image
     */
    public void processUploadedImage(String inputPath, String outputPath) {
        try {
            PluginSettings settings = loadSettings(); // Load settings from config
            if (shouldProcessImage(inputPath, settings)) {
                imageProcessor.processImage(inputPath, outputPath, settings);
            }
        } catch (Exception e) {
            System.err.println("Error processing image: " + e.getMessage());
        }
    }

    private PluginSettings loadSettings() {
        // Placeholder: Load settings from Halo's config system
        // In real implementation, use @Value or ConfigMap
        PluginSettings settings = new PluginSettings();
        settings.setWatermarkType("text");
        settings.setTextContent("Sample Watermark");
        settings.setTextColor("#ffffff");
        settings.setTextSize(20);
        settings.setTextPosition("bottom-right");
        settings.setWebpQuality(0.8f);
        settings.setWebpCompression(true);
        settings.setConvertHeaderImage(true);
        return settings;
    }

    private boolean shouldProcessImage(String path, PluginSettings settings) {
        // Check if it's an image and if header image conversion is enabled
        // Placeholder logic
        return path.endsWith(".jpg") || path.endsWith(".png") || path.endsWith(".jpeg");
    }
}
