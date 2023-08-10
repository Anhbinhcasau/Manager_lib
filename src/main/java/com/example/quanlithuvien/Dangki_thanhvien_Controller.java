package com.example.quanlithuvien;

import Model.Book;
import Model.Khoa;
import Model.TheLoai;
import Model.TheThanhVien;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Dangki_thanhvien_Controller implements Initializable {
    private Stage primaryStage;
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
    @FXML
    private DatePicker dateNgayDK;
    @FXML
    private DatePicker dateNgayHetHan;
    @FXML
    private ImageView img_register;


    String khoa;
    ObservableList<Khoa> khoaList= FXCollections.observableArrayList();
    ObservableList<String> items = FXCollections.observableArrayList();

    public  void DangKi(String madocgia,String tendocgia,String sdt,String email,String gioitinh,String khoa,String Ngaydangki,String Ngayhethan) throws SQLException {

        TheThanhVien theThanhVien=new TheThanhVien();
        theThanhVien.setMaDocGia(madocgia);
        theThanhVien.setTenDocGia(tendocgia);
        theThanhVien.setSoDienThoai(sdt);
        theThanhVien.setEmail(email);
        theThanhVien.setGioiTinh(gioitinh);
        theThanhVien.setKhoa(khoa);
        theThanhVien.setNgayDangKi(Ngaydangki);
        theThanhVien.setNgayHetHan(Ngayhethan);
        theThanhVien.setTinhTrangThe("Đang sử dụng");
        ////Chọn giói tínhDangki_thanhvien tv = new Dangki_thanhvien();
        //        tv.setPrimaryStage(primaryStage);
        //        tv.showRegistrationSuccessDialog();



        String insertTV="insert into thethanhvien(maDocGia,tenDocGia,soDienThoai,email,gioiTinh,khoa,NgaydangKy,NgayHetHan,TinhTrangThe) values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement=connection.prepareStatement(insertTV);
        statement.setString(1,theThanhVien.getMaDocGia());
        statement.setString(2,theThanhVien.getTenDocGia());
        statement.setString(3,theThanhVien.getSoDienThoai());
        statement.setString(4,theThanhVien.getEmail());
        statement.setString(5,theThanhVien.getGioiTinh());
        statement.setString(6,theThanhVien.getKhoa());
        statement.setString(7,theThanhVien.getNgayDangKi());
        statement.setString(8,theThanhVien.getNgayHetHan());
        statement.setString(9,theThanhVien.getTinhTrangThe());

        statement.executeUpdate();



    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File image = new File("img/Register.png");
        Image img = new Image(image.toURI().toString());
        img_register.setImage(img);

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
        LocalDate localDateKH = dateNgayDK.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatnkh = localDateKH.format(formatter);
        LocalDate localDateHH = dateNgayHetHan.getValue();
        String formatnkt = localDateHH.format(formatter);
        String gender = ((RadioButton) group.getSelectedToggle()).getText();
        DangKi(textMaDocGia.getText(),textTenDG.getText(),textSDT.getText(),textEmail.getText(),gender,khoa,formatnkh,formatnkt);
       // this.primaryStage = primaryStage;
        Dangki_thanhvien tv = new Dangki_thanhvien();
        tv.showRegistrationSuccessDialog();
    }

}
