package com.example.logregfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class Register extends AppCompatActivity {

    private EditText eUser,eEmail,ePassword;
    private Button bRegister;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        eUser = findViewById(R.id.textUsername);
        ePassword = findViewById(R.id.textEmail);
        eEmail = findViewById(R.id.textPassword);
        bRegister = findViewById(R.id.btnRegister);

        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://reglogfirebase19-default-rtdb.firebaseio.com/");

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = eUser.getText().toString();
                String email = eEmail.getText().toString();
                String password = ePassword.getText().toString();

                if(username.isEmpty()||email.isEmpty()||password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Data Ada Yang Masih Kosong", Toast.LENGTH_SHORT).show();
                } else {
                    database = FirebaseDatabase.getInstance().getReference("users");
                    database.child(username).child("username").setValue(username);
                    database.child(username).child("email").setValue(email);
                    database.child(username).child("password").setValue(password);

                    Toast.makeText(getApplicationContext(), "Register Berhasil", Toast.LENGTH_SHORT).show();
                    Intent register = new Intent(getApplicationContext(),Login.class);
                    startActivity(register);
                }
            }
        });
    }
}