package com.example.quanlithuvien.PhieuMuonTra;

import Model.LichSuMuonSach;
import Model.PhieuMuonTra;
import com.example.quanlithuvien.Books.BooksService;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PhieuMuonTraService {
    private Connection connection;
    public PhieuMuonTraService(Connection connection) {
        this.connection = connection;
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 6;

    public static String generateRandomString() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
    public void addPhieuMuonTra(PhieuMuonTra phieuMuonTra) {
        String checkStatusQuery = "SELECT trangThai FROM phieumuontra WHERE maDocGia = ?";
        boolean isTrangThaiTrue = true;

        try (PreparedStatement checkStatusStatement = connection.prepareStatement(checkStatusQuery)) {
            System.out.println(phieuMuonTra.getMaPhieu());
            checkStatusStatement.setString(1, phieuMuonTra.getMaDocGia());
            ResultSet resultSet = checkStatusStatement.executeQuery();

            if (resultSet.next()) {
                isTrangThaiTrue = resultSet.getBoolean("trangThai");
                System.out.println(isTrangThaiTrue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String query = "INSERT INTO phieumuontra (maPhieuMuonTra ,ngayMuon, ngayTra, maDocGia, trangThai) " +
                "VALUES (?, ?, ?, ?, ?)";
        String queryForRentBook = "INSERT INTO sachmuon (phieuMuon, idSach, soLuongMuon) " +
                "VALUES (?, ?, ?)";
        if (isTrangThaiTrue) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            BooksService booksService = new BooksService(connection);
            String idRandom = generateRandomString();
            statement.setString(1, idRandom);
            statement.setString(4, phieuMuonTra.getMaDocGia());
            statement.setBoolean(5, false);

            statement.setDate(2, new java.sql.Date(phieuMuonTra.getThoiGianMuon().getTime()));
            statement.setDate(3, new java.sql.Date(phieuMuonTra.getThoiGianTra().getTime()));
            PreparedStatement statementForRent = connection.prepareStatement(queryForRentBook);
            statement.executeUpdate();
            for(Map.Entry<String, Integer> entry : phieuMuonTra.getSachMuon().entrySet()){
                String idSach = entry.getKey();
                int soLuong = entry.getValue();
                booksService.subtractQuantityBook(idSach, soLuong);
                statementForRent.setString(1, idRandom);
                statementForRent.setString(2, idSach);
                statementForRent.setInt(3, soLuong);
                statementForRent.executeUpdate();
            }


        } catch (SQLException e) {
            System.out.println("Thẻ đã hết hạn");
        } } else {
            System.out.println("Không thể thêm dữ liệu với maPhieuMuonTra vì vẫn còn đang mượn.");
        }
    }
    public String getBookNameById(String idSach) {
        String query = "SELECT tenSach FROM book WHERE idSach = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, idSach);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("tenSach");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void traSach(String maPhieuMuonTra) {
        String updateQuery = "UPDATE phieumuontra SET trangThai = ? WHERE maPhieuMuonTra = ?";
        String selectQuery = "SELECT * FROM sachmuon WHERE phieuMuon = ?";

        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
             PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {

            updateStatement.setBoolean(1, true);
            updateStatement.setString(2, maPhieuMuonTra);
            updateStatement.executeUpdate();

            selectStatement.setString(1, maPhieuMuonTra);
            ResultSet queryOutput = selectStatement.executeQuery();

            BooksService booksService = new BooksService(connection);
            while (queryOutput.next()) {
                String idSach = queryOutput.getString("idSach");
                int soLuong = queryOutput.getInt("soLuongMuon");
                booksService.plusQuantityBook(idSach, soLuong);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PhieuMuonTra> listPhieuMuonTra() {
        String query = "SELECT * FROM phieumuontra";
        String queryGetListBook = "SELECT pm.maPhieuMuonTra, sm.soLuongMuon, s.tenSach " +
                "FROM sachmuon sm " +
                "JOIN phieumuontra pm ON sm.phieuMuon = pm.maPhieuMuonTra " +
                "JOIN book s ON sm.idSach = s.idSach";

        List<PhieuMuonTra> phieuMuonTraList = new ArrayList<>();
        HashMap<String, HashMap<String, Integer>> getListBookRent = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            PreparedStatement statementForGetListBookRent = connection.prepareStatement(queryGetListBook);
            ResultSet dataOutputForListBookRent = statementForGetListBookRent.executeQuery();
            while(dataOutputForListBookRent.next()){
                String maPhieuMuonTra = dataOutputForListBookRent.getString("maPhieuMuonTra");
                String tenSach = dataOutputForListBookRent.getString("tenSach");
                System.out.println(tenSach);
                int soLuongMuon = dataOutputForListBookRent.getInt("soLuongMuon");

                if (getListBookRent.containsKey(maPhieuMuonTra)) {
                    HashMap<String, Integer> sachInfo = getListBookRent.get(maPhieuMuonTra);
                    sachInfo.put(tenSach, soLuongMuon);
                } else {
                    HashMap<String, Integer> sachInfo = new HashMap<>();
                    sachInfo.put(tenSach, soLuongMuon);
                    getListBookRent.put(maPhieuMuonTra, sachInfo);
                }
            }
            ResultSet queryOutput = statement.executeQuery();
            while (queryOutput.next()) {
                String idPhieu = queryOutput.getString("maPhieuMuonTra");
                Date ngayMuon = queryOutput.getDate("NgayMuon");
                Date ngayTra = queryOutput.getDate("NgayTra");
                String maDocGia = queryOutput.getString("maDocGia");
                Boolean trangThai = queryOutput.getBoolean("trangThai");
                HashMap<String, Integer> sachInfo = getListBookRent.get(idPhieu);
//                System.out.println(sachInfo);
                PhieuMuonTra phieuMuonTra = new PhieuMuonTra(idPhieu, maDocGia, ngayMuon, ngayTra, sachInfo, trangThai);
                phieuMuonTraList.add(phieuMuonTra);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phieuMuonTraList;
    }

    public List<LichSuMuonSach> listPhieuMuonTraByMaDocGia(String maDocGia) {
        String query = "SELECT pm.*, sm.soLuongMuon, s.tenSach " +
                "FROM phieumuontra pm " +
                "JOIN sachmuon sm ON pm.maPhieuMuonTra = sm.phieuMuon " +
                "JOIN book s ON sm.idSach = s.idSach " +
                "WHERE pm.maDocGia = ?";

        List<LichSuMuonSach> phieuMuonTraList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, maDocGia);

            ResultSet queryOutput = statement.executeQuery();
            while (queryOutput.next()) {
                String maPhieuMuonTra = queryOutput.getString("maPhieuMuonTra");
                Date ngayMuon = queryOutput.getDate("NgayMuon");
                Date ngayTra = queryOutput.getDate("NgayTra");
                Boolean trangThai = queryOutput.getBoolean("trangThai");
                String tenSachMuon = queryOutput.getString("tenSach");
                int soLuongMuon = queryOutput.getInt("soLuongMuon");
                LichSuMuonSach lichSuMuonSach = new LichSuMuonSach(ngayMuon, ngayTra, tenSachMuon, soLuongMuon,tenSachMuon ,trangThai);
                phieuMuonTraList.add(lichSuMuonSach);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return phieuMuonTraList;
    }

    public void xoaPhieuMuonTra(String maPhieuMuonTra) {
        String deleteQuery = "DELETE FROM phieumuontra WHERE maPhieuMuonTra = ?";
        String deleteRentBooksQuery = "DELETE FROM sachmuon WHERE phieuMuon = ?";

        try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
             PreparedStatement deleteRentBooksStatement = connection.prepareStatement(deleteRentBooksQuery)) {

            // Xóa dữ liệu trong bảng sachmuon trước để không vi phạm khóa ngoại
            deleteRentBooksStatement.setString(1, maPhieuMuonTra);
            deleteRentBooksStatement.executeUpdate();

            // Xóa dữ liệu trong bảng phieumuontra
            deleteStatement.setString(1, maPhieuMuonTra);
            deleteStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
