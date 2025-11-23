package cs151.application.controller;

import cs151.application.services.DataAccessor;
import cs151.application.services.Logger;
import cs151.application.view.StudentsListPage;

import java.util.ArrayList;
import java.util.List;

public class ReportPageController {
    Logger logger = Logger.getInstance();

    public ReportPageController(){
    }

    public void showBlackList() {
        List<String> blackListNames = new ArrayList<>();
        try (DataAccessor da = new DataAccessor()) {
            blackListNames = da.getBlackList();
        } catch (Exception e) {
            logger.log("<<__Debug__>> : " + e.getMessage());
        }
        StudentsListPage listPage = new StudentsListPage(blackListNames, "Black List");
        listPage.show();
    }

    public void showWhiteList() {
        List<String> whiteListNames = new ArrayList<>();
        try (DataAccessor da = new DataAccessor()) {
            whiteListNames = da.getWhiteList();
        } catch (Exception e) {
            logger.log("<<__Debug__>> : " + e.getMessage());
        }
        StudentsListPage listPage = new StudentsListPage(whiteListNames, "White List");
        listPage.show();
    }

}
