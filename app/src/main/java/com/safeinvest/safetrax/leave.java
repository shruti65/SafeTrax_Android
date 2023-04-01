package com.safeinvest.safetrax;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.text.format.DateUtils;
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
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;


public class leave extends Fragment {
    RadioButton pl,sl,lwp;
    RadioGroup leavetype;
    TextView change,seektext;
    ImageView date1;
    EditText date,subreason;
    String bday,prog;
    Spinner reason;
    Button choose,halfday,submit;
    SeekBar seekBar;
    Calendar c;
    int len,id;
    public class half{
        String date00;
        String radio;

        public half(String date00, String radio) {
            this.date00 = date00;
            this.radio = radio;
        }

        public String getDate00() {
            return date00;
        }

        public void setDate00(String date00) {
            this.date00 = date00;
        }

        public String getRadio() {
            return radio;
        }

        public void setRadio(String radio) {
            this.radio = radio;
        }


        @Override
        public String toString() {
            return
                   "{"+ date00  + " " + radio +"}";
        }
    }
    ArrayList<half> arrayList=new ArrayList<half>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_leave, container, false);
        leavetype=(RadioGroup)view.findViewById(R.id.leavetype);
        pl=(RadioButton) view.findViewById(R.id.PL);
        sl=(RadioButton) view.findViewById(R.id.SL);
        lwp=(RadioButton) view.findViewById(R.id.LWP);
        change=(TextView)view.findViewById(R.id.changetext);
        date1=(ImageView)view.findViewById(R.id.datepicker1);
        date=(EditText)view.findViewById(R.id.date);
        choose=(Button)view.findViewById(R.id.document);
        reason=(Spinner)view.findViewById(R.id.reason);
        seekBar=(SeekBar)view.findViewById(R.id.seekbar);
        seektext=(TextView)view.findViewById(R.id.seekbartext);
        halfday=(Button)view.findViewById(R.id.halfday);
        submit=(Button)view.findViewById(R.id.leavesubmit);
        subreason=(EditText)view.findViewById(R.id.subreason);


        pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change.setText("Request for Paid Leave");
            }
        });


        sl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change.setText("Request for Sick Leave");
            }
        });



        lwp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change.setText("Request for Leave Without Pay");
            }
        });



        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
            }
        });



        ArrayAdapter<?> array_Adapter3 =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.leavereason,
                        R.layout.spinner01);
        array_Adapter3.setDropDownViewResource(R.layout.spinner01);
        reason.setAdapter(array_Adapter3);
        reason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                prog=String.valueOf(progress);
                seektext.setText(prog);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar.setMax(45);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            seekBar.setMin(1);
        }




        date1.setOnClickListener(new View.OnClickListener() {
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



        /*halfday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                ViewGroup viewGroup= view.findViewById(android.R.id.content);
                View dialogview=LayoutInflater.from(v.getContext()).inflate(R.layout.halfdayalert,viewGroup,false);

                builder.setView(dialogview);
                AlertDialog alertDialog=builder.create();
                for(int i=1;i<=Integer.parseInt(seektext.getText().toString());i++){
                    System.out.println(i);
                   // LinearLayout l1=(LinearLayout)view.findViewById(R.id.horilayout);

                }
                alertDialog.show();


            }
        });*/
       halfday.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               try{
                   int r1=1,r2=2;

                   AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
                   ViewGroup viewGroup= view.findViewById(android.R.id.content);
                   View dialogview=LayoutInflater.from(v.getContext()).inflate(R.layout.fragment_previewalert,viewGroup,false);
                   TextView textView=(TextView)dialogview.findViewById(R.id.prevchangetext);
                   LinearLayout linearLayout=(LinearLayout)dialogview.findViewById(R.id.linear1);
                   textView.setText("Leave Days ");
                   builder.setView(dialogview);
                   AlertDialog alertDialog=builder.create();
                   alertDialog.show();
                   TableLayout tableLayout=new TableLayout(getContext());


                   for(int i=0;i<1;i++){
                       TableRow tableRow=new TableRow(getContext());
                       TextView textView1=new TextView(getContext());
                       textView1.setText(" Date ");
                       textView1.setWidth(250);
                       TextView textView2=new TextView(getContext());
                       textView1.setTextColor(Color.parseColor("#FFFFFF"));
                       textView2.setTextColor(Color.parseColor("#FFFFFF"));
                       textView2.setText(" Half Day ");
                       tableRow.addView(textView1);
                       tableRow.addView(textView2);
                       tableRow.setBackgroundColor(Color.parseColor("#4A587B"));
                       tableLayout.addView(tableRow);
                       linearLayout.addView(tableLayout);
                   }


                   String url="http://15.1.1.134:9000/hrms/getWorkingDay/"+bday+"/"+prog;
                   StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                       @Override
                       public void onResponse(String response) {
                           try {
                               JSONArray jsonArray=new JSONArray(response);
                               for(int j=0;j<jsonArray.length();j++){
                                   len=jsonArray.length();
                                   JSONObject jsonObject=jsonArray.getJSONObject(j);
                                   System.out.println(jsonObject.getString("wDate"));
                                   TableLayout tableLayout1=new TableLayout(getContext());
                                   TableRow tableRow1=new TableRow(getContext());
                                   TextView date01=new TextView(getContext());
                                   date01.setText(jsonObject.getString("wDate"));
                                   date01.setTextColor(Color.parseColor("#FFFFFF"));
                                   date01.setWidth(250);
                                   RadioGroup radioGroup=new RadioGroup(getContext());
                                   radioGroup.setOrientation(RadioGroup.HORIZONTAL);
                                   RadioButton radioButton=new RadioButton(getContext());
                                   RadioButton radioButton1=new RadioButton(getContext());
                                   radioButton.setText("1st");
                                   radioButton1.setText("2nd");
                                   radioButton.setId(r1);
                                   radioButton1.setId(r2);
                                   radioGroup.addView(radioButton);
                                   radioGroup.addView(radioButton1);
                                   tableRow1.addView(date01);
                                   tableRow1.addView(radioGroup);
                                   tableLayout1.addView(tableRow1);
                                   linearLayout.addView(tableLayout1);
                                   radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                       @Override
                                       public void onCheckedChanged(RadioGroup group, int checkedId) {
                                           int i0=radioGroup.getCheckedRadioButtonId();
                                           Boolean exiting = Boolean.FALSE;
                                           for(half halfObj : arrayList){
                                               if(halfObj.getDate00().equals(date01.getText().toString())){
                                                   exiting = Boolean.TRUE;
                                                   halfObj.setRadio(String.valueOf(i0));
                                               }
                                           }

                                           if(!exiting){
                                               arrayList.add(new half(date01.getText().toString(),String.valueOf(i0)));
                                           }

                                       }
                                   });

                                      Button button=new Button(getContext());
                                       button.setText("Add");
                                       button.setBackground(getContext().getDrawable(R.drawable.button2));
                                       linearLayout.addView(button);
                                       button.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               System.out.println(arrayList);

                                               alertDialog.dismiss();
                                               arrayList.clear();
                                           }
                                       });
                                       button.setVisibility(View.GONE);
                                       if(j==jsonArray.length()-1){
                                           button.setVisibility(View.VISIBLE);
                                       }


                               }
                           } catch (JSONException e) {
                               e.printStackTrace();
                           }

                       }
                   }, new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {

                       }
                   });
                   RequestQueue requestQueue= Volley.newRequestQueue(getContext());
                   requestQueue.add(stringRequest);




               }catch (Exception e){
                   Toast.makeText(getContext(), "Select Date", Toast.LENGTH_SHORT).show();
               }

           }
       });



       submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(TextUtils.isEmpty(change.getText().toString())){
                   change.requestFocus();
                   change.setError("Select Leave Type");
               }
               else if(TextUtils.isEmpty(date.getText().toString())){
                   date.requestFocus();
                   date.setError("Select Date");
               }
               else if(TextUtils.isEmpty(subreason.getText().toString())){
                   subreason.setError("Enter Subreason");
                   subreason.requestFocus();
               }
               else{
                   JSONObject jsonObject1=new JSONObject();
                   try{
                       String cal=String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
                       int clas=Integer.parseInt(cal)+1;
                       String type=null;
                       if(pl.isChecked()){type="PL";}
                       if(sl.isChecked()){type="SL";}
                       if(lwp.isChecked()){type="LWP";}
                       jsonObject1.put("userid","P1317");
                       jsonObject1.put("fy",Calendar.getInstance().get(Calendar.YEAR)+"-"+clas);
                       jsonObject1.put("reqtype",type);
                       jsonObject1.put("fromdate",date.getText().toString());
                       jsonObject1.put("numberofdays",Integer.parseInt(seektext.getText().toString()));
                       jsonObject1.put("standerdreason",reason.getSelectedItem().toString());
                       jsonObject1.put("otherreason",subreason.getText().toString());
                       System.out.println(jsonObject1);
                   }
                   catch (Exception e){
                       System.out.println(e);
                   }

                   String url1="http://15.1.1.134:9000/hrms/addLeaveRequestAndroid";
                   JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url1, jsonObject1, new Response.Listener<JSONObject>() {
                       @Override
                       public void onResponse(JSONObject response) {
                           try {
                               id=response.getInt("leaveRequestID");
                               JSONArray jsonArray1=new JSONArray();
                               for(half halfs:arrayList){
                                   try {
                                       JSONObject jsonObject1=new JSONObject();
                                       System.out.println(halfs.getDate00());
                                       jsonObject1.put("wDate",halfs.getDate00());
                                       jsonObject1.put("half",halfs.getRadio());
                                       jsonArray1.put(jsonObject1);
                                       System.out.println(jsonObject1);
                                   } catch (JSONException e) {
                                       e.printStackTrace();
                                   }
                               }
                               String url01="http://15.1.1.134:9000/hrms/addHalfDay/"+id;

                               JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.POST, url01, jsonArray1, new Response.Listener<JSONArray>() {
                                   @Override
                                   public void onResponse(JSONArray response) {
                                       System.out.println(response);
                                   }
                               }, new Response.ErrorListener() {
                                   @Override
                                   public void onErrorResponse(VolleyError error) {
                                       System.out.println(error);
                                       arrayList.clear();
                                   }
                               });
                               RequestQueue requestQueue1=Volley.newRequestQueue(getContext());
                               requestQueue1.add(jsonArrayRequest);
                               System.out.println("THISss" +jsonArray1);
                               System.out.println(id);
                           } catch (JSONException e) {
                               e.printStackTrace();
                           }
                       }
                   }, new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {
                                System.out.println("NO RES" +error);
                       }
                   });
                   RequestQueue requestQueue=Volley.newRequestQueue(getContext());
                   requestQueue.add(jsonObjectRequest);
               }

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
            date.setText(bday);
        }
    };


}