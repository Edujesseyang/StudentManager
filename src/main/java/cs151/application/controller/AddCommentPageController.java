package cs151.application.controller;

import cs151.application.model.Student;
import cs151.application.services.ControllerUtility;
import cs151.application.services.DataAccessor;
import cs151.application.view.AddCommentPage;
import cs151.application.view.StudentInfoPage;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

public class AddCommentPageController {
    private final AddCommentPage page;
    private final Student targetStudent;

    public AddCommentPageController(AddCommentPage page, Student targetStudent) {
        this.page = page;
        this.targetStudent = targetStudent;
    }

    public void addAct(TextArea commentText) {
        ControllerUtility tool = new ControllerUtility();
        String comText = commentText.getText();
        if (comText.isBlank()) {
            tool.popAlert(Alert.AlertType.ERROR, "Comment can not be blank").showAndWait();
            return;
        }
        try (DataAccessor da = new DataAccessor()) {
            da.addComment(targetStudent.getName(), comText);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tool.popAlert(Alert.AlertType.ERROR, "Adding fail! \nReason: "+e.getMessage()).showAndWait();
        }

        targetStudent.addComment(" <" + tool.getTimeString() + ">\n" + comText);
        tool.popAlert(Alert.AlertType.INFORMATION, "Added successfully").showAndWait();
        StudentInfoPage newInfoPage = new StudentInfoPage(targetStudent);
        page.close();
        newInfoPage.show();
    }

    public void cancelAct() {
        page.close();
    }
}
