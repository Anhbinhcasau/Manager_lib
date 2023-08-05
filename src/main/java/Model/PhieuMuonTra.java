package Model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhieuMuonTra {


    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    private String maPhieu;

    String maDocGia;
    private Date thoiGianMuon;
    private Date thoiGianTra;
    private HashMap<String, Integer> sachMuon;
    private String tenSachMuon;
    private int soLuongSach;

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

    private boolean trangThai;
    private String trangThaiHienThi;
    public String sachMuonHienThi;

    public String getSachMuonHienThi(){
        String hienthi = "";
        if (sachMuon == null) {
            return "Không tìm thấy sách";
        }
        for (Map.Entry<String, Integer> set : sachMuon.entrySet()) {
            hienthi += "Sach muon " + set.getKey() + " So luong " + set.getValue();
        }
        return hienthi;
    }


    public String getTrangThaiHienThi() {
        if (trangThai) {
            return "Đã trả";
        } else if (thoiGianTra != null && thoiGianMuon.after(new Date())) {
            return "Đang nợ";
        } else {
            return "Đang mượn";
        }
    }

    public PhieuMuonTra(String maPhieu, String maDocGia, Date thoiGianMuon, Date thoiGianTra, HashMap<String, Integer> sachMuon, boolean trangThai) {
        this.maPhieu = maPhieu;
        this.maDocGia = maDocGia;
        this.thoiGianMuon = thoiGianMuon;
        this.thoiGianTra = thoiGianTra;
        this.sachMuon = sachMuon;
        this.trangThai = trangThai;
        this.trangThaiHienThi = getTrangThaiHienThi();
        this.sachMuonHienThi = getSachMuonHienThi();
    }

    public PhieuMuonTra(String maDocGia, Date thoiGianMuon, Date thoiGianTra, HashMap<String, Integer> sachMuon, boolean trangThai) {
        this(null, maDocGia, thoiGianMuon, thoiGianTra, sachMuon, trangThai);
    }

    public PhieuMuonTra(Date thoiGianMuon, Date thoiGianTra, String tenSachMuon, int soLuongSach, boolean trangThai) {
        this(null, thoiGianMuon, thoiGianTra, new HashMap<>(), trangThai);
        this.tenSachMuon = tenSachMuon;
        this.soLuongSach = soLuongSach;
        this.sachMuonHienThi = getSachMuonHienThi();
    }




    public String getMaDocGia() {
        return maDocGia;
    }

    public void setMaDocGia(String maDocGia) {
        this.maDocGia = maDocGia;
    }

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

    public HashMap<String, Integer> getSachMuon() {
        return sachMuon;
    }

    public void setSachMuon(HashMap<String, Integer> sachMuon) {
        this.sachMuon = sachMuon;
    }

    public boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "PhieuMuonTra{" +
                "maDocGia='" + maDocGia + '\'' +
                ", thoiGianMuon=" + thoiGianMuon +
                ", thoiGianTra=" + thoiGianTra +
                ", sachMuon=" + sachMuon +
                ", trangThai=" + trangThai +
                '}';
    }
}
