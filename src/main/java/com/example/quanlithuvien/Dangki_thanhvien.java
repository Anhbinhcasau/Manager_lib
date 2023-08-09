package com.example.quanlithuvien;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Dangki_thanhvien extends Application {
    private Stage primaryStage1;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("dangki_thanhvien.fxml"));;
        Scene scene = new Scene(fxmlLoader.load());

        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setTitle("Register");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void showRegistrationSuccessDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Đăng kí thành công");
        alert.setHeaderText(null);
        alert.setContentText("Đăng kí tài khoản thành công!");


        ButtonType exitButton = new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll( exitButton);

    }
}
