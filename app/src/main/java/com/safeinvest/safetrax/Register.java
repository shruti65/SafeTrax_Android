package com.safeinvest.safetrax;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText rusername,rpass;
    Button register;
    String rdept;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EmployeeDBHelper empdbhelper = new EmployeeDBHelper(this);
        Spinner rspinner=(Spinner)findViewById(R.id.rdeptspinner);
        ArrayAdapter<?> array_Adapter10 =
                ArrayAdapter.createFromResource(this,R.array.dept,
                        android.R.layout.simple_spinner_item);
        array_Adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rspinner.setAdapter(array_Adapter10);
        rspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rdept=array_Adapter10.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        rusername=(EditText) findViewById(R.id.rusername);
        rpass=(EditText) findViewById(R.id.rpassword);
        register=(Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name,pass;
                    name = rusername.getText().toString();
                    pass = rpass.getText().toString();
                    empdbhelper.insertEmployee(name,pass,rdept);
                    Toast.makeText(getApplicationContext(),"Your record has been saved successfully with ID",Toast.LENGTH_LONG).show();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}