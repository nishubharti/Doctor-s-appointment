package com.example.nishu.doc;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nishu.doc.dbutil.ClinicConstant;
import com.example.nishu.doc.dbutil.ClinicManager;


public class ViewData extends AppCompatActivity {



    TextView uid,uname,uphone,uemail,uaddress,usalary;
    ClinicManager clerkManager;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        uid=findViewById(R.id.uid);
        uname=findViewById(R.id.uname);
        uphone=findViewById(R.id.uphone);
        uemail=findViewById(R.id.uemail);
        uaddress=findViewById(R.id.uaddress);
        usalary=findViewById(R.id.usalary);
        clerkManager=new ClinicManager(this);
        sqLiteDatabase=clerkManager.openDB();
        Intent intent=getIntent();
        String n=intent.getStringExtra("uid");
        populateData(n);
    }

    private void populateData(String n) {
        String[] args={n};
        String[] colnames={ClinicConstant.NAME, ClinicConstant.COL_PHONE,ClinicConstant.EMAIL,ClinicConstant.ADDRESS,ClinicConstant.COL_SALARY};
        Cursor cursor=sqLiteDatabase.query(ClinicConstant.TABLENAME,colnames,ClinicConstant.ID+"=?",args,null,null,null);
        if(cursor != null && cursor.moveToFirst()) {
            String nm = cursor.getString(cursor.getColumnIndex(ClinicConstant.NAME));
            String ph = cursor.getString(cursor.getColumnIndex(ClinicConstant.COL_PHONE));
            String em = cursor.getString(cursor.getColumnIndex(ClinicConstant.EMAIL));
            String ad = cursor.getString(cursor.getColumnIndex(ClinicConstant.ADDRESS));
            String sl = cursor.getString(cursor.getColumnIndex(ClinicConstant.COL_SALARY));
            uid.setText(n);
            uname.setText(nm);
            uphone.setText(ph);
            uemail.setText(em);
            uaddress.setText(ad);
            usalary.setText(sl);
        }
    }

    public void onBack(View view) {
        Intent i=new Intent(this,ViewClerk.class);
        startActivity(i);

    }
}