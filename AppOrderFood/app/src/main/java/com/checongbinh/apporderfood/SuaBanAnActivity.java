package com.checongbinh.apporderfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.checongbinh.apporderfood.DAO.BanAnDAO;

/**
 * Created by Nhox on 4/11/2016.
 */
public class SuaBanAnActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDongYSua;
    EditText edSuaTenBan;
    BanAnDAO banAnDAO;
    int maban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_suabanan);

        btnDongYSua = (Button) findViewById(R.id.btnDongYSuaBanAn);
        edSuaTenBan = (EditText) findViewById(R.id.edSuaTenBanAn);

        banAnDAO = new BanAnDAO(this);

        maban = getIntent().getIntExtra("maban",0);

        btnDongYSua.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String tenban = edSuaTenBan.getText().toString();
        if(tenban.trim().equals("") || tenban.trim() != null){
            boolean kiemtra = banAnDAO.CapNhatLaiTenBan(maban,tenban);
            Intent intent = new Intent();
            intent.putExtra("kiemtra",kiemtra);
            setResult(Activity.RESULT_OK,intent);
            finish();
        }else{
            Toast.makeText(SuaBanAnActivity.this, getResources().getString(R.string.vuilongnhapdulieu), Toast.LENGTH_SHORT).show();
        }
    }
}
