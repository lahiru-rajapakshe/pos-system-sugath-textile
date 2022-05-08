package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ForgotPasswordFormController {
    public TextField txtForgotPasswordHint;
    public Button btnforgotPasswordOk;
    public Label correctPassword;

    public void btnforgotPasswordOk_OnAction(ActionEvent event) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT hint,password FROM user WHERE role='ADMIN' ");

            ResultSet rst = stm.executeQuery();


                if(rst.next()){

                    String anInt = rst.getString("password");
                    String hint = rst.getString("hint");

                    if(txtForgotPasswordHint.getText().equals(hint)) {
                        System.out.println("wade karanna puluwn " + anInt);
                        correctPassword.setText(anInt);
                    }else{
                        System.out.println("hint eka waradie sagoo");
                        correctPassword.setText("");
                    }

                }else{
                    System.out.println("wade awlak thiyenawa");
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
