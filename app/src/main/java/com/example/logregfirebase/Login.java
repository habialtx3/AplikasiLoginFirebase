package com.example.logregfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private EditText eUser,ePassword;
    private Button bLogin,bRegister;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eUser = findViewById(R.id.formUsername);
        ePassword = findViewById(R.id.formPassword);
        bRegister = findViewById(R.id.btnRegister);
        bLogin = findViewById(R.id.btnLogin);

    bLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String username = eUser.getText().toString();
            String password = ePassword.getText().toString();

            database = FirebaseDatabase.getInstance().getReference("users");
            if(username.isEmpty()||password.isEmpty()){
                Toast.makeText(getApplicationContext(), "Username atau Password Salah", Toast.LENGTH_SHORT).show();
            } else {
                database.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(username).exists()){
                            if(snapshot.child(username).child("password").getValue(String.class).equals(password)){
                                Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Data Belum Terdaftar", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }
    });

    bRegister.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent register = new Intent(getApplicationContext(),Register.class);
            startActivity(register);
        }
    });
    }
}