package com.safeinvest.safetrax;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class cef2 extends Fragment {
    public static final String User_Credentials2 = "User_Credentials2";
    public static final String qualification01 = "qualification01";
    public static final String firmname01 = "firmname01";
    public static final String mobile01 = "mobile01";
    public static final String email01 = "email01";
    public static final String annualincome01 = "annualincome01";
    public static final String familyincome01 = "familyincome01";
    SharedPreferences settings1;
Spinner occupation,sector;
Button next,save;
    String occupation1,sector1;
    int occupation01,sector01;
    EditText qualification,firmname,mobile,email,annualincome,familyincome;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_cef2, container, false);
        occupation=(Spinner) view.findViewById(R.id.occupation);
        next=(Button)view.findViewById(R.id.cef2next);
        save=(Button)view.findViewById(R.id.cef2save);
        qualification=(EditText)view.findViewById(R.id.groupname);
        firmname=(EditText)view.findViewById(R.id.pan);
        mobile=(EditText)view.findViewById(R.id.mobile);
        email=(EditText)view.findViewById(R.id.email);
        annualincome=(EditText)view.findViewById(R.id.annualincome);
        familyincome=(EditText)view.findViewById(R.id.familyincome);
        sector=(Spinner) view.findViewById(R.id.sector);
        ArrayAdapter<?> array_Adapter3 =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.nation,
                        R.layout.spinner01);
        array_Adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        occupation.setAdapter(array_Adapter3);
        occupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                occupation1 = array_Adapter3.getItem(position).toString();
                occupation01= position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        ArrayAdapter<?> array_Adapter4 =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.nation,
                        R.layout.spinner01);
        array_Adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sector.setAdapter(array_Adapter4);
        sector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sector1 = array_Adapter4.getItem(position).toString();
                sector01= position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(qualification.getText().toString())){
                      qualification.setError("Enter Qualification");
                      qualification.requestFocus();
                }
                else if(TextUtils.isEmpty(firmname.getText().toString())){
                    firmname.setError("Enter FirmName");
                    firmname.requestFocus();
                }
                else if(TextUtils.isEmpty(mobile.getText().toString())){
                    mobile.setError("Enter Mobile");
                    mobile.requestFocus();
                }
                else if(TextUtils.isEmpty(email.getText().toString())){
                    email.setError("Enter Email");
                    email.requestFocus();
                }
                else if(TextUtils.isEmpty(annualincome.getText().toString())){
                    annualincome.setError("Enter Annual Income");
                    annualincome.requestFocus();
                }
                else if(TextUtils.isEmpty(familyincome.getText().toString())){
                   familyincome.setError("Enter Family Income");
                    familyincome.requestFocus();
                }
                else if(mobile.getText().toString().trim().length()<10 || mobile.getText().toString().trim().length()>10){
                    Toast.makeText(getActivity(), "Enter Correct Number", Toast.LENGTH_SHORT).show();
                }
                else {
                    Fragment ceffrag = new cef();
                    FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                    fm.replace(R.id.fragmentContainerView, ceffrag).commit();
                }
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings1 = getActivity().getSharedPreferences(User_Credentials2, Context.MODE_PRIVATE);

                settings1.edit().putString(qualification01, qualification.getText().toString()).
                        putString(firmname01, firmname.getText().toString()).
                        putString(mobile01, mobile.getText().toString()).
                        putString(email01, email.getText().toString()).
                        putString(annualincome01, annualincome.getText().toString()).
                        putString(familyincome01, familyincome.getText().toString()).
                        putInt("occupation01",occupation01).
                        putInt("sector01",sector01).commit();
            }
        });


        settings1 = getActivity().getSharedPreferences(User_Credentials2, 0);
        String qualification1 = settings1.getString(qualification01, "");
        String firmname1 = settings1.getString(firmname01 ,"");
        String mobile1 = settings1.getString(mobile01, "");
        String email1 = settings1.getString(email01, "");
        String annualincome1 = settings1.getString(annualincome01, "");
        String familyincome1 = settings1.getString(familyincome01, "");
        int occupation1=settings1.getInt("occupation01",0);
        int sector1=settings1.getInt("sector01",0);
       qualification.setText(qualification1);
       firmname.setText(firmname1);
       mobile.setText(mobile1);
       email.setText(email1);
       annualincome.setText(annualincome1);
       familyincome.setText(familyincome1);
       occupation.setSelection(occupation1);
       sector.setSelection(sector1);
        return view;
    }
}