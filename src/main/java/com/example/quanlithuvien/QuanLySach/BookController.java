package com.example.quanlithuvien.QuanLySach;

import Model.Book;
import Model.TheLoai;
import Model.TheThanhVien;
import com.example.quanlithuvien.ConnectDatabase;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
    private TableView<Book> tbSach;
    @FXML
    private TextField tfTimKiemSach;
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

    ObservableList<Book>  bookList= FXCollections.observableArrayList();
    String idSachUpdate;


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
//                System.out.println(idSach);
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

            ///taooj menu chỉnh sửa và xóa
            ///Khi click vào item
            ContextMenu contextMenu = new ContextMenu();
            MenuItem editMenuItem = new MenuItem("Chỉnh sửa");
            MenuItem deleteMenuItem = new MenuItem("Xóa");
            contextMenu.getItems().addAll(editMenuItem, deleteMenuItem);

            tbSach.setContextMenu(contextMenu);
            ///khi click chuột phải hiện chỉnh sửa và xóa
            tbSach.setOnContextMenuRequested(event -> {
                Book book = tbSach.getSelectionModel().getSelectedItem();
                if (book != null) {
                    contextMenu.show(tbSach, event.getScreenX(), event.getScreenY());
                }
            });

            deleteMenuItem.setOnAction(event -> {
                String idSach = tbSach.getSelectionModel().getSelectedItem().getMaSach();

                // Xóa bản ghi tương ứng
                deleteBook(idSach);
                tbSach.getItems().remove(idSach);
                tbSach.refresh();

            });
            editMenuItem.setOnAction(event -> {
                 idSachUpdate = tbSach.getSelectionModel().getSelectedItem().getMaSach();
                String tenSach = tbSach.getSelectionModel().getSelectedItem().getTenSach();
                String tg = tbSach.getSelectionModel().getSelectedItem().getTacGia();
                String nxb = tbSach.getSelectionModel().getSelectedItem().getNhaXuatBan();
                String tl = tbSach.getSelectionModel().getSelectedItem().getTheLoai();
                int sl = tbSach.getSelectionModel().getSelectedItem().getSoLuong();
                ObservableList<String> items = FXCollections.observableArrayList();



                textMaSach.setText(idSachUpdate);
                textTenSach.setText(tenSach);
                textTacGia.setText(tg);
                textNXB.setText(nxb);
                cbTheLoai.getItems().add(tl);

                textSoLuong.setText(String.valueOf(sl));




            });


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void deleteBook(String id) {
        ConnectDatabase data = new ConnectDatabase();
        Connection connection = data.getConnection();

        String query = "DELETE FROM book WHERE idSach = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
           statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Loi removeTL Model");
            e.printStackTrace();
        }
    }
    public List<Book> searchSach(String keyword) {
        ConnectDatabase data = new ConnectDatabase();
        Connection connection = data.getConnection();
        List<Book> result = new ArrayList<>();
        String query = "SELECT * FROM book WHERE idSach LIKE ? OR tenSach LIKE ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("idSach");
                String tenSach = resultSet.getString("TenSach");
                String tg = resultSet.getString("TacGia");
                String nxb = resultSet.getString("NhaXuatBan");
                int sl = resultSet.getInt("SoLuong");
                String tl = resultSet.getString("TheLoai");

                Book sach = new Book(id, tenSach, tg, nxb, tl,sl);
                result.add(sach);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void btnSearchBook(ActionEvent actionEvent) {
        String key=tfTimKiemSach.getText();
        List<Book> books = searchSach(key);
        ObservableList<Book> bookList = FXCollections.observableArrayList(books);
        refreshTableView(bookList);

    }
    private void refreshTableView(ObservableList<Book> listBook) {
        Platform.runLater(() -> {
            tbSach.getItems().clear();
            tbSach.setItems(listBook);
        });
    }

    public void updateSach(ActionEvent actionEvent) {
        String masach=textMaSach.getText();
        int sl = Integer.parseInt(textSoLuong.getText());
        updateBook(idSachUpdate, textTenSach.getText(), textTacGia.getText(), textNXB.getText(), sl, theLoaiValue);
        tbSach.refresh();

    }
    public void updateBook(String id, String tenSach, String tacGia, String nhaXuatBan, int soLuong, String theLoai) {
        ConnectDatabase data = new ConnectDatabase();
        Connection connection = data.getConnection();

        String query = "UPDATE book SET TenSach = ?, TacGia = ?, NhaXuatBan = ?, SoLuong = ?, TheLoai = ? WHERE idSach = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tenSach);
            statement.setString(2, tacGia);
            statement.setString(3, nhaXuatBan);
            statement.setInt(4, soLuong);
            statement.setString(5, theLoai);
            statement.setString(6, id);

            statement.executeUpdate();
            tbSach.refresh();
            System.out.println("Sách đã được cập nhật thành công!");
        } catch (SQLException e) {
            System.out.println("Lỗi updateBook: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


