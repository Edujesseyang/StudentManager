package cs151.application.stage;

import cs151.application.model.Student;
import cs151.application.model.StudentList;
import cs151.application.tools.Tools;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
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
    private TextField roleInput;
    private RadioButton employed;
    private RadioButton notEmployed;
    private List<String> languageList;
    private List<CheckBox> checkBoxResult;


    public DefineStudentPage() {
        this.languageList = tool.loadLanguageList();
        Scene pageScene = buildScene();
        this.setScene(pageScene);
        this.show();
    }

    private Scene buildScene() {
        Label title = new Label("Define a student");

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
        HBox employLine = new HBox(employedText, employed, notEmployed); // line 3

        // job line
        Label job = new Label("Job Role: ");
        jobInput = new TextField();
        jobInput.setPromptText("Enter student's job role");
        HBox jobLine = new HBox(job, jobInput);
        jobLine.disableProperty().bind(notEmployed.selectedProperty());   // line 4

        // role line
        Label role = new Label("Preferred Role: ");
        roleInput = new TextField();
        roleInput.setPromptText("Enter the role the student preferred");
        HBox roleLine = new HBox(role, roleInput);
        roleLine.disableProperty().bind(employed.selectedProperty());     // line 5

        // language select  line 6
        Label langSelect = new Label("Select skilled programming languages:");
        checkBoxResult = new ArrayList<>();
        FlowPane checkArea = new FlowPane();
        checkArea.setHgap(5);
        checkArea.setVgap(5);
        VBox selectArea = new VBox(langSelect, checkArea);
        checkArea.prefWrapLengthProperty().bind(selectArea.widthProperty());

        for (String lang : languageList) {
            CheckBox cb = new CheckBox(lang);
            checkBoxResult.add(cb);
            checkArea.getChildren().add(cb);
        }

        // layout the form area
        VBox form = new VBox(nameLine, academicStatusLine, employLine, jobLine, roleLine, selectArea);
        form.getStyleClass().add("inputLayout");

        // buttons area
        saveBtn = new Button("Save");
        saveBtn.setOnAction(e -> saveAct());
        clearBtn = new Button("Clear");
        clearBtn.setOnAction(e -> clearAct());
        cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(e -> cancelAct());
        HBox btnLayout = new HBox(saveBtn, clearBtn, cancelBtn);

        VBox pageLayout = new VBox(title, form, btnLayout);
        pageLayout.getStyleClass().add("PageLayout");


        Scene pageScene = new Scene(pageLayout, 600, 700);
        tool.setPageStyle(pageScene);
        return pageScene;
    }

    private void cancelAct() {
        this.close();
    }

    private void clearAct() {
        nameInput.clear();
        statusInput.cancelEdit();
        jobInput.clear();
        roleInput.clear();
    }

    private void saveAct() {
        tool.popAlert(Alert.AlertType.CONFIRMATION, "Are you sure to submit?").showAndWait().ifPresent(responds -> {
            if (responds == ButtonType.OK) {
                if (checkValid()) {
                    Student std = new Student();
                    std.setName(nameInput.getText());
                    std.setAcademicStatus(statusInput.getEditor().getSelectedText());
                    std.setEmployed(employed.selectedProperty().get());
                    std.setJobDetails(jobInput.getText());
                    std.setPreferredRole(roleInput.getText());
                    stdDB.addStudent(std);
                    std.setProgrammingLanguages(getResultOfLanguageSelectBox());
                }
            }
        });
        tool.popAlert(Alert.AlertType.INFORMATION, "Student Added");
        this.close();
    }

    private List<String> getResultOfLanguageSelectBox() {
        List<String> res = new ArrayList<>();
        for (CheckBox box : checkBoxResult) {
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
