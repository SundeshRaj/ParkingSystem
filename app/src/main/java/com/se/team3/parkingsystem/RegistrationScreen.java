package com.se.team3.parkingsystem;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.se.team3.parkingsystem.R;

public class RegistrationScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHelper helper = new DatabaseHelper(this);
    String userRoleSelected;

    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
    }

    public void onRegistrationButtonClick(View view){
        if(view.getId() == R.id.rsRegisterButton) {
            EditText username = findViewById(R.id.rsUsername);
            EditText password = findViewById(R.id.rsPassword);
            EditText lastname = findViewById(R.id.rsLastName);
            EditText firstname = findViewById(R.id.rsFirstName);
            Spinner userRole = (Spinner)findViewById(R.id.rsUserRoleSpinner);
            EditText utaID = findViewById(R.id.rsUTAID);
            EditText phone = findViewById(R.id.rsSystemUserPhoneGet);
            EditText email = findViewById(R.id.rsSystemUserEmail);
            EditText street = findViewById(R.id.rsSystemUserStreet);
            EditText city = findViewById(R.id.rsSystemUserCity);
            EditText state = findViewById(R.id.rsUserStateGet);
            EditText zip = findViewById(R.id.rsUserZipGet);
            EditText licencePlate = findViewById(R.id.rsLicencePlate);
            EditText permitType = findViewById(R.id.rsParkingPermitGet);

            //get strings from the EDITTEXT forms
            String usernameString = username.getText().toString();
            String passwordString = password.getText().toString();
            String lnameString = lastname.getText().toString();
            String fnameString = firstname.getText().toString();
            String userRoleString = userRole.getSelectedItem().toString();
            String utaIDString = utaID.getText().toString();
            String phoneString = phone.getText().toString();
            String emailString = email.getText().toString();
            String streetString = street.getText().toString();
            String cityString = city.getText().toString();
            String stateString = state.getText().toString();
            String zipString = zip.getText().toString();
            String licenceString = licencePlate.getText().toString();
            String permitTypeString = permitType.getText().toString();

            boolean insertUserDetails = helper.insertUserDetails(usernameString,passwordString,lnameString,fnameString,userRoleString,utaIDString,phoneString,emailString,streetString,cityString,stateString,zipString,licenceString,permitTypeString);
            if (insertUserDetails) {
                Toast toast = Toast.makeText(RegistrationScreen.this , "Registration Successful!!" ,Toast.LENGTH_LONG);
                toast.show();
                Intent intent = new Intent(RegistrationScreen.this,LoginScreen.class);
                startActivity(intent);
            }
            else{
                Toast toast = Toast.makeText(RegistrationScreen.this , "Registration failed!! Please check the form contents" ,Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        userRoleSelected = adapterView.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
