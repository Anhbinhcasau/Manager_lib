package Model;

public class Khoa {
    public Integer maKhoa;
    public String tenKhoa;
    public Khoa(Integer maKhoa, String tenKhoa) {
        this.maKhoa = maKhoa;
        this.tenKhoa = tenKhoa;
    }

    public Khoa() {

    }
    @Override
    public String toString() {
        return "Khoa{" +
                "maKhoa='" + maKhoa + '\'' +
                ", tenKhoa='" + tenKhoa + '\'' +
                '}';
    }




    public Integer getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(Integer maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }


}
