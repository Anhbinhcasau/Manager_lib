package com.example.quanlithuvien;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void btHung(ActionEvent actionEvent) {

    }

    public void btnDKThanhVien(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("dangki_thanhvien.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage primaryStage=new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle( "Đăng kí thành viên ");
        primaryStage.show();
    }

    public void btnQuanlisach(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("add_book.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage primaryStage=new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle( "Đăng kí thành viên ");
        primaryStage.show();
    }

    public void btnQuanlimuontra(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("PhieuMuon.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage primaryStage=new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle( "Đăng kí thành viên ");
        primaryStage.show();
    }

    public void btnQuanlythe(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("manager_docgia.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage primaryStage=new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle( "Đăng kí thành viên ");
        primaryStage.show();
    }
}