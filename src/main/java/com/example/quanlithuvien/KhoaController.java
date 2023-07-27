package com.example.quanlithuvien;

import Model.Khoa;
import Model.TheLoai;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class KhoaController implements Initializable {
    ConnectDatabase data = new ConnectDatabase();
    Connection connection = data.getConnection();
    @FXML
    private TextField textKhoa;
    @FXML
    private TableColumn<Khoa, String> tenKhoa;
    @FXML
    private TableColumn<Khoa, Integer> maKhoa;
    @FXML
    private TableView<Khoa> tableKhoa;
    ObservableList<Khoa> listKhoa = FXCollections.observableArrayList();

    Integer makhoa;
    public void themKhoa(ActionEvent actionEvent) {
        try {
            AddKhoa(textKhoa.getText());



        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableKhoa.refresh();
        tableKhoa.setItems(FXCollections.observableArrayList(listKhoa));
    }

    public void AddKhoa(String tenKhoa) throws SQLException {
        String insert = "insert into khoa(tenKhoa) values (?)";
        String queryKhoa = "Select maKhoa from khoa";
        Statement statement1 = connection.createStatement();
        ResultSet queryOutput = statement1.executeQuery(queryKhoa);
        while(queryOutput.next()){
            makhoa = queryOutput.getInt("maKhoa") + 1;
        }

        Khoa khoa = new Khoa();
        khoa.setMaKhoa(makhoa);
        khoa.setTenKhoa(tenKhoa);
        PreparedStatement statement = connection.prepareStatement(insert);
        statement.setString(1, khoa.getTenKhoa());
        statement.executeUpdate();
        listKhoa.add(khoa);
        tableKhoa.refresh();


    }
    public void removeKhoa(int id) {
        String query = "DELETE FROM khoa WHERE maKhoa = " + id;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {

            String queryKhoa = "Select maKhoa,tenKhoa from khoa";
            Statement statement = connection.createStatement();
            ResultSet queryOutput = statement.executeQuery(queryKhoa);
            while (queryOutput.next()) {
                Integer idKhoa = queryOutput.getInt("maKhoa");
                String tenKhoa = queryOutput.getString("tenKhoa");

                listKhoa.add(new Khoa(idKhoa, tenKhoa));

            }
            maKhoa.setCellValueFactory(new PropertyValueFactory<>("maKhoa"));
            tenKhoa.setCellValueFactory(new PropertyValueFactory<>("tenKhoa"));

            tableKhoa.setItems(listKhoa);

            ///Khi click vào item
            ContextMenu contextMenu = new ContextMenu();
            MenuItem editMenuItem = new MenuItem("Chỉnh sửa");
            MenuItem deleteMenuItem = new MenuItem("Xóa");
            contextMenu.getItems().addAll(editMenuItem, deleteMenuItem);

            tenKhoa.setContextMenu(contextMenu);

// Bắt sự kiện khi người dùng bấm chuột phải vào một item trong bảng
            tableKhoa.setOnContextMenuRequested(event -> {
                Khoa khoa =  tableKhoa.getSelectionModel().getSelectedItem();
                if (khoa != null) {
                    contextMenu.show(tableKhoa, event.getScreenX(), event.getScreenY());
                    tableKhoa.refresh();
                }
            });

            deleteMenuItem.setOnAction(event -> {
                Integer maKhoa1 = tableKhoa.getSelectionModel().getSelectedItem().getMaKhoa();
                // Xóa bản ghi tương ứng
                removeKhoa(maKhoa1);
                tableKhoa.getItems().remove(maKhoa1);
                listKhoa.remove(maKhoa1);
                tableKhoa.refresh();



            });

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
