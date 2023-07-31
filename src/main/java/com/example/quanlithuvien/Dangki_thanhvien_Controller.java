package com.example.quanlithuvien;

import Model.Book;
import Model.Khoa;
import Model.TheLoai;
import Model.TheThanhVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Dangki_thanhvien_Controller implements Initializable {
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
    String khoa;
    ObservableList<Khoa> khoaList= FXCollections.observableArrayList();
    ObservableList<String> items = FXCollections.observableArrayList();

    public  void DangKi(String madocgia,String tendocgia,String sdt,String email,String gioitinh,String khoa) throws SQLException {

        TheThanhVien theThanhVien=new TheThanhVien();
        theThanhVien.setMaDocGia(madocgia);
        theThanhVien.setTenDocGia(tendocgia);
        theThanhVien.setSoDienThoai(sdt);
        theThanhVien.setEmail(email);
        theThanhVien.setGioiTinh(gioitinh);
        theThanhVien.setKhoa(khoa);
        ////Chọn giói tính



        String insertTV="insert into thethanhvien(maDocGia,tenDocGia,soDienThoai,email,gioiTinh,khoa) values (?,?,?,?,?,?)";
        PreparedStatement statement=connection.prepareStatement(insertTV);
        statement.setString(1,theThanhVien.getMaDocGia());
        statement.setString(2,theThanhVien.getTenDocGia());
        statement.setString(3,theThanhVien.getSoDienThoai());
        statement.setString(4,theThanhVien.getEmail());
        statement.setString(5,theThanhVien.getGioiTinh());
        statement.setString(6,theThanhVien.getKhoa());

        statement.executeUpdate();



    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            String selectKhoa="select tenKhoa from khoa";
            PreparedStatement statement=connection.prepareStatement(selectKhoa);
            ResultSet kh=statement.executeQuery();

            while (kh.next()){
                String tenKhoa1=kh.getString("tenKhoa");
                Khoa thanhVien=new Khoa(tenKhoa1);
                khoaList.add(thanhVien);

            }


            choiceKhoa.getItems().addAll(khoaList);
            for (Khoa khoa1 : khoaList) {
                items.add(khoa1.getTenKhoa());
                System.out.println(items);
            }
            choiceKhoa.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    khoa = (String) choiceKhoa.getValue();

                }
            });
            choiceKhoa.setItems(items);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void Dangki_ThanhVien(ActionEvent actionEvent) throws SQLException {
        ToggleGroup group=new ToggleGroup();
        radiobtNam.setToggleGroup(group);
        radiobtNu.setToggleGroup(group);
        String gender = ((RadioButton) group.getSelectedToggle()).getText();
        DangKi(textMaDocGia.getText(),textTenDG.getText(),textSDT.getText(),textEmail.getText(),gender,khoa);
    }
}
