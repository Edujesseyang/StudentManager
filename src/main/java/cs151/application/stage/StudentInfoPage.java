package cs151.application.stage;

import cs151.application.model.Student;
import cs151.application.model.StudentList;
import cs151.application.tools.Tools;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Flow;


public class StudentInfoPage extends Stage {
    private static final StudentList studentDB = StudentList.getInstance();  // singleton db

    private final Student displayingStudent;
    Tools tool = new Tools();

    public StudentInfoPage(Student displaying) {
        this.displayingStudent = displaying;
        // title
        Label title = new Label("Information of Student");

        // basic info area
        HBox name = makeLine("Full name: ", displaying.getName());
        HBox edu = makeLine("Academic Status: ", displaying.getAcademicStatus());
        HBox employment = makeLine("Is student employed: ", displaying.isEmployed() ? "Yes" : "No");
        HBox job = makeLine("Employment details: ", (Objects.equals(displaying.getJobDetails(), "") ? "N/A" : displaying.getJobDetails()));
        HBox preferRole = makeLine("Preferred Professional Role: ", Objects.equals(displaying.getPreferredRole(), "") ? "N/A" : displaying.getPreferredRole());

        // language area
        Label lang = new Label("Student skilled in: ");
        Button langBtn = new Button("Programming language list");
        langBtn.setOnAction(e -> langBtnAct());
        Button dbBtn = new Button("Database skill list");
        dbBtn.setOnAction(e -> dbBtnAct());
        HBox langLayout = new HBox(lang, langBtn, dbBtn);
        langLayout.setSpacing(15);
        VBox commentAreaBox = new VBox();
        // comment area
        Label commentTitle = new Label("Comments: ");
        int counter = 1;
        for (String comment : displaying.getComments()) {
            TextArea text = new TextArea("Comment " + counter + ": \n" + comment + "\n");
            text.setEditable(false);
            text.setMaxWidth(498);
            text.setMinWidth(498);
            text.setMaxHeight(100);
            text.getStyleClass().add("showText");
            commentAreaBox.getChildren().add(text);
            counter++;
        }
        ScrollPane commentArea = new ScrollPane(commentAreaBox);
        VBox commentBox = new VBox(commentTitle, commentArea);
        commentArea.setMaxWidth(500);
        commentArea.setMinWidth(500);
        commentArea.setMaxHeight(400);
        commentArea.setMinHeight(400);


        // btn area
        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(e -> closeBtnAct());

        Button addCommentBtn = new Button("Add New Comment");
        addCommentBtn.setOnAction(e -> addCommentAct());
        HBox btnLayout = new HBox(addCommentBtn, closeBtn);
        btnLayout.setAlignment(Pos.CENTER);
        btnLayout.getStyleClass().add("buttonLayout");

        VBox section = new VBox(name, edu, employment, job, preferRole, langLayout, commentBox);
        section.getStyleClass().add("sectionLayout");
        section.setSpacing(20);

        VBox pageLayout = new VBox(title, section, btnLayout);
        Scene pageScene = new Scene(pageLayout, 800, 900);
        tool.setPageStyle(pageScene);
        this.setTitle("Student Information");
        this.setScene(pageScene);
    }

    private HBox makeLine(String text, String data) {
        Label name = new Label(text);
        Text nameInfo = new Text(data);
        return new HBox(name, nameInfo);
    }

    private void langBtnAct() {
        Stage showPage = new ListDisplay(displayingStudent.getProgrammingLanguages(), "    Student's Programming Languages:");
        showPage.show();
    }

    private void dbBtnAct() {
        Stage showPage = new ListDisplay(displayingStudent.getDatabases(), "    Student's database skills: ");
        showPage.show();
    }

    private void addCommentAct() {
        AddCommentPage addNewCommentPage = new AddCommentPage(displayingStudent);
        addNewCommentPage.show();
        studentDB.save();
        this.close();
    }

    private void closeBtnAct() {
        studentDB.save();
        this.close();
    }
}
