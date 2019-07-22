package com.example.nishu.doc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class DoctorLogin extends AppCompatActivity {

    EditText username, pass;
    SharedPreferences sp;
    SharedPreferences.Editor se;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);
        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        sp = getSharedPreferences("info", MODE_PRIVATE);
        se = sp.edit();
    }

    public void OnLogin(View view) {
        String n = username.getText().toString().trim();
        String p = pass.getText().toString().trim();
        se.putString("username", "doctor");
        se.putString("pass", "password");
        se.commit();

        if (sp != null) {
            String name = sp.getString("username", null);
            String passwrd = sp.getString("pass", null);
            if (n.equals(name) && p.equals(passwrd)) {
                Intent intent = new Intent(this, Doctor.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "username or password incorrect", Toast.LENGTH_SHORT).show();
            }
        }

    }
}