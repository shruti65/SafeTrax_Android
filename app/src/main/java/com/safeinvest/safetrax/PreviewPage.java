package com.safeinvest.safetrax;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PreviewPage extends Fragment {
 TextView gname,name,ctype,nation,pan,kyc,split,dob,age,mstatus,fsize,nod,quali,occu,sector;
 TextView firmname,desig,mobile,mail,fincome,aincome,residence,address,city,pin,district,state;
 TextView country,tel,resstatus,reby,homeown,two,four,advisor,residence1,address1,city1,pin1,district1,state1,country1,tel1;
 String clientid,url;

 Button einvestment,scriteria,fbusiness,einsurance,cancels;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_preview_page, container, false);


        einsurance=(Button)view.findViewById(R.id.einsurance);
        einvestment=(Button)view.findViewById(R.id.einvestment);
        scriteria=(Button)view.findViewById(R.id.scriteria);
        fbusiness=(Button)view.findViewById(R.id.fbusiness);
        residence1=(TextView)view.findViewById(R.id.residence1);
        address1=(TextView)view.findViewById(R.id.addresss1);
        city1=(TextView)view.findViewById(R.id.citys1);
        pin1=(TextView)view.findViewById(R.id.pins1);
        district1=(TextView)view.findViewById(R.id.districts1);
        state1=(TextView)view.findViewById(R.id.states1);
        country1=(TextView)view.findViewById(R.id.countrys1);
        tel1=(TextView)view.findViewById(R.id.tel1);
        fincome=(TextView)view.findViewById(R.id.fincome);
        aincome=(TextView)view.findViewById(R.id.aincome);
        residence=(TextView)view.findViewById(R.id.residence);
        address=(TextView)view.findViewById(R.id.addresss);
        city=(TextView)view.findViewById(R.id.citys);
        pin=(TextView)view.findViewById(R.id.pins);
        district=(TextView)view.findViewById(R.id.districts);
        state=(TextView)view.findViewById(R.id.states);
        country=(TextView)view.findViewById(R.id.countrys);
        tel=(TextView)view.findViewById(R.id.tel);
        resstatus=(TextView)view.findViewById(R.id.resstatus1);
        reby=(TextView)view.findViewById(R.id.reby);
        homeown=(TextView)view.findViewById(R.id.homeown);
        two=(TextView)view.findViewById(R.id.two);
        four=(TextView)view.findViewById(R.id.four);
        advisor=(TextView)view.findViewById(R.id.advisor);
        gname=(TextView) view.findViewById(R.id.gname);
        name=(TextView) view.findViewById(R.id.name);
        ctype=(TextView) view.findViewById(R.id.ctype);
        nation=(TextView) view.findViewById(R.id.nation);
        pan=(TextView) view.findViewById(R.id.pans);
        kyc=(TextView) view.findViewById(R.id.kycs);
        split=(TextView) view.findViewById(R.id.split);
        dob=(TextView) view.findViewById(R.id.dob);
        age=(TextView) view.findViewById(R.id.age);
        mstatus=(TextView) view.findViewById(R.id.mstatus);
        fsize=(TextView) view.findViewById(R.id.fsize);
        nod=(TextView) view.findViewById(R.id.nod);
        quali=(TextView) view.findViewById(R.id.quali);
        occu=(TextView) view.findViewById(R.id.occu);
        sector=(TextView) view.findViewById(R.id.sector);
        firmname=(TextView) view.findViewById(R.id.firmname);
        desig=(TextView) view.findViewById(R.id.desig);
        mobile=(TextView) view.findViewById(R.id.mobile);
        mail=(TextView) view.findViewById(R.id.mail);
        cancels=(Button)view.findViewById(R.id.b5);


        Bundle extras=getArguments();
        clientid=extras.getString("clientid");
        System.out.println(clientid+"HELLO");


         url="http://15.1.1.134:9000/cef/client/"+clientid;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    System.out.println(jsonObject);
                    gname.setText(jsonObject.getString("groupName"));
                    int p=jsonObject.getInt("prefix");
                    String pre = null;
                    if(p==1){pre="MR.";}
                    if(p==2){pre="MRS.";}
                    if(p==3){pre="Miss.";}
                    name.setText(pre +jsonObject.getString("firstName")+" "+jsonObject.getString("middleName")+" "+jsonObject.getString("lastName"));
                    int c=jsonObject.getInt("clientType");
                    if(c==0){
                        ctype.setText("Individual");
                    }
                    if(c==1){
                        ctype.setText("Non-Individual");
                    }
                    int nationss=jsonObject.getInt("nationalityID");
                    String nations= String.valueOf(nationss);


                    String url1="http://15.1.1.134:9000/cef/getAllNationalities";
                    StringRequest stringRequest1= new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray jsonArray=new JSONArray(response);
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                                    if(jsonObject.getString("value").equals(nations)){
                                        nation.setText(jsonObject.getString("label"));

                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                System.out.println("error2");
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    RequestQueue requestQueue1= Volley.newRequestQueue(getContext());
                    requestQueue1.add(stringRequest1);

                    pan.setText(jsonObject.getString("pan"));
                    int ky=jsonObject.getInt("kycStatus");
                    if(ky==1){kyc.setText("Yes");}
                    if(ky==0){kyc.setText("No");}

                    int spl=jsonObject.getInt("split");
                    if(spl==0){split.setText("No");}
                    if (spl==1){split.setText("Yes");}
                    dob.setText(jsonObject.getString("dob"));
                    reby.setText(jsonObject.getString("referenceBy"));
                    age.setText(jsonObject.getString("avgAge"));
                    int mstatu=jsonObject.getInt("maritalStatus");
                    if(mstatu==0){mstatus.setText("Single");}
                    if(mstatu==1){mstatus.setText("Married");}
                    if(mstatu==2){mstatus.setText("Divorced");}
                    if(mstatu==3){mstatus.setText("Widowed");}
                    String fs= String.valueOf(jsonObject.getInt("familySize"));
                    fsize.setText(fs);
                    String dn=String.valueOf(jsonObject.getInt("dependentNo"));
                    nod.setText(dn);
                    quali.setText(jsonObject.getString("qualification"));
                    occu.setText(jsonObject.getString("occupation"));
                    firmname.setText(jsonObject.getString("firmName"));
                    desig.setText(jsonObject.getString("designation"));
                    mobile.setText(jsonObject.getString("mobileNo"));
                    mail.setText(jsonObject.getString("emailid"));
                    sector.setText(jsonObject.getString("sector"));
                    aincome.setText(String.valueOf(jsonObject.getInt("annualIncome")));
                    fincome.setText(String.valueOf(jsonObject.getInt("familyIncome")));
                    JSONObject jsonObject1=jsonObject.getJSONObject("valuableDataMasterBean");
                    int res=jsonObject1.getInt("residantStatus");
                    if(res==0){resstatus.setText("India");}
                    if(res==1){resstatus.setText("NRI");}
                    four.setText(String.valueOf(jsonObject1.getInt("fourWheller")));
                    two.setText(String.valueOf(jsonObject1.getInt("twoWheller")));
                    advisor.setText(jsonObject1.getString("advRemark"));
                    JSONArray jsonArray=jsonObject.getJSONArray("addressList");
                    JSONObject jsonObject2=jsonArray.getJSONObject(0);
                    int ho=jsonObject1.getInt("homeOwnership");
                    if(ho==1){homeown.setText("Owned By Self/Spouse");}
                    if(ho==2){homeown.setText("Owned By Parents/Siblings");}
                    if(ho==3){homeown.setText("Rented");}
                    if(ho==4){homeown.setText("Paying Guest");}
                    if(ho==5){homeown.setText("Company Provided");}
                    residence.setText("Address");
                    address.setText(jsonObject2.getString("address"));
                    city.setText(jsonObject2.getString("city"));
                    district.setText(jsonObject2.getString("district"));
                    country.setText(jsonObject2.getString("country"));
                    pin.setText(jsonObject2.getString("pinCode"));
                    state.setText(jsonObject2.getString("state"));
                    tel.setText(jsonObject2.getString("telNo"));
                    JSONObject jsonObject3=jsonArray.getJSONObject(1);
                    residence1.setText("Correspondence");
                    address1.setText(jsonObject3.getString("address"));
                    city1.setText(jsonObject3.getString("city"));
                    district1.setText(jsonObject3.getString("district"));
                    country1.setText(jsonObject3.getString("country"));
                    pin1.setText(jsonObject3.getString("pinCode"));
                    state1.setText(jsonObject3.getString("state"));
                    tel1.setText(jsonObject3.getString("telNo"));
                    JSONArray jsonObject4=jsonObject.getJSONArray("existingDetailsList");
                    JSONArray jsonArray1=jsonObject.getJSONArray("criteriaDataList");
                    JSONArray jsonArray2=jsonObject.getJSONArray("firstBizDetailsList");
                    System.out.println("THIS" +jsonObject4);

                    einsurance.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int sr0=0;
                            AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
                            ViewGroup viewGroup= view.findViewById(android.R.id.content);
                            View dialogview=LayoutInflater.from(v.getContext()).inflate(R.layout.fragment_previewalert,viewGroup,false);
                            TextView textView=(TextView)dialogview.findViewById(R.id.prevchangetext);
                            LinearLayout linearLayout=(LinearLayout)dialogview.findViewById(R.id.linear1);
                            textView.setText("Existing Insurance");
                            TableLayout tableLayout=new TableLayout(getContext());
                            for(int i=0;i<1;i++){
                                TableRow tableRow=new TableRow(getContext());
                                TextView textView1=new TextView(getContext());
                                textView1.setText(" Sr.no. ");
                                TextView textView2=new TextView(getContext());
                                TextView textView3=new TextView(getContext());
                                textView1.setTextColor(Color.parseColor("#FFFFFF"));
                                textView2.setTextColor(Color.parseColor("#FFFFFF"));
                                textView3.setTextColor(Color.parseColor("#FFFFFF"));
                                textView2.setText(" Insurance Type ");
                                textView3.setText(" Sum Assured ");
                                tableRow.addView(textView1);
                                tableRow.addView(textView2);
                                tableRow.addView(textView3);
                                tableRow.setBackgroundColor(Color.parseColor("#4A587B"));
                                tableLayout.addView(tableRow);
                                linearLayout.addView(tableLayout);
                            }
                            for(int j=0;j<jsonObject4.length();j++){
                                try {
                                    JSONObject jsonObject5=jsonObject4.getJSONObject(j);
                                    String inv=jsonObject5.getString("productType");

                                    if(inv.equals("2")){
                                        System.out.println(jsonObject5);
                                        TableLayout tableLayout1=new TableLayout(getContext());
                                        TableRow tableRow=new TableRow(getContext());
                                        TextView sr=new TextView(getContext());
                                        sr.setText(String.valueOf(sr0+1));
                                        sr0++;
                                        TextView type=new TextView(getContext());
                                        type.setText(jsonObject5.getString("productName"));
                                        TextView amt=new TextView(getContext());
                                        amt.setText(jsonObject5.getString("value"));
                                        amt.setTextColor(Color.parseColor("#FFFFFF"));
                                        sr.setTextColor(Color.parseColor("#FFFFFF"));
                                        type.setTextColor(Color.parseColor("#FFFFFF"));
                                        sr.setWidth(150);
                                        amt.setWidth(250);
                                        type.setWidth(280);
                                        tableRow.addView(sr);
                                        tableRow.addView(type);
                                        tableRow.addView(amt);
                                        tableLayout1.addView(tableRow);
                                        linearLayout.addView(tableLayout1);

                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                                TableLayout tableLayout2=new TableLayout(getContext());
                                TableRow total=new TableRow(getContext());
                                TextView total1=new TextView(getContext());
                                total1.setWidth(400);
                                TextView tot=new TextView(getContext());
                                String url01="http://15.1.1.134:9000/cef/sumOfExistingDetails/"+clientid+"/2";
                                StringRequest stringRequest2=new StringRequest(Request.Method.GET, url01, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        tot.setText(response);
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                });
                                RequestQueue requestQueue=Volley.newRequestQueue(getContext());
                                requestQueue.add(stringRequest2);
                                tot.setTextColor(Color.parseColor("#FFFFFF"));
                                total1.setText("Total");
                                total1.setTextColor(Color.parseColor("#FFFFFF"));
                                total.addView(total1);
                                total.addView(tot);
                                tableLayout2.addView(total);
                                tableLayout2.setBackgroundColor(Color.parseColor("#4A587B"));
                                linearLayout.addView(tableLayout2);

                            builder.setView(dialogview);
                            AlertDialog alertDialog=builder.create();
                            alertDialog.show();
                        }
                    });




                    einvestment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int sr0=0;
                            AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
                            ViewGroup viewGroup= view.findViewById(android.R.id.content);
                            View dialogview=LayoutInflater.from(v.getContext()).inflate(R.layout.fragment_previewalert,viewGroup,false);
                            TextView textView=(TextView)dialogview.findViewById(R.id.prevchangetext);
                            LinearLayout linearLayout=(LinearLayout)dialogview.findViewById(R.id.linear1);
                            textView.setText("Existing Investment");
                            TableLayout tableLayout=new TableLayout(getContext());
                            for(int i=0;i<1;i++){
                                TableRow tableRow=new TableRow(getContext());
                                TextView textView1=new TextView(getContext());
                                textView1.setText(" Sr.no. ");
                                TextView textView2=new TextView(getContext());
                                TextView textView3=new TextView(getContext());
                                textView1.setTextColor(Color.parseColor("#FFFFFF"));
                                textView2.setTextColor(Color.parseColor("#FFFFFF"));
                                textView3.setTextColor(Color.parseColor("#FFFFFF"));
                                textView2.setText(" Investment Type ");
                                textView3.setText(" Investment Amount ");
                                tableRow.addView(textView1);
                                tableRow.addView(textView2);
                                tableRow.addView(textView3);
                                tableRow.setBackgroundColor(Color.parseColor("#4A587B"));
                                tableLayout.addView(tableRow);
                                linearLayout.addView(tableLayout);
                            }
                            for(int j=0;j<jsonObject4.length();j++){
                                try {
                                    JSONObject jsonObject5=jsonObject4.getJSONObject(j);
                                    String ins=jsonObject5.getString("productType");
                                    if(ins.equals("1")){
                                        TableLayout tableLayout1=new TableLayout(getContext());
                                        TableRow tableRow=new TableRow(getContext());
                                        TextView sr=new TextView(getContext());
                                        sr.setText(String.valueOf(sr0+1));
                                        sr0++;
                                        TextView type=new TextView(getContext());
                                        type.setText(jsonObject5.getString("productName"));
                                        TextView amt=new TextView(getContext());
                                        amt.setText(jsonObject5.getString("value"));
                                        amt.setTextColor(Color.parseColor("#FFFFFF"));
                                        sr.setTextColor(Color.parseColor("#FFFFFF"));
                                        type.setTextColor(Color.parseColor("#FFFFFF"));
                                        sr.setWidth(150);
                                        amt.setWidth(250);
                                        type.setWidth(280);
                                        tableRow.addView(sr);
                                        tableRow.addView(type);
                                        tableRow.addView(amt);
                                        tableLayout1.addView(tableRow);
                                        linearLayout.addView(tableLayout1);
                                        System.out.println(jsonObject4.length()+"THIS LEN");

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                                TableLayout tableLayout2=new TableLayout(getContext());
                                TableRow total=new TableRow(getContext());
                                TextView total1=new TextView(getContext());
                                total1.setWidth(400);
                                TextView tot=new TextView(getContext());
                                String url01="http://15.1.1.134:9000/sumOfExistingDetails/"+clientid+"/1";
                                StringRequest stringRequest2=new StringRequest(Request.Method.GET, url01, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        tot.setText(response);
                                        System.out.println("THE RES"+response);
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                });
                                RequestQueue requestQueue=Volley.newRequestQueue(getContext());
                                requestQueue.add(stringRequest2);
                                tot.setTextColor(Color.parseColor("#FFFFFF"));
                                total1.setText("Total");
                                total1.setTextColor(Color.parseColor("#FFFFFF"));
                                total.addView(total1);
                                total.addView(tot);
                                tableLayout2.addView(total);
                                tableLayout2.setBackgroundColor(Color.parseColor("#4A587B"));
                                linearLayout.addView(tableLayout2);

                            builder.setView(dialogview);
                            AlertDialog alertDialog=builder.create();
                            alertDialog.show();
                        }
                    });



                    scriteria.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int sr0=0;
                            AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
                            ViewGroup viewGroup= view.findViewById(android.R.id.content);
                            View dialogview=LayoutInflater.from(v.getContext()).inflate(R.layout.fragment_previewalert,viewGroup,false);
                            TextView textView=(TextView)dialogview.findViewById(R.id.prevchangetext);
                            LinearLayout linearLayout=(LinearLayout)dialogview.findViewById(R.id.linear1);
                            textView.setText("Criteria Details");
                            TableLayout tableLayout=new TableLayout(getContext());
                            for(int i=0;i<1;i++){
                                TableRow tableRow=new TableRow(getContext());
                                TextView textView1=new TextView(getContext());
                                textView1.setText(" Sr.no. ");
                                TextView textView2=new TextView(getContext());
                                TextView textView3=new TextView(getContext());
                                textView1.setTextColor(Color.parseColor("#FFFFFF"));
                                textView2.setTextColor(Color.parseColor("#FFFFFF"));
                                textView3.setTextColor(Color.parseColor("#FFFFFF"));
                                textView2.setText(" Investment Type ");
                                textView3.setText(" Minimum Amount ");
                                tableRow.addView(textView1);
                                tableRow.addView(textView2);
                                tableRow.addView(textView3);
                                tableRow.setBackgroundColor(Color.parseColor("#4A587B"));
                                tableLayout.addView(tableRow);
                                linearLayout.addView(tableLayout);
                                for(int j=0;j<jsonArray1.length();j++){
                                    try {
                                        JSONObject jsonObject5=jsonArray1.getJSONObject(j);
                                        TableLayout tableLayout1=new TableLayout(getContext());
                                        TableRow tableRow1=new TableRow(getContext());
                                        TextView sr=new TextView(getContext());
                                        sr.setText(String.valueOf(sr0+1));
                                        sr0++;
                                        TextView type=new TextView(getContext());
                                        type.setText(jsonObject5.getString("investmentType"));
                                        TextView amt=new TextView(getContext());
                                        amt.setText(jsonObject5.getString("minAmt"));
                                        amt.setTextColor(Color.parseColor("#FFFFFF"));
                                        sr.setTextColor(Color.parseColor("#FFFFFF"));
                                        type.setTextColor(Color.parseColor("#FFFFFF"));
                                        sr.setWidth(150);
                                        amt.setWidth(250);
                                        type.setWidth(280);
                                        tableRow1.addView(sr);
                                        tableRow1.addView(type);
                                        tableRow1.addView(amt);
                                        tableLayout1.addView(tableRow1);
                                        linearLayout.addView(tableLayout1);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }
                            builder.setView(dialogview);
                            AlertDialog alertDialog=builder.create();
                            alertDialog.show();
                        }
                    });



                    fbusiness.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int sr0=0;
                            AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
                            ViewGroup viewGroup= view.findViewById(android.R.id.content);
                            View dialogview=LayoutInflater.from(v.getContext()).inflate(R.layout.fragment_previewalert,viewGroup,false);
                            TextView textView=(TextView)dialogview.findViewById(R.id.prevchangetext);
                            LinearLayout linearLayout=(LinearLayout)dialogview.findViewById(R.id.linear1);
                            textView.setText("First Business");
                            TableLayout tableLayout=new TableLayout(getContext());
                            for(int i=0;i<1;i++){
                                TableRow tableRow=new TableRow(getContext());
                                TextView textView1=new TextView(getContext());
                                textView1.setText(" Sr.no. ");
                                TextView textView2=new TextView(getContext());
                                TextView textView3=new TextView(getContext());
                                textView1.setTextColor(Color.parseColor("#FFFFFF"));
                                textView2.setTextColor(Color.parseColor("#FFFFFF"));
                                textView3.setTextColor(Color.parseColor("#FFFFFF"));
                                textView2.setText(" Product ");
                                textView3.setText(" Amount ");
                                textView2.setWidth(280);
                                tableRow.addView(textView1);
                                tableRow.addView(textView2);
                                tableRow.addView(textView3);
                                tableRow.setBackgroundColor(Color.parseColor("#4A587B"));
                                tableLayout.addView(tableRow);
                                linearLayout.addView(tableLayout);
                                for(int j=0;j<jsonArray2.length();j++){
                                    try {
                                        JSONObject jsonObject5=jsonArray2.getJSONObject(j);
                                        TableLayout tableLayout1=new TableLayout(getContext());
                                        TableRow tableRow1=new TableRow(getContext());
                                        TextView sr=new TextView(getContext());
                                        sr.setText(String.valueOf(sr0+1));
                                        sr0++;
                                        TextView type=new TextView(getContext());
                                        type.setText(jsonObject5.getString("straturgy"));
                                        TextView amt=new TextView(getContext());
                                        amt.setText(jsonObject5.getString("value"));
                                        amt.setTextColor(Color.parseColor("#FFFFFF"));
                                        sr.setTextColor(Color.parseColor("#FFFFFF"));
                                        type.setTextColor(Color.parseColor("#FFFFFF"));
                                        sr.setWidth(150);
                                        amt.setWidth(250);
                                        type.setWidth(280);
                                        tableRow1.addView(sr);
                                        tableRow1.addView(type);
                                        tableRow1.addView(amt);
                                        tableLayout1.addView(tableRow1);
                                        linearLayout.addView(tableLayout1);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }
                            builder.setView(dialogview);
                            AlertDialog alertDialog=builder.create();
                            alertDialog.show();
                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    System.out.println("ERRORRR");
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this.getContext());
        requestQueue.add(stringRequest);


        cancels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment ceffrag = new cef4();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.fragmentContainerView, ceffrag).commit();
            }
        });



        return view;
    }
}