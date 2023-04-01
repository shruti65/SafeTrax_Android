package com.safeinvest.safetrax;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;



public class LeaveList extends Fragment {
    ScrollView scrollView;
    LinearLayout linear;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_leave_list, container, false);
        scrollView=(ScrollView) view.findViewById(R.id.scrollview);
        linear=(LinearLayout) view.findViewById(R.id.linear);
        for(int i=0;i<10;i++){
            LinearLayout linearLayout=new LinearLayout(getContext());
            TextView textView=new TextView(getContext());
            textView.setText("  Request ID");
            Button button=new Button(getContext());
            button.setText("view");
            button.setBackground(getResources().getDrawable(R.drawable.smallbtn));
            textView.setWidth(600);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.addView(textView);
            linearLayout.setBackground(getResources().getDrawable(R.drawable.listback));
            linearLayout.addView(button);
            linear.addView(linearLayout);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
                    ViewGroup viewGroup= view.findViewById(android.R.id.content);
                    View dialogview=LayoutInflater.from(v.getContext()).inflate(R.layout.fragment_leave__list__alert,viewGroup,false);
                    TextView textView=(TextView)dialogview.findViewById(R.id.changetext);
                    LinearLayout linearLayout=(LinearLayout)dialogview.findViewById(R.id.linear1);
                    textView.setText("Leave Details ");
                    builder.setView(dialogview);
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();
                }
            });
        }
        return view;

    }

}