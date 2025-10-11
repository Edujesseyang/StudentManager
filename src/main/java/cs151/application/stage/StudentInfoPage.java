package cs151.application.stage;

import cs151.application.model.Student;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
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
        Label title = new Label("    Information of Student  ");

        HBox name = makeLine("Full name: ",displaying.getName() ) ;
        HBox edu = makeLine("Academic Status: ",displaying.getAcademicStatus() );

        String isEmployStr = displaying.isEmployed() ? "Yes" : "No";
        HBox employment = makeLine("Is student employed: ", isEmployStr);
        HBox job = makeLine("Employment details: ",displaying.getJobDetails());
        HBox preferRole = makeLine("Preferred Professional Role: ",displaying.getPreferredRole());

        Label lang = new Label("Student skilled in: ");

        Button langBtn = new Button("Programming language list");
        langBtn.setOnAction(e -> langBtnAct());
        Button dbBtn = new Button("Database skill list");
        dbBtn.setOnAction(e -> dbBtnAct());

        HBox langLayout = new HBox(lang, langBtn, dbBtn);
        langLayout.setSpacing(15);

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(e -> closeBtnAct());

        VBox section = new VBox(title, new Separator(), name, edu, employment,preferRole, job, langLayout);
        section.getStyleClass().add("sectionLayout");
        VBox pageLayout = new VBox(section,closeBtn);
        pageLayout.getStyleClass().add("pageLayout");

        Scene pageScene = new Scene(pageLayout, 700, 400);
        pageScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style/homePage.css")).toExternalForm());
        this.setTitle("Student Information");
        this.setScene(pageScene);
    }

    private HBox makeLine(String text, String data) {
        Label name = new Label(text);
        Text nameInfo = new Text(data);
        return new HBox(name, nameInfo);
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
