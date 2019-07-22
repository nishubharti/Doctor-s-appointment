package com.example.nishu.doc.dbutil;

public class ClinicConstant  {   public static final String DBNAME="Clinic";
        public static final int DBVERSION=1;
        public static final String TABLE_NAME="patient";
        public static final String COL_ID="reg_no";
        public static final String COL_NAME="patname";
        public static final String COL_NUMBER="patnumber";
        public static final String COL_GENDER="patgen";
        public static final String COL_AGE="patage";
        public static final String COL_DOB="patdob";
        public static final String COL_EMAIL="patemail";
        public static final String COL_ADDRESS="patadd";
        public static final String COL_PROBLEM="patprob";
        public static final String COL_TREATMENT="pattreat";
        public static final String COL_DATE="patdate";
        public static final String COL_TOKEN="pattoken";



        public static final String PAT_SQL="create table patient(reg_no integer primary key,patname text,patnumber long,patgen text,patage integer,patdob date,patemail text,patadd text," +
                "patprob text,pattreat text,patdate date,pattoken integer)";


        public static final String TABLENAME="clerk";
        public static final String ID="clerkid";
        public static final String NAME="clerkname";
        public static final String COL_PHONE="clerkphone";
        public static final String EMAIL="clerkemail";
        public static final String ADDRESS="clerkaddress";
        public static final String COL_SALARY="clerksalary";
        public static final String clerk_SQL="create table clerk(clerkid integer primary key,clerkname text,clerkphone text,clerkemail text,clerkaddress text,clerksalary text)";

}

