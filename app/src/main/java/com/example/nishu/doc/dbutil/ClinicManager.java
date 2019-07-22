package com.example.nishu.doc.dbutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.nishu.doc.ClinicHelper;

public class ClinicManager {
    ClinicHelper clinicHelper;
    SQLiteDatabase sqLiteDatabase;
    public  ClinicManager(Context context)
    {
        clinicHelper=new ClinicHelper(context,ClinicConstant.DBNAME,null,ClinicConstant.DBVERSION);
    }
    public SQLiteDatabase openDB()
    {
        sqLiteDatabase=clinicHelper.getWritableDatabase();
        return  sqLiteDatabase;
    }
    public  void closeDB()
    {
        clinicHelper.close();
    }
}