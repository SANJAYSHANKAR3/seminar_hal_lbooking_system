package com.example.minp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class bookingdetails3 extends AppCompatActivity {
public TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookingdetails3);
        text=(TextView) findViewById(R.id.c225);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bookingdetails3.this, A223.class);
                startActivity(intent);
            }
        });
    }
}