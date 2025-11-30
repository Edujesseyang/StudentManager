package cs151.application.controller;

import cs151.application.services.DataAccessor;
import cs151.application.services.Logger;
import cs151.application.view.StudentsListPage;
import cs151.application.view.View;

import java.util.ArrayList;
import java.util.List;

public class SearchPageController {
    private final View page;
    private final Logger logger = Logger.getInstance();

    public SearchPageController(View page) {
        this.page = page;
    }

    public void searchAct(String keyword) {
        List<String> nameList = new ArrayList<>();
        try (DataAccessor da = new DataAccessor()) {
            nameList = da.searchByKeyWords(keyword);
            logger.log("<<INFO>> : SEARCHING: '" + keyword + "'");
        } catch (Exception e) {
            logger.log("<<__Debug__>> : " + e.getMessage());
        }
        View newPage = new StudentsListPage(nameList, "Search Result");
        newPage.show();
        page.close();
    }

    public void cancelAct() {
        page.close();
    }
}
