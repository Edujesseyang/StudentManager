package cs151.application.controller;

import cs151.application.model.Student;
import cs151.application.view.*;

import java.util.List;

public class StudentInfoPageController {
    private final View page;
    private final Student std;
    private final List<String> prevShowingStdList;
    private final String prevPageTitle;

    public StudentInfoPageController(View page, Student std, List<String> prevShowingStdList, String prevPageTitle) {
        this.page = page;
        this.std = std;
        this.prevPageTitle = prevPageTitle;
        this.prevShowingStdList = prevShowingStdList;
    }

    public void langBtnAct() {
        View showPage = new ListDisplay(std.getProgrammingLanguages(), "    Student's Programming Languages:");
        showPage.show();
    }

    public void dbBtnAct() {
        View showPage = new ListDisplay(std.getDatabases(), "    Student's database skills: ");
        showPage.show();
    }

    public void editStudentBtnAct() {
        View editStudentPage = new EditStudentPage(std, prevShowingStdList, prevPageTitle);
        editStudentPage.show();
        page.close();
    }

    public void closeBtnAct() {
        View listPage = new StudentsListPage(prevShowingStdList, prevPageTitle);
        listPage.show();
        page.close();
    }
}
