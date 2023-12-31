package com.example.quanlithuvien.QuanLyNguoiDung;

import Model.LichSuMuonSach;
import Model.PhieuMuonTra;
import Model.TheThanhVien;
import com.example.quanlithuvien.ConnectDatabase;
import com.example.quanlithuvien.Home;
import com.example.quanlithuvien.PhieuMuonTra.PhieuMuonTraService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

public class QLNDView implements Initializable {
    @FXML
    private TableView<LichSuMuonSach> tvSachMuon;
    @FXML
    private TableColumn<PhieuMuonTra, String> TcMaSach;
    @FXML
    private TableColumn<PhieuMuonTra, String> TcTenSach;
    @FXML
    private TableColumn<PhieuMuonTra, Date> TcNgayMuon;
    @FXML
    private TableColumn<PhieuMuonTra, Date> TcNgayTra;
    @FXML
    private TableColumn<PhieuMuonTra, Integer> TcSoLuong;
    @FXML
    private TableColumn<PhieuMuonTra, String> TcTinhTrang;
    @FXML
    private TableView<TheThanhVien> tvListUser;
    @FXML
    private TableColumn<TheThanhVien, String> TCMaDocGia;
    @FXML
    private TextField tfTimKiem;
    @FXML
    private TableColumn<TheThanhVien, String> TCTenDocGIa;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfSoDienThoai;
    @FXML
    private TextField tfTenDocGia;


//    @FXML
//    private TableColumn<TheThanhVien, String> TCSoDienThoai;
//    @FXML
//    private TableColumn<TheThanhVien, String> TCEmail;
//    @FXML
//    private TableColumn<TheThanhVien, String> TCGioiTInh;
//    @FXML
//    private TableColumn<TheThanhVien, String> TCKhoa;
    @FXML
    private TableColumn<TheThanhVien, String> ClNgayDK;
    @FXML
    private TableColumn<TheThanhVien, String> ClNgayHH;
    @FXML
    private TableColumn<TheThanhVien, String> ClTinhTrang;

    private ObservableList<TheThanhVien> theThanhVienObservableList = FXCollections.observableArrayList();
    private ObservableList<LichSuMuonSach> lichSuMuonSachObservableList = FXCollections.observableArrayList();

    ConnectDatabase connectDatabase = new ConnectDatabase();

    QLNDService qlndService = new QLNDService(connectDatabase.getConnection());
    PhieuMuonTraService phieuMuonTraService = new PhieuMuonTraService(connectDatabase.getConnection());
    private void refreshTableView(ObservableList<TheThanhVien> theThanhVienObservableList) {
        Platform.runLater(() -> {
            tvListUser.getItems().clear();
            tvListUser.setItems(theThanhVienObservableList);
        });
    }
    private void refreshTableView2(ObservableList<LichSuMuonSach> phieuMuonTraObservableList) {
        Platform.runLater(() -> {
            tvSachMuon.getItems().clear();
            TcTenSach.setCellValueFactory(new PropertyValueFactory<>("tenSachMuon"));
            TcNgayMuon.setCellValueFactory(new PropertyValueFactory<>("thoiGianMuon"));
            TcNgayTra.setCellValueFactory(new PropertyValueFactory<>("thoiGianTra"));
            TcSoLuong.setCellValueFactory(new PropertyValueFactory<>("soLuongSach"));
            TcTinhTrang.setCellValueFactory(new PropertyValueFactory<>("trangThaiHienThi"));

            tvSachMuon.setItems(phieuMuonTraObservableList);
        });
    }
    private void refreshTableViewForUserList() {
        Platform.runLater(() -> {
            tvListUser.getItems().clear();
            List<Model.TheThanhVien> theThanhVienList = qlndService.listNguoiDung();
            ObservableList<Model.TheThanhVien> phieuMuonTraObservableList = FXCollections.observableArrayList(theThanhVienList);

            tvListUser.setItems(phieuMuonTraObservableList);
        });
    }
    @Override
    public void initialize(URL location, ResourceBundle resources)  {


        TCMaDocGia.setCellValueFactory(new PropertyValueFactory<>("maDocGia"));
        TCTenDocGIa.setCellValueFactory(new PropertyValueFactory<>("tenDocGia"));
        //TCSoDienThoai.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
        //TCEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        //TCGioiTInh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        ClNgayDK.setCellValueFactory(new PropertyValueFactory<>("ngayDangKi"));
        ClNgayHH.setCellValueFactory(new PropertyValueFactory<>("ngayHetHan"));
        ClTinhTrang.setCellValueFactory(new PropertyValueFactory<>("tinhTrangThe"));
        //TCKhoa.setCellValueFactory(new PropertyValueFactory<>("khoa"));


        List<TheThanhVien> theThanhVienList = qlndService.listNguoiDung();
        ObservableList<TheThanhVien> theThanhVienObservableList = FXCollections.observableArrayList(theThanhVienList);
        tvListUser.setItems(theThanhVienObservableList);


//            tbThe.getItems().addAll((PhieuMuonTra) phieuMuonTraList);

    }

    public void btTimKiem(ActionEvent actionEvent) {
        String keyword = tfTimKiem.getText();
        List<TheThanhVien> theThanhVienList = qlndService.searchNguoiDung(keyword);
        ObservableList<TheThanhVien> theThanhVienObservableList = FXCollections.observableArrayList(theThanhVienList);
        refreshTableView(theThanhVienObservableList);
    }

    public void btXemLichSu(ActionEvent actionEvent) {
        Model.TheThanhVien selectedTheThanhVien = tvListUser.getSelectionModel().getSelectedItem();
        String maDocGia = selectedTheThanhVien.getMaDocGia();
        List<LichSuMuonSach> listLichSu = phieuMuonTraService.listPhieuMuonTraByMaDocGia(maDocGia);
        ObservableList<LichSuMuonSach> phieuMuonTraObservableList = FXCollections.observableArrayList(listLichSu);
        refreshTableView2(phieuMuonTraObservableList);


    }

    public void btChinhSua(ActionEvent actionEvent) {
        String soDienThoai = tfSoDienThoai.getText();
        String email = tfEmail.getText();
        String tenDocGia = tfTenDocGia.getText();
        TheThanhVien chonDong =  tvListUser.getSelectionModel().getSelectedItem();
        String maDocGia = chonDong.getMaDocGia();
        qlndService.suaThongTinNguoiDung(maDocGia,email, tenDocGia, soDienThoai);
        refreshTableViewForUserList();
    }

    public void btDangKiThanhVien(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("dangki_thanhvien.fxml"));
        AnchorPane root = fxmlLoader.load();
        Stage primaryStage=new Stage();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Thông kê");
        primaryStage.show();
    }
}
