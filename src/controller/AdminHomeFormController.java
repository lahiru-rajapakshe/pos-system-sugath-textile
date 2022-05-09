package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
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

    public void initialize(){

        getTimeFromAndroid();


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
}
