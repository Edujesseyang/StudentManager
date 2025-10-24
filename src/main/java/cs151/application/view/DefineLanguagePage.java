package cs151.application.view;


import cs151.application.controller.DefineLanguagePageController;
import cs151.application.services.DataAccessor;
import cs151.application.services.Tools;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;

public class DefineLanguagePage extends Stage {
    private final DefineLanguagePageController controller = new DefineLanguagePageController(this);
    TextField programLanguageName;

    public DefineLanguagePage() {
        Scene pageScene = loadPage();
        this.setTitle("Define Programming Language");
        this.setScene(pageScene);
    }

    public Scene loadPage() {
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
        List<String> languageList = controller.getLangList();
        for (String lang : languageList) allLang.appendText(lang + "   ");
        VBox showList = new VBox(allLang);

        // create buttons
        HBox btnLayout = buildButtons();

        // input area layout
        VBox inputLayout = new VBox(infoLabel, showList, programLanguageName);
        inputLayout.getStyleClass().add("sectionLayout");

        // layout page
        VBox pageLayout = new VBox(inputLayout, btnLayout);
        pageLayout.setPadding(new Insets(25));
        BorderPane root = new BorderPane(pageLayout);

        // return a scene
        Scene scene = new Scene(root, 600, 500);
        Tools tool = new Tools();
        tool.setPageStyle(scene);
        return scene;
    }

    private HBox buildButtons() {
        Button add = new Button("Add"); // 1
        add.setOnAction(e -> controller.addAction(programLanguageName));
        Button delete = new Button("Delete"); // 2
        delete.setOnAction(e -> controller.deleteAction(programLanguageName));
        Button clear = new Button("Clear"); // 3
        clear.setOnAction(e -> controller.clearAction(programLanguageName));
        Button back = new Button("Back"); // 4
        back.setOnAction(e -> controller.cancelAction());
        // layout buttons
        HBox btnLayout = new HBox(add, delete, clear, back);
        btnLayout.getStyleClass().add("buttonLayout");
        btnLayout.setAlignment(Pos.CENTER);
        return btnLayout;
    }

}
