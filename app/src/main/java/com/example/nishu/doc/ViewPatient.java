package com.example.nishu.doc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nishu.doc.dbutil.ClinicConstant;
import com.example.nishu.doc.dbutil.ClinicManager;

public class ViewPatient extends AppCompatActivity {

    TextView pid,pname,pphone,paddress,prblm,trt;
    ClinicManager clerkManager;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient);
        pid=findViewById(R.id.pid);
        pname=findViewById(R.id.pname);
        pphone=findViewById(R.id.pphone);
        paddress=findViewById(R.id.paddress);
        prblm=findViewById(R.id.prblm);
        trt=findViewById(R.id.trt);

        clerkManager=new ClinicManager(this);
        sqLiteDatabase=clerkManager.openDB();
        Intent intent=getIntent();
        String n=intent.getStringExtra("uid");
        populateData(n);
    }

    private void populateData(String n) {
        String[] args={n};
        String[] colnames={ClinicConstant.COL_NAME, ClinicConstant.COL_NUMBER,ClinicConstant.COL_GENDER,ClinicConstant.COL_AGE,ClinicConstant.COL_DOB,ClinicConstant.COL_EMAIL,ClinicConstant.COL_ADDRESS,ClinicConstant.COL_PROBLEM,ClinicConstant.COL_TREATMENT,ClinicConstant.COL_DATE,ClinicConstant.COL_TOKEN};
        Cursor cursor=sqLiteDatabase.query(ClinicConstant.TABLE_NAME,colnames,ClinicConstant.COL_ID+"=?",args,null,null,null);
        if(cursor != null && cursor.moveToFirst()) {
            String nm = cursor.getString(cursor.getColumnIndex(ClinicConstant.COL_NAME));
            String ph = cursor.getString(cursor.getColumnIndex(ClinicConstant.COL_NUMBER));
            String ad = cursor.getString(cursor.getColumnIndex(ClinicConstant.COL_ADDRESS));
            String pb = cursor.getString(cursor.getColumnIndex(ClinicConstant.COL_PROBLEM));
            String tr = cursor.getString(cursor.getColumnIndex(ClinicConstant.COL_TREATMENT));
            pid.setText(n);
            pname.setText(nm);
            pphone.setText(ph);
            paddress.setText(ad);
            prblm.setText(pb);
            trt.setText(tr);
        }
    }

    public void onBack(View view) {
        Intent i=new Intent(this,Doctor.class);
        startActivity(i);

    }
}
