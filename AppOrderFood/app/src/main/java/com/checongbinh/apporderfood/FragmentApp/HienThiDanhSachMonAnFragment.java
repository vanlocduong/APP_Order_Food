package com.checongbinh.apporderfood.FragmentApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.checongbinh.apporderfood.CustomAdapter.AdapterHienThiDanhSachMonAn;
import com.checongbinh.apporderfood.DAO.MonAnDAO;
import com.checongbinh.apporderfood.DTO.MonAnDTO;
import com.checongbinh.apporderfood.R;
import com.checongbinh.apporderfood.SoLuongActivity;

import java.util.List;

/**
 * Created by Nhox on 4/4/2016.
 */
public class HienThiDanhSachMonAnFragment extends Fragment {
    GridView gridView;
    MonAnDAO monAnDAO;
    List<MonAnDTO> monAnDTOList;
    AdapterHienThiDanhSachMonAn adapterHienThiDanhSachMonAn;
    int maban;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthithucdon, container, false);

        gridView = (GridView) view.findViewById(R.id.gvHienThiThucDon);

        monAnDAO = new MonAnDAO(getActivity());


        Bundle bundle = getArguments();
        if(bundle !=  null){
            int maloai = bundle.getInt("maloai");
            maban = bundle.getInt("maban");

            monAnDTOList = monAnDAO.LayDanhSachMonAnTheoLoai(maloai);

            adapterHienThiDanhSachMonAn = new AdapterHienThiDanhSachMonAn(getActivity(),R.layout.custom_layout_hienthidanhsachmonan,monAnDTOList);
            gridView.setAdapter(adapterHienThiDanhSachMonAn);
            adapterHienThiDanhSachMonAn.notifyDataSetChanged();

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(maban !=0 ){
                        Intent iSoLuong = new Intent(getActivity(), SoLuongActivity.class);
                        iSoLuong.putExtra("maban",maban);
                        iSoLuong.putExtra("mamonan",monAnDTOList.get(position).getMaMonAn());

                        startActivity(iSoLuong);
                    }

                }
            });

        }

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    getFragmentManager().popBackStack("hienthiloai", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                return false;
            }
        });
        return view;
    }
}
