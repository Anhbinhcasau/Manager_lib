package com.example.quanlithuvien;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ImageView exit,book,loan,user,setting,thongke;
    @FXML
    private StackPane stpane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        File image = new File("img/img.png");
        Image img = new Image(image.toURI().toString());
        book.setImage(img);
        File image1 = new File("img/exit.png");
        Image img1 = new Image(image1.toURI().toString());
        exit.setImage(img1);
        File image4 = new File("img/loan.png");
        Image img4 = new Image(image4.toURI().toString());
        loan.setImage(img4);
        File image5 = new File("img/user.png");
        Image img5 = new Image(image5.toURI().toString());
        user.setImage(img5);
        File image6 = new File("img/logout.png");
        Image img6 = new Image(image6.toURI().toString());
        setting.setImage(img6);
        File image7 = new File("img/thong.png");
        Image img7 = new Image(image7.toURI().toString());
        thongke.setImage(img7);


        exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
    }

    public void quanlysach(ActionEvent actionEvent) throws Exception{
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("add_book.fxml"));
        stpane.getChildren().removeAll();
        stpane.getChildren().setAll(fxmlLoader);

    }

    public void quanly_muontra(ActionEvent actionEvent) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("QuanLyMuonTra.fxml"));
        stpane.getChildren().removeAll();
        stpane.getChildren().setAll(fxmlLoader);

    }

    public void quanly_nguoidung(ActionEvent actionEvent) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("KiemTraNguoiDung.fxml"));
        stpane.getChildren().removeAll();
        stpane.getChildren().setAll(fxmlLoader);
    }

    public void dangxuat(ActionEvent actionEvent) {
    }

    public void thongke(ActionEvent actionEvent) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("ThongKe.fxml"));
        stpane.getChildren().removeAll();
        stpane.getChildren().setAll(fxmlLoader);
    }
}
