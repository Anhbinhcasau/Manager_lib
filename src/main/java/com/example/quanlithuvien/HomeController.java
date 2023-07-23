package com.example.quanlithuvien;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
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
}
