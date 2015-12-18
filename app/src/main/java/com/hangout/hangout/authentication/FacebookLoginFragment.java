package com.hangout.hangout.authentication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.hangout.hangout.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFacebookLoginListener} interface
 * to handle interaction events.
 */
public class FacebookLoginFragment extends Fragment {
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private OnFacebookLoginListener callBack;
    public FacebookLoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            callBack = (OnFacebookLoginListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " should implement FacebookLoginFragment.OnFacebookLoginListener");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_facebook_login, container, false);

        loginButton = (LoginButton) view.findViewById(R.id.facebook_login);
        loginButton.setReadPermissions("user_friends");
        // If using in a fragment
        loginButton.setFragment(this);
        // Other app specific specialization

        // Callback registration
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                for (String recentlyGrantedPermissions : loginResult.getRecentlyGrantedPermissions()) {
                    Log.d("onCreateView Facebook Fragment", recentlyGrantedPermissions);
                }
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        callBack.onFacebookLogin(AccessToken.getCurrentAccessToken());
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the callBack and potentially other fragments contained in that
     * callBack.
     */
    public interface OnFacebookLoginListener {
        void onFacebookLogin(AccessToken accessToken);
    }
}
