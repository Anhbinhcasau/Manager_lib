package Model;

import java.util.Date;

public class TrangThaiPhieuMuon {
    private Boolean trangThai;
    private Date thoiGianMuon;

    public TrangThaiPhieuMuon(Boolean trangThai, Date thoiGianMuon) {
        this.trangThai = trangThai;
        this.thoiGianMuon = thoiGianMuon;
    }

    public String getTrangThaiHienThi() {
        if (trangThai) {
            return "Đã trả";
        } else if (thoiGianMuon != null && thoiGianMuon.before(new Date())) {
            return "Đang nợ";
        } else {
            return "Đang mượn";
        }
    }
}

