package cs151.application;

import cs151.application.services.DatabaseUtility;
import cs151.application.view.HomePage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        if (!Files.exists(Paths.get("localData", "database.db"))) {
            DatabaseUtility initializer = new DatabaseUtility();
            initializer.initDatabase();
            initializer.initDatabaseSkillList();
            initializer.initDefaultLanguages();
            initializer.initSampleStudents();
        }
        HomePage homePage = new HomePage();
        homePage.show();
    }

}
