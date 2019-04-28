package com.se.team3.parkingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class UserReservationSuccessful extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reservation_successful);
        session = new UserSessionManager(getApplicationContext());

        Bundle results = getIntent().getExtras();
        TextView reservationID = (TextView)findViewById(R.id.usReservedID);
        reservationID.setText("Reservation ID : " + results.getString("randomParkingID"));
        TextView startTime = (TextView)findViewById(R.id.usReservedStartTime);
        startTime.setText("Start Time : " + results.getString("startTime"));
        TextView endTime = (TextView)findViewById(R.id.usReservedEndTime);
        endTime.setText("End Time : " + results.getString("endTime"));
        TextView parkingArea = (TextView)findViewById(R.id.usReservedParkingArea);
        parkingArea.setText("Parking Area : " +results.getString("areaName"));
        TextView floor = (TextView)findViewById(R.id.usReservedAreaFloor);
        floor.setText("Floor : " +results.getString("floor"));
        TextView spotNumber = (TextView)findViewById(R.id.usReservedSpotNumber);
        spotNumber.setText("Spot Number : " +results.getString("spotNumber"));

        String start = results.getString("startTime").substring(0,5);
        String end = results.getString("endTime").substring(0,5);

        String startHours = start.substring(0,2);
        int stHours = Integer.valueOf(startHours);

        String startMins = start.substring(3,5);
        int stMins = Integer.valueOf(startMins);
        int from = Integer.valueOf(startHours+startMins);

        String endHours = end.substring(0,2);
        int eHours = Integer.valueOf(endHours);

        String endMins = end.substring(3,5);
        int eMins = Integer.valueOf(endMins);
        int to = Integer.valueOf(endHours+endMins);

        int final_val = getTotalTime(from, to);

        double totalParkingFee = getTotalParkingFee(final_val, results.getString("parkingType"), results.getString("cartCheck"), results.getString("cameraCheck"), results.getString("historyCheck"));

        TextView parkingFee = (TextView)findViewById(R.id.usReservedSpotFee);
        parkingFee.setText("Parking Fee : $" +totalParkingFee);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        String currentDate = localDate.toString();

        HashMap<String, String> user = session.getUserDetails();
        String username = user.get("name");

        boolean success = helper.insertUserReservation(username, results.getString("randomParkingID"), results.getString("parkingType"), results.getString("areaName"), Integer.valueOf(results.getString("floor")), results.getString("cartCheck"), results.getString("cameraCheck"), results.getString("historyCheck"), Integer.valueOf(results.getString("spotNumber")), results.getString("startTime"), results.getString("endTime"), currentDate, totalParkingFee);

        if (success) {
            Toast toast = Toast.makeText(UserReservationSuccessful.this , "Successfully Reserved!!!" ,Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            Intent i = new Intent(UserReservationSuccessful.this, UserSearchParkingResult.class);
            Toast toast = Toast.makeText(UserReservationSuccessful.this , "Reservation Failed. Try Again!!!" ,Toast.LENGTH_LONG);
            toast.show();
            startActivity(i);
        }
    }

    public void userReservationSuccessfulLogout (View view) {
        if (view.getId() == R.id.usReservationSuccessfulLogoutButton) {
            session.logoutUser();
        }
    }

    public int getTotalTime (int start, int end) {

        if (start > end) {
            return start-end-40;
        }
        else {
            return end-start-40;
        }
    }

    public double getTotalParkingFee (int mins, String parkingType, String cartCheck, String cameraCheck, String historyCheck) {
        double totalFee = 0;
        double cart = 0;
        double camera = 0;
        double history = 0;
        double basicFee = 0;
        double premiumFee = 0;
        double accessFee = 0;
        double midRangeFee = 0;

        if (cartCheck.equalsIgnoreCase("1")) {
            cart = 15.95;
        }
        if (cameraCheck.equalsIgnoreCase("1")) {
            camera = 2.95;
        }
        if (historyCheck.equalsIgnoreCase("1")) {
            history = 1.95;
        }

        if (parkingType.equalsIgnoreCase("Basic")) {
            basicFee = mins*0.3;
            totalFee = basicFee+cart+camera+history;
        }
        else if (parkingType.equalsIgnoreCase("Access")) {
            accessFee = mins*0.4;
            totalFee = accessFee+camera+cart+history;
        }
        else if (parkingType.equalsIgnoreCase("Midrange")) {
            midRangeFee = mins*0.5;
            totalFee = midRangeFee+cart+camera+history;
        }
        else if (parkingType.equalsIgnoreCase("Premium")) {
            premiumFee = mins*0.7;
            totalFee = premiumFee+cart+camera+history;
        }
        return totalFee;
    }
}
