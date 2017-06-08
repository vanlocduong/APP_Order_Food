package com.checongbinh.apporderfood.DTO;

/**
 * Created by Nhox on 3/19/2016.
 */
public class NhanVienDTO {
    int MANV;
    int CMND;
    int MAQUYEN;
    String TENDN,MATKHAU,GIOITINH,NGAYSINH;

    public int getMAQUYEN() {
        return MAQUYEN;
    }

    public void setMAQUYEN(int MAQUYEN) {
        this.MAQUYEN = MAQUYEN;
    }



    public int getMANV() {
        return MANV;
    }

    public void setMANV(int MANV) {
        this.MANV = MANV;
    }

    public int getCMND() {
        return CMND;
    }

    public void setCMND(int CMND) {
        this.CMND = CMND;
    }

    public String getTENDN() {
        return TENDN;
    }

    public void setTENDN(String TENDN) {
        this.TENDN = TENDN;
    }

    public String getMATKHAU() {
        return MATKHAU;
    }

    public void setMATKHAU(String MATKHAU) {
        this.MATKHAU = MATKHAU;
    }

    public String getGIOITINH() {
        return GIOITINH;
    }

    public void setGIOITINH(String GIOITINH) {
        this.GIOITINH = GIOITINH;
    }

    public String getNGAYSINH() {
        return NGAYSINH;
    }

    public void setNGAYSINH(String NGAYSINH) {
        this.NGAYSINH = NGAYSINH;
    }


}
