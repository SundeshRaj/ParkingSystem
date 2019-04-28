package com.se.team3.parkingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

@SuppressWarnings("ALL")
public class System_User_Profile extends AppCompatActivity {
    Bundle bundle;
    HashMap<String, String> userDetails;
    DatabaseHelper helper = new DatabaseHelper(this);
    String user;
    UserSessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_user_profile);
        session = new UserSessionManager(getApplicationContext());
        userDetails = session.getUserDetails();
        user = userDetails.get(session.KEY_NAME);
        String[] profileDet = helper.profileDetails(user);
         //bundle = getIntent().getExtras();
        bundle = new Bundle();
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

        TextView username = (TextView)findViewById(R.id.pmParkingSpotDetailsText);
        username.setText("Username : "+bundle.getString("Username"));

        TextView password1 = (TextView)findViewById(R.id.pmParkingSpotDetailsText33);
        password1.setText(bundle.getString("Password"));

        TextView LastName = (TextView)findViewById(R.id.pmParkingSpotDetailsFName);
        LastName.setText("LastName : "+bundle.getString("LastName"));
        TextView utaid = (TextView)findViewById(R.id.pmParkingSpotDetailsText0);
        utaid.setText("UTAID : "+bundle.getString("UTAID"));

        TextView FirstName = (TextView)findViewById(R.id.pmParkingSpotDetailsLName);
        FirstName.setText("FirstName : "+bundle.getString("FirstName"));

        TextView email = (TextView)findViewById(R.id.pmParkingSpotDetailsText1);
        email.setText("Email : "+bundle.getString("EmailID"));

        TextView role = (TextView)findViewById(R.id.pmParkingSpotDetailsText2);
        role.setText("Role : "+bundle.getString("UserRole"));
        if(bundle.getString("UserRole").equalsIgnoreCase("User"))
        {
            TextView licencePlate = (TextView)findViewById(R.id.userLicencePlate);
            licencePlate.setText("Licence Plate : "+bundle.getString("LicencePlate"));
        }
        TextView phone = (TextView)findViewById(R.id.pmParkingSpotDetailsText4);
        phone.setText("Phone : "+bundle.getString("PhoneNumber"));

        TextView street = (TextView)findViewById(R.id.pmParkingSpotDetailsText3);
        street.setText("Street : "+bundle.getString("Street"));

        TextView city = (TextView)findViewById(R.id.pmParkingSpotDetailsText5);
        city.setText("City : "+bundle.getString("City"));

        TextView State = (TextView)findViewById(R.id.pmParkingSpotDetailsText6);
        State.setText("State : "+bundle.getString("State"));

    }

    public void updateProfile (View view) {
        if (view.getId() == R.id.userUpdateProfileButton) {
            Intent intent = new Intent(System_User_Profile.this, UpdateProfile.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    public void userProfileScreenLogout (View view) {
        if(view.getId() == R.id.userProfileScreenLogoutButton) {
            session.logoutUser();
        }
    }
}


