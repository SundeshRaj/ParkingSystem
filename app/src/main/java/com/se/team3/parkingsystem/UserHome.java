package com.se.team3.parkingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class UserHome extends AppCompatActivity {

    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_home_screen);
        session = new UserSessionManager(getApplicationContext());
    }

    public void userHomeLogoutUser (View view) {
        if(view.getId() == R.id.userHomeLogoutButton) {
            session.logoutUser();
        }
    }

    public void userSearchParking (View view) {
        if (view.getId() == R.id.userSearchParking) {
            Intent intent = new Intent(UserHome.this, UserSearchParking.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
