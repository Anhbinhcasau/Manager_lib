package com.example.quanlithuvien.PhieuMuonTra;

import javafx.application.Application;
import javafx.stage.Stage;

public class PhieuMuonTra extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        PhieuMuonTraView phieuMuonTraView = new PhieuMuonTraView();
        phieuMuonTraView.handleThemPhieuMuonTra();
//        phieuMuonTraView.handleTraPhieu();
    }
}
