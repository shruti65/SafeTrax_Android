package com.safeinvest.safetrax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button login;
    EditText uname,pass;
    Cursor c= null;
    TextView forget,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=(Button) findViewById(R.id.login);
        uname=(EditText)findViewById(R.id.username);
        register=(TextView) findViewById(R.id.register);
        pass=(EditText)findViewById(R.id.password);
        forget=(TextView)findViewById(R.id.fpass) ;
        final EmployeeDBHelper empdb1 = new EmployeeDBHelper(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname=(EditText)findViewById(R.id.username);
                pass=(EditText)findViewById(R.id.password);
                String uname1=uname.getText().toString();
                String pass1=pass.getText().toString();
                Boolean checkuserpass=empdb1.checkusernamepass(uname1,pass1);
                if(checkuserpass==true){
                    Toast.makeText(getApplicationContext(),"Successfull Login",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(MainActivity.this,Dashboard.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Unsuccessfull",Toast.LENGTH_LONG).show();
                }


            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Dashboard.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });

    }
}