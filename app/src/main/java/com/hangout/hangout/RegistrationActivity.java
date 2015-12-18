package com.hangout.hangout;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.facebook.AccessToken;

public class RegistrationActivity extends FragmentActivity implements FacebookLoginFragment.OnFacebookLoginListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    @Override
    public void onFacebookLogin(AccessToken accessToken) {
        Log.d("FACEBOOK IN DA HOUSE", accessToken.getUserId());
    }
}
