package cs151.application.view;

import cs151.application.controller.ReportPageController;
import cs151.application.services.ViewUtility;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReportPage extends Stage implements View {
    private final ReportPageController controller = new ReportPageController();

    public ReportPage() {
        Scene sc = buildScene();
        ViewUtility tool = new ViewUtility();
        tool.setPageStyle(sc);
        this.setScene(sc);
    }

    public Scene buildScene() {
        Label title = new Label("Select Report Type:");

        Button blackListBtn = new Button("Get all student in black list");
        blackListBtn.setOnAction(e -> controller.showBlackList());

        Button whiteListBtn = new Button("Get all students in white list");
        whiteListBtn.setOnAction(e -> controller.showWhiteList());

        VBox layout = new VBox(title, blackListBtn, whiteListBtn);

        layout.getStyleClass().add("sectionLayout");
        return new Scene(layout, 300, 200);
    }
}
