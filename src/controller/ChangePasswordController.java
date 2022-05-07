package controller;

import db.DBConnection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import security.Principal;
import security.SecurityContextHolder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangePasswordController {
    public PasswordField txtCurrentPassword;
    public PasswordField txtNewPassword;
    public PasswordField txtNewpasswordAgain;
    public TextField txtHint;


    public Button btnChangePassword;

    public void btnChangePassword_OnAction(ActionEvent event) throws IOException {




        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stm = null;
            stm = connection.prepareStatement("SELECT * FROM user WHERE  password=?");


            stm.setString(1, txtCurrentPassword.getText().trim());

            ResultSet rst = stm.executeQuery();

            if (rst.next()){
                System.out.println("password ok");


        }

    } catch (SQLException e) {
            e.printStackTrace();
        }
    }}
