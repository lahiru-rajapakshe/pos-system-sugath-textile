package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import db.DBConnection;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginUI {

    public TextField txtUserName;
    public PasswordField txtPassword;

    public void initialize() {

    }

    public void btnSignIn_OnAction(ActionEvent actionEvent) {

        if (!isValidated()){
//            new DepAlert(Alert.AlertType.ERROR, "Invalid username or password", "Invalid credentials").show();



            txtUserName.requestFocus();
            txtUserName.selectAll();
            return;
        }

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT name, role FROM user WHERE username=? AND password=?");
            stm.setString(1, txtUserName.getText().trim());
            stm.setString(2, txtPassword.getText().trim());
            ResultSet rst = stm.executeQuery();

            if (rst.next()){
                SecurityContextHolder.setPrincipal(new Principal(
                        txtUserName.getText(),
                        rst.getString("name"),
                        Principal.UserRole.valueOf(rst.getString("role"))));
                String path = null;

                if (rst.getString("role").equals("ADMIN")){
                    path = "/view/AdminHomeForm.fxml";
                }else{
                    path = "/view/UserHomeForm.fxml";
                }
                FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(path));
                AnchorPane root = fxmlLoader.load();
                Scene homeScene = new Scene(root);
                Stage primaryStage = (Stage)(btnSignIn.getScene().getWindow());
                homeScene.getStylesheets().add("view/style/userHomeStyles.css");
                primaryStage.setScene(homeScene);
                primaryStage.setTitle("Student Attendance System: Home");
                Platform.runLater(()-> {
                    primaryStage.sizeToScene();
                    primaryStage.centerOnScreen();
                });
            }else{
//                new DepAlert(Alert.AlertType.ERROR, "Invalid username or password", "Invalid credentials").show();
                txtUserName.requestFocus();
                txtUserName.selectAll();
            }
        } catch (Exception e) {
//            new DepAlert(Alert.AlertType.WARNING, "Something went wrong, please try again", "Oops..!", "Failure").show();
            e.printStackTrace();
        }
    }

    private boolean isValidated() {
        String username = txtUserName.getText().trim();
        String password = txtPassword.getText().trim();

        return !(username.length() < 4 || !username.matches("[A-Za-z0-9]+") || password.length() < 4);
    }

    public void btnClose_OnAction(ActionEvent event) {
        Platform.exit();
    }
}
