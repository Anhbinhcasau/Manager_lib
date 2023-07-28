package Model;

public class TheThanhVien {
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
    private int gioiTinh;
    private String khoa;
    public TheThanhVien(String maDocGia, String tenDocGia, String soDienThoai, String email, int gioiTinh, String khoa) {
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

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }





}
