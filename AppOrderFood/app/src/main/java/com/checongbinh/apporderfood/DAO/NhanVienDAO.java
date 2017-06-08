package com.checongbinh.apporderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.checongbinh.apporderfood.DTO.NhanVienDTO;
import com.checongbinh.apporderfood.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nhox on 3/19/2016.
 */
public class NhanVienDAO {
    SQLiteDatabase database;

    public NhanVienDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public long ThemNhanVien(NhanVienDTO nhanVienDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_NHANVIEN_TEDN,nhanVienDTO.getTENDN());
        contentValues.put(CreateDatabase.TB_NHANVIEN_CMND,nhanVienDTO.getCMND());
        contentValues.put(CreateDatabase.TB_NHANVIEN_GIOITINH,nhanVienDTO.getGIOITINH());
        contentValues.put(CreateDatabase.TB_NHANVIEN_MATKHAU,nhanVienDTO.getMATKHAU());
        contentValues.put(CreateDatabase.TB_NHANVIEN_NGAYSINH,nhanVienDTO.getNGAYSINH());
        contentValues.put(CreateDatabase.TB_NHANVIEN_MAQUYEN, nhanVienDTO.getMAQUYEN());

        long kiemtra = database.insert(CreateDatabase.TB_NHANVIEN, null, contentValues);
        return kiemtra;
    }

    public int LayQuyenNhanVien(int manv){
        int maquyen = 0;
        String truyvan = "SELECT * FROM " + CreateDatabase.TB_NHANVIEN + " WHERE " + CreateDatabase.TB_NHANVIEN_MANV + " = " + manv;
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            maquyen = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_MAQUYEN));
            cursor.moveToNext();
        }

        return maquyen;
    }

    public boolean SuaNhanVien(NhanVienDTO nhanVienDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_NHANVIEN_TEDN,nhanVienDTO.getTENDN());
        contentValues.put(CreateDatabase.TB_NHANVIEN_CMND, nhanVienDTO.getCMND());
        contentValues.put(CreateDatabase.TB_NHANVIEN_GIOITINH,nhanVienDTO.getGIOITINH());
        contentValues.put(CreateDatabase.TB_NHANVIEN_MATKHAU,nhanVienDTO.getMATKHAU());
        contentValues.put(CreateDatabase.TB_NHANVIEN_NGAYSINH,nhanVienDTO.getNGAYSINH());

        long kiemtra = database.update(CreateDatabase.TB_NHANVIEN, contentValues, CreateDatabase.TB_NHANVIEN_MANV + " = " + nhanVienDTO.getMANV(), null);
        if(kiemtra !=0 ){
            return true;
        }else{
            return false;
        }
    }

    public boolean KiemTraNhanVien(){
        String truyvan = "SELECT * FROM " + CreateDatabase.TB_NHANVIEN;
        Cursor cursor = database.rawQuery(truyvan, null);
        if(cursor.getCount() != 0){
            return true;
        }else{
            return false;
        }
    }

    public int KiemTraDangNhap(String tendangnhap, String matkhau){
        String truyvan = "SELECT * FROM " + CreateDatabase.TB_NHANVIEN + " WHERE " + CreateDatabase.TB_NHANVIEN_TEDN + " = '" + tendangnhap
                + "' AND " + CreateDatabase.TB_NHANVIEN_MATKHAU + " = '" + matkhau + "'";

        int manhanvien = 0;
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            manhanvien = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_MANV));
            cursor.moveToNext();
        }

        return manhanvien;
    }

    public List<NhanVienDTO> LayDanhSachNhanVien(){
        List<NhanVienDTO> nhanVienDTOs = new ArrayList<NhanVienDTO>();
        String truyvan = "SELECT * FROM " + CreateDatabase.TB_NHANVIEN;
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            NhanVienDTO nhanVienDTO = new NhanVienDTO();
            nhanVienDTO.setGIOITINH(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_GIOITINH)));
            nhanVienDTO.setNGAYSINH(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_NGAYSINH)));
            nhanVienDTO.setCMND(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_CMND)));
            nhanVienDTO.setMATKHAU(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_MATKHAU)));
            nhanVienDTO.setTENDN(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_TEDN)));
            nhanVienDTO.setMANV(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_MANV)));

            nhanVienDTOs.add(nhanVienDTO);
            cursor.moveToNext();
        }

        return nhanVienDTOs;
    }

    public NhanVienDTO LayDanhSachNhanVienTheoMa(int manv){
        NhanVienDTO nhanVienDTO = new NhanVienDTO();
        String truyvan = "SELECT * FROM " + CreateDatabase.TB_NHANVIEN + " WHERE " + CreateDatabase.TB_NHANVIEN_MANV + " = " + manv;
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            nhanVienDTO.setGIOITINH(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_GIOITINH)));
            nhanVienDTO.setNGAYSINH(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_NGAYSINH)));
            nhanVienDTO.setCMND(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_CMND)));
            nhanVienDTO.setMATKHAU(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_MATKHAU)));
            nhanVienDTO.setTENDN(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_TEDN)));
            nhanVienDTO.setMANV(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_MANV)));

            cursor.moveToNext();
        }

        return nhanVienDTO;
    }


    public boolean XoaNhanVien(int manv){

        long kiemtra = database.delete(CreateDatabase.TB_NHANVIEN,CreateDatabase.TB_NHANVIEN_MANV + " = " + manv,null);
        if(kiemtra !=0 ){
            return true;
        }else{
            return false;
        }
    }

}
