package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddProductController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtCategory;
    public TextField txtPrice;
    public Button btnOk;

    public void btnOk_OnAction(ActionEvent event) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stm = null;
            stm = connection.prepareStatement("INSERT INTO product (product_name,product_category,product_price) VALUES(?,?,?)");
            stm.setString(1, txtName.getText().trim());
            stm.setString(2, txtCategory.getText().trim());
            stm.setString(3, txtPrice.getText().trim());
            int i = stm.executeUpdate();

           if(i==1){
               System.out.println("insert una");

           }else{
               System.out.println("insert une na");
           }


        } catch (SQLException e) {Note:
            e.printStackTrace();
        }

    }
}
