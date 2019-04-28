package com.se.team3.parkingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

@SuppressWarnings("ALL")
public class UpdateProfile extends AppCompatActivity {
    UserHome uh= new UserHome();
    Bundle bundle;
    UserSessionManager session;
    DatabaseHelper helper = new DatabaseHelper(this);
    HashMap<String, String> userDetails;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        session = new UserSessionManager(getApplicationContext());

        bundle = getIntent().getExtras();
        EditText username = (EditText) findViewById(R.id.registrationPageEditText1);
        username.setText(bundle.getString("Username"));

        EditText password1 = (EditText) findViewById(R.id.registrationPageEditText2);
        password1.setText(bundle.getString("Password"));

        EditText LastName = (EditText) findViewById(R.id.registrationPageEditText3);
        LastName.setText(bundle.getString("LastName"));

        EditText FirstName = (EditText) findViewById(R.id.registrationPageEditText4);
        FirstName.setText(bundle.getString("FirstName"));
        EditText utaid = (EditText) findViewById(R.id.registrationPageEditText6);
        utaid.setText(bundle.getString("UTAID"));

        EditText email = (EditText) findViewById(R.id.registrationPageEditText7);
        email.setText(bundle.getString("EmailID"));


        EditText phone = (EditText) findViewById(R.id.registrationPageEditText5);
        phone.setText(bundle.getString("PhoneNumber"));

        EditText street = (EditText) findViewById(R.id.registrationPageEditText8);
        street.setText(bundle.getString("Street"));

        EditText city = (EditText) findViewById(R.id.registrationPageEditText9);
        city.setText(bundle.getString("City"));

        EditText State = (EditText) findViewById(R.id.registrationPageEditText10);
        State.setText(bundle.getString("State"));
    }

    public void updatePro (View view) {
        if (view.getId() == R.id.sysUserUpdateButton) {
            session = new UserSessionManager(getApplicationContext());
            userDetails = session.getUserDetails();
            user = userDetails.get(session.KEY_NAME);
            EditText username = findViewById(R.id.registrationPageEditText1);
            EditText password = findViewById(R.id.registrationPageEditText2);
            EditText lastname = findViewById(R.id.registrationPageEditText3);
            EditText firstname = findViewById(R.id.registrationPageEditText4);
            EditText utaID = findViewById(R.id.registrationPageEditText6);
            EditText phone = findViewById(R.id.registrationPageEditText5);
            EditText email = findViewById(R.id.registrationPageEditText7);
            EditText street = findViewById(R.id.registrationPageEditText8);
            EditText city = findViewById(R.id.registrationPageEditText9);
            EditText state = findViewById(R.id.registrationPageEditText10);


            //get strings from the EDITTEXT forms
            String usernameString = username.getText().toString();
            String passwordString = password.getText().toString();
            String lnameString = lastname.getText().toString();
            String fnameString = firstname.getText().toString();
            String utaIDString = utaID.getText().toString();
            String phoneString = phone.getText().toString();
            String emailString = email.getText().toString();
            String streetString = street.getText().toString();
            String cityString = city.getText().toString();
            String stateString = state.getText().toString();
            boolean uptodate= helper.upDateUserDetails(user,usernameString,passwordString,lnameString,fnameString,utaIDString,phoneString,emailString,streetString,cityString,stateString);
           // String updateQuery = "Update SYSTEMUSERTABLE set "

            if(uptodate) {
                Toast toast = Toast.makeText(UpdateProfile.this, " Updated Successfully", Toast.LENGTH_LONG);
               toast.show();

                Intent intent = new Intent(this, System_User_Profile.class);

                startActivity(intent);
                finish();
            }
            if(!uptodate) {
                Toast toast = Toast.makeText(UpdateProfile.this, " input field cannot be empty", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }

    public void updateProfileLogout (View view) {
        if(view.getId() == R.id.updateProfileLogoutButton) {
            session.logoutUser();
        }
    }


}



