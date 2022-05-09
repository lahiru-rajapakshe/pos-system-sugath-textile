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

public class OrderFormController {

    public TextField txtMoney;
    public Label lblProduct_price;
    public Label Balance;
    public Button btnOk;


    public void initialize(){




    }

    public void btnOk_OnAction(ActionEvent event) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stm = null;

            stm = connection.prepareStatement("SELECT product_price FROM product WHERE product_name='kasun' ");
            ResultSet rst = stm.executeQuery();

            if(rst.next()){
                int product_price = Integer.parseInt(rst.getString("product_price"));
                lblProduct_price.setText(String.valueOf(product_price));

//                int money = Integer.parseInt(txtMoney.getText());
                int text = Integer.parseInt(txtMoney.getText());

                Balance.setText(String.valueOf(text));

                int c=(product_price-text);
                System.out.println(c);
                Balance.setText(String.valueOf(c));





            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
