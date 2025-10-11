package cs151.application.stage;

import cs151.application.model.Student;
import cs151.application.model.StudentList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class StudentInfoPage extends Stage {
    private final Student displaying;

    public StudentInfoPage(Student displaying) {
        this.displaying = displaying;
        Label title = new Label("    Information of Student  ");                // item 1
        // =============== here is a separator ===============                       // item 2

        Label name = new Label("Full name: ");
        Text nameInfo = new Text(displaying.getName());
        HBox nameLayout = new HBox(name, nameInfo);                                  // item 3

        Label eduStatus = new Label("Academic Status: ");
        Text eduStatusInfo = new Text(displaying.getAcademicStatus());
        HBox eduStatusLayout = new HBox(eduStatus, eduStatusInfo);                   // item 4

        Label employment = new Label("Is student employed: ");
        String isEmployStr = displaying.isEmployed() ? "Yes" : "No";
        Text employmentInfo = new Text(isEmployStr);
        HBox employmentLayout = new HBox(employment, employmentInfo);                // item 5

        Label job = new Label("Employment details: ");
        Text jobInfo = new Text(displaying.getJobDetails());
        HBox jobLayout = new HBox(job, jobInfo);                                    // item 6

        Label preferredJob = new Label("Preferred Professional Role: ");
        Text preferredJobInfo = new Text(displaying.getPreferredRole());
        HBox preferredJobLayout = new HBox(preferredJob, preferredJobInfo);         // item 7

        Label lang = new Label("Student skilled in: ");
        Button langBtn = new Button("Programming language list");
        langBtn.setOnAction(e -> langBtnAct());
        Button dbBtn = new Button("Database skill list");
        dbBtn.setOnAction(e -> dbBtnAct());
        HBox langLayout = new HBox(lang, langBtn, dbBtn);                          // item 8
        langLayout.setSpacing(15);

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(e -> closeBtnAct());

        VBox section = new VBox(title, new Separator(), nameLayout, eduStatusLayout, employmentLayout,preferredJobLayout, jobLayout, langLayout);
        section.getStyleClass().add("sectionLayout");
        VBox pageLayout = new VBox(section,closeBtn);
        pageLayout.getStyleClass().add("pageLayout");


        Scene pageScene = new Scene(pageLayout, 700, 400);
        pageScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style/homePage.css")).toExternalForm());
        this.setTitle("Student Information");
        this.setScene(pageScene);
    }

    private void langBtnAct() {
        List<String> stringPackage = new ArrayList<>();
        stringPackage.add("    Student's Programming Languages:");
        stringPackage.add(displaying.getLanguageList_String());
        Stage showPage = new ListDisplay(stringPackage);
        showPage.show();
    }

    private void dbBtnAct() {
        // later for database page
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Working on it ... ");
        alert.setHeaderText("Be patience");
        alert.setContentText("This feature is implementing ...");
        alert.showAndWait();
    }

    private void closeBtnAct() {
        this.close();
    }
}
