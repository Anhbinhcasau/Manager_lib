package com.example.quanlithuvien.Books;

import Model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BooksService {
    private Connection connection;
    private ArrayList<Book> bookList;

    public BooksService(){
        bookList = new ArrayList<Book>();
    }

    public BooksService(Connection connection) {
        this.connection = connection;
    }


    public List<Book> listBook() throws SQLException {
        List<Book> bookList = new ArrayList<>();
        String query = "SELECT idSach, tenSach FROM book";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet result = statement.executeQuery();
    while (result.next()){
        String idSach = result.getString("idSach");
        String tenSach = result.getString("tenSach");
        Book book = new Book(idSach, tenSach);
        bookList.add(book);
    }
    return  bookList;
    }

    public void subtractQuantityBook(String idBook, int quantity) throws SQLException {
        String query = "SELECT * FROM book WHERE idSach = ?";
        String querySubtract = "UPDATE book SET soLuong = ? WHERE idSach = ?";

        PreparedStatement statementForQuantity = connection.prepareStatement(query);
        statementForQuantity.setString(1, idBook);

        ResultSet result = statementForQuantity.executeQuery();

        if (result.next()) {
            int quantityOfBook = result.getInt("soLuong");
            if (quantityOfBook < quantity) {
                throw new Error("Khong du so luong sach");
            }
            int resultSubtract = quantityOfBook - quantity;
            PreparedStatement statementForSubtractQuantity = connection.prepareStatement(querySubtract);
            statementForSubtractQuantity.setInt(1, resultSubtract);
            statementForSubtractQuantity.setString(2, idBook);
            statementForSubtractQuantity.executeUpdate();
            statementForSubtractQuantity.close();
        } else {
            throw new Error("Khong tim thay sach co id: " + idBook);
        }

        statementForQuantity.close();
        result.close();
    }
    public void plusQuantityBook(String idBook, int quantity) throws SQLException {
        String query = "SELECT * FROM book WHERE idSach = ?";
        String querySubtract = "UPDATE book SET soLuong = ? WHERE idSach = ?";

        PreparedStatement statementForQuantity = connection.prepareStatement(query);
        statementForQuantity.setString(1, idBook);

        ResultSet result = statementForQuantity.executeQuery();

        if (result.next()) {
            int quantityOfBook = result.getInt("soLuong");



            int resultSubtract = quantityOfBook + quantity;

            PreparedStatement statementForSubtractQuantity = connection.prepareStatement(querySubtract);
            statementForSubtractQuantity.setInt(1, resultSubtract);
            statementForSubtractQuantity.setString(2, idBook);
            statementForSubtractQuantity.executeUpdate();

            statementForSubtractQuantity.close();
        } else {
            throw new Error("Khong tim thay sach co id: " + idBook);
        }

        // Close the statement and resultset to release resources
        statementForQuantity.close();
        result.close();
    }

}
