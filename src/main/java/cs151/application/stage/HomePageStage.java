package cs151.application.stage;

import cs151.application.services.Tools;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomePageStage extends Stage {
    Button definePageBtn;
    Button displayBtn;
    Button defineStudentBtn;
    Label welcomeText;
    Tools tool = new Tools();

    public HomePageStage() {
        ensureDefaultLang(); // preload default languages

        // create button and button action
        definePageBtn = new Button("Define programming languages");
        definePageBtn.setOnAction(e -> defineBtnAct());

        defineStudentBtn = new Button("Define student");
        defineStudentBtn.setOnAction(e -> defineStudentAct());

        // create button for display students
        displayBtn = new Button("Show all students");
        displayBtn.setOnAction(e -> displayBtnAct());

        // create welcome label
        welcomeText = new Label(" Welcome Back ");
        welcomeText.getStyleClass().add("title");

        // set page layout
        VBox btnLayout = new VBox(definePageBtn, defineStudentBtn, displayBtn);
        btnLayout.getStyleClass().add("buttonLayout");
        btnLayout.setAlignment(Pos.CENTER);

        VBox pageLayout = new VBox(welcomeText, btnLayout);
        pageLayout.setSpacing(30);

        // set scene
        BorderPane root = new BorderPane(pageLayout);
        Scene pageScene = new Scene(root, 800, 600);
        tool.setPageStyle(pageScene);

        this.setTitle("Home Page");
        this.setScene(pageScene);
    }

    /**
     * Create a define page stage and show it
     */
    private void defineBtnAct() {
        DefineLanguagePage definePage = new DefineLanguagePage();
        definePage.show();
    }

    /**
     * create students list show stage and show it
     */
    private void displayBtnAct() {
        StudentsListPage showPage = new StudentsListPage();
        showPage.show();
    }

    private void defineStudentAct() {
        DefineStudentPage showPage = new DefineStudentPage();
        showPage.show();
    }

    private void ensureDefaultLang() {
        List<String> lang = tool.loadLanguageList();
        List<String> defaultLang = new ArrayList<>(Arrays.asList("Java", "C++", "Python", "Javascript", "Rust"));
        for (String s : defaultLang) {
            if (lang.size() >= 3) {
                break;
            }
            lang.add(s);
        }
        tool.writeLanguageList(lang); // ensure there are 3 languages
    }
}
