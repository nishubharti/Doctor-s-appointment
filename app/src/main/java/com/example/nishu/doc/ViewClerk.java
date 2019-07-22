package com.example.nishu.doc;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
//import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nishu.doc.beans.Clerk;
import com.example.nishu.doc.dbutil.ClinicConstant;
import com.example.nishu.doc.dbutil.ClinicManager;

import java.util.ArrayList;


public class ViewClerk extends AppCompatActivity implements DialogInterface.OnClickListener{
    ArrayList<String> data_list=new ArrayList<String>();

    ListView ckdetails;
    Clerk clk;
    ArrayList<Clerk> clerklist;

    ClinicManager clerkManager;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_clerk);
        ckdetails=(ListView)findViewById(R.id.ckdetails);
        //swipeRefreshLayout=findViewById(R.id.swiperefresh);
     /*   ckdetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ckdetails.setItemChecked(position,true);
            }
        });*/
        clerklist=new ArrayList<>();
        clerkManager=new ClinicManager(this);
        sqLiteDatabase=clerkManager.openDB();
        populatelist();
    }


    private void populatelist() {
        Cursor cursor=sqLiteDatabase.query(ClinicConstant.TABLENAME,null,null,null,null,null,null);
        if(cursor != null && cursor.moveToFirst())
        {
            do{
                String id=cursor.getString(cursor.getColumnIndex(ClinicConstant.ID));
                String nm=cursor.getString(cursor.getColumnIndex((ClinicConstant.NAME)));
                String ph=cursor.getString(cursor.getColumnIndex(ClinicConstant.COL_PHONE));
                String em=cursor.getString(cursor.getColumnIndex(ClinicConstant.EMAIL));
                String ad=cursor.getString(cursor.getColumnIndex(ClinicConstant.ADDRESS));
                String sl=cursor.getString(cursor.getColumnIndex(ClinicConstant.COL_SALARY));
                clk=new Clerk(id,nm,ph,em,ad,sl);
                clerklist.add(clk);
                data_list.add(clk.getName());

            }while(cursor.moveToNext());

            ArrayAdapter<String> ad=new ArrayAdapter(this,android.R.layout.simple_list_item_checked,data_list);

           // ArrayAdapter<Clerk> ad=new ArrayAdapter<Clerk>(this,android.R.layout.simple_list_item_checked,clerklist);
            ckdetails.setAdapter(ad);
            ckdetails.setChoiceMode(ckdetails.CHOICE_MODE_SINGLE);



        }
        /*else{
            Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();
        }*/

    }


    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater mf=getMenuInflater();
        mf.inflate(R.menu.viewmenu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int n=item.getItemId();
        switch(n)
        {
            case R.id.upd:
                updateClerk();
                break;
            case R.id.del:
                deleteClerk();
                break;
            case R.id.call:
                callClerk();
                break;
            case R.id.refresh:
                onRefreshlist();
                break;
            case R.id.sendsms:
                onsendsms();
                break;
            case R.id.view:
                onviewClerk();
                break;
            case R.id.logout:
                onlogout();
                break;


        }
        return true;
    }


    private void onviewClerk()
    {
        Toast.makeText(this,"here",Toast.LENGTH_LONG).show();

        int id=ckdetails.getCheckedItemPosition();

        Clerk clk=clerklist.get(id);
        String upid=clk.getId();
        Intent intent=new Intent(this,ViewData.class);
        intent.putExtra("uid",upid);
        startActivity(intent);

    }


    private void onlogout() {
        finish();
        Intent intent=new Intent(this,DoctorLogin.class);
        startActivity(intent);
    }
    private void onsendsms() {
        int position=ckdetails.getCheckedItemPosition();
        Clerk clk=clerklist.get(position);
        String num=clk.getPhone();
        Intent intent=new Intent(this,Sendsms.class);
        intent.putExtra("contact",num);
        startActivity(intent);
    }

    public void onRefreshlist() {
        finish();
        Intent intent=new Intent(this,ViewClerk.class);
        startActivity(intent);

    }


    private void deleteClerk() {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Record Deletion");
        ad.setMessage("are you sure you want to delete");
        ad.setPositiveButton("yes",this);
        ad.setNegativeButton("No",this);
        AlertDialog adg = ad.create();
        adg.show();


    }
    @Override
    public void onClick(DialogInterface dialog, int i) {
        switch(i)
        {
            case DialogInterface.BUTTON_POSITIVE:
                deleteRecord();
                break;
            case DialogInterface.BUTTON_NEGATIVE:

        }


    }




    private void deleteRecord() {
        int id = ckdetails.getCheckedItemPosition();
        Clerk clk=clerklist.get(id);
        String num=clk.getId();
        String[] args={num};
        int rw=sqLiteDatabase.delete(ClinicConstant.TABLENAME,ClinicConstant.ID+"=?",args);
        if(rw>0)
        {
            Toast.makeText(this, "Record Deleted Successfully", Toast.LENGTH_SHORT).show();

        }


    }

    private void updateClerk() {
        Toast.makeText(this,"here",Toast.LENGTH_LONG).show();

        int id=ckdetails.getCheckedItemPosition();

        Clerk clk=clerklist.get(id);
        String upid=clk.getId();
        Intent intent=new Intent(this,UpdateClerk.class);
        intent.putExtra("uid",upid);
        startActivity(intent);

    }

    private void callClerk() {
        int position = ckdetails.getCheckedItemPosition();
        Clerk clk=clerklist.get(position);
        String num=clk.getPhone();
        Toast.makeText(this, ""+num, Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(Intent.ACTION_CALL);
        Uri u=Uri.parse("tel:"+num);
        intent.setData(u);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        startActivity(intent);
    }

}