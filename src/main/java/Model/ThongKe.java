package Model;

import java.util.Date;

public class ThongKe {
    private String maSach;
    private String tenSach;
    private int soLuongMuon;
    private String maDocGia;

    public String getMaDocGia() {
        return maDocGia;
    }

    public void setMaDocGia(String maDocGia) {
        this.maDocGia = maDocGia;
    }



    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }
    public String getTrangThai() {
        if (trangThai) {
            return "Đã trả";
        }  else {
            return "Đang mượn";
        }
    }

    private Boolean trangThai;

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getSoLuongMuon() {
        return soLuongMuon;
    }

    public void setSoLuongMuon(int soLuongMuon) {
        this.soLuongMuon = soLuongMuon;
    }

    public int getSoLuongNo() {
        return soLuongNo;
    }

    public void setSoLuongNo(int soLuongNo) {
        this.soLuongNo = soLuongNo;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    private int soLuongNo;
    private Date ngayMuon;
    private Date ngayTra;

    public ThongKe(String maSach, String tenSach, int soLuongMuon, int soLuongNo) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.soLuongMuon = soLuongMuon;
        this.soLuongNo = soLuongNo;
    }
    public ThongKe(String tenSach, Date ngayMuon, Date ngayTra, String maDocGia, int soLuongMuon, Boolean trangThai) {
        this.maDocGia = maDocGia;
        this.soLuongMuon = soLuongMuon;
        this.tenSach = tenSach;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.trangThai = trangThai;
    }

}
