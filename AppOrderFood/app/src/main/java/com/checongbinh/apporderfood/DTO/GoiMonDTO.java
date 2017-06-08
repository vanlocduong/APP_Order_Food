package com.checongbinh.apporderfood.DTO;

/**
 * Created by Nhox on 4/5/2016.
 */
public class GoiMonDTO {

    public int getMaGoiMon() {
        return MaGoiMon;
    }

    public void setMaGoiMon(int maGoiMon) {
        MaGoiMon = maGoiMon;
    }

    public int getMaBan() {
        return MaBan;
    }

    public void setMaBan(int maBan) {
        MaBan = maBan;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int maNV) {
        MaNV = maNV;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        TinhTrang = tinhTrang;
    }

    public String getNgayGoi() {
        return NgayGoi;
    }

    public void setNgayGoi(String ngayGoi) {
        NgayGoi = ngayGoi;
    }

    int MaGoiMon,MaBan,MaNV;
    String TinhTrang,NgayGoi;




}
