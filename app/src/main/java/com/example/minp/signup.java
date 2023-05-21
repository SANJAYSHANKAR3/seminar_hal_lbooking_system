package com.example.minp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {
   private EditText user_name, pass_word;
    FirebaseAuth mAuth;
    RadioGroup radioGroup;
String st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        user_name=findViewById((R.id.email));
        pass_word=findViewById(R.id.password);
        Button btn_login = findViewById(R.id.btn_login);
        TextView registers = findViewById(R.id.registers);
        EditText et=findViewById(R.id.name);
        mAuth=FirebaseAuth.getInstance();

        btn_login.setOnClickListener(v -> {
            Toast.makeText(signup.this,
                    "Logging check",
                    Toast.LENGTH_SHORT).show();
            String email= user_name.getText().toString().trim();

            String password=pass_word.getText().toString().trim();


            if(email.isEmpty())
            {
                user_name.setError("Email is empty");
                user_name.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                user_name.setError("Enter the valid email");
                user_name.requestFocus();
                return;
            }
            if(password.isEmpty())
            {
                pass_word.setError("Password is empty");
                pass_word.requestFocus();
                return;
            }
            if(password.length()<6)
            {
                pass_word.setError("Length of password is more than 6");
                pass_word.requestFocus();
                return;
            }


            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {


                if(task.isSuccessful()&&email.equals("admin@gmail.com")&&password.equals("1234567"))
                {
                    Toast.makeText(signup.this,
                            "Login Successful",
                            Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(signup.this,findfriend.class);
                    startActivity(intent);


                }
                else if(task.isSuccessful()){
                    Toast.makeText(signup.this,
                            "Login Successful",
                            Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(signup.this,dashboard.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(signup.this,
                            "Please Check Your login Credentials",
                            Toast.LENGTH_SHORT).show();
                }

            });
        });

        registers.setOnClickListener(v -> startActivity(new Intent(signup.this,register.class )));

    }


}