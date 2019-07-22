package com.example.nishu.doc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nishu.doc.dbutil.ClinicConstant;
import com.example.nishu.doc.dbutil.ClinicManager;

public class ClerkLogin extends AppCompatActivity {

    EditText ckname,ckpass;
    ClinicManager clinicManager;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clerk_login);
        ckname=findViewById(R.id.ckname);
        ckpass=findViewById(R.id.ckpass);
        clinicManager=new ClinicManager(this);
        sqLiteDatabase=clinicManager.openDB();
    }

    public void OnCkLogin(View view) {
        int flag=0;
        String nm=ckname.getText().toString().trim();
        String ps=ckpass.getText().toString().trim();
        String[] colnames={ClinicConstant.EMAIL,ClinicConstant.COL_PHONE};
        Cursor cursor=sqLiteDatabase.query(ClinicConstant.TABLENAME,colnames,null,null,null,null,null);
        if (cursor!=null && cursor.moveToFirst()) {
            do {
                String e = cursor.getString(cursor.getColumnIndex(ClinicConstant.EMAIL));
                String p = cursor.getString(cursor.getColumnIndex(ClinicConstant.COL_PHONE));
                if (nm.equals(e) && ps.equals(p)) {
                    flag=1;
                    Intent intent = new Intent(this, AppointmentList.class);
                    startActivity(intent);
                    break;
                }
            } while (cursor.moveToNext());
            if(flag==0)
            {
                Toast.makeText(this, "incorrect username or password", Toast.LENGTH_SHORT).show();
            }

        }

    }
}
