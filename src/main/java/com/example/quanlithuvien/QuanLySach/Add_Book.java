package com.example.quanlithuvien.QuanLySach;

import com.example.quanlithuvien.Home;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Add_Book extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("add_book.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(new Scene(scene.getRoot(), 800, 500));

        // Bắt sự kiện thay đổi kích thước cửa sổ
        primaryStage.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            // Thực hiện thay đổi kích thước và vị trí các thành phần trong giao diện tại đây
        });

        primaryStage.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            // Thực hiện thay đổi kích thước và vị trí các thành phần trong giao diện tại đây
        });

        primaryStage.show();;
    }

}
