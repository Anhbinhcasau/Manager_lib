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


    public  void  ButtonThem()  {


        try {
            addBook();
        }
        catch (Exception e){
            e.printStackTrace();

        }


    }
    public  void  addBook(){
        ConnectDatabase data=new ConnectDatabase();
        Connection connection=data.getConnection();
        int masach=0;
        String tensach=textTenSach.getText();

        String insertText="insert into book(idSach,TenSach) values ('";
        String insertValues=masach + "','"+tensach+ "')";
        String insertBook=insertText+insertValues;

        try {
            Statement statement =connection.createStatement();
            statement.executeUpdate(insertBook);
        } catch (Exception e) {
        }
    }





}


