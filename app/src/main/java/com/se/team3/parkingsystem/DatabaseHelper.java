package com.se.team3.parkingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.widget.Spinner;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "RANDOMTABLE";
    private static final int DATABASE_VERSION = 1;
    private static final String DB_NAME = "UTAParkingSystemDatabase.db";
    private static final String SYSTEM_USER_TABLE_NAME = "SYSTEMUSERTABLE";
    private static final String COL1_UserName = "Username";
    private static final String COL2_Password = "Password";
    private static final String COL3_LastName = "LastName";
    private static final String COL4_FirstName = "FirstName";
    private static final String COL5_UserRole = "UserRole";
    private static final String COL6_UTAID = "UTAID";
    private static final String COL7_PhoneNumber = "PhoneNumber";
    private static final String COL8_EmailID = "EmailID";
    private static final String COL9_Street = "Street";
    private static final String COL10_City = "City";
    private static final String COL11_State = "State";
    private static final String COL12_ZIP = "ZIP";
    private static final String COL13_LicencePlate = "LicencePlate";
    private static final String COL14_ParkingPermitType = "ParkingPermitType";
    private static final String COL15_Status = "Status";

    private static final String TABLE_CREATE = "CREATE TABLE SYSTEMUSERTABLE (Username text not null primary key, " +
            "Password text not null, "+
            "LastName text not null, "+
            "FirstName text not null, "+
            "UserRole text not null, "+
            "UTAID text not null, "+
            "PhoneNumber text not null, "+
            "EmailID text not null, "+
            "Street text , "+
            "City text , "+
            "State text , "+
            "ZIP text , "+
            "LicencePlate text , "+
            "ParkingPermitType text , "+
            "Status text DEFAULT null); ";

    private SQLiteDatabase sqliteDB;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
        //sqLiteDatabase.execSQL(EVENTREQUESTS_CREATE);
        this.sqliteDB = sqLiteDatabase;
        //String AdminInsertQuery = "INSERT INTO " + TABLE_NAME + " VALUES('jm123','james','Admin','james@gm.com','9087653456','jm123','Approved');";
        String AdminInsertQuery2 = "INSERT INTO " + SYSTEM_USER_TABLE_NAME + " VALUES('admin','admin','Admin','Admin','Admin','1001633297','5687651234','admin@mavs.uta.edu','101 Center Street','Arlington','Texas','76010','RCB7714','Basic','Approved');";
        //sqLiteDatabase.execSQL(AdminInsertQuery);
        sqLiteDatabase.execSQL(AdminInsertQuery2);

    }

    public String searchPassword(String username) {
        sqliteDB = this.getReadableDatabase();
        String fetchQuery = "SELECT Password FROM SYSTEMUSERTABLE WHERE Username='"+username.toLowerCase()+"';";
        System.out.println("The username : "+username);
        System.out.println("The fetch query : "+fetchQuery);
        Cursor curs = sqliteDB.rawQuery(fetchQuery, null);
        String passwordCheck ;
        passwordCheck = curs.getString(0);
        System.out.println("Sundesh Password returned : "+passwordCheck);
        return passwordCheck;
    }

    public String getUserRole (String username) {
        return "string";
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String checkQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(checkQuery);
        checkQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(checkQuery);
        this.onCreate(sqLiteDatabase);
    }

    public boolean insertUserDetails(String username, String password, String lastname, String firstname, String userRole, String utaID, String phone, String email, String street, String city, String state, String zip, String licencePlate, String permitType) {
        sqliteDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL1_UserName, username);
        values.put(COL2_Password, password);
        values.put(COL3_LastName, lastname);
        values.put(COL4_FirstName, firstname);
        values.put(COL5_UserRole, userRole);
        values.put(COL6_UTAID, utaID);
        values.put(COL7_PhoneNumber, phone);
        values.put(COL8_EmailID, email);
        values.put(COL9_Street, street);
        values.put(COL10_City, city);
        values.put(COL11_State, state);
        values.put(COL12_ZIP, zip);
        values.put(COL13_LicencePlate, licencePlate);
        values.put(COL14_ParkingPermitType, permitType);
        values.put(COL15_Status, "");
        long insertResult = sqliteDB.insert(SYSTEM_USER_TABLE_NAME,null,values);
        if (insertResult == -1) {
            return false;
        }
        else {
            return true;
        }
    }
}