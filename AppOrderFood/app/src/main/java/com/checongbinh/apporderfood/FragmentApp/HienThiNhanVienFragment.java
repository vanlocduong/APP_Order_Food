package com.checongbinh.apporderfood.FragmentApp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.checongbinh.apporderfood.CustomAdapter.AdapterHienThiNhanVien;
import com.checongbinh.apporderfood.DAO.NhanVienDAO;
import com.checongbinh.apporderfood.DTO.NhanVienDTO;
import com.checongbinh.apporderfood.DangKyActivity;
import com.checongbinh.apporderfood.R;

import java.util.List;

/**
 * Created by Nhox on 4/13/2016.
 */
public class HienThiNhanVienFragment extends Fragment {

    ListView listNhanVien;
    NhanVienDAO nhanVienDAO;
    List<NhanVienDTO> nhanVienDTOList;
    int maquyen;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthinhanvien,container,false);
        setHasOptionsMenu(true);

        listNhanVien = (ListView) view.findViewById(R.id.listViewNhanVien);

        nhanVienDAO = new NhanVienDAO(getActivity());

        HienThiDanhSachNhanVien();

        sharedPreferences = getActivity().getSharedPreferences("luuquyen", Context.MODE_PRIVATE);
        maquyen = sharedPreferences.getInt("maquyen",0);

        if(maquyen == 1 ){
            registerForContextMenu(listNhanVien);
        }


        return view;
    }

    private void HienThiDanhSachNhanVien(){
        nhanVienDTOList = nhanVienDAO.LayDanhSachNhanVien();

        AdapterHienThiNhanVien adapterHienThiNhanVien = new AdapterHienThiNhanVien(getActivity(),R.layout.custom_layout_hienthinhanvien,nhanVienDTOList);
        listNhanVien.setAdapter(adapterHienThiNhanVien);
        adapterHienThiNhanVien.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.edit_context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int vitri = menuInfo.position;
        int manhanvien = nhanVienDTOList.get(vitri).getMANV();

        switch (id){
            case R.id.itSua:
                Intent iDangKy = new Intent(getActivity(),DangKyActivity.class);
                iDangKy.putExtra("manv",manhanvien);
                startActivity(iDangKy);
                ;break;

            case R.id.itXoa:
                 boolean kiemtra = nhanVienDAO.XoaNhanVien(manhanvien);
                 if (kiemtra){
                     HienThiDanhSachNhanVien();
                     Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.xoathanhcong),Toast.LENGTH_SHORT).show();
                 }else{
                     Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.loi),Toast.LENGTH_SHORT).show();
                 }
                ;break;
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if(maquyen == 1){
            MenuItem itThemBanAn = menu.add(1,R.id.itThemNhanVien,1,R.string.themnhanvien);
            itThemBanAn.setIcon(R.drawable.nhanvien);
            itThemBanAn.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itThemNhanVien:
                 Intent iDangKy = new Intent(getActivity(), DangKyActivity.class);
                startActivity(iDangKy);
                ;break;
        }
        return true;
    }
}
