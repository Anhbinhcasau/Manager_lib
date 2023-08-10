package com.example.quanlithuvien.ThongKe;

import Model.LichSuMuonSach;
import Model.TheThanhVien;
import Model.ThongKe;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThongKeService {
    private Connection connection;
    public ThongKeService(Connection connection) {
        this.connection = connection;
    }


    public List<ThongKe> listThongKe(Date ngayMuon, Date ngayTra) {
        List<ThongKe> thongKeList = new ArrayList<>();

        String queryForBorrow = "SELECT s.idSach, s.tenSach, SUM(sm.soLuongMuon) AS tongSoLuongMuon " +
                "FROM phieumuontra pm " +
                "JOIN sachmuon sm ON pm.maPhieuMuonTra = sm.phieuMuon " +
                "JOIN book s ON sm.idSach = s.idSach " +
                "WHERE (pm.ngayMuon >= ? OR pm.ngayTra <= ?) AND pm.trangThai = 0 " +
                "GROUP BY s.idSach, s.tenSach ";
        String queryForReturn = "SELECT s.idSach, s.tenSach, SUM(sm.soLuongMuon) AS tongSoLuongTra " +
                "FROM phieumuontra pm " +
                "JOIN sachmuon sm ON pm.maPhieuMuonTra = sm.phieuMuon " +
                "JOIN book s ON sm.idSach = s.idSach " +
                "WHERE (pm.ngayMuon >= ? OR pm.ngayTra <= ?) AND pm.trangThai = 1 " +
                "GROUP BY s.idSach, s.tenSach ";

        try (PreparedStatement statementForBorrow = connection.prepareStatement(queryForBorrow);
             PreparedStatement statementForReturn = connection.prepareStatement(queryForReturn)) {
            java.sql.Date ngayMuonSQL = new java.sql.Date(ngayMuon.getTime());
            java.sql.Date ngayTraSQL = new java.sql.Date(ngayTra.getTime());

            statementForBorrow.setDate(1,  ngayMuonSQL);
            statementForBorrow.setDate(2,  ngayTraSQL);

            statementForReturn.setDate(1,  ngayMuonSQL);
            statementForReturn.setDate(2,  ngayTraSQL);

            ResultSet borrowResult = statementForBorrow.executeQuery();
            ResultSet returnResult = statementForReturn.executeQuery();

            while (borrowResult.next()) {
                String idSachBorrow = borrowResult.getString("idSach");
                String tenSachBorrow = borrowResult.getString("tenSach");
                int tongSoLuongMuon = borrowResult.getInt("tongSoLuongMuon");
                int tongSoLuongTra = 0;

                while (returnResult.next()) {
                    if (returnResult.getString("idSach").equals(idSachBorrow)) {
                        tongSoLuongTra = returnResult.getInt("tongSoLuongTra");
                        break;
                    }
                }

                ThongKe thongKe = new ThongKe(idSachBorrow, tenSachBorrow, tongSoLuongMuon, tongSoLuongTra);
                thongKeList.add(thongKe);

                // Reset the returnResult cursor before processing the next borrowResult
                returnResult.beforeFirst();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return thongKeList;
    }

    public List<ThongKe> listChiTietMuon(String maSach, Date ngayMuon, Date ngayTra) {
        List<ThongKe> thongKeList = new ArrayList<>();

        String queryForListDetails = "SELECT pm.*, sm.soLuongMuon, s.tenSach " +
                "FROM phieumuontra pm " +
                "JOIN sachmuon sm ON pm.maPhieuMuonTra = sm.phieuMuon " +
                "JOIN book s ON sm.idSach = s.idSach " +
                "WHERE sm.idSach = ? AND (pm.ngayMuon >= ? OR pm.ngayTra <= ?)";

        try (PreparedStatement statement = connection.prepareStatement(queryForListDetails)) {
            statement.setString(1, maSach);
            statement.setDate(2, new java.sql.Date(ngayMuon.getTime()));
            statement.setDate(3, new java.sql.Date(ngayTra.getTime()));

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Date ngayMuonResult = resultSet.getDate("ngayMuon");
                Date ngayTraResult = resultSet.getDate("ngayTra");
                String tenSach = resultSet.getString("tenSach");
                int soLuongMuon = resultSet.getInt("soLuongMuon");
                String maDocGia = resultSet.getString("maDocGia");
                Boolean trangThai = resultSet.getBoolean("trangThai");
                System.out.println(trangThai);

                ThongKe thongKe = new ThongKe(tenSach, ngayMuonResult, ngayTraResult, maDocGia, soLuongMuon, trangThai);
                thongKeList.add(thongKe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return thongKeList;
    }



}
