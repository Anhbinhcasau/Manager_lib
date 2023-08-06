package Model;

public class TheThanhVien {
    public TheThanhVien() {

    }

    @Override
    public String toString() {
        return "TheThanhVien{" +
                "maDocGia='" + maDocGia + '\'' +
                ", tenDocGia='" + tenDocGia + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", email='" + email + '\'' +
                ", gioiTinh=" + gioiTinh +
                ", khoa='" + khoa + '\'' +
                '}';
    }

    private String maDocGia;
    private String tenDocGia;
    private String soDienThoai;
    private String email;
    private String gioiTinh;
    private String khoa;
    private String tinhTrangThe;
    private  String ngayDangKi;
    private  String NgayHetHan;
    private  boolean tt;

    public TheThanhVien(String maDocGia, String tenDocGia, String soDienThoai, String email, String gioiTinh, String khoa, String tinhTrangThe, String ngayDangKi, String ngayHetHan) {
        this.maDocGia = maDocGia;
        this.tenDocGia = tenDocGia;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.khoa = khoa;
        this.tinhTrangThe = tinhTrangThe;
        this.ngayDangKi = ngayDangKi;
        NgayHetHan = ngayHetHan;
    }

    public String getNgayDangKi() {
        return ngayDangKi;
    }

    public void setNgayDangKi(String ngayDangKi) {
        this.ngayDangKi = ngayDangKi;
    }

    public String getNgayHetHan() {
        return NgayHetHan;
    }

    public void setNgayHetHan(String ngayHetHan) {
        NgayHetHan = ngayHetHan;
    }

    public String getTinhTrangThe() {
        return tinhTrangThe;
    }

    public void setTinhTrangThe(String tinhTrangThe) {
        this.tinhTrangThe = tinhTrangThe;
    }

    public TheThanhVien(String maDocGia, String tenDocGia, String soDienThoai, String email, String gioiTinh, String khoa, String tinhTrangThe) {
        this.maDocGia = maDocGia;
        this.tenDocGia = tenDocGia;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.khoa = khoa;
        this.tinhTrangThe = tinhTrangThe;
    }

    public TheThanhVien(String maDocGia, String tenDocGia, String soDienThoai, String email, String gioiTinh, String khoa) {
        this.maDocGia = maDocGia;
        this.tenDocGia = tenDocGia;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.khoa = khoa;
    }

    public String getMaDocGia() {
        return maDocGia;
    }

    public void setMaDocGia(String maDocGia) {
        this.maDocGia = maDocGia;
    }

    public String getTenDocGia() {
        return tenDocGia;
    }

    public void setTenDocGia(String tenDocGia) {
        this.tenDocGia = tenDocGia;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }





}
