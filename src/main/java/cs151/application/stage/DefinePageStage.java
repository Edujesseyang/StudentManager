package cs151.application.stage;

import cs151.application.model.Student;
import cs151.application.model.StudentList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class DefinePageStage extends Stage {
    private final Label infoLabel;
    private final TextField studentName;
    private final TextField programLanguageName;
    private final Button submit;
    private final Button clear;
    private final Button cancel;

    public DefinePageStage(int height, int width, String title) {
        // create label
        infoLabel = new Label("Please enter the program language name:");

        // create name input box
        studentName = new TextField();
        studentName.setPromptText("Please enter the student's name");

        // create info input box
        programLanguageName = new TextField();
        programLanguageName.setPromptText("Enter programming languages, separate by whitespace");

        // create buttons
        submit = new Button("Submit"); // 1
        submit.setOnAction(e -> submitAction());
        clear = new Button("Clear"); // 2
        clear.setOnAction(e -> clearAction());
        cancel = new Button("Cancel"); // 3
        cancel.setOnAction(e -> cancelAction());

        // input area layout
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
        Scene definePageScene = new Scene(pageLayout, height, width);
        this.setTitle(title);
        this.setScene(definePageScene);
    }

    /**
     * check if form are filled correctly
     */
    private void submitAction() {
        if (studentName.getText().isBlank()) { // pop alert for blank nane
            Alert blankInputAlert = new Alert(Alert.AlertType.ERROR);
            blankInputAlert.setTitle("Error");
            blankInputAlert.setHeaderText(null);
            blankInputAlert.setContentText("The input can not be empty!");
            blankInputAlert.showAndWait();
        } else { // pop alert for confirm
            Alert blankInputAlert = new Alert(Alert.AlertType.CONFIRMATION);
            blankInputAlert.setTitle("Confirm");
            blankInputAlert.setHeaderText(null);
            blankInputAlert.setContentText("Are you sure to submit?");
            blankInputAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    submitData();
                }
            });
        }
    }

    /**
     * submit input and create student obj then put it into the db
     */
    private void submitData() {
        Student newStudent = new Student();
        newStudent.setName(studentName.getText());

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
        db.save();
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
