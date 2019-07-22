package com.example.nishu.doc;

import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.nishu.doc.dbutil.ClinicConstant;
import com.example.nishu.doc.dbutil.ClinicManager;

import java.util.Calendar;

public class Patient extends AppCompatActivity implements View.OnClickListener{

    EditText Regno,name,age,dob,add,number,email,problm,treat,token;
    RadioGroup gen;
    RadioButton male,female;
    Button reg;
    EditText date;
    DatePickerDialog datePickerDialog;

    ClinicManager clinicManager;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        Regno=(EditText)findViewById(R.id.Regno);
        name=(EditText)findViewById(R.id.name);
        age=(EditText)findViewById(R.id.age);
        dob=(EditText)findViewById(R.id.dob);
        add=(EditText)findViewById(R.id.add);
        number=(EditText)findViewById(R.id.number);
        email=(EditText)findViewById(R.id.email);
        problm=(EditText)findViewById(R.id.problm);
        treat=(EditText)findViewById(R.id.treat);
        token=(EditText)findViewById(R.id.token);
        gen=findViewById(R.id.gen);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        reg=findViewById(R.id.reg);
        date=findViewById(R.id.date);
        dob.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(Patient.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                dob.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(Patient.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        clinicManager = new ClinicManager(this);
        sqLiteDatabase = clinicManager.openDB();

        reg.setOnClickListener(this);
    }
    public void onClick(View view)
    {
        String  id=Regno.getText().toString();
        String pname=name.getText().toString();
        String page=age.getText().toString();
        String pdob=dob.getText().toString();
        String padd=add.getText().toString();
        String pnum=number.getText().toString();
        String pemail=email.getText().toString();
        String pproblm=problm.getText().toString();
        String ptreat=treat.getText().toString();
        String ptoken=token.getText().toString();
        int idrb=gen.getCheckedRadioButtonId();
        RadioButton rd=gen.findViewById(idrb);
        String pgen=rd.getText().toString();
        String appdate=date.getText().toString();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ClinicConstant.COL_ID, id);
        contentValues.put(ClinicConstant.COL_NAME,pname );
        contentValues.put(ClinicConstant.COL_AGE,page );
        contentValues.put(ClinicConstant.COL_DOB,pdob);
        contentValues.put(ClinicConstant.COL_EMAIL,pemail);
        contentValues.put(ClinicConstant.COL_ADDRESS,padd );
        contentValues.put(ClinicConstant.COL_NUMBER, pnum);
        contentValues.put(ClinicConstant.COL_PROBLEM,pproblm);
        contentValues.put(ClinicConstant.COL_TREATMENT,ptreat);
        contentValues.put(ClinicConstant.COL_DATE,appdate);
        contentValues.put(ClinicConstant.COL_TOKEN,ptoken);
        contentValues.put(ClinicConstant.COL_GENDER,pgen);
        int num=Integer.parseInt(ptoken);
        Cursor cursor=sqLiteDatabase.query(ClinicConstant.TABLE_NAME,null,null,null,null,null,null,null);
        if(cursor!=null && cursor.moveToFirst()) {
            do{
                String chkdate=cursor.getString(cursor.getColumnIndex(ClinicConstant.COL_DATE));
                String chktoken=cursor.getString(cursor.getColumnIndex(ClinicConstant.COL_TOKEN));
                int tk=Integer.parseInt(chktoken);
                if(chkdate.equals(appdate) && num==tk)
                {
                    Toast.makeText(this, "already booked for current token for"+appdate, Toast.LENGTH_SHORT).show();
                    break;
                }
                else{
                    if(num>30)
                    {
                        Toast.makeText(this, "Appointment Full for the day", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        long add = sqLiteDatabase.insert(ClinicConstant.TABLE_NAME, null, contentValues);
                        if (add > 0) {
                            Toast.makeText(this, "data inserted", Toast.LENGTH_SHORT).show();

                            String msg = "your appointment is booked for " + appdate;
                            SmsManager sm = SmsManager.getDefault();
                            Intent i = new Intent(this, Patient.class);
                            PendingIntent pi = PendingIntent.getActivity(this, 2, i, PendingIntent.FLAG_ONE_SHOT);
                            sm.sendTextMessage(pnum, null, msg, pi, null);
                            Toast.makeText(this, "message sent", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }while(cursor.moveToNext());
        }
        else
        {
            long add = sqLiteDatabase.insert(ClinicConstant.TABLE_NAME, null, contentValues);
            if (add > 0) {
                Toast.makeText(this, "data inserted", Toast.LENGTH_SHORT).show();

                String msg = "your appointment is booked for " + appdate;
                SmsManager sm = SmsManager.getDefault();
                Intent i = new Intent(this, Patient.class);
                PendingIntent pi = PendingIntent.getActivity(this, 2, i, PendingIntent.FLAG_ONE_SHOT);
                sm.sendTextMessage(pnum, null, msg, pi, null);
                Toast.makeText(this, "message sent", Toast.LENGTH_SHORT).show();
            }
        }


    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater mf=getMenuInflater();
        mf.inflate(R.menu.patmenu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch(id)
        {

            case R.id.logout:
                onlogout();
                break;
            case R.id.refresh:
                onrefresh();
                break;

        }
        return true;
    }

    private void onrefresh() {
        finish();
        Intent intent=new Intent(this,Patient.class);
        startActivity(intent);
    }


    private void onlogout() {
        finish();
        Intent intent=new Intent(this,LoginModule.class);
        startActivity(intent);
    }

}