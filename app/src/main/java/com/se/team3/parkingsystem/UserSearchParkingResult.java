package com.se.team3.parkingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Random;

public class UserSearchParkingResult extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_output);
        session = new UserSessionManager(getApplicationContext());
        Bundle bundle = getIntent().getExtras();
        TextView from = (TextView)findViewById(R.id.usFromTime);
        from.setText("From Time : "+bundle.getString("fromTime"));
        String startTime = bundle.getString("fromTime");
        TextView to = (TextView)findViewById(R.id.usToTime);
        to.setText("To Time : "+bundle.getString("toTime"));
        String endTime = bundle.getString("toTime");
        TextView areaName = (TextView)findViewById(R.id.usParkingAreaName);
        areaName.setText("Parking Area Name : "+bundle.getString("parkingAreaName"));
        String area = bundle.getString("parkingAreaName");
        TextView floor = (TextView)findViewById(R.id.usParkingFloor);
        floor.setText("Parking Floor : "+bundle.getString("parkingFloor"));
        String tFloor = bundle.getString("parkingFloor");
        TextView type = (TextView)findViewById(R.id.usParkingType);
        type.setText("Parking Type : "+bundle.getString("parkingType"));
        String pType = bundle.getString("parkingType");
        int totalSpots = helper.getTotalSpots(area, Integer.valueOf(tFloor), pType);
        TextView availableSpots = (TextView)findViewById(R.id.usSpotsAvailableResult);
        availableSpots.setText("Spots Available : " + totalSpots);
    }

    public void userReserveParkingSpot (View view) {
        if (view.getId() == R.id.usReserveParkingSpotButton){
            Intent intent = new Intent(UserSearchParkingResult.this, UserReservationSuccessful.class);
            Bundle bundle = getIntent().getExtras();
            Bundle resResult = new Bundle();
            String randomParkingID = generateRandomParkingID(6);
            String startTime = bundle.getString("fromTime");
            String endTime = bundle.getString("toTime");
            String areaName = bundle.getString("parkingAreaName");
            String floor = bundle.getString("parkingFloor");
            String parkingType = bundle.getString("parkingType");
            int min = 1;
            int totalSpaces = helper.getTotalSpots(areaName, Integer.valueOf(floor), parkingType);
            Random r = new Random();
            int spot = r.nextInt(totalSpaces-min) + min;
            String spotNumber = String.valueOf(spot);
            String parkingFee = "20";
            CheckBox cartCheckBox = (CheckBox)findViewById(R.id.usCartCheckBox);
            CheckBox cameraCheckBox = (CheckBox)findViewById(R.id.usCameraCheckbox);
            CheckBox historyCheckBox = (CheckBox)findViewById(R.id.usHistoryCheckBox);
            String cartCheck = "";
            String cameraCheck = "";
            String historyCheck = "";
            if (cartCheckBox.isChecked()) {
                cartCheck = "1";
            }
            else {
                cartCheck = "0";
            }
            if (cameraCheckBox.isChecked()) {
                cameraCheck = "1";
            }
            else {
                cameraCheck = "0";
            }
            if (historyCheckBox.isChecked()) {
                historyCheck = "1";
            }
            else {
                historyCheck = "0";
            }
            resResult.putString("cartCheck", cartCheck);
            resResult.putString("cameraCheck", cameraCheck);
            resResult.putString("historyCheck", historyCheck);
            resResult.putString("randomParkingID", randomParkingID);
            resResult.putString("startTime", startTime);
            resResult.putString("endTime", endTime);
            resResult.putString("areaName", areaName);
            resResult.putString("floor", floor);
            //get first available spot and then assign this field
            resResult.putString("spotNumber", spotNumber);
            //calculate the fee for the parking and then assign here
            resResult.putString("parkingFee", parkingFee);
            resResult.putString("parkingType", parkingType);
            intent.putExtras(resResult);
            startActivity(intent);
        }
    }

    public void userSearchResultLogout (View view) {
        if(view.getId() == R.id.userSearchResultLogoutButton) {
            session.logoutUser();
        }
    }

    private String generateRandomParkingID (int length) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[rand.nextInt(chars.length)];
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
}
