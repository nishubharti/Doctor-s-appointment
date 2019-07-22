package com.example.nishu.doc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class LoginModule extends AppCompatActivity implements View.OnClickListener{

    ImageView imageView;
    ImageView imageView2;
    Button button2;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_module);

        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId())
        {
            case R.id.button2:
                intent=new Intent(this,DoctorLogin.class);
                startActivity(intent);
                break;
            case R.id.button3:
                intent=new Intent(this,ClerkLogin.class);
                startActivity(intent);
                break;
        }

    }

}

