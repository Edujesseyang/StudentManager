package cs151.application.stage;

import cs151.application.model.Student;
import cs151.application.model.StudentList;
import cs151.application.tools.Tools;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefineStudentPage extends Stage {
    private final StudentList stdDB = StudentList.getInstance();
    private Tools tool = new Tools();
    private Button saveBtn;
    private Button clearBtn;
    private Button cancelBtn;
    private TextField nameInput;
    private ComboBox<String> statusInput;
    private TextField jobInput;
    private ComboBox<String> roleInput;
    private RadioButton employed;
    private RadioButton notEmployed;

    // for language check box
    private List<String> languageList;
    private List<CheckBox> langCheckBoxes = new ArrayList<>();

    // for database check box
    private List<String> dataList = new ArrayList<>(Arrays.asList("SQLite", "MySQL", "PostgresSQL", "MongoDB", "AWS", ""));
    private List<CheckBox> dataCheckBoxes = new ArrayList<>();

    TextArea commentArea;

    public DefineStudentPage() {
        this.languageList = tool.loadLanguageList();
        Scene pageScene = buildScene();
        this.setScene(pageScene);
        this.show();
    }

    private Scene buildScene() {
        Label title = new Label("Define a student");
        title.getStyleClass().add("subtitle");
        // name line
        Label name = new Label("Name: ");
        nameInput = new TextField();
        nameInput.setPromptText("Enter student name");
        HBox nameLine = new HBox(name, nameInput);                   // line 1

        // academic status line
        Label academicStatus = new Label("Academic Status: ");
        statusInput = new ComboBox<>();
        statusInput.getItems().addAll("Freshman", "sophomore", "Junior", "Senior");
        statusInput.setPromptText("Choose academic status");
        statusInput.setVisibleRowCount(4);
        HBox academicStatusLine = new HBox(academicStatus, statusInput);      // line 2

        // employment info line
        Label employedText = new Label("Is student employed? ");
        employed = new RadioButton("Employed");
        notEmployed = new RadioButton("Not Employed");
        ToggleGroup isEmployed = new ToggleGroup();
        employed.setToggleGroup(isEmployed);
        notEmployed.setToggleGroup(isEmployed);
        notEmployed.setSelected(true);
        HBox employLine = new HBox(employedText, employed, notEmployed); // line 3

        // job line
        Label job = new Label("Job Role: ");
        jobInput = new TextField();
        jobInput.setPromptText("Enter student's job role");
        HBox jobLine = new HBox(job, jobInput);
        jobLine.disableProperty().bind(notEmployed.selectedProperty());   // line 4

        // role line
        Label role = new Label("Preferred Role: ");
        roleInput = new ComboBox<>();
        roleInput.getItems().addAll("Backend", "Frontend", "QA", "Security", "Database", "UI/UX", "DevOps");
        roleInput.setPromptText("Enter the role the student preferred");
        HBox roleLine = new HBox(role, roleInput);   // line 5

        // language select  line 6
        VBox langSelectArea = buildSelectArea(languageList, "Select skilled programming languages: ", langCheckBoxes);

        // database select box  line 7
        VBox dataSelectArea = buildSelectArea(dataList, "Select skilled database: ", dataCheckBoxes);

        // comment area line 8
        Label commentLabel = new Label("Write comment:");
        commentArea = new TextArea();
        commentArea.setPromptText("Write comment for the student");
        commentArea.setWrapText(true);
        commentArea.setPrefHeight(200);
        commentArea.setMaxWidth(400);
        VBox commentBox = new VBox(commentLabel, commentArea);

        // layout the form area
        VBox form = new VBox(nameLine, academicStatusLine, employLine, jobLine, roleLine, langSelectArea, dataSelectArea, commentBox);
        form.setSpacing(10);
        // buttons area
        saveBtn = new Button("Save");
        saveBtn.setOnAction(e -> saveAct());
        clearBtn = new Button("Clear");
        clearBtn.setOnAction(e -> clearAct());
        cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(e -> cancelAct());
        HBox btnLayout = new HBox(saveBtn, clearBtn, cancelBtn);
        btnLayout.getStyleClass().add("buttonLayout");
        btnLayout.setAlignment(Pos.CENTER);

        VBox contentLayout = new VBox(form, btnLayout);
        contentLayout.getStyleClass().add("sectionLayout");

        VBox pageLayout = new VBox(title, contentLayout);

        Scene pageScene = new Scene(pageLayout, 700, 800);
        tool.setPageStyle(pageScene);
        return pageScene;
    }

    private VBox buildSelectArea(List<String> choice, String title, List<CheckBox> boxList) {
        Label titleText = new Label(title);
        FlowPane checkArea = new FlowPane();
        checkArea.setHgap(5);
        checkArea.setVgap(5);
        VBox selectArea = new VBox(titleText, checkArea);
        checkArea.prefWrapLengthProperty().bind(selectArea.widthProperty());

        for (String lang : choice) {
            CheckBox cb = new CheckBox(lang);
            boxList.add(cb);
            checkArea.getChildren().add(cb);
        }

        return selectArea;
    }

    private void cancelAct() {
        stdDB.save();
        this.close();
    }

    private void clearAct() {
        reloadPage();
    }

    private void reloadPage() {
        this.close();
        Scene sc = buildScene();
        this.setScene(sc);
        this.show();
    }

    private void saveAct() {
        tool.popAlert(Alert.AlertType.CONFIRMATION, "Are you sure to submit?").showAndWait().ifPresent(responds -> {
            if (responds == ButtonType.OK) {
                if (checkValid()) {
                    Student std = new Student();
                    std.setName(nameInput.getText());
                    std.setAcademicStatus(statusInput.getValue());
                    std.setEmployed(employed.selectedProperty().get());
                    std.setJobDetails(jobInput.getText());
                    std.setPreferredRole(roleInput.getValue());
                    stdDB.addStudent(std);
                    std.setProgrammingLanguages(getResultOfSelectBoxes(langCheckBoxes));
                    std.setDatabases(getResultOfSelectBoxes(dataCheckBoxes));
                    String comText = commentArea.getText();
                    if (!comText.isBlank()) {
                        std.addComment(" <" + tool.getTimeString() + ">\n" + comText);
                    }
                }
            }
        });
        tool.popAlert(Alert.AlertType.INFORMATION, "Student Added");
        stdDB.save();
        this.close();
    }

    private List<String> getResultOfSelectBoxes(List<CheckBox> cb) {
        List<String> res = new ArrayList<>();
        for (CheckBox box : cb) {
            if (box.isSelected()) {
                res.add(box.getText());
            }
        }
        return res;
    }

    private boolean checkValid() {
        String stdName = nameInput.getText();
        if (stdName.isBlank()) {
            tool.popAlert(Alert.AlertType.ERROR, "Name can not be empty").showAndWait();
            return false;
        }
        if (stdDB.isPresent(stdName)) {
            tool.popAlert(Alert.AlertType.ERROR, "Student is already defined");
            return false;
        }
        return true;
    }
}
