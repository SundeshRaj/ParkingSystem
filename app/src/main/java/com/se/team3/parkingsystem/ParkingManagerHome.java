package com.se.team3.parkingsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ParkingManagerHome extends AppCompatActivity {

    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_manager_home_screen);
        session = new UserSessionManager(getApplicationContext());
    }

    public void pmHomeScreenLogoutButton (View view) {
        if(view.getId() == R.id.pmHomeScreenLogout) {
            session.logoutUser();
        }
    }
}
