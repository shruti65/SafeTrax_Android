package com.safeinvest.safetrax;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CefViewList extends Fragment {
    ScrollView scrollView;
    LinearLayout linear;
    Spinner filter;
    String filter1="select";
    int filter01;
    private List<String> filtered = new ArrayList<>();
    private List<String> fil = new ArrayList<>();
    EditText tv01;
    int j=0;
    ImageButton reload;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_cef_view_list, container, false);
        scrollView=(ScrollView) view.findViewById(R.id.scrollview);
        linear=(LinearLayout) view.findViewById(R.id.linear);
        filter=(Spinner)view.findViewById(R.id.filter);
        tv01=(EditText) view.findViewById(R.id.tv02);
        reload=(ImageButton)view.findViewById(R.id.reload);

        String url="http://15.1.1.134:9000/cef/client";
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                filtered.add("Select FA");
                final String str = String.valueOf(response.length());
                tv01.setText(str);
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        filtered.add(jsonObject.getString("fa"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    fil= filtered.stream().distinct().collect(Collectors.toList());
                }

                
                ArrayAdapter<String> array_Adapter4= new ArrayAdapter<String>(getContext(), R.layout.spinner01,fil);
                filter.setAdapter(array_Adapter4);
                filter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        filter1 = array_Adapter4.getItem(position).toString();
                        filter01 = position;
                        if (!filter1.equals("Select FA")) {
                            linear.removeAllViews();
                            JsonArrayRequest jsonArrayRequest1 = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    for (int i = 0; i < response.length(); i++) {
                                        try {
                                            JSONObject jsonObject01 = response.getJSONObject(i);

                                            if (jsonObject01.getString("fa").equals(filter1)) {
                                                j++;
                                                LinearLayout linearLayout = new LinearLayout(getContext());
                                                TextView textView = new TextView(getContext());
                                                String name = jsonObject01.getString("firstName") + " " + jsonObject01.getString("middleName") + " " + jsonObject01.getString("lastName");
                                                textView.setText(name);
                                                Button button = new Button(getContext());
                                                button.setText("view");
                                                textView.setWidth(640);
                                                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                                                linearLayout.addView(textView);
                                                linearLayout.addView(button);
                                                linear.addView(linearLayout);
                                                button.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                                        ViewGroup viewGroup = view.findViewById(android.R.id.content);
                                                        View dialogview = LayoutInflater.from(v.getContext()).inflate(R.layout.fragment_cef_list_alert, viewGroup, false);
                                                        TextView textView = (TextView) dialogview.findViewById(R.id.changetext);
                                                        TextView gname = (TextView) dialogview.findViewById(R.id.gname);
                                                        TextView fa = (TextView) dialogview.findViewById(R.id.fadvisor);
                                                        TextView status = (TextView) dialogview.findViewById(R.id.status);
                                                        TextView suser = (TextView) dialogview.findViewById(R.id.suser);
                                                        TextView sdate = (TextView) dialogview.findViewById(R.id.sdate);
                                                        TextView mobile = (TextView) dialogview.findViewById(R.id.mobile);
                                                        TextView email = (TextView) dialogview.findViewById(R.id.email);
                                                        LinearLayout linearLayout = (LinearLayout) dialogview.findViewById(R.id.linear1);
                                                        textView.setText(" CEF Details ");
                                                        try {
                                                            gname.setText(jsonObject01.getString("groupName"));
                                                            fa.setText(jsonObject01.getString("fa"));
                                                            status.setText(jsonObject01.getString("status"));
                                                            suser.setText(jsonObject01.getString("postuser"));
                                                            sdate.setText(jsonObject01.getString("statusDate"));
                                                            mobile.setText(jsonObject01.getString("mobileNo"));
                                                            email.setText(jsonObject01.getString("emailid"));

                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                        builder.setView(dialogview);
                                                        AlertDialog alertDialog = builder.create();
                                                        alertDialog.show();
                                                    }
                                                });
                                                tv01.setText(String.valueOf(j));

                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                }
                            });
                            RequestQueue requestQueue1 = Volley.newRequestQueue(getContext());
                            requestQueue1.add(jsonArrayRequest1);
                        }
                        else
                        {
                           refresh(view);
                        }
                        j=0;
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter.setSelection(0);
                refresh(view);
            }
        });

        return view;
    }

    private void refresh(View view) {
        linear.removeAllViews();
        String url="http://15.1.1.134:9000/cef/client";
        JsonArrayRequest jsonArrayRequest1 = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println(response.length());
                final String str01=String.valueOf(response.length());
                tv01.setText(str01);
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject01 = response.getJSONObject(i);
                        LinearLayout linearLayout = new LinearLayout(getContext());
                        TextView textView = new TextView(getContext());
                        String name = jsonObject01.getString("firstName") + " " + jsonObject01.getString("middleName") + " " + jsonObject01.getString("lastName");
                        textView.setText(name);
                        Button button = new Button(getContext());
                        button.setText("view");
                        textView.setWidth(640);
                        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                        linearLayout.addView(textView);
                        linearLayout.addView(button);
                        linear.addView(linearLayout);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                ViewGroup viewGroup = view.findViewById(android.R.id.content);
                                View dialogview = LayoutInflater.from(v.getContext()).inflate(R.layout.fragment_cef_list_alert, viewGroup, false);
                                TextView textView = (TextView) dialogview.findViewById(R.id.changetext);
                                TextView gname = (TextView) dialogview.findViewById(R.id.gname);
                                TextView fa = (TextView) dialogview.findViewById(R.id.fadvisor);
                                TextView status = (TextView) dialogview.findViewById(R.id.status);
                                TextView suser = (TextView) dialogview.findViewById(R.id.suser);
                                TextView sdate = (TextView) dialogview.findViewById(R.id.sdate);
                                TextView mobile = (TextView) dialogview.findViewById(R.id.mobile);
                                TextView email = (TextView) dialogview.findViewById(R.id.email);
                                LinearLayout linearLayout = (LinearLayout) dialogview.findViewById(R.id.linear1);
                                textView.setText(" CEF Details ");
                                try {
                                    gname.setText(jsonObject01.getString("groupName"));
                                    fa.setText(jsonObject01.getString("fa"));
                                    status.setText(jsonObject01.getString("status"));
                                    suser.setText(jsonObject01.getString("postuser"));
                                    sdate.setText(jsonObject01.getString("statusDate"));
                                    mobile.setText(jsonObject01.getString("mobileNo"));
                                    email.setText(jsonObject01.getString("emailid"));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                builder.setView(dialogview);
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        RequestQueue requestQueue1 = Volley.newRequestQueue(getContext());
        requestQueue1.add(jsonArrayRequest1);
    }
}