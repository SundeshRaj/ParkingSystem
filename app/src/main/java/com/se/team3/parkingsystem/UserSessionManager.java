package com.se.team3.parkingsystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class UserSessionManager {

    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    //Shared Preference file name
    private static final String PREFERENCE_FILE_NAME = "UserSavedPreference";

    //Shared preference keys
    private static final String IS_USER_LOGGEDIN = "IsUserLoggedIn";

    public static final String KEY_NAME = "name";
    public static final String KEY_PASS = "pass";

    public UserSessionManager (Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREFERENCE_FILE_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createUserLoginSession (String name, String pass) {
        editor.putBoolean(IS_USER_LOGGEDIN, true);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_PASS, pass);
        editor.commit();
    }

    public boolean checkUserLoggedIn () {
        if (!this.isUserLoggedIn()) {
            return false;
        }
        return true;
    }

    public boolean checkLogin () {

        if (!this.isUserLoggedIn()) {
            Intent i = new Intent(_context, LoginScreen.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
            return true;
        }
        return false;
    }

    public HashMap<String, String> getUserDetails () {
        HashMap<String, String> user =  new HashMap<>();
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_PASS, pref.getString(KEY_PASS, null));
        return user;
    }

    public void logoutUser () {
        editor.clear();
        editor.commit();

        Intent i = new Intent(_context, LoginScreen.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    public boolean isUserLoggedIn(){
        return pref.getBoolean(IS_USER_LOGGEDIN, false);
    }
}
