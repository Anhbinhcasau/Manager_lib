package com.example.quanlithuvien;

import Model.TheLoai;
import java.sql.Connection;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class TheLoaiController implements Initializable {
    @FXML
    private TableView<TheLoai> tableTheLoai;
    @FXML
    private  TableColumn<TheLoai,Integer > idTheLoaiCL;
    @FXML
    private  TableColumn<TheLoai,String > tenTheLoaiCL;

    ObservableList<TheLoai>  theLoaiObservableList= FXCollections.observableArrayList();




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConnectDatabase data=new ConnectDatabase();
        Connection connection=  data.getConnection();

        String queryTheLoai="Select idTheLoai,tenTheLoai from TheLoai";


        try{
            Statement statement=connection.createStatement();
            ResultSet queryOutput=statement.executeQuery(queryTheLoai);

            while(queryOutput.next()){
                Integer idTL=queryOutput.getInt("idTheLoai");
                String tenTL=queryOutput.getString("tenTheLoai");

                theLoaiObservableList.add(new TheLoai(idTL,tenTL));

            }
            idTheLoaiCL.setCellValueFactory(new PropertyValueFactory<>("idTheLoai"));
            tenTheLoaiCL.setCellValueFactory(new PropertyValueFactory<>("tenTheLoai"));

            tableTheLoai.setItems(theLoaiObservableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
