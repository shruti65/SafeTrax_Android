package com.safeinvest.safetrax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity {
ImageView notification,safeimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        DrawerLayout drawerLayout= findViewById(R.id.drawerlayout);
        notification=(ImageView)findViewById(R.id.noti);
        safeimg=(ImageView)findViewById(R.id.safeimg);


        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        AlertDialog.Builder builder =new AlertDialog.Builder(Dashboard.this);

        safeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadfrag(new home());
            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadfrag(new Notification());
               /* ViewGroup viewGroup= (ViewGroup) findViewById(android.R.id.content);
                View dialogview= LayoutInflater.from(v.getContext()).inflate(R.layout.noti_alert,viewGroup,false);
                TextView textView=(TextView)dialogview.findViewById(R.id.changetext);
                LinearLayout mlayout=(LinearLayout)dialogview.findViewById(R.id.mainnoti);
                Button clr=(Button)dialogview.findViewById(R.id.button);
                LinearLayout linearLayout=(LinearLayout)dialogview.findViewById(R.id.linear1);
                textView.setText("Notification ");
                for(int i=0;i<5;i++){
                    LinearLayout linearLayout1=new LinearLayout(Dashboard.this);
                    TextView textView1=new TextView(Dashboard.this);
                    textView1.setText("Message");
                    Button button=new Button(Dashboard.this);
                    button.setText("Hide");
                    textView1.setWidth(600);
                    linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
                    linearLayout1.addView(textView1);
                    linearLayout1.addView(button);
                    linearLayout.addView(linearLayout1);
                }
                builder.setView(dialogview);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
                clr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       mlayout.removeView(linearLayout);
                    }
                });*/
            }
        });

        NavigationView navigationView= findViewById(R.id.navigationview);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.menu_cef:
                        loadfrag(new cef());
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.viewtask:
                        loadfrag(new Viewtask());
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.menu_leave:
                        loadfrag(new LeaveMain());
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.addtask:
                        loadfrag(new Task());
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.menu_claim:
                        loadfrag(new ClaimForm());
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.menu_cefList:
                        loadfrag(new CefViewList());
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.menu_crm:
                        loadfrag(new CrmMain());
                        drawerLayout.closeDrawers();
                        return true;

                }
                return false;
            }
        });
    }
    private void loadfrag(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft =fm.beginTransaction();
        ft.replace(R.id.fragmentContainerView,fragment);
        ft.commit();
    }
}