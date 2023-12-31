module com.example.quanlithuvien {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                    requires org.kordamp.bootstrapfx.core;
                requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.quanlithuvien to javafx.fxml;
    opens Model to javafx.base;

    exports com.example.quanlithuvien;
    exports com.example.quanlithuvien.PhieuMuonTra;
    exports com.example.quanlithuvien.QuanLyNguoiDung;
    exports com.example.quanlithuvien.ThongKe;
    opens com.example.quanlithuvien.PhieuMuonTra to javafx.fxml;
    opens com.example.quanlithuvien.QuanLyNguoiDung to javafx.fxml;
    opens com.example.quanlithuvien.ThongKe to javafx.fxml;

    exports com.example.quanlithuvien.QuanLySach;
    opens com.example.quanlithuvien.QuanLySach to javafx.fxml;

}