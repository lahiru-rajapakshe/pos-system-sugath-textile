package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Calendar;

public class AdminHomeFormController {
    public Button btnChangePassword;
    public Label lblGreeting;
    public Button btnAddProduct;

    public Label lblProductId;
    public AnchorPane ancShowData;
    public Label lblProductName;
    public Label lblProductCategory;
    public Label lblProductPrice;
    public Button btnOrder;
    public Label lblTodaySales;

    public void initialize(){
        today_sales();
        getTimeFromAndroid();


        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stm = null;
            stm = connection.prepareStatement("SELECT product_name,product_category,product_price FROM product WHERE product_id=6 ");

            ResultSet rst = stm.executeQuery();


            if(rst.next()){

                String p_name = rst.getString("product_name");
                String p_category = rst.getString("product_category");
                String p_price = rst.getString("product_price");

                lblProductName.setText(p_name);
                lblProductCategory.setText(p_category);
                lblProductPrice.setText(p_price);


            }else{
                System.out.println("data retrive wenne na,awlak thiyenawa");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }




    }

    private void today_sales(){
        try {
            Date datea = Date.valueOf(LocalDate.now());
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stm = null;

            PreparedStatement stm2 = connection.prepareStatement("SELECT SUM(price) AS PRC FROM todaySales WHERE date =?");
            stm2.setString(1, String.valueOf(datea));
            ResultSet rst2 = stm2.executeQuery();
            if(rst2.next()){

                double prc = rst2.getDouble("PRC");

//                System.out.println(prc);
//                System.out.println(lblTodaySales.);
                lblTodaySales.setText(String.valueOf(prc));
            }




        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


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

    private void getTimeFromAndroid() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12){

            lblGreeting.setText("Good Morning MR. Sugath");
        }else if(timeOfDay >= 12 && timeOfDay < 16){

            lblGreeting.setText("Good Afternoon MR. Sugath");
        }else if(timeOfDay >= 16 && timeOfDay < 21){

            lblGreeting.setText("Good Evening MR. Sugath");
        }else if(timeOfDay >= 21 && timeOfDay < 24){

            lblGreeting.setText("Good Night MR. Sugath");
        }
    }

    public void btnAddProduct_OnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        AnchorPane root = FXMLLoader.load(this.getClass().getResource("/view/AddProduct.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Student Attendance System: Create Admin");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.sizeToScene();

        stage.show();
    }

    public void btnOrder_OnAction(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        AnchorPane root = FXMLLoader.load(this.getClass().getResource("/view/OrderForm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Student Attendance System: Create Admin");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.sizeToScene();

        stage.show();
    }
}
