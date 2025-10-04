package cs151.application.stage;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePageStage extends Stage {
    Button definePageBtn;
    Button displayBtn;
    Label welcomeText;

    public HomePageStage(int height, int width, String title) {
        // create button and button action
        definePageBtn = new Button("Go to Define Page");
        definePageBtn.setOnAction(e -> defineBtnAct());

        // create button for display students
        displayBtn = new Button("Show all students");
        displayBtn.setOnAction(e -> displayBtnAct());

        // create welcome label
        welcomeText = new Label("Welcome to use our application");

        // handle layout
        VBox layout = new VBox(20);
        layout.getChildren().addAll(welcomeText, definePageBtn, displayBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(50);


        // set scene
        Scene homePageScene = new Scene(layout, height, width);
        this.setTitle(title);
        this.setScene(homePageScene);
    }

    /**
     * Create a define page stage and show it
     */
    private void defineBtnAct() {
        DefinePageStage definePage = new DefinePageStage(400, 250, "Define coding language");
        definePage.show();
    }

    /**
     * create students list show stage and show it
     */
    private void displayBtnAct() {
        StudentsShowPage showPage = new StudentsShowPage(700, 300, "All Students");
        showPage.show();
    }
}
