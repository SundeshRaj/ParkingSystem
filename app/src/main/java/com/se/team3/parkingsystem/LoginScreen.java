package com.se.team3.parkingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class LoginScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseHelper helper = new DatabaseHelper(this);
    String userName;
    HashMap<String, String> userDetails;
    String user;
    String pass;
    UserSessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        session = new UserSessionManager(getApplicationContext());
        if(session.checkUserLoggedIn()) {
            userDetails = session.getUserDetails();
            user = userDetails.get(session.KEY_NAME);
            pass = userDetails.get(session.KEY_PASS);
            StringBuilder userRole = helper.login(user,pass);
            if (userRole.toString().equalsIgnoreCase("User")) {
                session.createUserLoginSession(user,pass);
                Intent intent = new Intent(LoginScreen.this,UserHome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            else if (userRole.toString().equalsIgnoreCase("Admin")) {
                session.createUserLoginSession(user,pass);
                Intent intent = new Intent(LoginScreen.this,AdminHome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            else if (userRole.toString().equalsIgnoreCase("ParkingManager")) {
                session.createUserLoginSession(user,pass);
                Intent intent = new Intent(LoginScreen.this,ParkingManagerHome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }
    }

    public void onButtonClick(View view){
        if(view.getId() == R.id.loginButton){
            EditText username , password;
            username = (EditText)findViewById(R.id.etLoginUsername);
            password = (EditText)findViewById(R.id.etLoginPassword);

            String usernameStr = username.getText().toString();
            userName = usernameStr;
            String passwordStr = password.getText().toString();
            StringBuilder systemUserRole = helper.login(userName,passwordStr);
                if (systemUserRole.toString().equalsIgnoreCase("User")) {
                    session.createUserLoginSession(usernameStr, passwordStr);
                    Intent intent = new Intent(LoginScreen.this,UserHome.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //intent.putExtra("USERNAME",usernameStr);
                    startActivity(intent);
                }
                else if (systemUserRole.toString().equalsIgnoreCase("Admin")) {
                    session.createUserLoginSession(usernameStr, passwordStr);
                    Intent intent = new Intent(LoginScreen.this,AdminHome.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //intent.putExtra("USERNAME",usernameStr);
                    startActivity(intent);
                }
                else if (systemUserRole.toString().equalsIgnoreCase("ParkingManager")) {
                    session.createUserLoginSession(usernameStr, passwordStr);
                    Intent intent = new Intent(LoginScreen.this,ParkingManagerHome.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //intent.putExtra("USERNAME",usernameStr);
                    startActivity(intent);
                }
            else if(systemUserRole.toString().equalsIgnoreCase("loginerror")){
                Toast toast = Toast.makeText(LoginScreen.this , "Incorrect Login, Password or User Role" , Toast.LENGTH_LONG);
                toast.show();
            }
        }

        if(view.getId() == R.id.signUPLink) {
            Intent intent = new Intent(LoginScreen.this, RegistrationScreen.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
