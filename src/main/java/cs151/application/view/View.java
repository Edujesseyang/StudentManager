package cs151.application.view;

import javafx.scene.Scene;

public interface View {
    Scene buildScene();

    void close();

    void show();

    void setScene(Scene scene);

    String getTitle();
}
