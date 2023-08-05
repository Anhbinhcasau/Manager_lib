package com.example.quanlithuvien;

import Model.Khoa;
import Model.KichHoat;
import Model.TheLoai;
import Model.TheThanhVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.Parent;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class Mangaer_Kichhoat_Controller extends Parent implements Initializable  {
    ConnectDatabase data = new ConnectDatabase();
    Connection connection = data.getConnection();
    @FXML
    private TextField textMaDocGia;
    @FXML
    private DatePicker dateNgayKichHoat;
    @FXML
    private DatePicker dateHetHan;
    @FXML
    private TextField textTinhTrang;
    @FXML
    private TableColumn<TheLoai,String  > ClMaDG;
    @FXML
    private TableColumn<TheLoai,String  > ClTenDG;
    @FXML
    private TableColumn<TheLoai,String  > ClNgayKichHoat;
    @FXML
    private TableColumn<TheLoai,String  > ClHetHan;
    @FXML
    private TableColumn<TheLoai,String  > ClTinhTrang;
    @FXML
    private TableView<KichHoat> tbThe;
    @FXML
    private Label erroMaDG;
    ObservableList<KichHoat> listTheTV= FXCollections.observableArrayList();

    String tenDG;

    public void btKichHoat(ActionEvent actionEvent) throws SQLException {
        //Lấy tên độc giả
        tenDGia(textMaDocGia.getText());

        // Đổi đinh dạng của ngày
        LocalDate localDateKH = dateNgayKichHoat.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatnkh = localDateKH.format(formatter);
        LocalDate localDateHH = dateHetHan.getValue();
        String formatnkt = localDateHH.format(formatter);
        //Kiểm tra mã sinh viên

        String checkIdExistInTheThanhVien = "SELECT * FROM thethanhvien WHERE maDocGia = ?";
        //Query kiểm tra maDocGia co ton tai trong bang thethanhvien

        try {
            PreparedStatement statement = connection.prepareStatement(checkIdExistInTheThanhVien);
            statement.setString(1, textMaDocGia.getText());
            ResultSet kh = statement.executeQuery();
            while (kh.next()) {
                String madg = kh.getString("maDocGia");
                System.out.println("madg");
                if (textMaDocGia.getText().equals(madg)) {
                    System.out.println("Đăng ký thành công");
                    KichHoat(textMaDocGia.getText(), formatnkh, formatnkt, textTinhTrang.getText());
                    erroMaDG.setVisible(false);
                    return;
                }
            }
                System.out.println("Sai ma doc gia");
                erroMaDG.setTextFill(Color.RED);
                erroMaDG.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void KichHoat(String maDocGia, String ngaykichhoat, String ngayhethan, String tinhtrang) throws SQLException {
        KichHoat kh = new KichHoat();
        kh.setMaDocGia(maDocGia);
        kh.setTenDG(tenDG);
        kh.setNgayKichHoat(ngaykichhoat);
        kh.setNgayHetHan(ngayhethan);
        kh.setTinhTrang(tinhtrang);

        String insertKH="insert into kichhoat(maDocGia,tenDocGia,ngayKichHoat,ngayHetHan,tinhTrang) values (?,?,?,?,?)";
        PreparedStatement statement=connection.prepareStatement(insertKH);
        statement.setString(1,kh.getMaDocGia());
        statement.setString(2,kh.getTenDG());
        statement.setString(3,  kh.getNgayKichHoat());
        statement.setString(4,  kh.getNgayHetHan());
        statement.setString(5,kh.getTinhTrang());
        statement.executeUpdate();

    }

    public void tenDGia(String MaDG) {
        try {
            String selectTTV = "select maDocGia,tenDocGia from thethanhvien";
            PreparedStatement statement = connection.prepareStatement(selectTTV);
            ResultSet kh = statement.executeQuery();

            while (kh.next()) {
                String madg = kh.getString("maDocGia");
                String tendg=kh.getString("tenDocGia");
                if(MaDG.equals(madg)) this.tenDG=tendg;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String queryTheTV="select maDocGia,tenDocGia,ngayKichHoat,ngayHetHan,tinhTrang from kichhoat ";
        Statement statement= null;
        try {
            statement = connection.createStatement();
            ResultSet the=statement.executeQuery(queryTheTV);
            while (the.next()){
                String maDG=the.getString("maDocGia");
                String tenDocGia=the.getString("tenDocGia");
                String ngaykh=the.getString("ngayKichHoat");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(ngaykh, formatter);
                String ngaykichhoat = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                String ngaykt=the.getString("ngayHetHan");
                LocalDate date1 = LocalDate.parse(ngaykt, formatter);
                String ngayhethan = date1.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                String tt=the.getString("tinhTrang");

                listTheTV.addAll(new KichHoat(maDG,tenDocGia,ngaykichhoat,ngayhethan,tt));
            }
            ClMaDG.setCellValueFactory(new PropertyValueFactory<>("maDocGia"));
            ClTenDG.setCellValueFactory(new PropertyValueFactory<>("tenDG"));
            ClNgayKichHoat.setCellValueFactory(new PropertyValueFactory<>("ngayKichHoat"));
            ClHetHan.setCellValueFactory(new PropertyValueFactory<>("ngayHetHan"));
            ClTinhTrang.setCellValueFactory(new PropertyValueFactory<>("tinhTrang"));
            tbThe.setItems(listTheTV);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
