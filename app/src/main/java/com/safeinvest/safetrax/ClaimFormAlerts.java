package com.safeinvest.safetrax;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.TimeZone;

public class ClaimFormAlerts extends Fragment {
Spinner status;
String status1;
int status01;
ImageView datepicker;
Calendar c;
String bday;
EditText date,remark;
Button add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_claim_form_alerts, container, false);

         status=(Spinner) view.findViewById(R.id.status);
         datepicker=(ImageView)view.findViewById(R.id.datepicker) ;
         date=(EditText)view.findViewById(R.id.date);
         add=(Button)view.findViewById(R.id.add);
         remark=(EditText)view.findViewById(R.id.remark);


        ArrayAdapter<?> array_Adapter3 =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.claimstatus,
                        R.layout.spinner01);
        array_Adapter3.setDropDownViewResource(R.layout.spinner01);
        status.setAdapter(array_Adapter3);
        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                status1=array_Adapter3.getItem(position).toString();
                status01=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance(TimeZone.getDefault()); // Get current date
// Create the DatePickerDialog instance
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


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(date.getText().toString())){
                    date.setError("Select Date");
                    date.requestFocus();
                }
                else if(status01==0){
                    Toast.makeText(getContext(), "Select Status", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(remark.getText().toString())){
                    remark.setError("Enter Remark");
                    remark.requestFocus();
                }
                else{
                    Fragment ceffrag = new ClaimForm();
                    FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                    fm.replace(R.id.fragmentContainerView, ceffrag).commit();
                    Toast.makeText(getContext(), "Added", Toast.LENGTH_SHORT).show();

                }
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
            date.setText(bday);
        }
    };

}