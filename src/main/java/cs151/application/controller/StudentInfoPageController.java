package cs151.application.controller;

import cs151.application.model.Student;
import cs151.application.view.*;
import javafx.stage.Stage;

import java.util.List;

public class StudentInfoPageController {
    private final StudentInfoPage page;
    private final Student std;
    private final List<String> prevShowingStdList;
    private final String prevPageTitle;

    public StudentInfoPageController(StudentInfoPage page, Student std, List<String> prevShowingStdList, String prevPageTitle) {
        this.page = page;
        this.std = std;
        this.prevPageTitle = prevPageTitle;
        this.prevShowingStdList = prevShowingStdList;
    }

    public void langBtnAct() {
        Stage showPage = new ListDisplay(std.getProgrammingLanguages(), "    Student's Programming Languages:");
        showPage.show();
    }

    public void dbBtnAct() {
        Stage showPage = new ListDisplay(std.getDatabases(), "    Student's database skills: ");
        showPage.show();
    }

    public void editStudentBtnAct() {
        EditStudentPage editStudentPage = new EditStudentPage(std, prevShowingStdList, prevPageTitle);
        editStudentPage.show();
        page.close();
    }

    public void closeBtnAct() {
        StudentsListPage listPage = new StudentsListPage(prevShowingStdList, prevPageTitle);
        listPage.show();
        page.close();
    }
}
