package cs151.application.view;

import cs151.application.controller.ReportPageController;
import cs151.application.services.ViewUtility;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReportPage extends Stage {
    private final ViewUtility tool = new ViewUtility();
    private final ReportPageController controller = new ReportPageController();

    public ReportPage() {
        Label title = new Label("Select Report Type:");

        Button blackListBtn = new Button("Get all student in black list");
        blackListBtn.setOnAction(e -> controller.showBlackList());

        Button whiteListBtn = new Button("Get all students in white list");
        whiteListBtn.setOnAction(e -> controller.showWhiteList());

        VBox layout = new VBox(title, blackListBtn, whiteListBtn);

        layout.getStyleClass().add("sectionLayout");
        Scene sc = new Scene(layout, 300, 200);
        tool.setPageStyle(sc);
        this.setScene(sc);
    }
}
