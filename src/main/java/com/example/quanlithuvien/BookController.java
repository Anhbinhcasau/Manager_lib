package com.example.quanlithuvien;

import Model.Book;
import Model.TheLoai;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class BookController implements Initializable {

    @FXML
    private TextField textTenSach;
    @FXML
    private TextField textMaSach;
    @FXML
    private ChoiceBox cbTheLoai;
    @FXML
    private TextField textTacGia;
    @FXML
    private TextField textNXB;
    @FXML
    private TextField textSoLuong;
    ObservableList<TheLoai> theLoaiList  = FXCollections.observableArrayList();
    ObservableList<String> items = FXCollections.observableArrayList();
    String theLoaiValue;
    @FXML
    private TableView tbSach;
    @FXML
    private TableColumn<Book,String > ClumMaSach;
    @FXML
    private TableColumn<Book,String > ClTenSach;
    @FXML
    private TableColumn<Book,String > ClTacGia;
    @FXML
    private TableColumn<Book,String > ClNXB;
    @FXML
    private TableColumn<Book,String > ClTheLoai;
    @FXML
    private TableColumn<Book,String > ClSoLuong;

    @FXML private ImageView imageView1;

    ObservableList<Book>  bookList= FXCollections.observableArrayList();


    public  void  ThemBook(String masach,String tensach,String tacgia,String nhaxuatban,String theloai,int soluong) {
        try {
            ConnectDatabase data = new ConnectDatabase();
            Connection connection = data.getConnection();



            Book book = new Book();
            book.setMaSach(masach);
            book.setTenSach(tensach);
            book.setTacGia(tacgia);
            book.setTheLoai(theloai);
            book.setNhaXuatBan(nhaxuatban);
            book.setSoLuong(soluong);

            String insertText = "INSERT INTO book (idSach, TenSach,TacGia,NhaXuatBan,SoLuong,TheLoai) VALUES (?, ?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(insertText);
             statement.setString(1, book.getMaSach());
            statement.setString(2, book.getTenSach());
            statement.setString(3, book.getTacGia());
            statement.setString(4, book.getNhaXuatBan());
            statement.setInt(5, book.getSoLuong());

            statement.setString(6, book.getTheLoai());
            statement.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void btThemSach(ActionEvent actionEvent) throws SQLException {
        try {


            String masach=textMaSach.getText();
            int sl = Integer.parseInt(textSoLuong.getText());
            ThemBook(masach, textTenSach.getText(), textTacGia.getText(), textNXB.getText(), theLoaiValue, sl);
            tbSach.refresh();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Load the image from a file
//        File image = new File("img/iconBook.png");
//        Image image1 = new Image(image.toURI().toString());
//        imageView1.setImage(image1);
        try{
        ConnectDatabase data = new ConnectDatabase();
        Connection connection = data.getConnection();

        String selectSql = "SELECT tenTheLoai FROM theloai";
        PreparedStatement selectStmt = connection.prepareStatement(selectSql);
        ResultSet rs = selectStmt.executeQuery();
        // Thêm các thể loại sách vào ChoiceBox

        while (rs.next()) {
            String tenTheLoai = rs.getString("tenTheLoai");
            TheLoai theLoai = new TheLoai(tenTheLoai);
            theLoaiList.add(theLoai);

        }
        cbTheLoai.getItems().addAll(theLoaiList);

            for (TheLoai theLoai : theLoaiList) {
                items.add(theLoai.getTenTheLoai());
            }
            cbTheLoai.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    theLoaiValue = (String) cbTheLoai.getValue();
                    System.out.println(theLoaiValue);
                    // Thêm giá trị theLoaiValue vào cơ sở dữ liệu ở đây
                }
            });
            cbTheLoai.setItems(items);
            ////List sách
            String queryBook = "SELECT * FROM book";

            PreparedStatement selectbook = connection.prepareStatement(queryBook);
            ResultSet rs1 = selectbook.executeQuery();

            while(rs1.next()){
                String idSach=rs1.getString("idSach");
                //System.out.println(idSach);
                String tenSach=rs1.getString("TenSach");
                String tacgia=rs1.getString("TacGia");
                String nxb=rs1.getString("NhaXuatBan");
                String theloai=rs1.getString("TheLoai");
                int soluong=rs1.getInt("SoLuong");

                bookList.add(new Book(idSach,tenSach,tacgia,nxb,theloai,soluong));
            }
            ClumMaSach.setCellValueFactory(new PropertyValueFactory<>("maSach"));
            ClTenSach.setCellValueFactory(new PropertyValueFactory<>("TenSach"));
            ClTacGia.setCellValueFactory(new PropertyValueFactory<>("TacGia"));
            ClNXB.setCellValueFactory(new PropertyValueFactory<>("NhaXuatBan"));
            ClTheLoai.setCellValueFactory(new PropertyValueFactory<>("TheLoai"));
            ClSoLuong.setCellValueFactory(new PropertyValueFactory<>("SoLuong"));
            tbSach.setItems(bookList);
            System.out.println(bookList.toString());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}


