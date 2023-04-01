package com.safeinvest.safetrax;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Notification extends Fragment {

    LinearLayout linearLayout;
    Button clr;
    ScrollView scrollView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_notification, container, false);
        FrameLayout mlayout=(FrameLayout)view.findViewById(R.id.mainnoti);
        linearLayout=(LinearLayout) view.findViewById(R.id.linear1);
        scrollView=(ScrollView)view.findViewById(R.id.scroll);
        clr=(Button) view.findViewById(R.id.button);
        for(int i=1;i<20;i++){
            LinearLayout linearLayout1=new LinearLayout(getContext());
            TextView textView1=new TextView(getContext());
            textView1.setText(i +". Message");
            Button button=new Button(getContext());
            button.setText("Hide");
            textView1.setWidth(600);
            linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout1.addView(textView1);
            linearLayout1.addView(button);
            linearLayout.addView(linearLayout1);
            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
                    ViewGroup viewGroup= (ViewGroup)view.findViewById(android.R.id.content);
                    View dialogview= LayoutInflater.from(v.getContext()).inflate(R.layout.noti_alert,viewGroup,false);
                    TextView textView=(TextView)dialogview.findViewById(R.id.changetext);
                    textView.setText("Task Message");
                    Button add=(Button)dialogview.findViewById(R.id.button2);
                    builder.setView(dialogview);
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();
                    add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                         alertDialog.dismiss();
                        }
                    });
                }
            });
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    linearLayout.removeView(linearLayout1);

                }
            });

        }
        clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Clicked");
                scrollView.removeView(linearLayout);
            }
        });
        return view;
    }
}