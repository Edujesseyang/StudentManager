package cs151.application.controller;

import cs151.application.model.Student;
import cs151.application.view.*;
import javafx.stage.Stage;

import java.util.List;

public class StudentInfoPageController {
    private final StudentInfoPage page;
    private final Student std;
    private final List<String> prevShowingStdList;

    public StudentInfoPageController(StudentInfoPage page, Student std, List<String> prevShowingStdList) {
        this.page = page;
        this.std = std;
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
        EditStudentPage editStudentPage = new EditStudentPage(std, prevShowingStdList);
        editStudentPage.show();
        page.close();
    }

    public void closeBtnAct() {
        StudentsListPage listPage = new StudentsListPage(prevShowingStdList);
        listPage.show();
        page.close();
    }
}
