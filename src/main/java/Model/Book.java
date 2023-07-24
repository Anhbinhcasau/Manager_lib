package Model;

public class Book {
    int MaSach;
    String TenSach;

    public Book(int maSach, String tenSach) {
        MaSach = maSach;
        TenSach = tenSach;
    }

    public Book() {
    }

    public int getMaSach() {
        return MaSach;
    }

    public void setMaSach(int maSach) {
        MaSach = maSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }
}
