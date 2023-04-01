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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
    Spinner clienttype,nationality,maritialstatus,prefix;
    String clienttype1,nationality1,maritialstatus1;
    ImageView datepicker;
    RadioButton kycyes,kycno,splityes,splitno;
    RadioGroup kyc,split;
    LinearLayout spouselayout;

    int year00;
    int month00;
    int days00;
    int split01,kyc01,prefix01;
    int clienttype01,maritialstatus01;
    String bday,btoday,nationlabel,nationvalue;
    EditText dob,groupname,fname,lname,mname,pan,noofdep,famsize,sname,sdob,sdate;
    int kycid,splitid;
    TextView age;
    Button next;
    String url,prefix1;
    int nationality01;
    ArrayList<Nationality11> nations=new ArrayList<>();

    public static class Nationality11{
        String label11,value11;

        public Nationality11(String label11, String value11) {
            this.label11 = label11;
            this.value11 = value11;
        }

        public String getLabel11() {
            return label11;
        }

        public void setLabel11(String label11) {
            this.label11 = label11;
        }

        public String getValue11() {
            return value11;
        }

        public void setValue11(String value11) {
            this.value11 = value11;
        }

        @Override
        public String toString() {
            return label11;
        }
    }


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
       spouselayout=(LinearLayout)view.findViewById(R.id.spouselayout);
       kycyes=(RadioButton)view.findViewById(R.id.kycyes);
       splitno=(RadioButton)view.findViewById(R.id.splitno);
       splityes=(RadioButton)view.findViewById(R.id.splityes);
       fname=(EditText)view.findViewById(R.id.fname);
       lname=(EditText)view.findViewById(R.id.lname);
       mname=(EditText)view.findViewById(R.id.mname);
       pan=(EditText)view.findViewById(R.id.pan);
       noofdep=(EditText)view.findViewById(R.id.noofdep);
       famsize=(EditText)view.findViewById(R.id.famsize);
       age=(TextView)view.findViewById(R.id.age);
       nationality=(Spinner) view.findViewById(R.id.nationality);
       sname=(EditText)view.findViewById(R.id.spousename);
       sdob=(EditText)view.findViewById(R.id.spousedob);
       sdate=(EditText)view.findViewById(R.id.anniversarydate);
       prefix=(Spinner)view.findViewById(R.id.prefix);



       url="http://15.1.1.134:9000/cef/getAllNationalities";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("success" +response.length());
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        nationlabel=jsonObject.getString("label");
                        nationvalue=jsonObject.getString("value");
                        Nationality11 nationality11=new Nationality11(nationlabel,nationvalue);
                        nations.add(nationality11);
                        ArrayAdapter<Nationality11> array_Adapter4= new ArrayAdapter<>(getContext(), R.layout.spinner01,nations);
                        nationality.setAdapter(array_Adapter4);
                        nationality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                nationality1 = array_Adapter4.getItem(position).toString();
                                Nationality11 nationality111=(Nationality11)parent.getSelectedItem();
                                nationality01=Integer.parseInt(nationality111.getValue11());
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("error2");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error" +error);
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this.getContext());
        requestQueue.add(stringRequest);



        clienttype=(Spinner) view.findViewById(R.id.clienttype);
        ArrayAdapter<?> array_Adapter3 =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.clienttype,
                        R.layout.spinner01);
        array_Adapter3.setDropDownViewResource(R.layout.spinner01);
        clienttype.setAdapter(array_Adapter3);
        clienttype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                clienttype1 = array_Adapter3.getItem(position).toString();
                clienttype01= position;
                System.out.println(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        ArrayAdapter<?> array_Adapter30 =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.prefix,
                        R.layout.spinner01);
        array_Adapter30.setDropDownViewResource(R.layout.spinner01);
        prefix.setAdapter(array_Adapter30);
        prefix.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                prefix1=array_Adapter30.getItem(position).toString();
                prefix01=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        maritialstatus=(Spinner)view.findViewById(R.id.maritialstatus);
        ArrayAdapter<?> array_Adapter5 =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.maritialstatus,
                        R.layout.spinner01);
        array_Adapter5.setDropDownViewResource(R.layout.spinner01);
        maritialstatus.setAdapter(array_Adapter5);
        maritialstatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maritialstatus1 = array_Adapter5.getItem(position).toString();
                maritialstatus01= position;
                if(maritialstatus01==1){
                    spouselayout.setVisibility(View.VISIBLE);
                }
                if(maritialstatus01==0 ||maritialstatus01==2 ||maritialstatus01==3){
                    spouselayout.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        split.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                split01=checkedId;
               if(checkedId==2131296717){
                   splitid=1;
               }
               else{
                   splitid=0;
               }
            }
        });


        kyc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                kyc01=checkedId;
                if(kyc01==2131296526){
                    kycid=1;
                }
                else {
                    kycid=0;
                }
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
                System.out.println(prefix01+"prefix");
                System.out.println(clienttype01+"type");
                System.out.println(nationality01+1 +"nation");
                System.out.println(kycid+"kyc");
                System.out.println(splitid+"split");
                System.out.println(maritialstatus01+"mstatus");
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
        String sname1=settings.getString("sname","");
        String sdob1=settings.getString("sdob","");
        String sdate1=settings.getString("sdate","");
        sname.setText(sname1);
        sdate.setText(sdate1);
        sdob.setText(sdob1);
        int kyc1=settings.getInt("kyc00",0);
        int split1=settings.getInt("split00",0);
        int client1=settings.getInt("clienttype",0);
        int maritial1=settings.getInt("maritial",0);
        int prefix1=settings.getInt("prefix",0);
        //int nation1=settings.getInt("nation",0);
        maritialstatus.setSelection(maritial1);
        prefix.setSelection(prefix1);
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
       // nationality.setSelection(nation1);

           System.out.println("NA are" +nations);
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




    @Override
    public void onStop() {
        super.onStop();
        settings = getActivity().getSharedPreferences(User_Credentials, Context.MODE_PRIVATE);

        settings.edit().putString(fname01, fname.getText().toString()).
                putInt("prefix",prefix01).
                putString(lname01, lname.getText().toString()).
                putString(mname01, mname.getText().toString()).
                putString(pan01, pan.getText().toString()).
                putString(groupname01, groupname.getText().toString()).
                putString(dob01, dob.getText().toString()).
                putString("age01", age.getText().toString()).
                putString(famsize01, famsize.getText().toString()).
                putInt("fam",Integer.parseInt(famsize.getText().toString())).
                putInt("nod",Integer.parseInt(noofdep.getText().toString())).
                putString(noofdep01, noofdep.getText().toString()).
                putString("client01",clienttype1).
                putString("nation",nationality1).
                putInt("clienttype",clienttype01).
                putInt("kyc",kycid).putInt("split",splitid).
                putInt("nations",nationality01).
                putInt("kyc00",kyc01).putInt("split00",split01).
               // putString("kyc01",kycid).putString("split01",splitid).
                putInt("maritial",maritialstatus01).
                putString("sname",sname.getText().toString()).
                putString("sdob",sdob.getText().toString()).
                putString("sdate",sdate.getText().toString()).
                commit();
    }
}