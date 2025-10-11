package cs151.application;

import cs151.application.model.StudentList;
import cs151.application.stage.HomePageStage;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private static final StudentList studentDB = StudentList.getInstance();  // singleton db

    @Override
    public void init() {
        studentDB.load();
    } // init the program, load json file to singleton

    @Override
    public void start(Stage primaryStage) {
        HomePageStage homePage = new HomePageStage(700, 320, "Home Page");
        homePage.show();
    }

    @Override
    public void stop() {
        studentDB.save();
    } // when program close, save data to json
}
