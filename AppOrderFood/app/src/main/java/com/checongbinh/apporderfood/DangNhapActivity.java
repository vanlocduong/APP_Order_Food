package com.checongbinh.apporderfood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.checongbinh.apporderfood.DAO.NhanVienDAO;

/**
 * Created by Nhox on 3/19/2016.
 */
public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDongYDN,btnDangKyDN;
    EditText edTenDangNhapDN, edMatKhauDN;
    NhanVienDAO nhanVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);

        btnDangKyDN = (Button) findViewById(R.id.btnDangKyDN);
        btnDongYDN = (Button) findViewById(R.id.btnDongYDN);
        edMatKhauDN = (EditText) findViewById(R.id.edMatKhauDN);
        edTenDangNhapDN = (EditText) findViewById(R.id.edTenDangNhapDN);

        nhanVienDAO = new NhanVienDAO(this);
        btnDongYDN.setOnClickListener(this);
        btnDangKyDN.setOnClickListener(this);

        HienThiButtonDangKyVSDongY();
    }

    private void HienThiButtonDangKyVSDongY(){
        boolean kiemtra = nhanVienDAO.KiemTraNhanVien();
        if(kiemtra){
            btnDangKyDN.setVisibility(View.GONE);
            btnDongYDN.setVisibility(View.VISIBLE);
        }else{
            btnDangKyDN.setVisibility(View.VISIBLE);
            btnDongYDN.setVisibility(View.GONE);
        }
    }

    private void btnDongY(){
        String sTenDangNhap = edTenDangNhapDN.getText().toString();
        String sMatKhau = edMatKhauDN.getText().toString();
        int kiemtra = nhanVienDAO.KiemTraDangNhap(sTenDangNhap, sMatKhau);
        int maquyen = nhanVienDAO.LayQuyenNhanVien(kiemtra);
        if(kiemtra != 0){
            SharedPreferences sharedPreferences = getSharedPreferences("luuquyen", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("maquyen",maquyen);
            editor.commit();

            Intent iTrangChu = new Intent(DangNhapActivity.this,TrangChuActivity.class);
            iTrangChu.putExtra("tendn",edTenDangNhapDN.getText().toString());
            iTrangChu.putExtra("manhanvien",kiemtra);
            startActivity(iTrangChu);
            overridePendingTransition(R.anim.hieuung_activity_vao,R.anim.hieuung_activity_ra);

        }else{
            Toast.makeText(DangNhapActivity.this,"Đăng nhập thất bại !",Toast.LENGTH_SHORT).show();
        }
    }

    private void btnDangKy(){
        Intent iDangKy = new Intent(DangNhapActivity.this,DangKyActivity.class);
        iDangKy.putExtra("landautien",1);
        startActivity(iDangKy);
    }

    @Override
    protected void onResume() {
        super.onResume();
        HienThiButtonDangKyVSDongY();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){

            case R.id.btnDongYDN:
                btnDongY();
                ;break;

            case R.id.btnDangKyDN:
                btnDangKy();
                ;break;
        }
    }
}
