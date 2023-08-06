package com.example.quanlithuvien.ThongKe;

import Model.LichSuMuonSach;
import Model.TheThanhVien;
import Model.ThongKe;
import com.example.quanlithuvien.ConnectDatabase;
import com.example.quanlithuvien.QuanLyNguoiDung.QLNDService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

public class ThongKeController implements Initializable {


    @FXML
    private TableView<ThongKe> thongKeTable;

    @FXML
    private TableColumn<ThongKe, String> maSachColumn;

    @FXML
    private TableColumn<ThongKe, String> tenSachColumn;

    @FXML
    private TableColumn<ThongKe, Integer> soLuongMuonColumn;

    @FXML
    private TableColumn<ThongKe, Integer> conNoColumn;

    @FXML
    private TableView<ThongKe> chiTietTable;

    @FXML
    private TableColumn<ThongKe, String> tenSachChiTietColumn;

    @FXML
    private TableColumn<ThongKe, Date> ngayTraColumn;
    @FXML
    private TableColumn<ThongKe, String> maNguoiMuonColumn;

    @FXML
    private TableColumn<ThongKe, Integer> soLuongColumn;

    @FXML
    private TableColumn<ThongKe, String> tinhTrangColumn;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    private ObservableList<ThongKe> listSachThongKeObservableList = FXCollections.observableArrayList();

    ConnectDatabase connectDatabase = new ConnectDatabase();

    ThongKeService thongKeService = new ThongKeService(connectDatabase.getConnection());

    @Override
    public void initialize(URL location, ResourceBundle resources)  {

        maSachColumn.setCellValueFactory(new PropertyValueFactory<>("maSach"));
        tenSachColumn.setCellValueFactory(new PropertyValueFactory<>("tenSach"));
        soLuongMuonColumn.setCellValueFactory(new PropertyValueFactory<>("soLuongMuon"));
        conNoColumn.setCellValueFactory(new PropertyValueFactory<>("soLuongNo"));



    }

    private void refreshTableView(ObservableList<ThongKe> thongKeObservableList) {
        Platform.runLater(() -> {
            thongKeTable.getItems().clear();
            thongKeTable.setItems(thongKeObservableList);
        });
    }
    private void refreshTableView2(ObservableList<ThongKe> thongKeObservableList) {
        Platform.runLater(() -> {
            chiTietTable.getItems().clear();
            tenSachChiTietColumn.setCellValueFactory(new PropertyValueFactory<>("tenSach"));
            ngayTraColumn.setCellValueFactory(new PropertyValueFactory<>("ngayTra"));
            maNguoiMuonColumn.setCellValueFactory(new PropertyValueFactory<>("maDocGia"));
            soLuongColumn.setCellValueFactory(new PropertyValueFactory<>("soLuongMuon"));
            tinhTrangColumn.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
            chiTietTable.setItems(thongKeObservableList);
        });
    }

    public void filterBooks(ActionEvent actionEvent) {
        String soLuongMuon = startDatePicker.getEditor().getText();
        String soLuongTra = endDatePicker.getEditor().getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date ngayMuonDate, ngayTraDate;
        try {
            ngayMuonDate = dateFormat.parse(soLuongMuon);
            ngayTraDate = dateFormat.parse(soLuongTra);
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        List<ThongKe> listThongKe = thongKeService.listThongKe(ngayMuonDate, ngayTraDate);
        ObservableList<ThongKe> thongKeObservableList = FXCollections.observableArrayList(listThongKe);
        refreshTableView(thongKeObservableList);
    }

    public void detailBooks(ActionEvent actionEvent) {
        String soLuongMuon = startDatePicker.getEditor().getText();
        String soLuongTra = endDatePicker.getEditor().getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date ngayMuonDate, ngayTraDate;
        try {
            ngayMuonDate = dateFormat.parse(soLuongMuon);
            ngayTraDate = dateFormat.parse(soLuongTra);
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        Model.ThongKe selectedTheThanhVien = thongKeTable.getSelectionModel().getSelectedItem();
        String maSach = selectedTheThanhVien.getMaSach();
        List<ThongKe> listThongKeChiTiet = thongKeService.listChiTietMuon(maSach,ngayMuonDate, ngayTraDate);
        ObservableList<ThongKe> thongKeObservableList = FXCollections.observableArrayList(listThongKeChiTiet);
        refreshTableView2(thongKeObservableList);
    }
}


