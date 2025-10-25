package cs151.application.controller;

import cs151.application.services.DataAccessor;
import cs151.application.services.Tools;
import cs151.application.view.SearchStudentPage;
import cs151.application.view.StudentsListPage;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

public class SearchPageController {
    private SearchStudentPage page;

    public SearchPageController(SearchStudentPage page) {
        this.page = page;
    }

    public void searchAct(String keyword) {
        Tools tool = new Tools();
        List<String> nameList = new ArrayList<>();
        try (DataAccessor da = new DataAccessor()) {
            nameList = da.searchByKeyWords(keyword);
        } catch (Exception ignore) {
            tool.popAlert(Alert.AlertType.ERROR, "Database issue");
        }
        StudentsListPage newPage = new StudentsListPage(nameList);
        newPage.show();
    }

    public void cancelAct() {
        page.close();
    }

}
