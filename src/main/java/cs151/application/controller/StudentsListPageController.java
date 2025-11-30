package cs151.application.controller;

import cs151.application.model.Student;
import cs151.application.services.DataAccessor;
import cs151.application.services.Logger;
import cs151.application.view.StudentInfoPage;
import cs151.application.view.View;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.List;

public class StudentsListPageController {
    private final View page;
    private final Logger logger = Logger.getInstance();
    private final List<String> stdNames;

    public StudentsListPageController(View page, List<String> stdNames) {
        this.stdNames = stdNames;
        this.page = page;
    }

    public void closeBtnAction() {
        page.close();
    }

    public void selectAct(String stdName) {
        Student std = new Student();
        try (DataAccessor da = new DataAccessor()) {
            std = da.getStudent(stdName);
            logger.log("<<INFO>> : displaying student '" + stdName + "'");
        } catch (Exception e) {
            logger.log("<<__Debug__>> : " + e.getMessage());
        }
        View infoPage = new StudentInfoPage(std, stdNames, page.getTitle());
        infoPage.show();
        page.close();
    }

    public void deleteAct(String stdName) {
        page.close();
        try (DataAccessor da = new DataAccessor()) {
            da.deleteStudent(stdName);
            logger.log("<<WARNING>> : deleting student '" + stdName + "'");
        } catch (Exception e) {
            logger.log("<<__Debug__>> : " + e.getMessage());
        }
        stdNames.remove(stdName);
        Scene newScene = page.buildScene();
        page.setScene(newScene);
        page.show();
    }

    public List<String> getList() {
        List<String> res = new ArrayList<>();
        try (DataAccessor da = new DataAccessor()) {
            res = da.getStudentNameList();
        } catch (Exception e) {
            logger.log("<<__Debug__>> : " + e.getMessage());
        }
        return res;
    }
}
