package cs151.application.controller;

import cs151.application.model.Student;
import cs151.application.services.ControllerUtility;
import cs151.application.services.DataAccessor;
import cs151.application.view.DefineStudentPage;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.util.List;

public class DefineStudentPageController {
    private final DefineStudentPage page;
    private final ControllerUtility tool = new ControllerUtility();

    public DefineStudentPageController(DefineStudentPage page) {
        this.page = page;
    }

    public void cancelAct() {
        page.close();
    }

    public void clearAct() {
        reloadPage();
    }

    public void reloadPage() {
        page.close();
        page.setScene(page.buildScene());
        page.show();
    }

    public void saveAct(TextField nameInput, ComboBox<String> statusInput, RadioButton employed, TextField jobInput, ComboBox<String> roleInput, List<CheckBox> langCheckBoxes, List<CheckBox> dataCheckBoxes, TextArea commentArea) {
        tool.popAlert(Alert.AlertType.CONFIRMATION, "Are you sure to submit?").showAndWait().ifPresent(responds -> {
            if (responds == ButtonType.OK) {
                String name = nameInput.getText();
                if (isValid(name) && !isDuplicate(name)) {
                    Student std = new Student();
                    std.setName(nameInput.getText());
                    std.setAcademicStatus(statusInput.getValue());
                    std.setEmployed(employed.selectedProperty().get());
                    std.setJobDetails(jobInput.getText());
                    std.setPreferredRole(roleInput.getValue());
                    std.setProgrammingLanguages(makeListFromCheckBox(langCheckBoxes));
                    std.setDatabases(makeListFromCheckBox(dataCheckBoxes));
                    String comText = commentArea.getText();
                    if (!comText.isBlank()) {
                        std.addComment(" <" + tool.getTimeString() + ">\n" + comText);
                    }
                    try (DataAccessor da = new DataAccessor()) {
                        da.addStudent(std);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    tool.popAlert(Alert.AlertType.INFORMATION, "Student Added").showAndWait();
                }
            }
        });

        page.close();
    }

    public List<String> makeListFromCheckBox(List<CheckBox> cb) {
        List<String> res = new ArrayList<>();
        for (CheckBox box : cb) {
            if (box.isSelected()) {
                res.add(box.getText());
            }
        }
        return res;
    }

    private boolean isValid(String name) {
        if (name.isBlank()) {
            tool.popAlert(Alert.AlertType.ERROR, "Name can not be empty").showAndWait();
            return false;
        }
        return true;
    }

    private boolean isDuplicate(String name){
        boolean isDuplicate = false;
        try(DataAccessor da = new DataAccessor()){
            isDuplicate = da.isPresent(name);
        } catch (Exception ignore){}
        if(isDuplicate) tool.popAlert(Alert.AlertType.ERROR, "Student already exists").showAndWait();
        return isDuplicate;
    }
}
