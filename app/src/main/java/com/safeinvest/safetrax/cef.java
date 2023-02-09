package com.safeinvest.safetrax;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.TimeZone;

public class cef extends Fragment {
    public static final String User_Credentials = "User_Credentials";
    public static final String fname01 = "fname01";
    public static final String mname01 = "mname01";
    public static final String lname01 = "lname01";
    public static final String pan01 = "pan01";
    public static final String dob01 = "dob01";
    public static final String groupname01 = "groupname01";
    public static final String noofdep01 = "noofdep01";
    public static final String famsize01 = "famsize01";
    SharedPreferences settings;
    Spinner clienttype,nationality,maritialstatus;
    String clienttype1,nationality1,maritialstatus1;
    ImageView datepicker;
    RadioButton kycyes,kycno,splityes,splitno;
    RadioGroup kyc,split;
    int year00;
    int month00;
    int days00;
    int split01,kyc01;
    int clienttype01,nationality01,maritialstatus01;
    String bday,btoday;
    EditText dob,groupname,fname,lname,mname,pan,noofdep,famsize;
    TextView age;
    Button save,next;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_cef, container, false);
       next=(Button)view.findViewById(R.id.cefnext);
       kyc=(RadioGroup)view.findViewById(R.id.kyc);
       split=(RadioGroup)view.findViewById(R.id.splitgroup);
       groupname=(EditText)view.findViewById(R.id.groupname);
       kycno=(RadioButton)view.findViewById(R.id.kycno);
       kycyes=(RadioButton)view.findViewById(R.id.kycyes);
       splitno=(RadioButton)view.findViewById(R.id.splitno);
       splityes=(RadioButton)view.findViewById(R.id.splityes);
       fname=(EditText)view.findViewById(R.id.fname);
       lname=(EditText)view.findViewById(R.id.lname);
       mname=(EditText)view.findViewById(R.id.mname);
       pan=(EditText)view.findViewById(R.id.pan);
       noofdep=(EditText)view.findViewById(R.id.noofdep);
       famsize=(EditText)view.findViewById(R.id.famsize);
       save=(Button)view.findViewById(R.id.cefsave);
       age=(TextView)view.findViewById(R.id.age);
        clienttype=(Spinner) view.findViewById(R.id.occupation);
        ArrayAdapter<?> array_Adapter3 =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.nation,
                        R.layout.spinner01);
        array_Adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clienttype.setAdapter(array_Adapter3);
        clienttype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                clienttype1 = array_Adapter3.getItem(position).toString();
                clienttype01= position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        nationality=(Spinner) view.findViewById(R.id.sector);
        ArrayAdapter<?> array_Adapter4 =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.nation,
                        R.layout.spinner01);
        array_Adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nationality.setAdapter(array_Adapter4);
        nationality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nationality1 = array_Adapter4.getItem(position).toString();
                nationality01= position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        maritialstatus=(Spinner)view.findViewById(R.id.maritialstatus);
        ArrayAdapter<?> array_Adapter5 =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.nation,
                        R.layout.spinner01);
        array_Adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maritialstatus.setAdapter(array_Adapter5);
        maritialstatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maritialstatus1 = array_Adapter5.getItem(position).toString();
                maritialstatus01= position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        split.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                split01=checkedId;
            }
        });

        kyc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                kyc01=checkedId;
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings = getActivity().getSharedPreferences(User_Credentials, Context.MODE_PRIVATE);

                settings.edit().putString(fname01, fname.getText().toString()).
                        putString(lname01, lname.getText().toString()).
                        putString(mname01, mname.getText().toString()).
                        putString(pan01, pan.getText().toString()).
                        putString(groupname01, groupname.getText().toString()).
                        putString(dob01, dob.getText().toString()).
                        putString("age01", age.getText().toString()).
                        putString(famsize01, famsize.getText().toString()).
                        putString(noofdep01, noofdep.getText().toString()).
                        putInt("client01",clienttype01).
                        putInt("nation01",nationality01).
                        putInt("kyc01",kyc01).putInt("split01",split01).commit();

            }
        });
        dob=(EditText)view.findViewById(R.id.dob);
        datepicker=(ImageView)view.findViewById(R.id.datepicker);
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
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(groupname.getText().toString())){
                    groupname.requestFocus();
                    groupname.setError("Enter Groupname");
                }
                else if(TextUtils.isEmpty(fname.getText().toString())){
                    fname.requestFocus();
                   fname.setError("Enter name");
                }
                else if(TextUtils.isEmpty(mname.getText().toString())){
                    mname.requestFocus();
                    mname.setError("Enter name");
                }
                else if(TextUtils.isEmpty(lname.getText().toString())){
                    lname.requestFocus();
                    lname.setError("Enter name");
                }
                else if(TextUtils.isEmpty(pan.getText().toString())){
                    pan.requestFocus();
                    pan.setError("Enter PAN");
                }
                else if(TextUtils.isEmpty(dob.getText().toString())){
                    dob.requestFocus();
                    dob.setError("Enter D.O.B");
                }
                else if(TextUtils.isEmpty(famsize.getText().toString())){
                    famsize.requestFocus();
                }
                else if(TextUtils.isEmpty(noofdep.getText().toString())){
                    noofdep.requestFocus();
                }
                else {
                    Fragment ceffrag = new cef2();
                    FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                    fm.replace(R.id.fragmentContainerView, ceffrag).commit();
                }
            }
        });
        settings = getActivity().getSharedPreferences(User_Credentials, 0);
        String fname1 = settings.getString(fname01, "");
        String lname1 = settings.getString(lname01, "");
        String mname1 = settings.getString(mname01, "");
        String pan1 = settings.getString(pan01, "");
        String groupname1 = settings.getString(groupname01, "");
        String dob1 = settings.getString(dob01, "");
        String age1= settings.getString("age01","");
        String famsize1 = settings.getString(famsize01, "");
        String noofdep1= settings.getString(noofdep01,"");
        int kyc1=settings.getInt("kyc01",0);
        int split1=settings.getInt("split01",0);
        int client1=settings.getInt("client01",0);
        int nation1=settings.getInt("nation01",0);
        kyc.check(kyc1);
        split.check(split1);
        fname.setText(fname1);
        lname.setText(lname1);
        mname.setText(mname1);
        pan.setText(pan1);
        groupname.setText(groupname1);
        dob.setText(dob1);
        age.setText(age1);
        famsize.setText(famsize1);
        noofdep.setText(noofdep1);
        clienttype.setSelection(client1);
        nationality.setSelection(nation1);
        return view;

    }
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String year1 = String.valueOf(year);
            String month1 = String.valueOf(month + 1);
            String day1 = String.valueOf(dayOfMonth);
            bday=day1 + "/" + month1 + "/" + year1;
            Calendar c=Calendar.getInstance();
            year00=c.get(Calendar.YEAR);
            month00=c.get(Calendar.MONTH);
            days00=c.get(Calendar.DAY_OF_MONTH);
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
            btoday=simpleDateFormat.format(Calendar.getInstance().getTime());
            try {
                int period= Period.between(LocalDate.of(year,month,dayOfMonth),LocalDate.now()).getYears();
                int period2=Period.between(LocalDate.of(year,month,dayOfMonth),LocalDate.now()).getMonths();
                String date2=String.valueOf(period2);
                String date= String.valueOf(period);
                age.setText(date+" Years & "+date2+" Months");

            } catch (Exception e) {
                e.printStackTrace();
            }

            dob.setText(bday);
        }


    };
}