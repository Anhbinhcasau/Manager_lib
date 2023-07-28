package com.example.quanlithuvien;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.sql.Connection;

public class Dangki_thanhvien_Controller {
    ConnectDatabase data = new ConnectDatabase();
    Connection connection = data.getConnection();
    @FXML
    private TextField  textMaDocGia;
    @FXML
    private TextField  textTenDG;
    @FXML
    private TextField  textSDT;
    @FXML
    private TextField  textEmail;
    @FXML
    private RadioButton radiobtNam;
    @FXML
    private RadioButton radiobtNu;
    @FXML
    private ChoiceBox choiceKhoa;

    public  void DangKi(String madocgia,String tendocgia,String sdt,String email,String gioitinh,String khoa)
    {

    }




}
