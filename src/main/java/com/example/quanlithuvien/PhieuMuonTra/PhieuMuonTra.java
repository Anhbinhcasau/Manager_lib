package com.example.quanlithuvien.PhieuMuonTra;

import com.example.quanlithuvien.Home;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PhieuMuonTra extends Application {
    //tui quen dev 2 cai button do //xoa sach + chinh sua sach
    // quá hạn nữa hùng ơi nó hiện lên chỗ qlnd á
    //hung dev r a de lam lai
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("QuanLyMuonTra.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Quản lý mượn trả");
        primaryStage.show();
    }
}
