package cs151.application.controller;

import cs151.application.services.DataAccessor;
import cs151.application.services.Logger;
import cs151.application.view.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class HomePageController{
    private final Logger logger = Logger.getInstance();

    public HomePageController() {
    }

    public void defineBtnAct() {
        Stage definePage = new DefineLanguagePage();
        definePage.show();
    }

    public void displayBtnAct() {
        List<String> studentList = new ArrayList<>();
        try (DataAccessor da = new DataAccessor()) {
            studentList = da.getStudentNameList();
        } catch (Exception e) {
            logger.log("<<__Debug__>> : " + e.getMessage());
        }
        StudentsListPage showPage = new StudentsListPage(studentList, "All Students");
        showPage.show();
    }

    public void defineStudentAct() {
        Stage showPage = new DefineStudentPage();
        showPage.show();
    }

    public void searchBtnAct() {
        Stage newPage = new SearchStudentPage();
        newPage.show();
    }

    public void reportBtnAct() {
        Stage newPage = new ReportPage();
        newPage.show();

    }
}
