package Model;

import java.util.Date;
import java.util.HashMap;

public class LichSuMuonSach {
    public Date getThoiGianMuon() {
        return thoiGianMuon;
    }

    public void setThoiGianMuon(Date thoiGianMuon) {
        this.thoiGianMuon = thoiGianMuon;
    }

    public Date getThoiGianTra() {
        return thoiGianTra;
    }

    public void setThoiGianTra(Date thoiGianTra) {
        this.thoiGianTra = thoiGianTra;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getHienThiTrangThai() {
        return hienThiTrangThai;
    }

    public void setHienThiTrangThai(String hienThiTrangThai) {
        this.hienThiTrangThai = hienThiTrangThai;
    }

    public String getTenSachMuon() {
        return tenSachMuon;
    }

    public void setTenSachMuon(String tenSachMuon) {
        this.tenSachMuon = tenSachMuon;
    }

    public int getSoLuongSach() {
        return soLuongSach;
    }

    public void setSoLuongSach(int soLuongSach) {
        this.soLuongSach = soLuongSach;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    private Date thoiGianMuon;
    private Date thoiGianTra;
    private Boolean trangThai;
    private String hienThiTrangThai;
    private String tenSachMuon;
    private int soLuongSach;
    private String maSach;

    public LichSuMuonSach(Date thoiGianMuon, Date thoiGianTra, String tenSachMuon, int soLuongSach, String maSach, Boolean trangThai) {
        this.thoiGianMuon = thoiGianMuon;
        this.thoiGianTra = thoiGianTra;
        this.tenSachMuon = tenSachMuon;
        this.soLuongSach = soLuongSach;
        this.maSach = maSach;
        this.trangThai = trangThai;
        this.hienThiTrangThai = getTrangThaiHienThi();
    }


    public String getTrangThaiHienThi() {
        if (trangThai) {
            return "Đã trả";
        }
        if (thoiGianTra != null && thoiGianTra.before(new Date())) {
            return "Đang nợ";
        } else {
            return "Đang mượn";
        }
    }

}
