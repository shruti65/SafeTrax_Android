package com.safeinvest.safetrax;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button login;
    EditText uname,pass;
 //   Cursor c= null;
    TextView forget,register,realforget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannels();
        login=(Button) findViewById(R.id.login);
        uname=(EditText)findViewById(R.id.username);
        register=(TextView) findViewById(R.id.register);
        pass=(EditText)findViewById(R.id.password);
        forget=(TextView)findViewById(R.id.fpass) ;
        realforget=(TextView) findViewById(R.id.fpass01);


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


        realforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ForgetPass.class);
                startActivity(intent);
            }
        });

    }

    private void createNotificationChannels() {
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
            NotificationChannel channel1= new NotificationChannel("channel1","Channel 1", NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("This is channel1");
            NotificationManager notificationManager= getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel1);
        }
    }


}