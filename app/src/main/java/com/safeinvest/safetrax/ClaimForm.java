package com.safeinvest.safetrax;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.TimeZone;

public class ClaimForm extends Fragment {
    ImageView datepicker1,datepicker2;
    EditText ldate,idate,name;
    EditText email,mobile,pofinci,incident,sname,gname;
    Calendar c,c1;
    String bday,bday1;
    CheckBox checkBox;
    TableRow relation;
    Button save,reset,addstatus;
    RadioGroup radioGroup;
    private NotificationManagerCompat managerCompat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_claim_form, container, false);
        datepicker1=(ImageView) view.findViewById(R.id.datepicker);
        datepicker2=(ImageView) view.findViewById(R.id.datepicker1);
        ldate=(EditText)view.findViewById(R.id.ldate);
        idate=(EditText)view.findViewById(R.id.idate);
        checkBox=(CheckBox)view.findViewById(R.id.checkbox);
        name=(EditText)view.findViewById(R.id.name);
        relation=(TableRow)view.findViewById(R.id.relation);
        save=(Button)view.findViewById(R.id.save);
        reset=(Button)view.findViewById(R.id.reset);
        radioGroup=(RadioGroup)view.findViewById(R.id.cstype);
        email=(EditText)view.findViewById(R.id.email);
        mobile=(EditText)view.findViewById(R.id.mobile);
        pofinci=(EditText)view.findViewById(R.id.pofinci);
        incident=(EditText)view.findViewById(R.id.incident);
        sname=(EditText)view.findViewById(R.id.sname);
        gname=(EditText)view.findViewById(R.id.gname);
        addstatus=(Button)view.findViewById(R.id.addstatus);
        managerCompat=NotificationManagerCompat.from(getActivity());



        datepicker1.setOnClickListener(new View.OnClickListener() {
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

        datepicker2.setOnClickListener(new View.OnClickListener() {
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

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked){
                   name.setText("Shruti");
                   relation.setVisibility(View.GONE);
               }
               if(!isChecked){
                   name.setText("");
                   relation.setVisibility(View.VISIBLE);
               }

           }
       });

        save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(TextUtils.isEmpty(ldate.getText().toString())){
                   ldate.setError("Select Date");
                   ldate.requestFocus();
               }
               else if(TextUtils.isEmpty(idate.getText().toString())){
                   idate.setError("Select Date");
                   idate.requestFocus();
               }
               else if(TextUtils.isEmpty(name.getText().toString())){
                   name.setError("Enter Name");
                   name.requestFocus();
               }
               else if(TextUtils.isEmpty(email.getText().toString())){
                   email.setError("Enter Email");
                   email.requestFocus();
               }
               else if(TextUtils.isEmpty(mobile.getText().toString())){
                   mobile.setError("Enter Mobile");
                   mobile.requestFocus();
               }
               else if(TextUtils.isEmpty(pofinci.getText().toString())){
                   pofinci.setError("Enter Place");
                   pofinci.requestFocus();
               }
               else if(TextUtils.isEmpty(incident.getText().toString())){
                   incident.setError("Enter Incident");
                   incident.requestFocus();
               }
               else if(TextUtils.isEmpty(sname.getText().toString())){
                   sname.setError("Enter Surveyor");
                   sname.requestFocus();
               }
               else if(TextUtils.isEmpty(gname.getText().toString())){
                   gname.setError("Enter Garage");
                   gname.requestFocus();
               }
               else{
                   Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
               }
           }
       });

        addstatus.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Fragment ceffrag = new ClaimFormAlerts();
               FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
               fm.replace(R.id.fragmentContainerView, ceffrag).commit();
           }
       });

        reset.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                sendonchannel(view);
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
            ldate.setText(bday);
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
            bday1=year1 +"-" + month1 + "-" + day1;
            c1=Calendar.getInstance();
            idate.setText(bday1);
        }
    };
    public void sendonchannel(View v){
        Intent intent = new Intent(getActivity(), Dashboard.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 1, intent, PendingIntent.FLAG_IMMUTABLE);
      //  NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.mipmap.ic_launcher, "Go", pendingIntent).build();
        Notification notification=new NotificationCompat.Builder(getActivity(),"channel1")
                .setSmallIcon(R.drawable.ic_one)
                .setContentText("Title")
                .setAutoCancel(true)
                .setContentText("Message is this")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(pendingIntent)
                .build();

        managerCompat.notify(1,notification);




    }

}