package com.safeinvest.safetrax;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.TimeZone;

public class CrmAdd extends Fragment {
RadioButton meeting,event;
LinearLayout l1,l2;
TextView add;
Spinner ctype,fa,group,commutype,event1;
ImageView date,date2,date3;
EditText date1,mtime,fdate,todate;
Calendar c,c2,c3;
String bday;
TimePickerDialog timedialog;
int ctypepos,commutypepos,mMinute,mHour,event1pos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_crm_add, container, false);
        meeting=(RadioButton) view.findViewById(R.id.meeting);
        event=(RadioButton) view.findViewById(R.id.event);
        l1=(LinearLayout)view.findViewById(R.id.linear01);
        l2=(LinearLayout)view.findViewById(R.id.linear02);
        ctype=(Spinner)view.findViewById(R.id.ctype);
        fa=(Spinner)view.findViewById(R.id.fa);
        group=(Spinner)view.findViewById(R.id.group);
        date=(ImageView)view.findViewById(R.id.datepicker1);
        commutype=(Spinner)view.findViewById(R.id.commutype);
        mtime=(EditText)view.findViewById(R.id.mtime);
        date1=(EditText)view.findViewById(R.id.date);
        event1=(Spinner) view.findViewById(R.id.event1);
        fdate=(EditText)view.findViewById(R.id.fdate);
        date2=(ImageView)view.findViewById(R.id.datepicker2);
        date3=(ImageView)view.findViewById(R.id.datepicker3);
        todate=(EditText)view.findViewById(R.id.todate);
        add=(TextView)view.findViewById(R.id.crmtadd);
        meeting.setChecked(true);


        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.VISIBLE);
            }
        });
        final Calendar c1 = Calendar.getInstance();

        meeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l2.setVisibility(View.GONE);
                l1.setVisibility(View.VISIBLE);
            }
        });

        ArrayAdapter<?> array_Adapter =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.crmctype,
                        R.layout.spinner01);
        array_Adapter.setDropDownViewResource(R.layout.spinner01);
        ctype.setAdapter(array_Adapter);
        ctype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ctypepos= position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        ArrayAdapter<?> array_Adapter1 =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.crmcommutype,
                        R.layout.spinner01);
        array_Adapter1.setDropDownViewResource(R.layout.spinner01);
        commutype.setAdapter(array_Adapter1);
        commutype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                commutypepos= position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        ArrayAdapter<?> array_Adapter2 =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.crmevent,
                        R.layout.spinner01);
        array_Adapter2.setDropDownViewResource(R.layout.spinner01);
        event1.setAdapter(array_Adapter2);
        event1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                event1pos= position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        date.setOnClickListener(new View.OnClickListener() {
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

        date2.setOnClickListener(new View.OnClickListener() {
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

        date3.setOnClickListener(new View.OnClickListener() {
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


        mHour = c1.get(Calendar.HOUR_OF_DAY);
        mMinute = c1.get(Calendar.MINUTE);
        mtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener mTimesetListner= new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mHour = hourOfDay;
                        mMinute = minute;
                        mtime.setText(new StringBuilder().append(mHour).append(":").append(mMinute));

                    }
                };timedialog=new TimePickerDialog(getContext(),mTimesetListner,mHour,mMinute,false);
                timedialog.show();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrmDialog crmDialog=new CrmDialog();
                crmDialog.show(getActivity().getSupportFragmentManager(), "crm dialog");
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
            bday=year1 +"-" + month1 + "-" + day1;
            c=Calendar.getInstance();
            date1.setText(bday);
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
            bday=year1 +"-" + month1 + "-" + day1;
            c2=Calendar.getInstance();
            fdate.setText(bday);
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
            bday=year1 +"-" + month1 + "-" + day1;
            c3=Calendar.getInstance();
            todate.setText(bday);
        }
    };
}