package cs151.application.stage;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePageStage extends Stage {

    public HomePageStage(int height, int width, String title) {
        // create button and button action
        Button btn = new Button("Go to Define Page");
        btn.setOnAction(e -> btnActionOnClick());

        // create welcome label
        Label welcomeText = new Label("Welcome to use our application");

        // handle layout
        VBox layout = new VBox(20);
        layout.getChildren().addAll(welcomeText, btn);
        layout.setAlignment(Pos.CENTER);

        // set scene
        Scene homePageScene = new Scene(layout, height, width);
        this.setTitle(title);
        this.setScene(homePageScene);
    }

    /**
     * Create a new stage and show it
     */
    private void btnActionOnClick() {
        DefinePageStage definePage = new DefinePageStage(400, 200, "Define coding language");
        definePage.show();
    }
}
