package com.safeinvest.safetrax;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class cef4 extends Fragment {


       ImageButton invimg,insimg,stratimg;
       Button prev,firstbusiness,add,send,invview,insview,stratview;
       public static final String User_Credentials4 = "User_Credentials4";
       public static final String User_Credentials = "User_Credentials";
       public static final String User_Credentials2 = "User_Credentials2";
       public static final String User_Credentials31 = "User_Credentials31";
       public static final String User_Credentials3 = "User_Credentials3";
       EditText fourwheel,twowheel,advremark;
       Spinner restatus,homeowner,existinvest,existinsurance,investstrat;
       TextView insur,inves,strat,tnc,preview;
       boolean [] selectedfbusiness;
       String restatus1,url7,url6,url10,urlsend,url9,url8,homeowner1,existinvest1,url2,existinsurance1,url3,fbusiness1,url4,strat1,url5;
       int restatus01,homeowner01,existinvest01,existinsurance01,fbusiness01,strat01;
       String clientid;


    ArrayList<Invest> invests=new ArrayList<Invest>();
    ArrayList<Insurance> insurances=new ArrayList<Insurance>();
    ArrayList<String> fbusiness=new ArrayList<String>();
    ArrayList<Strategy> strats=new ArrayList<Strategy>();
    ArrayList<Sarray> sendarray1=new ArrayList<Sarray>();
    ArrayList<Sarray2> sendarray2=new ArrayList<Sarray2>();
    ArrayList<String> flist=new ArrayList<>();
    ArrayList<investlist> ilist=new ArrayList<investlist>();
    ArrayList<insurlist> ilist2=new ArrayList<insurlist>();
    ArrayList<stratlist> ilist3=new ArrayList<stratlist>();

    SharedPreferences settings4,settings,settings1,settings31,settings3;
    String [] array;
    String [] ilist1;
    String [] ilist01;


    public class Strategy{
        String label;int type;

        public Strategy(String label, int type) {
            this.label = label;
            this.type = type;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return label ;
        }
    }
    public class Insurance{
        String label;int type;

        public Insurance(String label, int type) {
            this.label = label;
            this.type = type;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return  label;
        }
    }
    public class Invest{
        String label;int type;

        public Invest(String label, int type) {
            this.label = label;
            this.type = type;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return  label ;
        }
    }
    public class Sarray{
        String clientid,address,city,pinCode,district,state,country,tellno;
        int addtype,iscorrespond;

        public Sarray(String clientid, String address, String city, String pinCode, String district, String state, String country, String tellno, int addtype, int iscorrespond) {
            this.clientid = clientid;
            this.address = address;
            this.city = city;
            this.pinCode = pinCode;
            this.district = district;
            this.state = state;
            this.country = country;
            this.tellno = tellno;
            this.addtype = addtype;
            this.iscorrespond = iscorrespond;
        }

        public String getClientid() {
            return clientid;
        }

        public void setClientid(String clientid) {
            this.clientid = clientid;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPinCode() {
            return pinCode;
        }

        public void setPinCode(String pinCode) {
            this.pinCode = pinCode;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getTellno() {
            return tellno;
        }

        public void setTellno(String tellno) {
            this.tellno = tellno;
        }

        public int getAddtype() {
            return addtype;
        }

        public void setAddtype(int addtype) {
            this.addtype = addtype;
        }

        public int getIscorrespond() {
            return iscorrespond;
        }

        public void setIscorrespond(int iscorrespond) {
            this.iscorrespond = iscorrespond;
        }

        @Override
        public String toString() {
            return "{" +
                    "clientid='" + clientid + '\'' +
                    ", address='" + address + '\'' +
                    ", city='" + city + '\'' +
                    ", pinCode='" + pinCode + '\'' +
                    ", district='" + district + '\'' +
                    ", state='" + state + '\'' +
                    ", country='" + country + '\'' +
                    ", tellno='" + tellno + '\'' +
                    ", addtype=" + addtype +
                    ", iscorrespond=" + iscorrespond +
                    '}';
        }
    }
    public class investlist{
        String insurance; int insint;

        public investlist(String insurance, int insint) {
            this.insurance = insurance;
            this.insint = insint;
        }

        public String getInsurance() {
            return insurance;
        }

        public void setInsurance(String insurance) {
            this.insurance = insurance;
        }

        public int getInsint() {
            return insint;
        }

        public void setInsint(int insint) {
            this.insint = insint;
        }

        @Override
        public String toString() {
            return
                    "investment='" + insurance + '\'' +
                    ",No.=" + insint ;
        }
    }
    public  class Sarray2{
        String clientid,address,city,pinCode,district,state,country,tellno;
        int addtype,iscorrespond;

        public Sarray2(String clientid, String address, String city, String pinCode, String district, String state, String country, String tellno, int addtype, int iscorrespond) {
            this.clientid = clientid;
            this.address = address;
            this.city = city;
            this.pinCode = pinCode;
            this.district = district;
            this.state = state;
            this.country = country;
            this.tellno = tellno;
            this.addtype = addtype;
            this.iscorrespond = iscorrespond;
        }

        public String getClientid() {
            return clientid;
        }

        public void setClientid(String clientid) {
            this.clientid = clientid;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPinCode() {
            return pinCode;
        }

        public void setPinCode(String pinCode) {
            this.pinCode = pinCode;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getTellno() {
            return tellno;
        }

        public void setTellno(String tellno) {
            this.tellno = tellno;
        }

        public int getAddtype() {
            return addtype;
        }

        public void setAddtype(int addtype) {
            this.addtype = addtype;
        }

        public int getIscorrespond() {
            return iscorrespond;
        }

        public void setIscorrespond(int iscorrespond) {
            this.iscorrespond = iscorrespond;
        }
    }
    public class insurlist{
        String insurance;int insint;

        public insurlist(String insurance, int insint) {
            this.insurance = insurance;
            this.insint = insint;
        }

        public String getInsurance() {
            return insurance;
        }

        public void setInsurance(String insurance) {
            this.insurance = insurance;
        }

        public int getInsint() {
            return insint;
        }

        public void setInsint(int insint) {
            this.insint = insint;
        }

        @Override
        public String toString() {
            return
                    "Insurance='" + insurance + '\'' +
                    ", No." + insint ;
        }
    }
    public class stratlist{
        String strategy;int strategyint;

        public stratlist(String strategy, int strategyint) {
            this.strategy = strategy;
            this.strategyint = strategyint;
        }

        public String getStrategy() {
            return strategy;
        }

        public void setStrategy(String strategy) {
            this.strategy = strategy;
        }

        public int getStrategyint() {
            return strategyint;
        }

        public void setStrategyint(int strategyint) {
            this.strategyint = strategyint;
        }

        @Override
        public String toString() {
            return "strategy='" + strategy + '\'' +
                    ", No." + strategyint ;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_cef4, container, false);
       restatus=(Spinner)view.findViewById(R.id.resstatus);
       prev=(Button)view.findViewById(R.id.cef4prev);
       invview=(Button)view.findViewById(R.id.investview);
       insview=(Button)view.findViewById(R.id.insureview);
       investstrat=(Spinner)view.findViewById(R.id.investstrat);
       firstbusiness=(Button)view.findViewById(R.id.firstbusiness);
       stratview=(Button)view.findViewById(R.id.investstratview);
       insur=(TextView)view.findViewById(R.id.einsure) ;
       inves=(TextView)view.findViewById(R.id.einv);
       invimg=(ImageButton)view.findViewById(R.id.investimgbtn);
       insimg=(ImageButton)view.findViewById(R.id.insureimagebtn);
       existinvest=(Spinner)view.findViewById(R.id.existinvest);
       existinsurance=(Spinner)view.findViewById(R.id.existinsurance);
       homeowner=(Spinner)view.findViewById(R.id.homeowner);
       strat=(TextView)view.findViewById(R.id.invstratext);
       stratimg=(ImageButton)view.findViewById(R.id.invstratimagebtn);
       advremark=(EditText)view.findViewById(R.id.advremark);
       fourwheel=(EditText)view.findViewById(R.id.fourwheel);
       twowheel=(EditText)view.findViewById(R.id.twowheel);
       add=(Button) view.findViewById(R.id.cef4add);
       send=(Button)view.findViewById(R.id.send);
       tnc=(TextView)view.findViewById(R.id.tnc);
       preview=(TextView)view.findViewById(R.id.preview);


        ArrayAdapter<?> array_Adapter5 =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.restatus,
                        R.layout.spinner01);
        array_Adapter5.setDropDownViewResource(R.layout.spinner01);
        restatus.setAdapter(array_Adapter5);
        restatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                restatus1=array_Adapter5.getItem(position).toString();
                restatus01= position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });




        ArrayAdapter<?> array_Adapter6 =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.homeowner,
                        R.layout.spinner01);
        array_Adapter6.setDropDownViewResource(R.layout.spinner01);
        homeowner.setAdapter(array_Adapter6);
        homeowner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                homeowner1=array_Adapter6.getItem(position).toString();
               homeowner01= position;
               System.out.println(homeowner01+"hme");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });




        url2="http://15.1.1.134:9000/cef/getAllProducts";
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String invlabel=jsonObject.getString("label");
                        String invtype=jsonObject.getString("type");
                        int invalue= Integer.parseInt(jsonObject.getString("value"));
                        if(invtype.equals("1")){
                            invests.add(new Invest(invlabel,invalue));
                        }
                        else {
                            insurances.add(new Insurance(invlabel,invalue));
                        }
                        ArrayAdapter<Invest> array_Adapter41= new ArrayAdapter<Invest>(getContext(), R.layout.spinner01,invests);
                        existinvest.setAdapter(array_Adapter41);
                        existinvest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                existinvest1 = array_Adapter41.getItem(position).toString();
                                Invest invest=(Invest) parent.getSelectedItem();
                                existinvest01= invest.getType();
                                System.out.println(existinvest01);
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                        ArrayAdapter<Insurance> array_Adapter42= new ArrayAdapter<Insurance>(getContext(), R.layout.spinner01,insurances);
                        existinsurance.setAdapter(array_Adapter42);
                        existinsurance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                existinsurance1 = array_Adapter42.getItem(position).toString();
                                Insurance insurance=(Insurance)parent.getSelectedItem();
                                existinsurance01= insurance.getType();
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("BIG ERROR");
            }
        });
        RequestQueue requestQueue1= Volley.newRequestQueue(this.getContext());
        requestQueue1.add(stringRequest1);




        url3="http://15.1.1.134:9000/cef/getAllFirstBussiness";
        StringRequest stringRequest2=new StringRequest(Request.Method.GET, url3, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        fbusiness.add(jsonObject.getString("label"));
                        firstbusiness.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                               AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                               builder.setTitle("Select FirstBusiness");
                               builder.setCancelable(false);
                               int size=fbusiness.size();
                               array=fbusiness.toArray(new String[size]);
                                selectedfbusiness=new boolean[array.length];
                               builder.setMultiChoiceItems(array, selectedfbusiness, new DialogInterface.OnMultiChoiceClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                          if(isChecked){
                                              flist.add(String.valueOf(which+1));
                                          }
                                          else{
                                              flist.remove(which);
                                          }

                                   }
                               }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialog, int which) {
                                       System.out.println("FLIST"+ flist);
                                       RequestQueue MyRequestQueue = Volley.newRequestQueue(getContext());
                                       url5 = "http://15.1.1.134:9000/cef/investmentStrategies/android";
                                       StringRequest stringRequest5 = new StringRequest(Request.Method.POST, url5, new Response.Listener<String>() {
                                           @Override
                                           public void onResponse(String response) {
                                               try {
                                                   JSONArray jsonArray=new JSONArray(response);
                                                   for(int i=0;i<jsonArray.length();i++){
                                                       JSONObject jsonObject=jsonArray.getJSONObject(i);
                                                       String s=jsonObject.getString("label");
                                                       int s1= Integer.parseInt(jsonObject.getString("value"));
                                                       strats.add(new Strategy(s,s1));
                                                       ArrayAdapter<Strategy> array_Adapter4= new ArrayAdapter<Strategy>(getContext(), R.layout.spinner01,strats);
                                                       investstrat.setAdapter(array_Adapter4);
                                                       investstrat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                           @Override
                                                           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                              strat1=array_Adapter4.getItem(position).toString();
                                                               Strategy strategy=(Strategy)parent.getSelectedItem();
                                                               strat01=strategy.getType();
                                                           }

                                                           @Override
                                                           public void onNothingSelected(AdapterView<?> parent) {

                                                           }
                                                       });
                                                       System.out.println(s);
                                                   }
                                               } catch (JSONException e) {
                                                   e.printStackTrace();
                                               }
                                           }
                                       }, new Response.ErrorListener() {
                                           @Override
                                           public void onErrorResponse(VolleyError error) {
                                                   System.out.println("Error is here..");
                                           }
                                       }) {

                                           @Nullable
                                           @Override

                                           protected Map<String, String> getParams() {
                                               Map<String, String> MyData = new HashMap<String, String>();

                                               StringBuilder str=new StringBuilder("");

                                               for(String sk1:flist){
                                                      str.append(sk1).append(",");
                                               }
                                               String commaseparatedlist = str.toString();
                                               if (commaseparatedlist.length() > 0)

                                                   commaseparatedlist
                                                           = commaseparatedlist.substring(
                                                           0, commaseparatedlist.length() - 1);
                                               MyData.put("key1",commaseparatedlist);
                                               flist.clear();
                                               System.out.println(flist);
                                               System.out.println(MyData+"Shrt");
                                               return MyData;
                                           }
                                           @Override
                                           public Map<String, String> getHeaders() throws AuthFailureError {
                                               Map<String, String> params = new HashMap<String, String>();
//                                               params.put("Content-Type", "application/json");
                                               return params;
                                           }

                                       };
                                       MyRequestQueue.add(stringRequest5);
                                   }

                               }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialog, int which) {
                                               dialog.dismiss();
                                   }
                               }).setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialog, int which) {
                                         for(int i=0;i<selectedfbusiness.length;i++){
                                             selectedfbusiness[i]=false;
                                             flist.clear();
                                         }
                                   }
                               });
                               builder.show();

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
        RequestQueue requestQueue3= Volley.newRequestQueue(this.getContext());
        requestQueue3.add(stringRequest2);



        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment ceffrag = new cef3();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.fragmentContainerView, ceffrag).commit();
            }
        });



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urlsend="http://15.1.1.134:9000/cef/clientMaster";
                settings=getContext().getSharedPreferences(User_Credentials,Context.MODE_PRIVATE);
                settings1=getContext().getSharedPreferences(User_Credentials2,Context.MODE_PRIVATE);
                settings3=getContext().getSharedPreferences(User_Credentials3,Context.MODE_PRIVATE);
                settings31=getContext().getSharedPreferences(User_Credentials31,Context.MODE_PRIVATE);
                settings4=getContext().getSharedPreferences(User_Credentials4,Context.MODE_PRIVATE);
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("clientType",settings.getInt("clienttype",0));
                    jsonObject.put("prefix",settings.getInt("prefix",0));
                    jsonObject.put("firstName",settings.getString("fname01",""));
                    jsonObject.put("middleName",settings.getString("mname01",""));
                    jsonObject.put("lastName",settings.getString("lname01",""));
                    jsonObject.put("nationalityID",settings.getInt("nations",1));
                    jsonObject.put("pan",settings.getString("pan01",""));
                    jsonObject.put("kycStatus",settings.getInt("kyc",0));
                    jsonObject.put("groupName",settings.getString("groupname01",""));
                    jsonObject.put("split",settings.getInt("split",0));
                    jsonObject.put("dob",settings.getString("dob01",""));
                    jsonObject.put("maritalStatus",settings.getInt("maritial",0));
                    jsonObject.put("familySize",settings.getInt("fam",0));
                    jsonObject.put("dependentNo",settings.getInt("nod",0));
                    jsonObject.put("occupation",settings1.getString("occupation",""));
                    jsonObject.put("proId",settings1.getInt("occupation01",0));
                    jsonObject.put("proSubId",settings1.getInt("sector01",0));
                    jsonObject.put("qualification",settings1.getString("qualification01",""));
                    jsonObject.put("firmName",settings1.getString("firmname01",""));
                    jsonObject.put("designation",settings1.getString("designation",""));
                    jsonObject.put("mobileNo",settings1.getString("mobile01",""));
                    jsonObject.put("emailid",settings1.getString("email01",""));
                    jsonObject.put("annualIncome",settings1.getInt("aincome",0));
                    jsonObject.put("familyIncome",settings1.getInt("fincome",0));
                    jsonObject.put("referenceBy",settings1.getString("refname",""));
                    jsonObject.put("referenceTypeID",settings1.getInt("reby",0));
                    System.out.println(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("object error");
                }

                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, urlsend, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            clientid=response.getString("generatedClientId");
                            System.out.println(clientid);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Error Shruti" +error);
                    }
                }){

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Content-Type", "application/json");
                        return params;
                    }

                };
                RequestQueue requestQueue7= Volley.newRequestQueue(getContext());
                requestQueue7.add(jsonObjectRequest);
            }
        });




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  settings=getContext().getSharedPreferences(User_Credentials,Context.MODE_PRIVATE);
               // settings.edit().clear().commit();
               // settings1=getContext().getSharedPreferences(User_Credentials2,Context.MODE_PRIVATE);
               // settings1.edit().clear().commit();
               // settings4=getContext().getSharedPreferences(User_Credentials4,Context.MODE_PRIVATE);
               // settings4.edit().clear().commit();
               // settings31=getContext().getSharedPreferences(User_Credentials31,Context.MODE_PRIVATE);
               // settings31.edit().clear().commit();
               // settings3=getContext().getSharedPreferences(User_Credentials3,Context.MODE_PRIVATE);
               // settings3.edit().clear().commit();
               url6="http://15.1.1.134:9000/cef/addressDetails";
                settings=getContext().getSharedPreferences(User_Credentials,Context.MODE_PRIVATE);
                settings1=getContext().getSharedPreferences(User_Credentials2,Context.MODE_PRIVATE);
                settings3=getContext().getSharedPreferences(User_Credentials3,Context.MODE_PRIVATE);
                settings31=getContext().getSharedPreferences(User_Credentials31,Context.MODE_PRIVATE);
                settings4=getContext().getSharedPreferences(User_Credentials4,Context.MODE_PRIVATE);

               Sarray sarray=new Sarray(clientid,settings3.getString("address01",""),settings3.getString("city",""),
                       settings3.getString("pin",""),settings3.getString("district",""),
                       settings3.getString("state",""),settings3.getString("country",""),
                       settings3.getString("telno",""),settings3.getInt("atype",1),1);
               sendarray1.add(sarray);

               Sarray2 sarray2 = new Sarray2(clientid,settings31.getString("address",""),settings31.getString("city",""),
                       settings31.getString("pin",""),settings31.getString("district",""),
                       settings31.getString("state",""),settings31.getString("country",""),
                       settings31.getString("tel",""),settings31.getInt("atype",1),0);
                //JSONArray jsonArray=new JSONArray(Arrays.toString(sendarray1));
                sendarray2.add(sarray2);
                JSONArray jsonArray = new JSONArray();
                try {

                    for (Sarray sa : sendarray1) {
                        JSONObject obj = new JSONObject();
                        obj.put("clientid",sa.getClientid());
                        obj.put("address",sa.getAddress());
                        obj.put("city",sa.getCity());
                        obj.put("pin", sa.getPinCode());
                        obj.put("district",sa.getDistrict());
                        obj.put("state", sa.getState());
                        obj.put("country",sa.getCountry());
                        obj.put("telNo",sa.getTellno());
                        obj.put("addressType",sa.getAddtype());
                        obj.put("isCorrospond",0);
                        jsonArray.put(obj);
                    }
                }catch (Exception e){
                    System.out.println("error :: " + e.toString());
                }
                try {

                    for (Sarray2 sa : sendarray2) {
                        JSONObject obj = new JSONObject();
                        obj.put("clientid",sa.getClientid());
                        obj.put("address",sa.getAddress());
                        obj.put("city",sa.getCity());
                        obj.put("pin", sa.getPinCode());
                        obj.put("district",sa.getDistrict());
                        obj.put("state", sa.getState());
                        obj.put("country",sa.getCountry());
                        obj.put("telNo",sa.getTellno());
                        obj.put("addressType",sa.getAddtype());
                        obj.put("isCorrospond",1);
                        jsonArray.put(obj);
                    }
                }catch (Exception e){
                    System.out.println("error :: " + e.toString());
                }
                System.out.println(sendarray1);
                System.out.println(sendarray2);
                System.out.println("json array is" + jsonArray);

                JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.POST, url6, jsonArray, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                               System.out.println("Done 232");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                             System.out.println("Attention here error"+error);
                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Content-Type", "application/json");
                        return params;
                    }
                };
                RequestQueue requestQueue8= Volley.newRequestQueue(getContext());
                requestQueue8.add(jsonArrayRequest);
                 url10="http://11.1.1.86:9000/cef/clientValuableData";
                JSONObject jsonObject=new JSONObject();
                try{
                    jsonObject.put("clientid",Integer.parseInt(clientid));
                    jsonObject.put("residantStatus",restatus01);
                    jsonObject.put("homeOwnership",homeowner01+1);
                    jsonObject.put("fourWheller",Integer.parseInt(fourwheel.getText().toString()));
                    jsonObject.put("twoWheller",Integer.parseInt(twowheel.getText().toString()));
                    jsonObject.put("advRemark",advremark.getText().toString());
                }catch (Exception e){

                }
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url10, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Content-Type", "application/json");
                        return params;
                    }
                };
                RequestQueue requestQueue10= Volley.newRequestQueue(getContext());
                requestQueue10.add(jsonObjectRequest);

            }
        });



        invimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inv=existinvest1;
                int in=Integer.parseInt(inves.getText().toString());
                ilist.add(new investlist(inv,in));
                System.out.println(ilist);
                url7="http://15.1.1.134:9000/cef/clientExistingData";
                settings4=getContext().getSharedPreferences(User_Credentials4,Context.MODE_PRIVATE);
                JSONObject jsonObject =new JSONObject();
                try {
                    jsonObject.put("clientid", Integer.parseInt(clientid));
                    jsonObject.put("productid", existinvest01);
                    jsonObject.put("value", Integer.parseInt(inves.getText().toString()));
                    System.out.println(jsonObject);
                }catch (Exception e){
                    System.out.println("error::");
                }
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url7, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
//                                               params.put("Content-Type", "application/json");
                        return params;
                    }
                };
                RequestQueue requestQueue09= Volley.newRequestQueue(getContext());
                requestQueue09.add(jsonObjectRequest);

            }
        });
        invview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
                builder.setTitle("Existing Investment");
                builder.setCancelable(false);
                ArrayAdapter<investlist> arrayAdapter=new ArrayAdapter<investlist>(getContext(),R.layout.spinner01,ilist);
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.setItems(ilist1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();

            }
        });



        insimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ins=existinsurance1;
                int in=Integer.parseInt(insur.getText().toString());
                ilist2.add(new insurlist(ins,in));
                System.out.println(ilist2);
                url8="http://15.1.1.134:9000/cef/clientExistingData";
                JSONObject jsonObject=new JSONObject();
                try{
                    jsonObject.put("clientid",Integer.parseInt(clientid));
                    jsonObject.put("productid",existinsurance01);
                    jsonObject.put("value",Integer.parseInt(insur.getText().toString()));
                }catch (Exception e){
                    System.out.println("error::");
                }
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url8, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Error");
                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
//                                               params.put("Content-Type", "application/json");
                        return params;
                    }
                };
                RequestQueue requestQueue01= Volley.newRequestQueue(getContext());
                requestQueue01.add(jsonObjectRequest);

            }
        });
        insview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setTitle("Existing Insurance");
                builder.setCancelable(false);
                ArrayAdapter<insurlist> arrayAdapter=new ArrayAdapter<insurlist>(getContext(),R.layout.spinner01,ilist2);
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
                builder.show();
            }
        });


        stratimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String st=strat1;
                int in= Integer.parseInt(strat.getText().toString());
                ilist3.add(new stratlist(st,in));
              url9 = "http://15.1.1.134:9000/cef/clientInvestmentStrategyData";
              JSONObject jsonObject= new JSONObject();
              try{
                  jsonObject.put("bizId",strat01);
                  jsonObject.put("value",Integer.parseInt(strat.getText().toString()));
                  jsonObject.put("clientid",Integer.parseInt(clientid));
                  System.out.println(jsonObject);
              }catch (Exception e){

              }
              JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url9, jsonObject, new Response.Listener<JSONObject>() {
                  @Override
                  public void onResponse(JSONObject response) {

                  }
              }, new Response.ErrorListener() {
                  @Override
                  public void onErrorResponse(VolleyError error) {

                  }
              }){
                  @Override
                  public Map<String, String> getHeaders() throws AuthFailureError {
                      Map<String, String> params = new HashMap<String, String>();
//                                               params.put("Content-Type", "application/json");
                      return params;
                  }
              };
                RequestQueue requestQueue03= Volley.newRequestQueue(getContext());
                requestQueue03.add(jsonObjectRequest);
            }
        });
        stratview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setTitle("Investment Strategy");
                builder.setCancelable(false);
                ArrayAdapter<stratlist> arrayAdapter=new ArrayAdapter<stratlist>(getContext(),R.layout.spinner01,ilist3);
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
                builder.show();
            }
        });



        tnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
                ViewGroup viewGroup= view.findViewById(android.R.id.content);
                View dialogview=LayoutInflater.from(v.getContext()).inflate(R.layout.fragment_termsncondi,viewGroup,false);

                RadioButton nri=(RadioButton)dialogview.findViewById(R.id.nri);
                RadioButton domestic=(RadioButton)dialogview.findViewById(R.id.domestic);
                TableLayout ntable=(TableLayout)dialogview.findViewById(R.id.nritable);
                TableLayout dtable=(TableLayout)dialogview.findViewById(R.id.domestictable);
                Button tcancel=(Button)dialogview.findViewById(R.id.tnccancel);


                nri.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ntable.setVisibility(View.VISIBLE);
                        dtable.setVisibility(View.GONE);
                    }
                });
                domestic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dtable.setVisibility(View.VISIBLE);
                        ntable.setVisibility(View.GONE);
                    }
                });
                builder.setView(dialogview);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
                tcancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();

                        url4="http://15.1.1.134:9000/cef/criteria/"+clientid+ "/K0542";
                        StringRequest stringRequest=new StringRequest(Request.Method.GET, url4, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                    System.out.println("RESPONSE GOT");
                                try {
                                    JSONArray jsonArray=new JSONArray(response);
                                    System.out.println(jsonArray);
                                    for(int i=0;i<jsonArray.length();i++){
                                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                                        String ids=jsonObject.getString("id");
                                        System.out.println(ids);

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                System.out.println("error ::");
                            }
                        });
                        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
                        requestQueue.add(stringRequest);
                        List<Integer> list=new ArrayList<>();
                        CheckBox nsip=(CheckBox)dialogview.findViewById(R.id.nsip);
                        CheckBox ninves=(CheckBox)dialogview.findViewById(R.id.ninves);
                        CheckBox nlum=(CheckBox)dialogview.findViewById(R.id.nlum);
                        CheckBox dsip=(CheckBox)dialogview.findViewById(R.id.dsip);
                        CheckBox dinves=(CheckBox)dialogview.findViewById(R.id.dinsur);
                        CheckBox dlum=(CheckBox)dialogview.findViewById(R.id.dlum);
                        if(nsip.isChecked()==true){
                            list.add(43);
                        }
                        if(ninves.isChecked()==true){
                            list.add(45);
                        }
                        if(nlum.isChecked()==true){
                            list.add(44);
                        }
                        if(dlum.isChecked()==true){
                            list.add(47);
                        }
                        if(dsip.isChecked()==true){
                            list.add(46);
                        }
                        if(dinves.isChecked()==true){
                            list.add(48);
                        }

                        JSONObject jsonObject= new JSONObject();
                        JSONArray jsonArray=new JSONArray(list);
                        try{
                            jsonObject.put("clientid",clientid);
                            jsonObject.put("criteriaIdList",jsonArray);
                            jsonObject.put("postuser","K0542");
                            System.out.println(jsonObject);
                            System.out.println(list +"listof");
                        }catch (Exception e){
                        }
                        String url11="http://15.1.1.134:9000/cef/clientCriteriaData";
                        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,url11, jsonObject, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });

                        RequestQueue requestQueue1= Volley.newRequestQueue(getContext());
                        requestQueue1.add(jsonObjectRequest);
                      list.clear();
                    }

                });
            }
        });



        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment ceffrag1 = new PreviewPage();
                Bundle bundle=new Bundle();
                bundle.putString("clientid",clientid);
                FragmentTransaction fm1 = getActivity().getSupportFragmentManager().beginTransaction();
                ceffrag1.setArguments(bundle);
                fm1.replace(R.id.fragmentContainerView, ceffrag1).commit();
            }
        });




        settings4=getActivity().getSharedPreferences(User_Credentials4,Context.MODE_PRIVATE);
        String four1=settings4.getString("four","");
        String two1=settings4.getString("two","");
        String aremark1=settings4.getString("advremark","");
        String invest1=settings4.getString("einvest","0");
        String insur1=settings4.getString("einsur","0");
        String strat1=settings4.getString("istrat","0");
        int home1=settings4.getInt("home",0);
        int restatus1=settings4.getInt("restatus",0);
        fourwheel.setText(four1);
        twowheel.setText(two1);
        advremark.setText(aremark1);
        inves.setText(invest1);
        insur.setText(insur1);
        strat.setText(strat1);
        homeowner.setSelection(home1);
        restatus.setSelection(restatus1);


        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        settings4=getActivity().getSharedPreferences(User_Credentials4, Context.MODE_PRIVATE);
        settings4.edit().putString("four",fourwheel.getText().toString()).
                putString("two",twowheel.getText().toString()).
                putString("advremark",advremark.getText().toString()).
                putString("einvest",inves.getText().toString()).
                putString("einsur",insur.getText().toString()).
                putInt("invest",existinvest01).putInt("insurance",existinsurance01).
                putInt("restatus",restatus01).
                putInt("home",homeowner01).
                putString("istrat",strat.getText().toString()).commit();
    }
}