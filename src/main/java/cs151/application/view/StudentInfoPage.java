package cs151.application.view;

import cs151.application.controller.StudentInfoPageController;
import cs151.application.model.Student;
import cs151.application.services.ViewUtility;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;

public class StudentInfoPage extends Stage {

    private final StudentInfoPageController controller;
    private final Student displaying;

    public StudentInfoPage(Student displaying, List<String> prevShowingStdList, String prevPageTile) {
        this.controller = new StudentInfoPageController(this, displaying, prevShowingStdList, prevPageTile);
        this.displaying = displaying;

        Scene pageScene = buildScene();
        ViewUtility tool = new ViewUtility();
        tool.setPageStyle(pageScene);
        this.setTitle("Student Information");
        this.setScene(pageScene);
    }

    private Scene buildScene(){
        Label title = new Label("Information of Student");
        title.getStyleClass().add("subtitle");
        HBox btnLayout = buildButtons();
        VBox section = buildSection(displaying);
        VBox pageLayout = new VBox(title, section, btnLayout);
        return new Scene(pageLayout, 800, 900);
    }

    private VBox buildSection(Student displaying) {
        // basic info area
        HBox name = makeLine("Full name: ", displaying.getName());
        HBox edu = makeLine("Academic Status: ", displaying.getAcademicStatus());
        HBox employment = makeLine("Is student employed: ", displaying.isEmployed() ? "Yes" : "No");
        HBox job = makeLine("Employment details: ", (Objects.equals(displaying.getJobDetails(), "") ? "N/A" : displaying.getJobDetails()));
        HBox preferRole = makeLine("Preferred Professional Role: ", Objects.equals(displaying.getPreferredRole(), "") ? "N/A" : displaying.getPreferredRole());
        HBox blackList = makeLine("Student is in ", displaying.isBlacklist() ? "Black List" : "White List");
        // language area
        HBox langLayout = buildLanguageArea();

        // comment area
        VBox commentBox = buildCommentArea(displaying);
        VBox section = new VBox(name, edu, employment, job, preferRole, langLayout, blackList, commentBox);
        section.getStyleClass().add("sectionLayout");
        section.setSpacing(20);
        return section;
    }

    private HBox makeLine(String text, String data) {
        Label name = new Label(text);
        Text nameInfo = new Text(data);
        return new HBox(name, nameInfo);
    }

    private HBox buildLanguageArea() {
        Label lang = new Label("Student skilled in: ");
        Button langBtn = new Button("Programming language list");
        langBtn.setOnAction(e -> controller.langBtnAct());
        Button dbBtn = new Button("Database skill list");
        dbBtn.setOnAction(e -> controller.dbBtnAct());
        HBox langLayout = new HBox(lang, langBtn, dbBtn);
        langLayout.setSpacing(15);
        return langLayout;
    }

    private VBox buildCommentArea(Student displaying) {
        VBox commentAreaBox = new VBox();
        commentAreaBox.setSpacing(5);
        commentAreaBox.setAlignment(Pos.CENTER);
        Label commentTitle = new Label("Comments: ");
        int counter = 1;
        for (String comment : displaying.getComments()) {
            TextArea text = new TextArea("Comment " + counter + ":  " + comment + "\n");
            text.setEditable(false);
            text.setMaxWidth(483);
            text.setMinWidth(483);
            text.setMaxHeight(100);
            text.getStyleClass().add("showText");
            commentAreaBox.getChildren().add(text);
            counter++;
        }
        ScrollPane commentArea = new ScrollPane(commentAreaBox);
        commentArea.getStyleClass().add("showText");
        VBox commentBox = new VBox(commentTitle, commentArea);
        commentArea.setMaxWidth(500);
        commentArea.setMinWidth(500);
        commentArea.setMaxHeight(400);
        commentArea.setMinHeight(400);
        return commentBox;
    }

    private HBox buildButtons() {
        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(e -> controller.closeBtnAct());
        Button editStudentBtn = new Button("Edit");
        editStudentBtn.setOnAction(e -> controller.editStudentBtnAct());
        HBox btnLayout = new HBox(editStudentBtn, closeBtn);
        btnLayout.setAlignment(Pos.CENTER);
        btnLayout.getStyleClass().add("buttonLayout");
        return btnLayout;
    }
}
