package com.example.quanlithuvien;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(new Scene(root, 800, 500));

        // Bắt sự kiện thay đổi kích thước cửa sổ
        primaryStage.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            // Thực hiện thay đổi kích thước và vị trí các thành phần trong giao diện tại đây
        });

        primaryStage.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            // Thực hiện thay đổi kích thước và vị trí các thành phần trong giao diện tại đây
        });

        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
