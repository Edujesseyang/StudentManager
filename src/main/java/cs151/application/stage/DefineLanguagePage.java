package cs151.application.stage;


import cs151.application.tools.Tools;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;

public class DefineLanguagePage extends Stage {
    private TextField programLanguageName;
    private List<String> languageList;

    Tools tool = new Tools();

    public DefineLanguagePage() {
        Scene pageScene = loadPage();
        this.setTitle("Define Programming Language");
        this.setScene(pageScene);
    }

    private Scene loadPage() {
        languageList = tool.loadLanguageList();

        // create label
        Label infoLabel = new Label("   Define a Student Programming Language   ");

        // create info input box
        programLanguageName = new TextField();
        programLanguageName.setPromptText("Enter programming languages name");
        // showing area to show all languages
        TextArea allLang = new TextArea("Language List:\n");
        allLang.setEditable(false);
        allLang.getStyleClass().add("showText");
        allLang.setWrapText(true);
        allLang.setMaxWidth(400);
        allLang.setMinWidth(400);
        for (String lang : languageList) allLang.appendText(lang + "   ");
        VBox showList = new VBox(allLang);

        // create buttons
        Button add = new Button("Add"); // 1
        add.setOnAction(e -> addAction());
        Button delete = new Button("Delete"); // 2
        delete.setOnAction(e -> deleteAction());
        Button clear = new Button("Clear"); // 3
        clear.setOnAction(e -> clearAction());
        Button back = new Button("Back"); // 4
        back.setOnAction(e -> cancelAction());
        // layout buttons
        HBox btnLayout = new HBox(add, delete, clear, back);
        btnLayout.getStyleClass().add("buttonLayout");
        btnLayout.setAlignment(Pos.CENTER);

        // input area layout
        VBox inputLayout = new VBox(infoLabel, showList, programLanguageName);
        inputLayout.getStyleClass().add("sectionLayout");

        // layout page
        VBox pageLayout = new VBox(inputLayout, btnLayout);
        pageLayout.setPadding(new Insets(25));
        BorderPane root = new BorderPane(pageLayout);

        // return a scene
        Scene scene = new Scene(root, 600, 500);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style/homePage.css")).toExternalForm());
        return scene;
    }

    private void addAction() {
        if (programLanguageName.getText().isBlank()) { // pop alert for blank nane
            tool.popAlert(Alert.AlertType.ERROR, "Input can not be empty!").showAndWait();
        } else { // pop alert for confirm
            Alert confirm = tool.popAlert(Alert.AlertType.CONFIRMATION, "Are you sure to submit?");
            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    if (languageList.contains(programLanguageName.getText())) {
                        tool.popAlert(Alert.AlertType.ERROR, "Language has already defined!").showAndWait();
                        return;
                    }
                    languageList.add(programLanguageName.getText());
                }
            });
        }
        tool.writeLanguageList(languageList);
        this.close();
        Scene newScene = loadPage();
        this.setScene(newScene);
        this.show();
    }

    private void deleteAction() {
        if (!languageList.contains(programLanguageName.getText())) {
            tool.popAlert(Alert.AlertType.ERROR, "Can not find the language").showAndWait();
            return;
        }
        Alert confirm = tool.popAlert(Alert.AlertType.CONFIRMATION, "Found the language! Are you sure you want to delete it?");
        confirm.showAndWait().ifPresent(response -> {
            languageList.remove(programLanguageName.getText());
            tool.popAlert(Alert.AlertType.INFORMATION, "Delete successful").showAndWait();
        });

        tool.writeLanguageList(languageList);
        this.close();
        Scene newScene = loadPage();
        this.setScene(newScene);
        this.show();
    }

    private void clearAction() {
        programLanguageName.clear();
    }

    private void cancelAction() {
        this.close();
    }
}
