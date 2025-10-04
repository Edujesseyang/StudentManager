package cs151.application;

import cs151.application.Model.StudentList;
import cs151.application.stage.HomePageStage;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    StudentList studentDB = StudentList.getInstance();  // temporary use, will be replaced by database

    @Override
    public void start(Stage primaryStage) throws Exception {
        HomePageStage homePage = new HomePageStage(400, 300, "Home Page");
        homePage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
