package com.example.quanlithuvien;

import Model.Book;
import Model.TheLoai;

import java.sql.*;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class TheLoaiController implements Initializable {
    @FXML
    private TableView<TheLoai> tableTheLoai;
    @FXML
    private  TableColumn<TheLoai,Integer > idTheLoaiCL;
    @FXML
    private  TableColumn<TheLoai,String > tenTheLoaiCL;
    @FXML
    TextField textTheLoai;
    int idTL = 10;

    ObservableList<TheLoai>  theLoaiObservableList= FXCollections.observableArrayList();
    ConnectDatabase data=new ConnectDatabase();
    Connection connection=  data.getConnection();




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


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

            ///Khi click vào item
            ContextMenu contextMenu = new ContextMenu();
            MenuItem editMenuItem = new MenuItem("Chỉnh sửa");
            MenuItem deleteMenuItem = new MenuItem("Xóa");
            contextMenu.getItems().addAll(editMenuItem, deleteMenuItem);

            tableTheLoai.setContextMenu(contextMenu);

// Bắt sự kiện khi người dùng bấm chuột phải vào một item trong bảng
            tableTheLoai.setOnContextMenuRequested(event -> {
                TheLoai theLoai = tableTheLoai.getSelectionModel().getSelectedItem();
                if (theLoai != null) {
                    contextMenu.show(tableTheLoai, event.getScreenX(), event.getScreenY());
                }
            });

            deleteMenuItem.setOnAction(event -> {
                int idTheLoai = tableTheLoai.getSelectionModel().getSelectedItem().getIdTheLoai();

                    // Xóa bản ghi tương ứng
                    removeTL(idTheLoai);
                    tableTheLoai.getItems().remove(idTheLoai);
                tableTheLoai.refresh();

            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public  void  AddTL(String tenTL) {
        try {
            ConnectDatabase data = new ConnectDatabase();
            Connection connection = data.getConnection();
            String queryGetListTheLoai = "Select * from theloai";
            PreparedStatement statement_2 = connection.prepareStatement(queryGetListTheLoai);
            ResultSet queryOutput=statement_2.executeQuery(queryGetListTheLoai);

            while(queryOutput.next()){
                idTL = queryOutput.getInt("idTheLoai") + 1;
            }


            TheLoai theLoai = new TheLoai();
            theLoai.setIdTheLoai(idTL);
            theLoai.setTenTheLoai(tenTL);


            String insertText = "INSERT INTO theloai (tenTheLoai) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(insertText);
            statement.setString(1, theLoai.getTenTheLoai());
            statement.executeUpdate();



            theLoaiObservableList.add(theLoai);
            tableTheLoai.refresh();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void ThemTL(ActionEvent actionEvent) {
        try {

            AddTL(textTheLoai.getText());
        }
        catch (Exception e){
            e.printStackTrace();

        }


    }
        public void removeTL(int id) {
        String query = "DELETE FROM theloai WHERE idTheLoai = " + id;
            System.out.println(id);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int rowAffect = statement.executeUpdate();
//            System.out.println(rowAffect);
            //Số dòng được xóa
            if (rowAffect > 0) {
                System.out.println("Xóa thành công");
            } else {
                System.out.println("Xóa that bai");
            }


        } catch (SQLException e) {
            System.out.println("Loi removeTL Model");
            e.printStackTrace();
        }
    }
}
