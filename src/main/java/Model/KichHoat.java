package Model;

import javafx.scene.control.DatePicker;

import java.sql.Date;

public class KichHoat {
    String maDocGia;
    String tenDG;
    String ngayKichHoat;
    String ngayHetHan;
    String tinhTrang;

    public KichHoat() {
    }

    public KichHoat(String tenDG) {
        this.tenDG = tenDG;
    }

    public KichHoat(String maDocGia, String tenDG, String ngayKichHoat, String ngayHetHan, String tinhTrang) {
        this.maDocGia = maDocGia;
        this.tenDG = tenDG;
        this.ngayKichHoat = ngayKichHoat;
        this.ngayHetHan = ngayHetHan;
        this.tinhTrang = tinhTrang;
    }

    public String getTenDG() {
        return tenDG;
    }

    public void setTenDG(String tenDG) {
        this.tenDG = tenDG;
    }

    public String getMaDocGia() {
        return maDocGia;
    }

    public void setMaDocGia(String maDocGia) {
        this.maDocGia = maDocGia;
    }

    public String getNgayKichHoat() {
        return ngayKichHoat;
    }

    public void setNgayKichHoat(String ngayKichHoat) {
        this.ngayKichHoat = ngayKichHoat;
    }

    public String getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(String ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
