package cs151.application;

import cs151.application.model.StudentList;
import cs151.application.stage.HomePageStage;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private static final StudentList studentDB = StudentList.getInstance();  // singleton db

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        studentDB.load();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        HomePageStage homePage = new HomePageStage(400, 300, "Home Page");
        homePage.show();
    }

    @Override
    public void stop() {
        studentDB.save();
    }


}
