package cs151.application.stage;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Objects;

public class HomePageStage extends Stage {
    Button definePageBtn;
    Button displayBtn;
    Button displayLanguageBtn;
    Label welcomeText;

    public HomePageStage(int width, int height, String title) {
        // create button and button action
        definePageBtn = new Button("Define a student");
        definePageBtn.setOnAction(e -> defineBtnAct());

        // create button for display students
        displayBtn = new Button("Show all students");
        displayBtn.setOnAction(e -> displayBtnAct());

        // create button for display languages
        displayLanguageBtn = new Button("Show all languages");
        displayLanguageBtn.setOnAction(e -> displayLanguageBtnAct());

        // create welcome label
        welcomeText = new Label(" Welcome Back ");
        welcomeText.getStyleClass().add("title");

        // set page layout
        VBox btnLayout = new VBox();
        btnLayout.getStyleClass().add("inputLayout");
        btnLayout.getChildren().addAll(definePageBtn,displayBtn,displayLanguageBtn);
        VBox pageLayout = new VBox(welcomeText,btnLayout);
        pageLayout.setId("pageLayout");

        // set scene
        BorderPane root = new BorderPane(pageLayout);
        root.setId("background");
        Scene pageScene = new Scene(root, width, height);
        pageScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style/homePage.css")).toExternalForm());

        this.setTitle(title);
        this.setScene(pageScene);
    }

    /**
     * Create a define page stage and show it
     */
    private void defineBtnAct() {
        DefinePageStage definePage = new DefinePageStage(600, 250, "Define coding language");
        definePage.show();
    }

    /**
     * create students list show stage and show it
     */
    private void displayBtnAct() {
        StudentsListPage showPage = new StudentsListPage( "All Students");
        showPage.show();
    }

    private void displayLanguageBtnAct() {
        LanguagesShowPage showPage = new LanguagesShowPage(500, 400, "All Languages");
        showPage.show();
    }
}
