package cs151.application.controller;

import cs151.application.model.Student;
import cs151.application.services.ControllerUtility;
import cs151.application.services.DataAccessor;
import cs151.application.view.StudentInfoPage;
import cs151.application.view.StudentsListPage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

public class StudentsListPageController {
    private final StudentsListPage page;
    private final ControllerUtility tool = new ControllerUtility();

    public StudentsListPageController(StudentsListPage page) {
        this.page = page;
    }

    public void closeBtnAction() {
        page.close();
    }

    public void selectAct(String stdName) {
        Student std = new Student();
        try (DataAccessor da = new DataAccessor()) {
            std = da.getStudent(stdName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StudentInfoPage infoPage = new StudentInfoPage(std);
        infoPage.show();
    }

    public void deleteAct(String stdName) {
        page.close();
        boolean report = false;
        try (DataAccessor da = new DataAccessor()) {
            report = da.deleteStudent(stdName);
        } catch (Exception ignore) {
        }
        if (report) {
            tool.popAlert(Alert.AlertType.INFORMATION, stdName + " is deleted").showAndWait();
        }
        Scene reloadPage = page.buildScene();
        page.setScene(reloadPage);
        page.show();
    }

}
