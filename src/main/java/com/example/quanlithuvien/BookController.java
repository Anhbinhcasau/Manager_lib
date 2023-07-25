package com.example.quanlithuvien;

import Model.Book;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BookController {

    @FXML
    private TextField textTenSach;
    @FXML
    private TextField textMasach;


    public  void  ButtonThem()  {


        try {
            ThemBook(textMasach.getText(),textTenSach.getText());
        }
        catch (Exception e){
            e.printStackTrace();

        }


    }
//    public  void  addBook(){
//        ConnectDatabase data=new ConnectDatabase();
//        Connection connection=data.getConnection();
//        int masach=0;
//        String tensach=textTenSach.getText();
//
//        String insertText="insert into book(idSach,TenSach) values ('";
//        String insertValues=masach + "','"+tensach+ "')";
//        String insertBook=insertText+insertValues;
//
//        try {
//            Statement statement =connection.createStatement();
//            statement.executeUpdate(insertBook);
//        } catch (Exception e) {
//        }
//
//    }
    public  void  ThemBook(String masach,String tensach) {
        try {
            ConnectDatabase data = new ConnectDatabase();
            Connection connection = data.getConnection();


            Book book = new Book();
            book.setMaSach(masach);
            book.setTenSach(tensach);


            String insertText = "INSERT INTO book (idSach, TenSach) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertText);
             statement.setString(1, book.getMaSach());
            statement.setString(2, book.getTenSach());
            statement.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}


