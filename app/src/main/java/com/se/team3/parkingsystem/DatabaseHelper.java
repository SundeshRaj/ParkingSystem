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
    private static final String COL15_Status = "noshows";

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
            "noshows int DEFAULT 0); ";

    private static final String PARKING_TABLE_CREATE = "CREATE TABLE PARKING (ParkingType text not null, ParkingArea text not null, Floor integer, Capacity integer not null, PRIMARY KEY(ParkingType,ParkingArea,Floor));";
    private static final String RESERVATIONS_TABLE_CREATE = "CREATE TABLE RESERVATIONS (Username text not null, ReservationID text not null, ParkingType text not null, ParkingArea text not null, Floor integer, Cart integer, Camera integer, History integer, SpotNumber integer not null,StartTime text not null,EndTime text not null,Date text not null,Fee real not null, PRIMARY KEY(Username,ReservationID,ParkingType,ParkingArea,SpotNumber,StartTime,EndTime),FOREIGN KEY (Username) REFERENCES SYSTEMUSERTABLE(Username),FOREIGN KEY (ParkingType) REFERENCES PARKING(ParkingType),FOREIGN KEY (ParkingArea) REFERENCES PARKING(ParkingArea),FOREIGN KEY (Floor) REFERENCES PARKING(Floor)); ";

    private SQLiteDatabase sqliteDB;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
        sqLiteDatabase.execSQL(PARKING_TABLE_CREATE);
        sqLiteDatabase.execSQL(RESERVATIONS_TABLE_CREATE);
        this.sqliteDB = sqLiteDatabase;
        String AdminInsertQuery2 = "INSERT INTO " + SYSTEM_USER_TABLE_NAME + " VALUES('admin','admin','Admin','Admin','Admin','1001633297','5687651234','admin@mavs.uta.edu','101 Center Street','Arlington','Texas','76010','RCB7714','Basic',0);";
        String AdminInsertQuery3 = "INSERT INTO PARKING VALUES('Basic','Maverick',1,200);";
        String AdminInsertQuery4 = "INSERT INTO PARKING VALUES('Basic','WestGarage',5,250);";
        String AdminInsertQuery5 = "INSERT INTO PARKING VALUES('Midrange','WestGarage',4,750);";
        String AdminInsertQuery6 = "INSERT INTO PARKING VALUES('Midrange','WestGarage',3,750);";
        String AdminInsertQuery7 = "INSERT INTO PARKING VALUES('Midrange','WestGarage',2,750);";
        String AdminInsertQuery8 = "INSERT INTO PARKING VALUES('Premium','WestGarage',1,230);";
        String AdminInsertQuery9 = "INSERT INTO PARKING VALUES('Access','WestGarage',1,20);";
        String AdminInsertQuery10 = "INSERT INTO PARKING VALUES('Access','Maverick',1,20);";
        String AdminInsertQuery11 = "INSERT INTO PARKING VALUES('Basic','Davis',1,150);";
        String AdminInsertQuery12 = "INSERT INTO PARKING VALUES('Access','Davis',1,20);";
        String AdminInsertQuery13 = "INSERT INTO PARKING VALUES('Access','Nedderman',1,20);";
        String AdminInsertQuery14 = "INSERT INTO PARKING VALUES('Basic','Nedderman',1,180);";
        sqLiteDatabase.execSQL(AdminInsertQuery2);
        sqLiteDatabase.execSQL(AdminInsertQuery3);
        sqLiteDatabase.execSQL(AdminInsertQuery4);
        sqLiteDatabase.execSQL(AdminInsertQuery5);
        sqLiteDatabase.execSQL(AdminInsertQuery6);
        sqLiteDatabase.execSQL(AdminInsertQuery7);
        sqLiteDatabase.execSQL(AdminInsertQuery8);
        sqLiteDatabase.execSQL(AdminInsertQuery9);
        sqLiteDatabase.execSQL(AdminInsertQuery10);
        sqLiteDatabase.execSQL(AdminInsertQuery11);
        sqLiteDatabase.execSQL(AdminInsertQuery12);
        sqLiteDatabase.execSQL(AdminInsertQuery13);
        sqLiteDatabase.execSQL(AdminInsertQuery14);
    }


    public StringBuilder login (String username,String password) {
        sqliteDB = this.getReadableDatabase();
        String fetchQuery = "SELECT * FROM SYSTEMUSERTABLE WHERE Username='"+username.toLowerCase()+"' AND Password='"+password.toLowerCase()+"';";
        Cursor curs = sqliteDB.rawQuery(fetchQuery, new String[]{});
        StringBuilder role=new StringBuilder();
        if (curs!=null) {
            if (!(curs.moveToFirst()) || curs.getCount() == 0) {
                role.append("loginerror");
            } else {
                curs.moveToFirst();

                do {
                    String value = curs.getString(4);
                    role.append(value);
                } while (curs.moveToNext());
                curs.close();
            }
        }

        return role;
    }

    public int getTotalSpots(String areaName, Integer floor, String areaType) {
        sqliteDB = this.getReadableDatabase();
        String fetchQuery = "SELECT * FROM PARKING WHERE ParkingArea='" + areaName + "' AND ParkingType='" + areaType + "' AND Floor=" + floor + ";";
        Cursor curs = sqliteDB.rawQuery(fetchQuery, null);
        int capacity = 0;
        if (curs!=null) {
            if (!(curs.moveToFirst()) || curs.getCount() == 0) {
                capacity = 0;
            } else {
                curs.moveToFirst();

                do {
                    int value = curs.getInt(3);
                    capacity = value;
                } while (curs.moveToNext());
                curs.close();
            }
        }
        return capacity;
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
        values.put(COL15_Status, 0);
        long insertResult = sqliteDB.insert(SYSTEM_USER_TABLE_NAME,null,values);
        if (insertResult == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean insertUserReservation (String username, String reservationID, String parkingType, String parkingAreaName, int floor, String cartCheck, String cameraCheck, String historyCheck, int spotNumber, String startTime, String endTime, String localDate, double parkingFee) {
        sqliteDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username", username);
        values.put("ReservationID", reservationID);
        values.put("ParkingType", parkingType);
        values.put("ParkingArea", parkingAreaName);
        values.put("Floor", floor);
        values.put("Cart", cartCheck);
        values.put("Camera", cameraCheck);
        values.put("History", historyCheck);
        values.put("SpotNumber", spotNumber);
        values.put("StartTime", startTime);
        values.put("EndTime", endTime);
        values.put("Date", localDate);
        values.put("Fee", parkingFee);
        long insertResult = sqliteDB.insert("RESERVATIONS",null,values);
        if (insertResult == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean upDateUserDetails(String user,String usernameString,String passwordString,String lnameString,String fnameString,String utaIDString,String phoneString,String emailString,String streetString,String cityString,String stateString){
        sqliteDB = this.getWritableDatabase();
        if((!usernameString.isEmpty())&&(!passwordString.isEmpty())&&(!lnameString.isEmpty())&&(!fnameString.isEmpty())&&(!utaIDString.isEmpty())&&(!phoneString.isEmpty())&&(!emailString.isEmpty())&&(!streetString.isEmpty())&&(!cityString.isEmpty())&&(!stateString.isEmpty())) {
            String upDateQuery1 = "UPDATE SYSTEMUSERTABLE SET FirstName='" + fnameString + "' WHERE Username='" + user.toLowerCase() + "';";
            String upDateQuery2 = "UPDATE SYSTEMUSERTABLE SET Password='" + passwordString + "' WHERE Username='" + user.toLowerCase() + "';";
            String upDateQuery3 = "UPDATE SYSTEMUSERTABLE SET UTAID='" + utaIDString + "' WHERE Username='" + user.toLowerCase() + "';";
            String upDateQuery4 = "UPDATE SYSTEMUSERTABLE SET PhoneNumber='" + phoneString + "' WHERE Username='" + user.toLowerCase() + "';";
            String upDateQuery5 = "UPDATE SYSTEMUSERTABLE SET EmailID='" + emailString + "' WHERE Username='" + user.toLowerCase() + "';";
            String upDateQuery6 = "UPDATE SYSTEMUSERTABLE SET Street='" + streetString + "' WHERE Username='" + user.toLowerCase() + "';";
            String upDateQuery7 = "UPDATE SYSTEMUSERTABLE SET City='" + cityString + "' WHERE Username='" + user.toLowerCase() + "';";
            String upDateQuery8 = "UPDATE SYSTEMUSERTABLE SET State='" + stateString + "' WHERE Username='" + user.toLowerCase() + "';";
            String upDateQuery9 = "UPDATE SYSTEMUSERTABLE SET Username='" + usernameString + "' WHERE Username='" + user.toLowerCase() + "';";
            String upDateQuery = "UPDATE SYSTEMUSERTABLE SET LastName='" + lnameString + "' WHERE Username='" + user.toLowerCase() + "';";
            sqliteDB.execSQL(upDateQuery);
            sqliteDB.execSQL(upDateQuery1);
            sqliteDB.execSQL(upDateQuery2);
            sqliteDB.execSQL(upDateQuery3);
            sqliteDB.execSQL(upDateQuery4);
            sqliteDB.execSQL(upDateQuery5);
            sqliteDB.execSQL(upDateQuery6);
            sqliteDB.execSQL(upDateQuery7);
            sqliteDB.execSQL(upDateQuery8);
            sqliteDB.execSQL(upDateQuery9);
            sqliteDB.close();
            return true;
        }
        else
            return false;
    }

    public String[] profileDetails(String username) {
        sqliteDB = this.getReadableDatabase();
        String fetchQuery = "SELECT * FROM SYSTEMUSERTABLE WHERE Username='" + username.toLowerCase() + "';";
        Cursor curs = sqliteDB.rawQuery(fetchQuery, new String[]{});
        String details[]=new String[20];
        if (curs != null) {

            curs.moveToFirst();

            do {
                details[0] = curs.getString(0);
                details[1] = curs.getString(1);
                details[2] = curs.getString(2);
                details[3]= curs.getString(3);
                details[4] = curs.getString(4);
                details[5] = curs.getString(5);
                details[6] = curs.getString(6);
                details[7] = curs.getString(7);
                details[8] = curs.getString(8);
                details[9] = curs.getString(9);
                details[10] = curs.getString(10);
                details[11] = curs.getString(11);
                details[12] = curs.getString(12);
                details[13] = curs.getString(13);
                details[14]= Integer.toString((curs.getInt(14)));
                //details[15]= Integer.toString((curs.getInt(15)));

            } while (curs.moveToNext());
            curs.close();
        }
        return details;
    }
}
