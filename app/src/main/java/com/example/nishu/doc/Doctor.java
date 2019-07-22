package com.example.nishu.doc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nishu.doc.beans.Clerk;
import com.example.nishu.doc.dbutil.ClinicConstant;
import com.example.nishu.doc.dbutil.ClinicManager;

import java.util.ArrayList;
import java.util.Calendar;




public class Doctor extends AppCompatActivity {

    ListView patdetails;
    EditText btn;
    SQLiteDatabase sqLiteDatabase;
    ClinicManager clinicManager;
    Appointment appt;
    DatePickerDialog datePickerDialog;
    ArrayList<Appointment> patlist;
    ArrayList<String> data_list=new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        btn=findViewById(R.id.btn);
        patdetails=findViewById(R.id.patdetails);

        patlist=new ArrayList<>();
        clinicManager=new ClinicManager(this);
        sqLiteDatabase=clinicManager.openDB();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(Doctor.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                btn.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


    }





    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater mf=getMenuInflater();
        mf.inflate(R.menu.mymenu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch(id)
        {
            case R.id.registerck:
                registerclerk();
                break;
            case R.id.viewck:
                viewclerk();
                break;
            case R.id.logout:
                onlogout();
                break;
            case R.id.sms:
                sendsms();
                break;
            case R.id.pat:
                onpat();
                break;

        }
        return true;
    }

    private void sendsms() {


        int position=patdetails.getCheckedItemPosition();
        Appointment clk=patlist.get(position);
        String num=clk.getMobno().trim();
        Toast.makeText(this, ""+num, Toast.LENGTH_SHORT).show();
        String msg="appointment cancelled";
        SmsManager sm = SmsManager.getDefault();
        Intent i = new Intent(this, Doctor.class);
        PendingIntent pi = PendingIntent.getActivity(this, 2, i, PendingIntent.FLAG_ONE_SHOT);
        sm.sendTextMessage(num, null, msg, pi, null);
        Toast.makeText(this, "message sent", Toast.LENGTH_SHORT).show();
    }


    private void onlogout() {
        finish();
        Intent intent=new Intent(this,DoctorLogin.class);
        startActivity(intent);
    }

    private void viewclerk() {
        Intent intent=new Intent(this,ViewClerk.class);
        startActivity(intent);

    }

    private void registerclerk() {
        Intent intent=new Intent(this,RegisterClerk.class);
        startActivity(intent);
    }


    public void appointment(View view) {

        String date=btn.getText().toString();
        String[] args={date};
        Cursor cursor=sqLiteDatabase.query(ClinicConstant.TABLE_NAME,null,ClinicConstant.COL_DATE+" =?",args,null,null,null);
        if(cursor !=null && cursor.moveToNext())
        {

            do{
                String id=cursor.getString(cursor.getColumnIndex(ClinicConstant.COL_ID));
                String nm=cursor.getString(cursor.getColumnIndex(ClinicConstant.COL_NAME));
                String gen=cursor.getString(cursor.getColumnIndex(ClinicConstant.COL_GENDER));
                String age=cursor.getString(cursor.getColumnIndex(ClinicConstant.COL_AGE));
                String dob=cursor.getString(cursor.getColumnIndex(ClinicConstant.COL_DOB));
                String ph=cursor.getString(cursor.getColumnIndex(ClinicConstant.COL_NUMBER));
                String em=cursor.getString(cursor.getColumnIndex(ClinicConstant.COL_EMAIL));
                String ad=cursor.getString(cursor.getColumnIndex(ClinicConstant.COL_ADDRESS));
                String pb=cursor.getString(cursor.getColumnIndex(ClinicConstant.COL_PROBLEM));
                String pt=cursor.getString(cursor.getColumnIndex(ClinicConstant.COL_TREATMENT));
                String dt=cursor.getString(cursor.getColumnIndex(ClinicConstant.COL_DATE));
                String tk=cursor.getString(cursor.getColumnIndex(ClinicConstant.COL_TOKEN));
                appt=new Appointment(id,nm,age,dob,gen,ph,em,ad,pb,pt,dt,tk);
                patlist.add(appt);
                data_list.add(appt.getName());


            }while (cursor.moveToNext());
            ArrayAdapter<String> ad=new ArrayAdapter(this,android.R.layout.simple_list_item_checked,data_list);


            //ArrayAdapter<Appointment> ad=new ArrayAdapter<Appointment>(this,android.R.layout.simple_list_item_checked,data_list);
            patdetails.setAdapter(ad);

            patdetails.setChoiceMode(patdetails.CHOICE_MODE_SINGLE);
        }
        else
        {
            Toast.makeText(this, "no appointment for today", Toast.LENGTH_SHORT).show();
        }
    }


    private void onpat()
    {
        Toast.makeText(this,"here",Toast.LENGTH_LONG).show();

        int id=patdetails.getCheckedItemPosition();

        Appointment clk=patlist.get(id);
        String upid=clk.getRegno();
        Intent intent=new Intent(this,ViewPatient.class);
        intent.putExtra("uid",upid);
        startActivity(intent);
    }
}