package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminHomeFormController {
    public Button btnChangePassword;

    public void btnChangePassword_OnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        AnchorPane root = FXMLLoader.load(this.getClass().getResource("/view/ChangePassword.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Student Attendance System: Create Admin");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.sizeToScene();

        stage.show();
    }
}
