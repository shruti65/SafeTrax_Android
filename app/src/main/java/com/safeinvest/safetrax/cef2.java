package com.safeinvest.safetrax;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.Settings;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class cef2 extends Fragment {

    public static final String User_Credentials2 = "User_Credentials2";
    public static final String qualification01 = "qualification01";
    public static final String firmname01 = "firmname01";
    public static final String mobile01 = "mobile01";
    public static final String email01 = "email01";
    public static final String annualincome01 = "annualincome01";
    public static final String familyincome01 = "familyincome01";
    private ArrayList<Occupation11> occupations=new ArrayList<Occupation11>();
    private ArrayList<Sector11> sectors=new ArrayList<>();
    SharedPreferences settings1;
    Spinner occupation,sector,referenceby;
    Button next,save;
    String occupation1,sector1,url,occuvalue,rlabel,rvalue,url10,url2,occulabel11,occuvalue11,occuvalue2,sectorvalue22,sectorlabel22,occus;
    int occupation01,referenceby01;
    EditText qualification,firmname,mobile,email,annualincome,familyincome,designation,refname;


    public class Occupation11 {
        public String label11,vallue11;

        public Occupation11(String label11, String vallue11) {
            this.label11 = label11;
            this.vallue11 = vallue11;
        }

        public String getLabel11() {
            return label11;
        }

        public void setLabel11(String label11) {
            this.label11 = label11;
        }

        public String getVallue11() {
            return vallue11;
        }

        public void setVallue11(String vallue11) {
            this.vallue11 = vallue11;
        }

        @NonNull
        @Override
        public String toString() {
            return label11;
        }
    }
    public class Sector11{
        public String label22,value22;

        public Sector11(String label22, String value22) {
            this.label22 = label22;
            this.value22 = value22;
        }

        public String getLabel22() {
            return label22;
        }

        public void setLabel22(String label22) {
            this.label22 = label22;
        }

        public String getValue22() {
            return value22;
        }

        public void setValue22(String value22) {
            this.value22 = value22;
        }

        @Override
        public String toString() {
            return  label22;
        }
    }
    public class Referenceby11{
        String label11,value11;

        public Referenceby11(String label11, String value11) {
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
            return  label11 ;
        }
    }


    ArrayList<Referenceby11> references =new ArrayList<Referenceby11>();


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
        designation=(EditText)view.findViewById(R.id.designation);
        referenceby=(Spinner)view.findViewById(R.id.referenceby);
        refname=(EditText)view.findViewById(R.id.refname);



        url="http://15.1.1.134:9000/cef/getAllProfessions";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("success" +response.length());
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        occulabel11=jsonObject.getString("label");
                        occuvalue11=jsonObject.getString("value");
                        Occupation11 o1=new Occupation11(occulabel11,occuvalue11);
                        occupations.add(o1);
                        occuvalue=jsonObject.getString("value");
                      //  System.out.println(occuvalue);
                        ArrayAdapter<Occupation11> array_Adapter3= new ArrayAdapter<Occupation11>(getContext(),R.layout.spinner01,occupations);
                        occupation.setAdapter(array_Adapter3);
                        occupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                occupation1 = array_Adapter3.getItem(position).toString();
                                occupation01= position;
                                Occupation11 occupation11=(Occupation11) parent.getSelectedItem();
                                occuvalue2=occupation11.getVallue11();
                                occus=occupation11.getLabel11();
                                System.out.println(occuvalue2);
                                url2 ="http://15.1.1.134:9000/cef/getAllSector/" +occuvalue2;
                                StringRequest stringRequest1=new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        System.out.println(response);
                                        try {
                                            JSONArray jsonArray=new JSONArray(response);
                                            for(int i=0;i<jsonArray.length();i++){
                                                JSONObject jsonObject= jsonArray.getJSONObject(i);
                                                sectorlabel22=jsonObject.getString("label");
                                                sectorvalue22=jsonObject.getString("value");
                                                Sector11 s1=new Sector11(sectorlabel22,sectorvalue22);
                                                sectors.add(s1);
                                                ArrayAdapter<Sector11> array_Adapter4 = new ArrayAdapter<>(getContext(),R.layout.spinner01,sectors);
                                                sector.setAdapter(array_Adapter4);
                                                sector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                       Sector11 sector11=(Sector11)parent.getSelectedItem();
                                                        sector1=sector11.getValue22();
                                                    }
                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {
                                                    }
                                                });
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            System.out.println("Error on response" );
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                    }
                                });
                                RequestQueue requestQueue1= Volley.newRequestQueue(getContext());
                                requestQueue1.add(stringRequest1);
                                sectors.clear();
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
                    mobile.setError("enter correct number");
                    mobile.requestFocus();
                }
                else if(TextUtils.isEmpty(designation.getText().toString())){
                    designation.setError("Enter Designation");
                    designation.requestFocus();
                }
                else {
                    Fragment ceffrag = new cef3();
                    FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                    fm.replace(R.id.fragmentContainerView, ceffrag).commit();
                }
                System.out.println(occus +"OCCU");
                System.out.println(occuvalue2 +"occu");
                System.out.println(sector1 +"sector");
            }
        });



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment ceffrag = new cef();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.fragmentContainerView, ceffrag).commit();
            }
        });



        url10="http://15.1.1.134:9000/cef/getAllReferenceBy";
        StringRequest stringRequest2=new StringRequest(Request.Method.GET, url10, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        rlabel=jsonObject.getString("label");
                        rvalue=jsonObject.getString("value");
                        Referenceby11 referenceby11=new Referenceby11(rlabel,rvalue);
                        references.add(referenceby11);
                        ArrayAdapter<Referenceby11> array_Adapter4= new ArrayAdapter<Referenceby11>(getContext(), R.layout.spinner01,references);
                        referenceby.setAdapter(array_Adapter4);
                        referenceby.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Referenceby11 referenceby111= (Referenceby11) parent.getSelectedItem();
                                referenceby01= Integer.parseInt(referenceby111.getValue11());
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
        RequestQueue requestQueue2= Volley.newRequestQueue(this.getContext());
        requestQueue2.add(stringRequest2);


        settings1 = getActivity().getSharedPreferences(User_Credentials2, 0);
        String qualification1 = settings1.getString(qualification01, "");
        String firmname1 = settings1.getString(firmname01 ,"");
        String mobile1 = settings1.getString(mobile01, "");
        String email1 = settings1.getString(email01, "");
        String annualincome1 = settings1.getString(annualincome01, "");
        String familyincome1 = settings1.getString(familyincome01, "");
        String refname1=settings1.getString("refname","");
        String desig1=settings1.getString("designation","");
        designation.setText(desig1);
      //  int occupation1=settings1.getInt("occupation01",0);
        //int sector1=settings1.getInt("sector01",0);
       qualification.setText(qualification1);
       firmname.setText(firmname1);
       mobile.setText(mobile1);
       email.setText(email1);
       annualincome.setText(annualincome1);
       familyincome.setText(familyincome1);
       refname.setText(refname1);
       //occupation.setSelection(occupation1);
      // sector.setSelection(sector1);
        System.out.println(occus +"OCCUPATION");
        return view;


    }


    @Override
    public void onStop() {
        super.onStop();
        settings1 = getActivity().getSharedPreferences(User_Credentials2, Context.MODE_PRIVATE);

        settings1.edit().putString(qualification01, qualification.getText().toString()).
                putString(firmname01, firmname.getText().toString()).
                putString(mobile01, mobile.getText().toString()).
                putString(email01, email.getText().toString()).
                putString("occupation",occus).
                putString(annualincome01, annualincome.getText().toString()).
                putString(familyincome01, familyincome.getText().toString()).
                putInt("occupation01",Integer.parseInt(occuvalue2)).
                putInt("reby",referenceby01).
                putString("refname",refname.getText().toString()).
                putInt("aincome",Integer.parseInt(annualincome.getText().toString())).
                putInt("fincome",Integer.parseInt(familyincome.getText().toString())).
                putString("designation",designation.getText().toString()).
                putInt("sector01",Integer.parseInt(sector1)).commit();
    }
}