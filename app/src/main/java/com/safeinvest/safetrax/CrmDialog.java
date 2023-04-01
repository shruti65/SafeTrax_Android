package com.safeinvest.safetrax;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialogFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class CrmDialog extends AppCompatDialogFragment {
    private Button add;
    private ImageView datepicker;
    private Calendar c;
    private EditText date,evalue;
    private String bday,productstr,purposestr,statusstr,sremarkstr;
    private Spinner product,purpose,status,sremark;
    private TextView viewlist;
    public JSONArray jsonArray=new JSONArray();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.crmentryaddlayout,null);
        builder.setView(view);
        add=(Button) view.findViewById(R.id.add);
        datepicker=(ImageView)view.findViewById(R.id.datepicker1);
        date=(EditText)view.findViewById(R.id.date);
        viewlist=(TextView)view.findViewById(R.id.viewlist);
        product=(Spinner)view.findViewById(R.id.product);
        purpose=(Spinner)view.findViewById(R.id.purpose);
        status=(Spinner)view.findViewById(R.id.status);
        sremark=(Spinner)view.findViewById(R.id.sremark);
        evalue=(EditText)view.findViewById(R.id.evalue);


        ArrayAdapter<?> array_Adapter =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.addtype,
                        R.layout.spinner01);
        array_Adapter.setDropDownViewResource(R.layout.spinner01);
        product.setAdapter(array_Adapter);
        product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                productstr=array_Adapter.getItem(position).toString();
                int addtype01= position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        ArrayAdapter<?> array_Adapter2 =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.addtype,
                        R.layout.spinner01);
        array_Adapter2.setDropDownViewResource(R.layout.spinner01);
        purpose.setAdapter(array_Adapter2);
        purpose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                purposestr=array_Adapter2.getItem(position).toString();
               int  pur= position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        ArrayAdapter<?> array_Adapter3 =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.addtype,
                        R.layout.spinner01);
        array_Adapter3.setDropDownViewResource(R.layout.spinner01);
        status.setAdapter(array_Adapter3);
        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                statusstr=array_Adapter3.getItem(position).toString();
                int addtype01= position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        ArrayAdapter<?> array_Adapter4 =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.addtype,
                        R.layout.spinner01);
        array_Adapter4.setDropDownViewResource(R.layout.spinner01);
        sremark.setAdapter(array_Adapter4);
        sremark.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sremarkstr=array_Adapter4.getItem(position).toString();
                int addtype01= position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p1=productstr;
                String p2=purposestr;
                String status01=statusstr;
                String sremark01=sremarkstr;
                String ndat01=date.getText().toString();
                String evalue01=evalue.getText().toString();

                if(TextUtils.isEmpty(ndat01)){
                    date.setError("Select Date");
                    date.requestFocus();
                }
                else if(TextUtils.isEmpty(evalue01)){
                    evalue.setError("Enter Value");
                    evalue.requestFocus();
                }
                else{
                    JSONObject jsonObject=new JSONObject();
                    try {
                        jsonObject.put("product",p1);
                        jsonObject.put("purpose",p2);
                        jsonObject.put("status",status01);
                        jsonObject.put("sremark",sremark01);
                        jsonObject.put("ndate",ndat01);
                        jsonObject.put("evalue",evalue01);
                        jsonArray.put(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    System.out.println(jsonArray);
                }


            }
        });


        datepicker.setOnClickListener(new View.OnClickListener() {
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


        viewlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
                ViewGroup viewGroup= view.findViewById(android.R.id.content);
                View dialogview=LayoutInflater.from(v.getContext()).inflate(R.layout.crmblank,viewGroup,false);
                LinearLayout linearLayout=(LinearLayout) dialogview.findViewById(R.id.linear);

                    try {
                        for(int i=0;i<jsonArray.length();i++){
                            TableLayout tableLayout=new TableLayout(getContext());
                            TableRow tableRow=new TableRow(getContext());
                            TextView prod=new TextView(getContext());
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            prod.setText("Product :");
                            prod.setWidth(300);
                            TextView prod01=new TextView(getContext());
                            prod01.setText(jsonObject.getString("product"));
                            tableRow.addView(prod);
                            tableRow.addView(prod01);
                            tableLayout.addView(tableRow);

                            TableRow tableRow2=new TableRow(getContext());
                            TextView purp=new TextView(getContext());
                            purp.setText("Purpose :");
                            purp.setWidth(300);
                            TextView purp01=new TextView(getContext());
                            purp01.setText(jsonObject.getString("purpose"));
                            tableRow2.addView(purp);
                            tableRow2.addView(purp01);
                            tableLayout.addView(tableRow2);

                            TableRow tableRow3=new TableRow(getContext());
                            TextView stat=new TextView(getContext());
                            stat.setText("Status :");
                            stat.setWidth(300);
                            TextView stat01=new TextView(getContext());
                            stat01.setText(jsonObject.getString("status"));
                            tableRow3.addView(stat);
                            tableRow3.addView(stat01);
                            tableLayout.addView(tableRow3);

                            TableRow tableRow4=new TableRow(getContext());
                            TextView srem=new TextView(getContext());
                            srem.setText("Standard Remark :");
                            srem.setWidth(300);
                            TextView srem01=new TextView(getContext());
                            srem01.setText(jsonObject.getString("sremark"));
                            tableRow4.addView(srem);
                            tableRow4.addView(srem01);
                            tableLayout.addView(tableRow4);

                            TableRow tableRow5=new TableRow(getContext());
                            TextView ndate=new TextView(getContext());
                            ndate.setText("Next Meeting Date :");
                            ndate.setWidth(300);
                            TextView ndate01=new TextView(getContext());
                            ndate01.setText(jsonObject.getString("ndate"));
                            tableRow5.addView(ndate);
                            tableRow5.addView(ndate01);
                            tableLayout.addView(tableRow5);

                            TableRow tableRow6=new TableRow(getContext());
                            TextView eval=new TextView(getContext());
                            eval.setText("Executed Value");
                            eval.setWidth(300);
                            TextView eval01=new TextView(getContext());
                            eval01.setText(jsonObject.getString("evalue"));
                            tableRow6.addView(eval);
                            tableRow6.addView(eval01);
                            tableLayout.addView(tableRow6);
                            tableLayout.setMinimumHeight(500);


                            linearLayout.addView(tableLayout);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                builder.setView(dialogview);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });



        return builder.create();


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
