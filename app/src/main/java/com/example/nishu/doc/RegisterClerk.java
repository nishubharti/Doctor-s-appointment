package com.example.nishu.doc;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nishu.doc.dbutil.ClinicConstant;
import com.example.nishu.doc.dbutil.ClinicManager;

public class RegisterClerk extends AppCompatActivity {

    EditText ckid, name,phone,email,address,salary;
    ClinicManager clinicManager;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_clerk);
        ckid=findViewById(R.id.ckid);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        address=findViewById(R.id.address);
        salary=findViewById(R.id.salary);
        clinicManager=new ClinicManager(this);
        sqLiteDatabase=clinicManager.openDB();

    }

    public void RegisterData(View view) {
        String id=ckid.getText().toString();
        String nm=name.getText().toString();
        String ad=address.getText().toString();
        String ph=phone.getText().toString();
        String em=email.getText().toString();
        String sl=salary.getText().toString();
        ContentValues contentValues=new ContentValues();
        contentValues.put(ClinicConstant.ID,id);
        contentValues.put(ClinicConstant.NAME,nm);
        contentValues.put(ClinicConstant.COL_PHONE,ph);
        contentValues.put(ClinicConstant.EMAIL,em);
        contentValues.put(ClinicConstant.ADDRESS,ad);
        contentValues.put(ClinicConstant.COL_SALARY,sl);
        long l=sqLiteDatabase.insert(ClinicConstant.TABLENAME,null,contentValues);
        if(l>0)
        {
            Toast.makeText(this, "clerk added", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,Doctor.class);
            startActivity(intent);
            Toast.makeText(this, "data inserted", Toast.LENGTH_SHORT).show();
        }



    }
}