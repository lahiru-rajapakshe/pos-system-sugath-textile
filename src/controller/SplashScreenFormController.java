package controller;

import db.DBConnection;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SplashScreenFormController {
    public Label lblStatus;
    private final SimpleObjectProperty<File> fileProperty = new SimpleObjectProperty<>();

    public void initialize() {
        establishDBConnection();
    }

    private void establishDBConnection() {
        lblStatus.setText("Establishing DB Connection..");

        new Thread(() -> {

            try {
                sleep(800);
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sugath_textile", "root", "mysql");

                Platform.runLater(() -> lblStatus.setText("Setting up the UI.."));
                sleep(800);

                Platform.runLater(() -> {
                    loadLoginForm(connection);
                });

            } catch (SQLException | ClassNotFoundException e) {

                /* Let's find out whether the DB exists or not */
                if (e instanceof SQLException && ((SQLException) e).getSQLState().equals("42000")) {
//                    Platform.runLater(this::loadImportDBForm);
                } else {
                    shutdownApp(e);
                }
            }

        }).start();
    }



    private void loadCreateAdminForm() {
        try {
            Stage stage = new Stage();
            AnchorPane root = FXMLLoader.load(this.getClass().getResource("/view/CreateAdminForm.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Student Attendance System: Create Admin");
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.sizeToScene();
            stage.setOnCloseRequest((e)->dropDatabase());
            stage.show();

            /* Let's close the splash screen eventually */
            ((Stage) (lblStatus.getScene().getWindow())).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadLoginForm(Connection connection) {
        /* Let's store the connection first */
        DBConnection.getInstance().init(connection);

        /* Let's redirect to log in form */
        try {
            Stage stage = new Stage();
            AnchorPane root = FXMLLoader.load(this.getClass().getResource("/view/LoginUI.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
//            root.setStyle("-fx-background-radius: 5em;");
//            stage.style("hover:-fx-background-color: #101010");
            stage.setTitle("Student Attendance System: Log In");
//            scene.getStylesheets().add("view/style/loginStyles.css");
//            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.sizeToScene();
            stage.show();

            /* Let's close the splash screen eventually */
            ((Stage) (lblStatus.getScene().getWindow())).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dropDatabase(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "mysql");
            Statement stm = connection.createStatement();
            stm.execute("DROP DATABASE IF EXISTS dep8_student_attendance");
            connection.close();
        } catch (SQLException e) {
            shutdownApp(e);
        }
    }

    private void shutdownApp(Throwable t) {
        Platform.runLater(() -> lblStatus.setText("Failed to initialize the app"));

        sleep(1200);

        if (t != null)
            t.printStackTrace();

        System.exit(1);
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
