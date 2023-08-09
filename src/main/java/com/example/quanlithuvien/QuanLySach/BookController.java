package com.example.quanlithuvien.QuanLySach;

import Model.Book;
import Model.TheLoai;
import Model.TheThanhVien;
import com.example.quanlithuvien.ConnectDatabase;
import com.example.quanlithuvien.Home;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
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
    public ObservableList<TheLoai> theLoaiList  = FXCollections.observableArrayList();
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
    @FXML
    private ImageView add,edit,search;
    @FXML
    private Label ms_error;
    @FXML
    private Label error_sl;


    ObservableList<Book>  bookList= FXCollections.observableArrayList();
    String idSachUpdate;
    Book book = new Book();


    public  void  ThemBook(String masach,String tensach,String tacgia,String nhaxuatban,String theloai,int soluong) {
        try {
            ConnectDatabase data = new ConnectDatabase();
            Connection connection = data.getConnection();

            String query ="select idSach from book ";
            PreparedStatement statement1 = connection.prepareStatement(query);
            ResultSet rs=statement1.executeQuery();
            while (rs.next()){
                String id = rs.getString("idSach");
                if(masach.equals(id)){
                    ms_error.setText("Mã đã được sử dụng");

                }

            }


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
            tbSach.refresh();
            bookList.add(book);
            showRegistrationSuccessDialog();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void btThemSach(ActionEvent actionEvent) throws SQLException {
        try {


            String masach=textMaSach.getText();
            int sl = Integer.parseInt(textSoLuong.getText());
            if(sl<0){
                error_sl.setText("Số lượng sách phải lớn hơn 0");
            }
            else {
                ThemBook(masach, textTenSach.getText(), textTacGia.getText(), textNXB.getText(), theLoaiValue, sl);
                tbSach.refresh();
                getClear();
            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void theLoai() throws SQLException {
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

                }
            });
            cbTheLoai.setItems(items);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File image = new File("img/add.png");
        Image img = new Image(image.toURI().toString());
        File image1 = new File("img/edit.png");
        Image img1 = new Image(image1.toURI().toString());
        File image2 = new File("img/kiem.png");
        Image img2 = new Image(image2.toURI().toString());
        add.setImage(img);
        edit.setImage(img1);
        search.setImage(img2);

        try{

            ////List sách
            String queryBook = "SELECT * FROM book";
            ConnectDatabase data = new ConnectDatabase();
            Connection connection = data.getConnection();
            PreparedStatement selectbook = connection.prepareStatement(queryBook);
            ResultSet rs1 = selectbook.executeQuery();
            System.out.println("Test");
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

                }
            });
            cbTheLoai.setItems(items);

            tbSach.refresh();

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
                Book bookToRemove = tbSach.getItems().stream()
                        .filter(book -> book.getMaSach().equals(idSach))
                        .findFirst()
                        .orElse(null);
                // Xóa đối tượng book khỏi danh sách
                if (bookToRemove != null) {
                    tbSach.getItems().remove(bookToRemove);
                    tbSach.refresh();
                }



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
            try {
                theLoai();
                System.out.println("refresh");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });
    }

    public void updateSach(ActionEvent actionEvent) {

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

            System.out.println("Sách đã được cập nhật thành công!");
        } catch (SQLException e) {
            System.out.println("Lỗi updateBook: " + e.getMessage());
            e.printStackTrace();
        }

    }
    public  void getClear() throws SQLException {
        textMaSach.setText("");
        textTenSach.setText("");
        textNXB.setText("");
        textTacGia.setText("");
        textSoLuong.setText("");



    }

    public void btThemTheLoai(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("TheLoai.fxml"));
        AnchorPane root = fxmlLoader.load();
        Stage primaryStage=new Stage();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Thông kê");
        primaryStage.show();
    }
    public void showRegistrationSuccessDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thêm sách thành công ");
        alert.setHeaderText(null);
        alert.setContentText("Thêm sách thành công ");
        ButtonType exitButton = new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll( exitButton);
        alert.show();
    }
}


