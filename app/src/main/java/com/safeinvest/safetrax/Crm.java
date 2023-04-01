package com.safeinvest.safetrax;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Crm extends Fragment {
   EditText pname,mobile,email,city,address;
   Spinner rby;
   Button add;
   int rbypos;
   ArrayList<Crmadd> list=new ArrayList<Crmadd>();
   public class Crmadd{
       String prname,reby,email01,mobile01,city01,add01;

       public Crmadd(String prname, String reby, String email01, String mobile01, String city01, String add01) {
           this.prname = prname;
           this.reby = reby;
           this.email01 = email01;
           this.mobile01 = mobile01;
           this.city01 = city01;
           this.add01 = add01;
       }

       public String getPrname() {
           return prname;
       }

       public void setPrname(String prname) {
           this.prname = prname;
       }

       public String getReby() {
           return reby;
       }

       public void setReby(String reby) {
           this.reby = reby;
       }

       public String getEmail01() {
           return email01;
       }

       public void setEmail01(String email01) {
           this.email01 = email01;
       }

       public String getMobile01() {
           return mobile01;
       }

       public void setMobile01(String mobile01) {
           this.mobile01 = mobile01;
       }

       public String getCity01() {
           return city01;
       }

       public void setCity01(String city01) {
           this.city01 = city01;
       }

       public String getAdd01() {
           return add01;
       }

       public void setAdd01(String add01) {
           this.add01 = add01;
       }

       @Override
       public String toString() {
           return
                   "prname=" + prname + ' ' +
                   " reby=" + reby + ' ' +
                   " email01=" + email01 + ' ' +
                   " mobile01=" + mobile01 + ' ' +
                   " city01=" + city01 + ' ' +
                   " add01=" + add01 + ' ';
       }
   }
   String rebystr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_crm, container, false);
       pname=(EditText) view.findViewById(R.id.pname);
       rby=(Spinner) view.findViewById(R.id.rby);
       mobile=(EditText) view.findViewById(R.id.mobile);
       email=(EditText) view.findViewById(R.id.email);
       city=(EditText) view.findViewById(R.id.city);
       address=(EditText) view.findViewById(R.id.address);
       add=(Button) view.findViewById(R.id.add);


        ArrayAdapter<?> array_Adapter =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.crmrby,
                        R.layout.spinner01);
        array_Adapter.setDropDownViewResource(R.layout.spinner01);
        rby.setAdapter(array_Adapter);
        rby.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rebystr=array_Adapter.getItem(position).toString();
                rbypos= position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject jsonObject=new JSONObject();
                jsonObject.put("pname",pname.getText().toString());
                jsonObject.put("rby",rbypos);
                jsonObject.put("mobile",mobile.getText().toString());
                jsonObject.put("email",email.getText().toString());
                jsonObject.put("city",city.getText().toString());
                jsonObject.put("address",address.getText().toString());


                if(TextUtils.isEmpty(pname.getText().toString())){
                    pname.requestFocus();
                    pname.setError("Enter Prospect");
                }
                else if(rbypos==0){
                    Toast.makeText(getContext(), "Select Reference BY", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                    String pname02=pname.getText().toString();
                    String reby02=rebystr;
                    String mobile02=mobile.getText().toString();
                    String email02=email.getText().toString();
                    String city02=city.getText().toString();
                    String address02=address.getText().toString();
                    list.add(new Crmadd(pname02,reby02,email02,mobile02,city02,address02));
                    System.out.println("LIST"+list);
                   /* AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                    builder.setTitle("Prospect Added");
                    builder.setCancelable(false);
                    ArrayAdapter<Crmadd> arrayAdapter=new ArrayAdapter<Crmadd>(getContext(),R.layout.spinner01,list);
                    builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();*/
                }
            }
        });



        return view;
    }
}