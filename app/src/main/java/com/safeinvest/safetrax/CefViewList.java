package com.safeinvest.safetrax;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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


public class CefViewList extends Fragment {
    ScrollView scrollView;
    LinearLayout linear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_cef_view_list, container, false);
        scrollView=(ScrollView) view.findViewById(R.id.scrollview);
        linear=(LinearLayout) view.findViewById(R.id.linear);
        String url="http://15.1.1.134:9000/cef/client";
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                   System.out.println(response.length()+"LENGTH");
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        JSONObject jsonObject1=jsonObject.getJSONObject("statusDetailBean");
                        System.out.println(jsonObject);
                        LinearLayout linearLayout=new LinearLayout(getContext());
                        TextView textView=new TextView(getContext());
                        String name=jsonObject.getString("firstName") +" "+ jsonObject.getString("middleName") +" "+ jsonObject.getString("lastName");
                        textView.setText(name);
                        Button button=new Button(getContext());
                        button.setText("view");
                        textView.setWidth(600);
                        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                        linearLayout.addView(textView);
                        linearLayout.addView(button);
                        linear.addView(linearLayout);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
                                ViewGroup viewGroup= view.findViewById(android.R.id.content);
                                View dialogview=LayoutInflater.from(v.getContext()).inflate(R.layout.fragment_cef_list_alert,viewGroup,false);
                                TextView textView=(TextView)dialogview.findViewById(R.id.changetext);
                                TextView gname=(TextView)dialogview.findViewById(R.id.gname);
                                TextView fa=(TextView)dialogview.findViewById(R.id.fadvisor);
                                TextView status=(TextView)dialogview.findViewById(R.id.status);
                                TextView suser=(TextView)dialogview.findViewById(R.id.suser);
                                TextView sdate=(TextView)dialogview.findViewById(R.id.sdate);
                                TextView mobile=(TextView)dialogview.findViewById(R.id.mobile);
                                TextView email=(TextView)dialogview.findViewById(R.id.email);
                                LinearLayout linearLayout=(LinearLayout)dialogview.findViewById(R.id.linear1);
                                textView.setText(" CEF Details ");
                                try {
                                    gname.setText(jsonObject.getString("groupName"));
                                    fa.setText(jsonObject.getString("fa"));
                                    status.setText(jsonObject.getString("status"));
                                    suser.setText(jsonObject1.getString("postuser"));
                                    sdate.setText(jsonObject1.getString("statusDate"));
                                    mobile.setText(jsonObject.getString("mobileNo"));
                                    email.setText(jsonObject.getString("emailid"));

                                } catch (JSONException e) {
                                    e.printStackTrace();
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
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);

        return view;
    }
}