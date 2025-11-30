package cs151.application.controller;

import cs151.application.services.DataAccessor;
import cs151.application.services.Logger;
import cs151.application.view.*;

import java.util.ArrayList;
import java.util.List;

public class HomePageController{
    private final Logger logger = Logger.getInstance();

    public HomePageController() {
    }

    public void defineBtnAct() {
        View definePage = new DefineLanguagePage();
        definePage.show();
    }

    public void displayBtnAct() {
        List<String> studentList = new ArrayList<>();
        try (DataAccessor da = new DataAccessor()) {
            studentList = da.getStudentNameList();
        } catch (Exception e) {
            logger.log("<<__Debug__>> : " + e.getMessage());
        }
        View showPage = new StudentsListPage(studentList, "All Students");
        showPage.show();
    }

    public void defineStudentAct() {
        View showPage = new DefineStudentPage();
        showPage.show();
    }

    public void searchBtnAct() {
        View newPage = new SearchStudentPage();
        newPage.show();
    }

    public void reportBtnAct() {
        View newPage = new ReportPage();
        newPage.show();
    }
}
