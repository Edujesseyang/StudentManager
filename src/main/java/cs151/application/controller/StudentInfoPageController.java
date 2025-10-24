package cs151.application.controller;

import cs151.application.model.Student;
import cs151.application.view.AddCommentPage;
import cs151.application.view.ListDisplay;
import cs151.application.view.StudentInfoPage;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

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
        List<String> dbList = new ArrayList<>();
        dbList.add(std.getDatabases());
        Stage showPage = new ListDisplay(dbList, "    Student's database skills: ");
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
