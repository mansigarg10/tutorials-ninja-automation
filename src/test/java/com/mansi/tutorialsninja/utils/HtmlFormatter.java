package com.mansi.tutorialsninja.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class HtmlFormatter {

    private static final Logger LOG = LogManager.getLogger(HtmlFormatter.class);

    /** Removes leading empty lines from the specified HTML file.
     *
     * @param htmlPath The path to the HTML file to be formatted.
     * @throws IOException If an error occurs while reading or writing the file.
     */
    public static void removeLeadingEmptyLines(Path htmlPath) throws IOException {
        List<String> lines = Files.readAllLines(htmlPath);
        while (!lines.isEmpty() && lines.get(0).trim().isEmpty()) {
            lines.remove(0);
        }
        Files.write(htmlPath, lines);
        LOG.info("Leading empty lines removed from HTML file: {}", htmlPath);
    }

    /** Sanitizes image paths in the specified HTML file by replacing a specific path pattern.
     *
     * @param htmlPath The path to the HTML file to be sanitized.
     * @throws IOException If an error occurs while reading or writing the file.
     */
    public static void sanitizeImagePaths(Path htmlPath) throws IOException {
        List<String> updatedLines = new ArrayList<>();
        List<String> lines = Files.readAllLines(htmlPath);
        for (String line : lines) {
            String cleanedLine = line.replace(TutorialsNinjaTestConstants.SCREENSHOT_CAPTURE_PATH,
                    TutorialsNinjaTestConstants.SCREENSHOT_REPORT_PATH);
            updatedLines.add(cleanedLine);
        }
        Files.write(htmlPath, updatedLines);
        LOG.info("Image paths sanitized in HTML file: {}", htmlPath);
    }

}
