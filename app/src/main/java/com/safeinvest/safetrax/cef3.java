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
import android.widget.TextView;
import android.widget.Toast;

public class cef3 extends Fragment {

TextView corrs;
public static final String User_Credentials3 = "User_Credentials3";
SharedPreferences settings3;
Button prev,next;
Spinner addtype;
String addtype1;
int addtype01;
EditText city,pin,district,state,country,telno,address;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_cef3, container, false);

        city=(EditText)view.findViewById(R.id.city);
        pin=(EditText)view.findViewById(R.id.pincode);
        next=(Button)view.findViewById(R.id.cef2next);
        addtype=(Spinner)view.findViewById(R.id.addtype);
        district=(EditText)view.findViewById(R.id.district);
        state=(EditText)view.findViewById(R.id.state);
        country=(EditText)view.findViewById(R.id.country);
        telno=(EditText)view.findViewById(R.id.telno);
        prev=(Button) view.findViewById(R.id.cef3prev);
        address=(EditText)view.findViewById(R.id.address);



        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment ceffrag = new cef2();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.fragmentContainerView, ceffrag).commit();
            }
        });



        corrs=(TextView) view.findViewById(R.id.corrsadd);
        corrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment ceffrag = new cef3_1();
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



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(city.getText().toString())){
                    city.setError("Enter City");
                    city.requestFocus();
                }
                else if(TextUtils.isEmpty(pin.getText().toString())){
                    pin.setError("Enter Pin");
                    pin.requestFocus();
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
                else if(TextUtils.isEmpty(address.getText().toString())){
                    address.requestFocus();
                    address.setError("Enter Address");
                }
                else{
                    Fragment ceffrag = new cef4();
                    FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                    fm.replace(R.id.fragmentContainerView, ceffrag).commit();
                }
            }
        });


        settings3 = getActivity().getSharedPreferences(User_Credentials3, 0);
        String city1=settings3.getString("city","");
        String pin1=settings3.getString("pin","");
        String district1=settings3.getString("district","");
        String state1=settings3.getString("state","");
        String country1=settings3.getString("country","");
        String telno1=settings3.getString("telno","");
        String address1= settings3.getString("address01","");
        int addtype1=settings3.getInt("addtype01",0);
        city.setText(city1);
        addtype.setSelection(addtype1);
        pin.setText(pin1);
        district.setText(district1);
        state.setText(state1);
        address.setText(address1);
        country.setText(country1);
        telno.setText(telno1);

        return view;

    }
    @Override
    public void onStop() {
        super.onStop();
        settings3 = getActivity().getSharedPreferences(User_Credentials3, Context.MODE_PRIVATE);
        settings3.edit().putString("city", city.getText().toString()).
                putString("pin",pin.getText().toString()).
                putInt("addtype01",addtype01).
                putInt("atype",addtype01+1).
                putString("district",district.getText().toString()).
                putString("state",state.getText().toString()).
                putString("address01",address.getText().toString()).
                putString("country",country.getText().toString()).
                putString("telno",telno.getText().toString()).commit();
    }
}