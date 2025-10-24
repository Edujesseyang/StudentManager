package cs151.application;

import cs151.application.services.DataAccessor;
import cs151.application.view.HomePageStage;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void init() {

        try (DataAccessor da = new DataAccessor()) {
            if(da.languagesSize() <3 ){
                da.ensureDefaultLanguage();
            }
        } catch (Exception e) {
            System.out.println("Database initialization fails: " + e.getMessage());
        }
    } // init the program, load database

    @Override
    public void start(Stage primaryStage) {
        HomePageStage homePage = new HomePageStage();
        homePage.show();
    }

}
