package com.se.team3.parkingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        TextView to = (TextView)findViewById(R.id.usToTime);
        to.setText("To Time : "+bundle.getString("toTime"));
        TextView areaName = (TextView)findViewById(R.id.usParkingAreaName);
        areaName.setText("Parking Area Name : "+bundle.getString("parkingAreaName"));
        TextView floor = (TextView)findViewById(R.id.usParkingFloor);
        floor.setText("Parking Floor : "+bundle.getString("parkingFloor"));
        String tFloor = bundle.getString("parkingFloor");
        TextView type = (TextView)findViewById(R.id.usParkingType);
        type.setText("Parking Type : "+bundle.getString("parkingType"));
        int totalSpots = helper.getTotalSpots(areaName.getText().toString(), Integer.valueOf(tFloor), type.getText().toString());
        TextView availableSpots = (TextView)findViewById(R.id.usSpotsAvailableResult);
        availableSpots.setText("Spots Available : " + totalSpots);
    }

    public void userSearchResultLogout (View view) {
        if(view.getId() == R.id.userSearchResultLogoutButton) {
            session.logoutUser();
        }
    }
}
