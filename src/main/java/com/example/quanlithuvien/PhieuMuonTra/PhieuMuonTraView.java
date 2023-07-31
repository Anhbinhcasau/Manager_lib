package com.example.quanlithuvien.PhieuMuonTra;

import com.example.quanlithuvien.ConnectDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

public class PhieuMuonTraView {
    public PhieuMuonTraView(){

    }
    ConnectDatabase connectDatabase = new ConnectDatabase();
    PhieuMuonTraController phieuMuonTraController = new PhieuMuonTraController(connectDatabase.getConnection());

    public void handleThemPhieuMuonTra() {
        LocalDate localDateThoiGianMuon = LocalDate.of(2023, 7, 31);
        Date thoiGianMuon = Date.from(localDateThoiGianMuon.atStartOfDay(ZoneId.systemDefault()).toInstant());
        HashMap<String, Integer> listSachMuon = new HashMap<String, Integer>();
        listSachMuon.put("1", 2);
        listSachMuon.put("2", 4);
        listSachMuon.put("3", 6);
        phieuMuonTraController.handleAddPhieuMuonTra("PM2101", thoiGianMuon, thoiGianMuon, listSachMuon, false);
    }

    public void handleTraPhieu(){
        phieuMuonTraController.handleTraSach("Okrsz6");
        System.out.println("Tra sach thanh cong");
    }


}
