package cs151.application.stage;

import cs151.application.Model.Student;
import cs151.application.Model.StudentList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class DefinePageStage extends Stage {
    private Label infoLabel;
    private TextField studentName;
    private TextField programLanguageName;
    private Button submit;
    private Button clear;
    private Button cancel;

    public DefinePageStage(int height, int width, String title) {
        // create label
        infoLabel = new Label("Please enter the program language name:");

        // create name input box
        studentName = new TextField();
        studentName.setPromptText("Please enter the student's name");

        // create info input box
        programLanguageName = new TextField();
        programLanguageName.setPromptText("Please enter programming languages, separate by whitespace");

        // create buttons
        submit = new Button("Submit");
        submit.setOnAction(e -> submitAction());

        clear = new Button("Clear");
        clear.setOnAction(e -> clearAction());

        cancel = new Button("Cancel");
        cancel.setOnAction(e -> cancelAction());

        VBox inputLayout = new VBox(infoLabel, studentName, programLanguageName);
        inputLayout.setPadding(new Insets(20));
        inputLayout.setSpacing(10);
        inputLayout.setAlignment(Pos.CENTER);

        // layout buttons
        HBox btnLayout = new HBox(submit, clear, cancel);
        btnLayout.setPadding(new Insets(25));
        btnLayout.setSpacing(10);
        btnLayout.setAlignment(Pos.CENTER);

        // layout page
        VBox pageLayout = new VBox(inputLayout, btnLayout);
        pageLayout.setPadding(new Insets(25));

        // set scene
        Scene definePageScene = new Scene(pageLayout);
        this.setTitle(title);
        this.setScene(definePageScene);
    }

    private void submitAction() {
        if (studentName.getText().isBlank()) {
            Alert blankInputAlert = new Alert(Alert.AlertType.ERROR);
            blankInputAlert.setTitle("Error");
            blankInputAlert.setHeaderText(null);
            blankInputAlert.setContentText("The input can not be empty!");
            blankInputAlert.showAndWait();
        } else {
            Alert blankInputAlert = new Alert(Alert.AlertType.CONFIRMATION);
            blankInputAlert.setTitle("Confirm");
            blankInputAlert.setHeaderText("Please Confirm");
            blankInputAlert.setContentText("Are you sure to submit?");
            blankInputAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    submitData();
                }
            });
        }
    }

    private void submitData() {
        Student newStudent = new Student(studentName.getText());
        String inputString = programLanguageName.getText();
        String[] languages = inputString.split("\\s+");
        for (String word : languages) {
            newStudent.getProgrammingLanguages().add(word);
        }

        StudentList db = StudentList.getInstance();
        db.addStudent(newStudent);

        Alert successAlert = new Alert(Alert.AlertType.WARNING);
        successAlert.setTitle("Success");
        successAlert.setHeaderText("Submit Successfully");
        successAlert.showAndWait();
        this.close();
    }

    private void clearAction() {
        studentName.clear();
        programLanguageName.clear();
    }

    private void cancelAction() {
        this.close();
    }
}
