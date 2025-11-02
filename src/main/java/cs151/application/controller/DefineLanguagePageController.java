package cs151.application.controller;

import cs151.application.services.ControllerUtility;
import cs151.application.services.DataAccessor;
import cs151.application.view.DefineLanguagePage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

public class DefineLanguagePageController {
    private final ControllerUtility tool = new ControllerUtility();
    private final DefineLanguagePage page;

    public DefineLanguagePageController(DefineLanguagePage page) {
        this.page = page;
    }

    public void addAction(TextField programLanguageName) {
        try (DataAccessor da = new DataAccessor()) {
            if (programLanguageName.getText().isBlank()) { // pop alert for blank nane
                tool.popAlert(Alert.AlertType.ERROR, "Input can not be empty!").showAndWait();
            } else { // pop alert for confirm
                Alert confirm = tool.popAlert(Alert.AlertType.CONFIRMATION, "Are you sure to submit?");
                confirm.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        try {
                            if (!da.addLanguage(programLanguageName.getText())) {
                                tool.popAlert(Alert.AlertType.ERROR, "Language has already defined!").showAndWait();
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        page.close();
        Scene newScene = page.loadPage();
        page.setScene(newScene);
        page.show();
    }

    public void deleteAction(TextField programLanguageName) {
        try (DataAccessor da = new DataAccessor()) {
            if (da.deleteLanguage(programLanguageName.getText())) {
                tool.popAlert(Alert.AlertType.INFORMATION, "Delete successful").showAndWait();
            } else {
                tool.popAlert(Alert.AlertType.ERROR, "Can not find the language").showAndWait();
                return;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        page.close();
        Scene newScene = page.loadPage();
        page.setScene(newScene);
        page.show();
    }

    public List<String> getLangList() {
        List<String> res = new ArrayList<>();
        try (DataAccessor da = new DataAccessor()) {
            res = da.getLanguageList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


    public void clearAction(TextField programLanguageName) {
        programLanguageName.clear();
    }

    public void cancelAction() {
        page.close();
    }
}
