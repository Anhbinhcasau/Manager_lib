package Model;

public class Book {
    String MaSach;
    String TenSach;

    public Book(String maSach, String tenSach) {
        MaSach = maSach;
        TenSach = tenSach;
    }

    public Book() {
    }

    public String getMaSach() {
        return MaSach;
    }

    public void setMaSach(String maSach) {
        MaSach = maSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }
}
