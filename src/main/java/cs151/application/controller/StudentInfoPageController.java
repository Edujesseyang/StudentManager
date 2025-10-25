package cs151.application.controller;

import cs151.application.model.Student;
import cs151.application.view.AddCommentPage;
import cs151.application.view.ListDisplay;
import cs151.application.view.StudentInfoPage;
import javafx.stage.Stage;


public class StudentInfoPageController {
    private final StudentInfoPage page;
    private final Student std;

    public StudentInfoPageController(StudentInfoPage page, Student std) {
        this.page = page;
        this.std = std;
    }

    public void langBtnAct() {
        Stage showPage = new ListDisplay(std.getProgrammingLanguages(), "    Student's Programming Languages:");
        showPage.show();
    }

    public void dbBtnAct() {
        Stage showPage = new ListDisplay(std.getDatabases(), "    Student's database skills: ");
        showPage.show();
    }

    public void addCommentAct() {
        AddCommentPage addNewCommentPage = new AddCommentPage(std);
        addNewCommentPage.show();
        page.close();
    }

    public void closeBtnAct() {
        page.close();
    }
}
