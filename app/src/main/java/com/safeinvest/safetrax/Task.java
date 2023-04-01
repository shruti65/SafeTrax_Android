package com.safeinvest.safetrax;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Calendar;
import java.util.TimeZone;

public class Task extends Fragment {
RadioGroup radioGroup;
RadioButton onetime,recurring;
LinearLayout lv1,lv2;
EditText adate,sdate,edate;
ImageView assigndate,startdate,enddate;
Calendar c,c1,c2;
String aday,sday,eday;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_task, container, false);
         radioGroup=(RadioGroup) view.findViewById(R.id.rg);
         onetime=(RadioButton) view.findViewById(R.id.onetime);
         recurring=(RadioButton) view.findViewById(R.id.recurring);
         lv1=(LinearLayout)view.findViewById(R.id.lv1);
         lv2=(LinearLayout) view.findViewById(R.id.lv2);
         assigndate=(ImageView)view.findViewById(R.id.datepicker1);
         startdate=(ImageView)view.findViewById(R.id.datepicker2);
         enddate=(ImageView)view.findViewById(R.id.datepicker3);
         adate=(EditText)view.findViewById(R.id.date);
         sdate=(EditText)view.findViewById(R.id.startdate);
         edate=(EditText)view.findViewById(R.id.enddate);



         onetime.setChecked(true);
         recurring.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 lv1.setVisibility(View.GONE);
                 lv2.setVisibility(View.VISIBLE);
             }
         });


         onetime.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 lv1.setVisibility(View.VISIBLE);
                 lv2.setVisibility(View.GONE);
             }
         });


         assigndate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Calendar cal = Calendar.getInstance(TimeZone.getDefault());
                 DatePickerDialog datePicker = new DatePickerDialog(getActivity(),
                         datePickerListener,
                         cal.get(Calendar.YEAR),
                         cal.get(Calendar.MONTH),
                         cal.get(Calendar.DAY_OF_MONTH));
                 datePicker.setCancelable(false);
                 datePicker.setTitle("Select the date");
                 datePicker.show();
             }
         });

         startdate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Calendar cal = Calendar.getInstance(TimeZone.getDefault());
                 DatePickerDialog datePicker = new DatePickerDialog(getActivity(),
                         datePickerListener1,
                         cal.get(Calendar.YEAR),
                         cal.get(Calendar.MONTH),
                         cal.get(Calendar.DAY_OF_MONTH));
                 datePicker.setCancelable(false);
                 datePicker.setTitle("Select the date");
                 datePicker.show();
             }
         });

         enddate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Calendar cal = Calendar.getInstance(TimeZone.getDefault());
                 DatePickerDialog datePicker = new DatePickerDialog(getActivity(),
                         datePickerListener2,
                         cal.get(Calendar.YEAR),
                         cal.get(Calendar.MONTH),
                         cal.get(Calendar.DAY_OF_MONTH));
                 datePicker.setCancelable(false);
                 datePicker.setTitle("Select the date");
                 datePicker.show();
             }
         });


        return view;
    }
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String year1 = String.valueOf(year);
            String month1 = String.valueOf(month + 1);
            String day1 = String.valueOf(dayOfMonth);
            if(Integer.parseInt(month1)<10){
                month1=0+month1;
            }
            if(Integer.parseInt(day1)<10){
                day1=0+day1;
            }
            aday=year1 +"-" + month1 + "-" + day1;
            c=Calendar.getInstance();
            adate.setText(aday);
        }
    };
    private DatePickerDialog.OnDateSetListener datePickerListener1 = new DatePickerDialog.OnDateSetListener() {

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String year1 = String.valueOf(year);
            String month1 = String.valueOf(month + 1);
            String day1 = String.valueOf(dayOfMonth);
            if(Integer.parseInt(month1)<10){
                month1=0+month1;
            }
            if(Integer.parseInt(day1)<10){
                day1=0+day1;
            }
            sday=year1 +"-" + month1 + "-" + day1;
            c1=Calendar.getInstance();
            sdate.setText(sday);
        }
    };
    private DatePickerDialog.OnDateSetListener datePickerListener2 = new DatePickerDialog.OnDateSetListener() {

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String year1 = String.valueOf(year);
            String month1 = String.valueOf(month + 1);
            String day1 = String.valueOf(dayOfMonth);
            if(Integer.parseInt(month1)<10){
                month1=0+month1;
            }
            if(Integer.parseInt(day1)<10){
                day1=0+day1;
            }
            eday=year1 +"-" + month1 + "-" + day1;
            c2=Calendar.getInstance();
            edate.setText(eday);
        }
    };
}