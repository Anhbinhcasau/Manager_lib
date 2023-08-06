package com.example.quanlithuvien;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    public ImageView imageView;

    public void initialize(URL url, ResourceBundle rb) {
        // Load the image from a file
        File image = new File("img/UILogin.png");
        Image image1 = new Image(image.toURI().toString());
        imageView.setImage(image1);

    }

    public void btnlogin(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Login !");
        stage.setScene(scene);
        stage.show();
    }
}
