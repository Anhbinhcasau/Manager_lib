package Model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PhieuMuonTra {
    String maDocGia;
    private Date thoiGianMuon;
    private Date thoiGianTra;
    private HashMap<String, Integer> sachMuon;
    private boolean trangThai;


    public PhieuMuonTra(String maDocGia, Date thoiGianMuon, Date thoiGianTra, HashMap<String, Integer> sachMuon, boolean trangThai) {
        this.maDocGia = maDocGia;
        this.thoiGianMuon = thoiGianMuon;
        this.thoiGianTra = thoiGianTra;
        this.sachMuon = sachMuon;
        this.trangThai = trangThai;
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
