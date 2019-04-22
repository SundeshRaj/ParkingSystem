package com.se.team3.parkingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class UserSearchParking extends AppCompatActivity {

    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search_parking_input);
        session = new UserSessionManager(getApplicationContext());
    }

    public void userSearchParkingLogout (View view) {
        if(view.getId() == R.id.userSearchParkingLogoutButton) {
            session.logoutUser();
        }
    }

    public void userGetAvailableSpots (View view) {
        if (view.getId() == R.id.userSearchParkingSpot) {
            Intent intent = new Intent(UserSearchParking.this, UserSearchParkingResult.class);
            EditText ft = (EditText)findViewById(R.id.userFromTime);
            String fromTime = ft.getText().toString();
            EditText tt = (EditText)findViewById(R.id.userToTime);
            String toTime = tt.getText().toString();
            EditText areaName = (EditText)findViewById(R.id.userParkingAreaName);
            String parkingAreaName = areaName.getText().toString();
            EditText floor = (EditText)findViewById(R.id.userParkingFloor);
            String parkingFloor = floor.getText().toString();
            EditText type = (EditText)findViewById(R.id.userParkingType);
            String parkingType = type.getText().toString();
            Bundle bundle = new Bundle();
            bundle.putString("fromTime", fromTime);
            bundle.putString("toTime", toTime);
            bundle.putString("parkingAreaName", parkingAreaName);
            bundle.putString("parkingFloor", parkingFloor);
            bundle.putString("parkingType", parkingType);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
