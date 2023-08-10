package com.example.quanlithuvien.PhieuMuonTra;

import Model.PhieuMuonTra;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class PhieuMuonTraController {
    private PhieuMuonTraService phieuMuonTraService;

//    @FXML
//    public ImageView delete_book,delete_loan,edit_sach,IMG_ADD,doc_g;

    public PhieuMuonTraController(Connection connection) {
        phieuMuonTraService = new PhieuMuonTraService(connection);
    }
    public void handleAddPhieuMuonTra(String maDocGia, Date thoiGianMuon, Date thoiGianTra, HashMap<String, Integer> sachMuon, Boolean trangThai) {
        PhieuMuonTra phieuMuonTra = new PhieuMuonTra(maDocGia, thoiGianMuon, thoiGianTra, sachMuon, trangThai);
        phieuMuonTraService.addPhieuMuonTra(phieuMuonTra);
    }
    public void findByIdName(String tenSach){

    }
    public void handleTraSach(String maPhieuMuonTra){
        phieuMuonTraService.traSach(maPhieuMuonTra);
    }



}
