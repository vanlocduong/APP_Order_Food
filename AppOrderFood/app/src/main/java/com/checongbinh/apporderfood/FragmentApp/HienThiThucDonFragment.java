package com.checongbinh.apporderfood.FragmentApp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.checongbinh.apporderfood.CustomAdapter.AdapdaterHienThiLoaiMonAnThucDon;
import com.checongbinh.apporderfood.CustomAdapter.AdapterHienThiLoaiMonAn;
import com.checongbinh.apporderfood.DAO.LoaiMonAnDAO;
import com.checongbinh.apporderfood.DTO.LoaiMonAnDTO;
import com.checongbinh.apporderfood.R;
import com.checongbinh.apporderfood.ThemThucDonActivity;
import com.checongbinh.apporderfood.TrangChuActivity;

import java.util.List;

/**
 * Created by Nhox on 4/2/2016.
 */
public class HienThiThucDonFragment extends Fragment {

    GridView gridView;
    List<LoaiMonAnDTO> loaiMonAnDTOs;
    LoaiMonAnDAO loaiMonAnDAO;
    FragmentManager fragmentManager;
    int maban;
    int maquyen;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthithucdon,container,false);
        setHasOptionsMenu(true);
        ((TrangChuActivity)getActivity()).getSupportActionBar().setTitle(R.string.thucdon);

        gridView = (GridView) view.findViewById(R.id.gvHienThiThucDon);

        fragmentManager = getActivity().getSupportFragmentManager();

        loaiMonAnDAO = new LoaiMonAnDAO(getActivity());
        loaiMonAnDTOs = loaiMonAnDAO.LayDanhSachLoaiMonAn();

        sharedPreferences = getActivity().getSharedPreferences("luuquyen", Context.MODE_PRIVATE);
        maquyen = sharedPreferences.getInt("maquyen",0);

        AdapdaterHienThiLoaiMonAnThucDon adapdater = new AdapdaterHienThiLoaiMonAnThucDon(getActivity(),R.layout.custom_layout_hienloaimonan,loaiMonAnDTOs);
        gridView.setAdapter(adapdater);
        adapdater.notifyDataSetChanged();

        Bundle bDuLieuThucDon = getArguments();
        if(bDuLieuThucDon != null){
            maban = bDuLieuThucDon.getInt("maban");

        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int maloai = loaiMonAnDTOs.get(position).getMaLoai();

                HienThiDanhSachMonAnFragment hienThiDanhSachMonAnFragment = new HienThiDanhSachMonAnFragment();

                Bundle bundle = new Bundle();
                bundle.putInt("maloai", maloai);
                bundle.putInt("maban",maban);
                hienThiDanhSachMonAnFragment.setArguments(bundle);

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content, hienThiDanhSachMonAnFragment).addToBackStack("hienthiloai");

                transaction.commit();
            }
        });



        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if(maquyen == 1){
            MenuItem itThemBanAn = menu.add(1,R.id.itThemThucDon,1,R.string.themthucdon);
            itThemBanAn.setIcon(R.drawable.logodangnhap);
            itThemBanAn.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itThemThucDon:
                Intent iThemThucDon = new Intent(getActivity(), ThemThucDonActivity.class);
                startActivity(iThemThucDon);
                getActivity().overridePendingTransition(R.anim.hieuung_activity_vao,R.anim.hieuung_activity_ra);
                ;break;
        }
        return super.onOptionsItemSelected(item);
    }
}
