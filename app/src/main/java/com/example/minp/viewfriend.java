package com.example.minp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class viewfriend extends AppCompatActivity {
    public Button btnac;
    public Button btndc;
    String st;
    public TextView tv;
    FirebaseAuth mAuth;
    FirebaseUser muser;
    DatabaseReference muserref, reqref,frndref,  databaseReference;
    String cs="nothing";
    EditText dept;
   TextView Username;
    String username,names1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewfriend);
        String userid=getIntent().getStringExtra("userkey");
        Toast.makeText(this,""+userid,Toast.LENGTH_SHORT).show();
          Username=findViewById(R.id.username);

            databaseReference = FirebaseDatabase.getInstance().getReference("shbs");
          userid=getIntent().getStringExtra("userkey");
          //  muserref = FirebaseDatabase.getInstance().getReference().child("users").child(userid);
//String name="sanjay";
            reqref = FirebaseDatabase.getInstance().getReference().child("requests");
            frndref = FirebaseDatabase.getInstance().getReference().child("booked");
            mAuth = FirebaseAuth.getInstance();
            muser = mAuth.getCurrentUser();
            loaduser();
      //  Username = Username.getText().toString().trim();

        String finalUserid = userid;
        btnac.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View view) {


                    performaction(finalUserid);


                }

            });
            checkuserexistance(userid);
        }

        private void loaduser() {
            reqref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        username=snapshot.child("name").getValue().toString();
                        Username.setText(username);
                    }
                    else{
                        Toast.makeText(viewfriend.this,"data not found",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        private void checkuserexistance(String userid) {
            frndref.child(muser.getUid()).child(userid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        cs="friend";

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            frndref.child(userid).child(muser.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        cs="friend";
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            reqref.child(muser.getUid()).child(userid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        if(snapshot.child("status").getValue().toString().equals("pending")){
                            cs="i_sent_pending";
                            btnac.setText("cancel booking");
                            btndc.setVisibility(View.GONE);
                        }

                        if(snapshot.child("status").getValue().toString().equals("decline")){
                            cs="i_sent_decline";
                            btnac.setText("cancel booking");
                            btndc.setVisibility(View.GONE);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            reqref.child(userid).child(muser.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        if(snapshot.child("status").getValue().toString().equals("pending")){

                            cs="he_sent_pending";
                            btnac.setText("accept booking");
                            btndc.setText("decline booking");
                            btndc.setVisibility(View.VISIBLE);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            if (cs.equals("nothing")) {
                cs = "nothing";
                btnac.setText("BOOK");
                btndc.setVisibility(View.GONE);
            }

        }



        public void performaction(String userid){

            if(cs.equals("nothing")) {
                HashMap hashMap = new HashMap();
                hashMap.put("status", "pending");

                reqref.child(muser.getUid()).child(userid).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(viewfriend.this, "BOOKING REQUEST SENT", Toast.LENGTH_SHORT).show();
                            btndc.setVisibility(View.GONE);
                            cs = "i_sent_pending";
                            btnac.setText("cancel booking");

                        } else {
                            Toast.makeText(viewfriend.this, "" + task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            if(cs.equals("i_sent_pending")||(cs.equals("i_sent_decline"))){
                reqref.child(muser.getUid()).child(userid).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(viewfriend.this,"Cancelled booking",Toast.LENGTH_SHORT).show();
                            cs="nothing";
                            btnac.setText("book");
                            btndc.setVisibility(View.GONE);
                        }
                        else{
                            Toast.makeText(viewfriend.this,""+task.getException().toString(),Toast.LENGTH_SHORT).show();


                        }
                    }
                });

            }
            if(cs.equals("he_sent_pending")){
                reqref.child(muser.getUid()).child(userid).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            HashMap hashMap=new HashMap();
                            hashMap.put("status","booked");
                            frndref.child(muser.getUid()).child(userid).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {
                                    if(task.isSuccessful()){
                                        frndref.child(userid).child(muser.getUid()).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                                            @Override
                                            public void onComplete(@NonNull Task task) {
                                                Toast.makeText(viewfriend.this,"booked successfully",Toast.LENGTH_SHORT).show();
                                                cs="friend";
                                                btnac.setText(("booked"));
                                                btndc.setText(("Cancel Booking"));
                                                btndc.setVisibility(View.VISIBLE);
                                            }
                                        });
                                    }
                                }
                            });
                        }
                        if(cs.equals("friend")){
                            //
                        }
                    }
                });
            }
        }
    }


