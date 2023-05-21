package com.example.minp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class acdc extends AppCompatActivity {
    Button accept, reject;
    FirebaseAuth mAuth;
    FirebaseUser muser;
    DatabaseReference muserref, reqref, frndref, databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReference("requests");

        frndref = FirebaseDatabase.getInstance().getReference().child("booked");
        TextView tv = findViewById(R.id.tv);
        mAuth = FirebaseAuth.getInstance();
        muser = mAuth.getCurrentUser();
        setContentView(R.layout.activity_acdc);
        accept = findViewById(R.id.btnac);
        reject = findViewById(R.id.btndc);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = "20BCS016";
                Map<String, Object> map = new HashMap<>();

                databaseReference.child("booked").push().setValue(map).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        map.put("name", username);
                        Toast.makeText(acdc.this, "Booking Accepted", Toast.LENGTH_SHORT).show();
                        accept.setVisibility(View.GONE);
                        reject.setText(("Cancel Request"));
                        reject.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(acdc.this, "error", Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });


        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                accept.setText("Accept");
                reject.setText(("Decline"));
                accept.setVisibility(View.VISIBLE);
                Toast.makeText(acdc.this, "Booking Rejected", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
