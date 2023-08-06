package com.example.quanlithuvien.PhieuMuonTra;

import Model.PhieuMuonTra;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PhieuMuonTraController {
    private PhieuMuonTraService phieuMuonTraService;

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
