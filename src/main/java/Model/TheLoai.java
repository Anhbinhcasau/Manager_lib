package Model;

public class TheLoai {
    public Integer idTheLoai;
    public String tenTheLoai;

    public TheLoai() {
    }

    public TheLoai(Integer idTheLoai, String tenTheLoai) {
        this.idTheLoai = idTheLoai;
        this.tenTheLoai = tenTheLoai;
    }

    public TheLoai(String tenTheLoai){
        this.tenTheLoai = tenTheLoai;
    }

    public Integer getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(Integer idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }
}
