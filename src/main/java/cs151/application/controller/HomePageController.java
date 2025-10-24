package cs151.application.controller;

import cs151.application.view.DefineLanguagePage;
import cs151.application.view.DefineStudentPage;
import cs151.application.view.StudentsListPage;

public class HomePageController {
    public HomePageController() {

    }

    /**
     * Create a define page stage and show it
     */
    public void defineBtnAct() {
        DefineLanguagePage definePage = new DefineLanguagePage();
        definePage.show();
    }

    /**
     * create students list show stage and show it
     */
    public void displayBtnAct() {
        StudentsListPage showPage = new StudentsListPage();
        showPage.show();
    }

    public void defineStudentAct() {
        DefineStudentPage showPage = new DefineStudentPage();
        showPage.show();
    }

}
