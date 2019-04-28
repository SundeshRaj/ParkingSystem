package com.se.team3.parkingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.HashMap;

public class AdminHome extends AppCompatActivity {

    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_screen);
        session = new UserSessionManager(getApplicationContext());
    }

    public void viewProfileAdmin (View view) {
        DatabaseHelper helper = new DatabaseHelper(this);
        HashMap<String, String> userDetails;
        String user;

        if (view.getId() == R.id.adminviewprofile) {
            session = new UserSessionManager(getApplicationContext());
            if (session.checkUserLoggedIn()) {
                userDetails = session.getUserDetails();
                user = userDetails.get(session.KEY_NAME);
                //pass = userDetails.get(session.KEY_PASS);
                String[] profileDet = helper.profileDetails(user);
                Intent intent = new Intent(AdminHome.this, System_User_Profile.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //UserHome.this.startActivity(intent);
                Bundle bundle = new Bundle();
                bundle.putString("Username", profileDet[0]);
                bundle.putString("Password", profileDet[1]);
                bundle.putString("LastName", profileDet[2]);
                bundle.putString("FirstName", profileDet[3]);
                bundle.putString("UserRole", profileDet[4]);
                bundle.putString("UTAID", profileDet[5]);
                bundle.putString("PhoneNumber", profileDet[6]);
                bundle.putString("EmailID", profileDet[7]);
                bundle.putString("Street", profileDet[8]);
                bundle.putString("City", profileDet[9]);
                bundle.putString("State", profileDet[10]);
                bundle.putString("ZIP", profileDet[11]);
                bundle.putString("LicencePlate", profileDet[12]);
                bundle.putString("ParkingPermitType", profileDet[13]);
                bundle.putString("Status", profileDet[14]);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }

    public void adminHomeScreenLogout (View view) {
        if(view.getId() == R.id.adminHomeScreenLogoutButton) {
            session.logoutUser();
        }
    }
}
