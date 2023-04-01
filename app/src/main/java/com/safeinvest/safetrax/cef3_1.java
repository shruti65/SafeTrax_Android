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

public class cef3_1 extends Fragment {
Spinner addtype;
EditText address,city,pin,district,state,country,telno;
String addtype1;
public static final String User_Credentials31 = "User_Credentials31";
int addtype01;
SharedPreferences settings31;
Button prev,add;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_cef3_1, container, false);
       prev=(Button)view.findViewById(R.id.cef31prev);
       address=(EditText)view.findViewById(R.id.address1);
       city=(EditText)view.findViewById(R.id.city1);
       pin=(EditText)view.findViewById(R.id.pincode1);
       district=(EditText)view.findViewById(R.id.district1);
       state=(EditText)view.findViewById(R.id.state1);
       country=(EditText)view.findViewById(R.id.country1);
       telno=(EditText)view.findViewById(R.id.telno1);
       addtype=(Spinner)view.findViewById(R.id.addtype1);
       add=(Button)view.findViewById(R.id.cef31add);


       prev.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Fragment ceffrag = new cef3();
               FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
               fm.replace(R.id.fragmentContainerView, ceffrag).commit();
           }
       });


        ArrayAdapter<?> array_Adapter5 =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.addtype,
                        R.layout.spinner01);
        array_Adapter5.setDropDownViewResource(R.layout.spinner01);
        addtype.setAdapter(array_Adapter5);
        addtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                addtype1=array_Adapter5.getItem(position).toString();
                addtype01= position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(address.getText().toString())){
                    address.setError("Enter Address");
                }
                else if(TextUtils.isEmpty(pin.getText().toString())){
                    pin.setError("Enter Pin");
                    pin.requestFocus();
                }
                else if(TextUtils.isEmpty(city.getText().toString())){
                    city.requestFocus();
                    city.setError("Enter City");
                }
                else if(TextUtils.isEmpty(district.getText().toString())){
                    district.setError("Enter District");
                    district.requestFocus();
                }
                else if(TextUtils.isEmpty(state.getText().toString())){
                    state.setError("Enter State");
                    state.requestFocus();
                }
                else if(TextUtils.isEmpty(country.getText().toString())){
                    country.requestFocus();
                    country.setError("Enter Country");
                }
                else if(TextUtils.isEmpty(telno.getText().toString())){
                    telno.requestFocus();
                    telno.setError("Enter TelNo.");
                }
                else{
                    Fragment ceffrag = new cef3();
                    FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                    fm.replace(R.id.fragmentContainerView, ceffrag).commit();
                }
            }
        });



        settings31=getActivity().getSharedPreferences(User_Credentials31,0);
        String address1=settings31.getString("address","");
        int addresstype1=settings31.getInt("addresstype",0);
        String city1=settings31.getString("city","");
        String pin1=settings31.getString("pin","");
        String district1=settings31.getString("district","");
        String state1=settings31.getString("state","");
        String country1=settings31.getString("country","");
        String tel1=settings31.getString("tel","");
        address.setText(address1);
        addtype.setSelection(addresstype1);
        city.setText(city1);
        pin.setText(pin1);
        district.setText(district1);
        state.setText(state1);
        country.setText(country1);
        telno.setText(tel1);

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        settings31 = getActivity().getSharedPreferences(User_Credentials31, Context.MODE_PRIVATE);
        settings31.edit().putString("address",address.getText().toString()).
                putInt("addresstype",addtype01).
                putString("city",city.getText().toString()).
                putString("pin",pin.getText().toString()).
                putString("district",district.getText().toString()).
                putString("state",state.getText().toString()).
                putString("country",country.getText().toString()).
                putInt("atype",addtype01+1).
                putString("tel",telno.getText().toString()).commit();



    }
}