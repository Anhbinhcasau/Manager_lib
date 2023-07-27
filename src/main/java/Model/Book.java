package Model;

public class Book {
    public String idSach;
    public String tenSach;
    public String tacGia;
    public String nhaXuatBan;
    public String theLoai;
    public int soLuong;

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "idSach='" + idSach + '\'' +
                ", tenSach='" + tenSach + '\'' +
                ", tacGia='" + tacGia + '\'' +
                ", nhaXuatBan='" + nhaXuatBan + '\'' +
                ", theLoai='" + theLoai + '\'' +
                ", soLuong=" + soLuong +
                '}';
    }

    public Book(String idSach, String tenSach, String tacGia, String nhaXuatBan, String theLoai, int soLuong) {
        this.idSach = idSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.nhaXuatBan = nhaXuatBan;
        this.theLoai = theLoai;
        this.soLuong = soLuong;
    }

    public String getMaSach() {
        return idSach;
    }

    public void setMaSach(String idSach) {
        this.idSach = idSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
