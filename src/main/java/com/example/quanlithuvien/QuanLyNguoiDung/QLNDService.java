package com.example.quanlithuvien.QuanLyNguoiDung;

import Model.PhieuMuonTra;
import Model.TheThanhVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QLNDService {
    private Connection connection;
    public QLNDService(Connection connection) {
        this.connection = connection;
    }

    public List<TheThanhVien> listNguoiDung() {
        List<TheThanhVien> nguoiDungList = new ArrayList<>();

        String query = "SELECT * FROM thethanhvien";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String maThe = resultSet.getString("maDocGia");
                String hoTen = resultSet.getString("tenDocGia");
                String soDienThoai = resultSet.getString("soDienThoai");
                String email = resultSet.getString("email");
                String gioTinh = resultSet.getString("gioiTinh");
                String khoa = resultSet.getString("khoa");

                TheThanhVien nguoiDung = new TheThanhVien(maThe, hoTen, soDienThoai, email, gioTinh, khoa);
                nguoiDungList.add(nguoiDung);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nguoiDungList;
    }

    public List<TheThanhVien> searchNguoiDung(String keyword) {
            List<TheThanhVien> result = new ArrayList<>();
            String query = "SELECT * FROM thethanhvien WHERE maDocGia LIKE ? OR tenDocGia LIKE ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, "%" + keyword + "%");
                statement.setString(2, "%" + keyword + "%");

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String maDocGia = resultSet.getString("maDocGia");
                    String tenDocGia = resultSet.getString("tenDocGia");
                    String soDienThoai = resultSet.getString("soDienThoai");
                    String email = resultSet.getString("email");
                    String gioiTinh = resultSet.getString("gioiTinh");
                    String khoa = resultSet.getString("khoa");

                    TheThanhVien nguoiDung = new TheThanhVien(maDocGia, tenDocGia, soDienThoai, email, gioiTinh, khoa);
                    result.add(nguoiDung);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return result;
    }
}
