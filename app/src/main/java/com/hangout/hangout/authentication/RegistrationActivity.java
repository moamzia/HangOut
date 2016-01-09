package com.hangout.hangout.authentication;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.AccessToken;
import com.hangout.hangout.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

//        ParseUser user = new ParseUser();
//        user.setEmail(email);
//        user.setUsername(email);
//        user.setPassword(password);
//
//        user.signUpInBackground(new SignUpCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null) {
//                    //success
//                } else {
//                    if (e.getCode() == ParseException.USERNAME_TAKEN || e.getCode() == ParseException.EMAIL_TAKEN) {
//                        mEmailView.setError(getString(R.string.error_email_exists));
//                    }
//                }
//            }
//        });
    }
}
