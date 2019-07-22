package com.example.nishu.doc;


   import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

    public class Sendsms extends AppCompatActivity {

        EditText number;
        EditText msg;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sendsms);
            number=findViewById(R.id.number);
            msg=findViewById(R.id.msg);
            Intent intent=getIntent();
            String n= intent.getStringExtra("contact");
            number.setText(n);


        }

        public void sendmsg(View view) {
            String num=number.getText().toString().trim();
            String m= msg.getText().toString();
            SmsManager sm= SmsManager.getDefault();
            Intent obj=new Intent(this,Sendsms.class);
            // Uri u= Uri.parse("tel:"+num);
            // obj.setData(u);
            PendingIntent pi=PendingIntent.getActivity(this,2,obj,PendingIntent.FLAG_ONE_SHOT);
            sm.sendTextMessage(num,null,m,pi,null);
            Toast.makeText(this, "message sent", Toast.LENGTH_SHORT).show();
        }
    }