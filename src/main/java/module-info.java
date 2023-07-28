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


}