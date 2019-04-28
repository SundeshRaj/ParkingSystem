package com.se.team3.parkingsystem;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

@SuppressWarnings("ALL")
public class ViewNoShow extends AppCompatActivity {

    UserSessionManager session;
    DatabaseHelper helper = new DatabaseHelper(this);
    HashMap<String, String> userDetails;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_noshow_violation);
        session = new UserSessionManager(getApplicationContext());
        userDetails = session.getUserDetails();
        user = userDetails.get(session.KEY_NAME);
        String a[]=helper.profileDetails(user);
        TextView noShow = (TextView)findViewById(R.id.pmRLText2);
        noShow.setText("NO Shows - "+a[14]   );
        TextView violations = (TextView)findViewById(R.id.textView);
        violations.setText("Violations - 0 ");
    }

    public void userNoShowLogout (View view) {
        if(view.getId() == R.id.userNoShowLogoutButton) {
            session.logoutUser();
        }
    }

}
