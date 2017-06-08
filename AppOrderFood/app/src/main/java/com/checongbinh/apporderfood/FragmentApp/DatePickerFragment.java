package com.checongbinh.apporderfood.FragmentApp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

import com.checongbinh.apporderfood.R;

import java.util.Calendar;

/**
 * Created by Nhox on 3/19/2016.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int iNam = calendar.get(Calendar.YEAR);
        int iThang = calendar.get(Calendar.MONTH);
        int iNgay = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),this,iNgay,iThang,iNam);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        EditText edNgaySinh = (EditText) getActivity().findViewById(R.id.edNgaySinhDK);
        String sNgaySinh = dayOfMonth + "/" + (monthOfYear+1) + "/" + year;
        edNgaySinh.setText(sNgaySinh);
    }
}
