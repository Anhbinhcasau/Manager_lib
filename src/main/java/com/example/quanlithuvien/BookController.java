package com.example.quanlithuvien;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BookController {

    @FXML
    private TextField textTenSach;

    public  void  ButtonThem()  {
        try {
            dataBook();
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }



    }
    public  void  dataBook(){
        ConnectDatabase data=new ConnectDatabase();
        Connection connection=data.getConnection();
         int masach=0;
        String tensach=textTenSach.getText();

        String insertText="insert into book(MaSach,TenSach) values ('";
        String insertValues=masach + "','"+tensach+ "')";
        String insertBook=insertText+insertValues;

        try {
            Statement statement =connection.createStatement();
            statement.executeUpdate(insertBook);
        } catch (Exception e) {

        }
    }

}


