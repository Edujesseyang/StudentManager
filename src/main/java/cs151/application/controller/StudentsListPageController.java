package cs151.application.controller;

import cs151.application.model.Student;
import cs151.application.services.ControllerUtility;
import cs151.application.services.DataAccessor;
import cs151.application.view.StudentInfoPage;
import cs151.application.view.StudentsListPage;
import javafx.scene.Scene;
import java.util.ArrayList;
import java.util.List;

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
        List<String> newList = new ArrayList<>();
        try (DataAccessor da = new DataAccessor()) {
            da.deleteStudent(stdName);
            newList = da.getStudentNameList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scene reloadPage = page.buildScene(newList);
        page.setScene(reloadPage);
        page.show();
    }

    public List<String> getList() {
        List<String> res = new ArrayList<>();
        try (DataAccessor da = new DataAccessor()) {
            res = da.getStudentNameList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
