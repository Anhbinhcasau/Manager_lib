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
                String ndk=resultSet.getString("NgayDangKy");
                String nhh=resultSet.getString("NgayHetHan");
                String ttt=resultSet.getString("TinhTrangThe");

                TheThanhVien nguoiDung = new TheThanhVien(maThe, hoTen, soDienThoai, email, gioTinh, khoa,ttt,ndk,nhh);
                nguoiDungList.add(nguoiDung);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nguoiDungList;
    }

    public void suaThongTinNguoiDung(String maND, String email, String hoTen, String soDienThoai){
        String updateQuery = "UPDATE thethanhvien SET (email, tenDocGia, soDienThoai) = (?, ?, ?) WHERE maDocGia = ?";
        try(PreparedStatement statement = connection.prepareStatement(updateQuery)){
            statement.setString(1, email);
            statement.setString(2, hoTen);
            statement.setString(3, soDienThoai);
            statement.setString(4, maND);
            statement.executeUpdate();
        }
        catch(Exception exception){
            System.out.println("Chua them du thong tin");
        }
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
                    String ndk=resultSet.getString("NgayDangKy");
                    String nhh=resultSet.getString("NgayHetHan");
                    String ttt=resultSet.getString("TinhTrangThe");

                    TheThanhVien nguoiDung = new TheThanhVien(maDocGia, tenDocGia, soDienThoai, email, gioiTinh, khoa,ttt,ndk,nhh);
                    result.add(nguoiDung);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return result;
    }
}
