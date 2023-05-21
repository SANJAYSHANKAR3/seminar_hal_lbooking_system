package com.example.minp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.navigation.NavigationView;

public class navigationbar extends AppCompatActivity {
    private DrawerLayout dl;
    private NavigationView nv;
private Toolbar tb;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigationbar);
        dl=findViewById(R.id.drawer_layout);
        nv=findViewById(R.id.navigationview);
        tb=findViewById(R.id.appbar);
        setSupportActionBar(tb);
        ActionBar ab=getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.upbar);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override

            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.profile:
                        Toast.makeText(navigationbar.this,"opening profile",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(navigationbar.this,admin.class);
                        startActivity(intent);
                        return  true;
                    case R.id.bookhalls:
                        Toast.makeText(navigationbar.this,"book halls",Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(navigationbar.this,dashboard.class);
                        startActivity(intent2);
                        return  true;
                    case R.id.register:
                        Toast.makeText(navigationbar.this,"book halls",Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(navigationbar.this,register.class);
                        startActivity(intent3);
                        return  true;


                    case R.id.moreinfo:
                        Toast.makeText(navigationbar.this,"more info",Toast.LENGTH_SHORT).show();
                        Intent intent4 = new Intent(navigationbar.this,about.class);
                        startActivity(intent4);
                        return  true;
                }
                return false;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                dl.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
