package cs151.application.tools;

import javafx.scene.Scene;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Tools {
    private final Path languageListFilePath = Paths.get("localData", "languages.txt");

    public Tools() {

    }

    public Alert popAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.setHeaderText(type.name());
        alert.setTitle("Attention");
        return alert;
    }

    public List<String> loadLanguageList() {
        List<String> res;
        try {
            ensureFileExists(languageListFilePath);
            res = Files.readAllLines(languageListFilePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to load file: " + languageListFilePath, e);
        }
        return res;
    }

    public void writeLanguageList(List<String> list) {
        try {
            Files.write(languageListFilePath, list,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING,
                    StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write file: " + languageListFilePath, e);
        }
    }

    private void ensureFileExists(Path path) throws IOException {
        if (Files.notExists(path)) {
            Files.createFile(path);
        }
    }

    public void setPageStyle(Scene scene) {
        String stylePath = "/style/homePage.css";
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(stylePath)).toExternalForm());
    }
}
