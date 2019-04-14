package com.se.team3.parkingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseHelper helper = new DatabaseHelper(this);
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
    }

    public void onButtonClick(View view){
        if(view.getId() == R.id.loginButton){
            EditText username , password;
            username = (EditText)findViewById(R.id.etLoginUsername);
            password = (EditText)findViewById(R.id.etLoginPassword);

            String usernameStr = username.getText().toString();
            userName = usernameStr;
            String passwordStr = password.getText().toString();

            String passwordVar = helper.searchPassword(usernameStr);
            String systemUserRole = helper.getUserRole(userName);
            if (passwordStr.equals(passwordVar)) {
                if (systemUserRole.equalsIgnoreCase("user")) {
                    Intent intent = new Intent(LoginScreen.this,UserHome.class);
                    intent.putExtra("USERNAME",usernameStr);
                    startActivity(intent);
                }
                //change to respective Home screens, currently all are directed to User Home screen
                else if (systemUserRole.equalsIgnoreCase("admin")) {
                    Intent intent = new Intent(LoginScreen.this,UserHome.class);
                    intent.putExtra("USERNAME",usernameStr);
                    startActivity(intent);
                }
                else if (systemUserRole.equalsIgnoreCase("parking manager")) {
                    Intent intent = new Intent(LoginScreen.this,UserHome.class);
                    intent.putExtra("USERNAME",usernameStr);
                    startActivity(intent);
                }

            }
            else {
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
