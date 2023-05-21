package com.example.minp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
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


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class findfriend extends AppCompatActivity {

FirebaseRecyclerOptions<person>options;
FirebaseRecyclerAdapter<person,FindFriendViewHolder>adapter;
    private RecyclerView recyclerView;
 // Create Object of the Adapter class
    DatabaseReference muserref;
    FirebaseAuth mauth;
    FirebaseUser muser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);
        recyclerView=findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        muserref=FirebaseDatabase.getInstance().getReference().child("requests");
        loadusers("");
    }

    private void loadusers(String s) {
Query query=muserref.orderByChild("name").startAt(s).endAt(s+"\uf8ff");
options =new FirebaseRecyclerOptions.Builder<person>().setQuery(query,person.class).build();
adapter=new FirebaseRecyclerAdapter<person, FindFriendViewHolder>(options) {
    @Override
    protected void onBindViewHolder(@NonNull FindFriendViewHolder holder, int position, @NonNull person model) {
        holder.name.setText(model.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(findfriend.this,acdc.class);
                intent.putExtra("userkey",getRef(holder.getAdapterPosition()).getKey().toString());
                startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public FindFriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person,parent,false);

        return new FindFriendViewHolder(view);
    }
};
adapter.startListening();
recyclerView.setAdapter(adapter);
    }

}





