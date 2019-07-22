package com.example.nishu.doc;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Wehelp extends AppCompatActivity {
    EditText msg;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wehelp);
        msg=findViewById(R.id.msg);
               btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    Intent intent=getIntent();
                    String n= intent.getStringExtra("contact");

                    @Override
                    public void onClick(View v) {
                        pool(n);

                    }
                }
        );


    }
    public void pool(String n) {
        String num=n;
        String m= msg.getText().toString();
        SmsManager sm= SmsManager.getDefault();
        Intent obj=new Intent(this,MainActivity.class);
        // Uri u= Uri.parse("tel:"+num);
        // obj.setData(u);
        PendingIntent pi=PendingIntent.getActivity(this,2,obj,PendingIntent.FLAG_ONE_SHOT);
        sm.sendTextMessage(num,null,m,pi,null);
        Toast.makeText(this, "message sent", Toast.LENGTH_SHORT).show();
    }
}
