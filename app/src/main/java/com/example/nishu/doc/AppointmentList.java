package com.example.nishu.doc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nishu.doc.dbutil.ClinicConstant;
import com.example.nishu.doc.dbutil.ClinicManager;

import java.util.ArrayList;

public class AppointmentList extends AppCompatActivity {

    ListView simpleList;
    Appointment appointment;
    ArrayList<Appointment> appointList;
    ClinicManager clinicManager;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_list);
        simpleList = (ListView)findViewById(R.id.simpleListView);
        appointList=new ArrayList<>();
        clinicManager=new ClinicManager(this);
        sqLiteDatabase=clinicManager.openDB();
        populatelist();

    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater mf=getMenuInflater();
        mf.inflate(R.menu.clerkmenu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch(id)
        {
            case R.id.addappoint:
                add();
                break;
            case R.id.logout:
                onlogout();
                break;

        }
        return true;
    }

    private void onlogout() {
        finish();
        Intent intent=new Intent(this,ClerkLogin.class);
        startActivity(intent);

    }


    private void add() {
        finish();
        Intent intent=new Intent(this,Patient.class);
        startActivity(intent);
    }

    private void populatelist() {


        StringBuilder sb = new StringBuilder();
        Cursor c = sqLiteDatabase.query(ClinicConstant.TABLE_NAME, null, null, null, null, null, null);

        if (c != null && c.moveToFirst()) {
            do {

                String id = c.getString(c.getColumnIndex(ClinicConstant.COL_ID));
                String name = c.getString(c.getColumnIndex(ClinicConstant.COL_NAME));
                String num = c.getString(c.getColumnIndex(ClinicConstant.COL_NUMBER));
                String gen = c.getString(c.getColumnIndex(ClinicConstant.COL_GENDER));
                String age = c.getString(c.getColumnIndex(ClinicConstant.COL_AGE));
                String dob = c.getString(c.getColumnIndex(ClinicConstant.COL_DOB));
                String email = c.getString(c.getColumnIndex(ClinicConstant.COL_EMAIL));
                String add = c.getString(c.getColumnIndex(ClinicConstant.COL_ADDRESS));
                String prob = c.getString(c.getColumnIndex(ClinicConstant.COL_PROBLEM));
                String treat = c.getString(c.getColumnIndex(ClinicConstant.COL_TREATMENT));
                String date = c.getString(c.getColumnIndex(ClinicConstant.COL_DATE));
                String tok = c.getString(c.getColumnIndex(ClinicConstant.COL_TOKEN));
                appointment=new Appointment(id,name,age,dob,gen,num,email,add,prob,treat,date,tok);
                appointList.add(appointment);
            }
            while (c.moveToNext());


            ArrayAdapter<Appointment> arrayAdapter=new ArrayAdapter<Appointment>(this,android.R.layout.simple_list_item_checked,appointList);
            simpleList.setAdapter(arrayAdapter);
            simpleList.setChoiceMode(simpleList.CHOICE_MODE_SINGLE);
        }


    }

}
