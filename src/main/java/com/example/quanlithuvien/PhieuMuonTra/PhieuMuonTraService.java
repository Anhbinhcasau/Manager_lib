package com.example.quanlithuvien.PhieuMuonTra;

import Model.PhieuMuonTra;
import com.example.quanlithuvien.Books.BooksService;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

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
        String query = "INSERT INTO phieumuontra (maPhieuMuonTra ,ngayMuon, ngayTra, maDocGia, trangThai) " +
                "VALUES (?, ?, ?, ?, ?)";

        String queryForRentBook = "INSERT INTO sachmuon (phieuMuon, idSach, soLuongMuon) " +
                "VALUES (?, ?, ?)";

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
            e.printStackTrace();
        }
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

}
