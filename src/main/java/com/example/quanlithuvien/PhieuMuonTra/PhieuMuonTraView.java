package com.example.quanlithuvien.PhieuMuonTra;

import Model.Book;
import Model.SachMuon;
import Model.TheThanhVien;
import Model.TrangThaiPhieuMuon;
import com.example.quanlithuvien.Books.BooksService;
import com.example.quanlithuvien.ConnectDatabase;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;


public class PhieuMuonTraView implements Initializable {
    @FXML
    private TextField tfSoLuong;
    @FXML
    private ListView lvDanhSachSach;
    @FXML
    private TableView<Model.PhieuMuonTra> tbThe;
    @FXML
    private TableColumn<PhieuMuonTra, String> ClMaDG;

    @FXML
    private TableColumn<PhieuMuonTra, Date> ClNgayMuon;
    @FXML
    private TableColumn<PhieuMuonTra, String> ClMaPhieu;
    @FXML
    private TableColumn<PhieuMuonTra, Date> ClNgayTra;
    @FXML
    private TableColumn<PhieuMuonTra, Boolean> ClTinhTrang;
    @FXML
    private TableColumn<PhieuMuonTra, String> ClSachMaSach;
    @FXML
    private ComboBox<String> cbSach;
    private List<Book> listBook;
    @FXML
    private DatePicker dateNgayMuon;
    @FXML
    private DatePicker dateNgayTra;
    @FXML
    private TextField textMaDocGia;



    private HashMap<String, Integer> listBeforeAddBook = new HashMap<>();
    private ObservableList<PhieuMuonTra> phieuMuonTraObservableList = FXCollections.observableArrayList();

    public PhieuMuonTraView(){

    }
    ConnectDatabase connectDatabase = new ConnectDatabase();
    PhieuMuonTraService phieuMuonTraService = new PhieuMuonTraService(connectDatabase.getConnection());

    PhieuMuonTraController phieuMuonTraController = new PhieuMuonTraController(connectDatabase.getConnection());

    private void refreshTableView() {
        Platform.runLater(() -> {
            PhieuMuonTraService phieuMuonTraService = new PhieuMuonTraService(connectDatabase.getConnection());
            List<Model.PhieuMuonTra> phieuMuonTraList = phieuMuonTraService.listPhieuMuonTra();
            ObservableList<Model.PhieuMuonTra> phieuMuonTraObservableList = FXCollections.observableArrayList(phieuMuonTraList);
            tbThe.getItems().clear();
            tbThe.setItems(phieuMuonTraObservableList);
        });
    }
    public void handleThemPhieuMuonTra() {
        LocalDate localDateThoiGianMuon = LocalDate.of(2023, 7, 31);
        Date thoiGianMuon = Date.from(localDateThoiGianMuon.atStartOfDay(ZoneId.systemDefault()).toInstant());
        HashMap<String, Integer> listSachMuon = new HashMap<String, Integer>();
        listSachMuon.put("1", 2);
        listSachMuon.put("2", 4);
        listSachMuon.put("3", 6);
    }

    public void handleTraPhieu(){
        phieuMuonTraController.handleTraSach("Okrsz6");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        BooksService booksService = new BooksService(connectDatabase.getConnection());
        PhieuMuonTraService phieuMuonTraService = new PhieuMuonTraService(connectDatabase.getConnection());
        try {
            ClMaDG.setCellValueFactory(new PropertyValueFactory<>("maDocGia"));
            ClNgayMuon.setCellValueFactory(new PropertyValueFactory<>("thoiGianMuon"));
            ClNgayTra.setCellValueFactory(new PropertyValueFactory<>("thoiGianTra"));
            ClSachMaSach.setCellValueFactory(new PropertyValueFactory<>("sachMuonHienThi"));
            ClMaPhieu.setCellValueFactory(new PropertyValueFactory<>("maPhieu"));

            List<Model.PhieuMuonTra> phieuMuonTraList = phieuMuonTraService.listPhieuMuonTra();
            ObservableList<Model.PhieuMuonTra> phieuMuonTraObservableList = FXCollections.observableArrayList(phieuMuonTraList);
            ClTinhTrang.setCellValueFactory(new PropertyValueFactory<>("trangThaiHienThi"));
            tbThe.setItems(phieuMuonTraObservableList);

//            tbThe.getItems().addAll((PhieuMuonTra) phieuMuonTraList);

            listBook = booksService.listBook();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        cbSach.getItems().clear();
        for (Book book : listBook) {
            cbSach.getItems().add(book.getTenSach());
        }

        cbSach.setOnAction(this::handleComboBoxSelection);
    }



    public void btTaoPhieuMuon(ActionEvent actionEvent) throws SQLException {
        String ngayMuon = dateNgayMuon.getEditor().getText();
        String ngayTra = dateNgayTra.getEditor().getText();
        String maDocGia = textMaDocGia.getText();

        if (ngayMuon.isEmpty() || ngayTra.isEmpty() || lvDanhSachSach.getItems().isEmpty()) {
            System.out.println("Vui lòng nhập đủ thông tin phiếu mượn và chọn sách.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date ngayMuonDate, ngayTraDate;
        try {
            ngayMuonDate = dateFormat.parse(ngayMuon);
            ngayTraDate = dateFormat.parse(ngayTra);
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        phieuMuonTraController.handleAddPhieuMuonTra(maDocGia, ngayMuonDate, ngayTraDate, listBeforeAddBook, false);
        refreshTableView();
    }

    public void handleComboBoxSelection(ActionEvent event) {
        String selectedBookName = cbSach.getValue();
        if (selectedBookName != null) {
            Book selectedBook = getBookByName(selectedBookName);
            if (selectedBook != null) {
                String selectedBookId = selectedBook.getMaSach();
            }
        }
    }

    private Book getBookByName(String bookName) {
        for (Book book : listBook) {
            if (book.getTenSach().equals(bookName)) {
                return book;
            }
        }
        return null;
    }

    private void refreshTableView2() {
        Platform.runLater(() -> {
            lvDanhSachSach.getItems().clear();

            ObservableList<String> items = FXCollections.observableArrayList();

            for (HashMap.Entry<String, Integer> entry : listBeforeAddBook.entrySet()) {
                String maSach = entry.getKey();
                int soLuongMuon = entry.getValue();
                String tenSach = phieuMuonTraService.getBookNameById(maSach);

                items.add(maSach + ": " + soLuongMuon + " - " + tenSach);
            }

            lvDanhSachSach.setItems(items);
        });
    }

    public void btThemSach(ActionEvent actionEvent) {
        String selectedBookName = cbSach.getValue();
        String soLuongMuonStr = tfSoLuong.getText();

        int soLuongMuon;
        try {
            soLuongMuon = Integer.parseInt(soLuongMuonStr);
        } catch (NumberFormatException e) {
            // Xử lý nếu số lượng mượn không hợp lệ (không phải số nguyên)
            System.out.println("Vui lòng nhập số lượng mượn là một số nguyên.");
            return;
        }

        if (selectedBookName != null && soLuongMuon > 0) {
            Book selectedBook = getBookByName(selectedBookName);
            if (selectedBook != null) {
                String maSach = selectedBook.getMaSach();
                String tenSach = selectedBook.getTenSach();

                ObservableList<String> items = lvDanhSachSach.getItems();

                boolean isExistingBook = false;
                for (int i = 0; i < items.size(); i++) {
                    String item = items.get(i);
                    if (item.contains(maSach)) {
                        isExistingBook = true;
                        int startIndex = item.indexOf(": ") + 2;
                        int endIndex = item.indexOf(" - ");

                        if (startIndex >= 0 && endIndex > startIndex) {
                            int soLuongCu = Integer.parseInt(item.substring(startIndex, endIndex));
                            int soLuongMoi = soLuongCu + soLuongMuon;
                            items.set(i, maSach + ": " + soLuongMoi + " - " + tenSach);
                        } else {
                            System.out.println("Không thể tìm thấy số lượng sách trong chuỗi: " + item);
                        }
                    }
                }

                if (!isExistingBook) {
                    // Nếu sách chưa tồn tại, thêm mới vào ListView
                    items.add(maSach + ": " + soLuongMuon + " - " + tenSach);
                }

                // Cập nhật hoặc thêm mới vào HashMap
                if (listBeforeAddBook.containsKey(maSach)) {
                    int soLuongTruoc = listBeforeAddBook.get(maSach);
                    listBeforeAddBook.put(maSach, soLuongTruoc + soLuongMuon);
                } else {
                    listBeforeAddBook.put(maSach, soLuongMuon);
                }

                lvDanhSachSach.setItems(items);

            }
        }
    }


    public void xoaSach(ActionEvent actionEvent) {
        String selectedItem = (String) lvDanhSachSach.getSelectionModel().getSelectedItem();

        String[] parts = selectedItem.split(":");
        if (parts.length == 2) {
            String tenSach = parts[0];

            if (listBeforeAddBook.containsKey(tenSach)) {
                listBeforeAddBook.keySet().remove(tenSach);
            }
        refreshTableView2();
        }
    }


    public void xoaPhieuMuon(ActionEvent actionEvent) {
        BooksService booksService = new BooksService(connectDatabase.getConnection());
        PhieuMuonTraService phieuMuonTraService = new PhieuMuonTraService(connectDatabase.getConnection());
        Model.PhieuMuonTra selectedPhieuMuonTra = tbThe.getSelectionModel().getSelectedItem();
        String maPhieuMuon = selectedPhieuMuonTra.getMaPhieu();
        if (selectedPhieuMuonTra != null) {
            maPhieuMuon = selectedPhieuMuonTra.getMaPhieu();
            phieuMuonTraService.xoaPhieuMuonTra(maPhieuMuon);
        }
        refreshTableView();
    }

    public void daTraSach(ActionEvent actionEvent) {
        PhieuMuonTraService phieuMuonTraService = new PhieuMuonTraService(connectDatabase.getConnection());
        Model.PhieuMuonTra selectedPhieuMuonTra = tbThe.getSelectionModel().getSelectedItem();
        String maPhieuMuon = selectedPhieuMuonTra.getMaPhieu();
        if (selectedPhieuMuonTra != null) {
            maPhieuMuon = selectedPhieuMuonTra.getMaPhieu();
            phieuMuonTraService.traSach(maPhieuMuon);
        }
        refreshTableView();
    }

    public void chinhSuaSach(ActionEvent actionEvent) {
    }
}
