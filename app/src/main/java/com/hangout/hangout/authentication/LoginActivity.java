package com.hangout.hangout.authentication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.appevents.AppEventsLogger;
import com.hangout.hangout.MyApplication;
import com.hangout.hangout.R;
import com.hangout.hangout.exceptions.Logger;
import com.hangout.hangout.registration.RegistrationActivity;
import com.hangout.textviewwithvalidator.views.TextViewWithValidator;
import com.moamzia.validator.ViewValidator;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * A login screen that offers login via username/password.
 */
public class LoginActivity extends AppCompatActivity {

    private static final Logger LOG = Logger.getLogger(LoginActivity.class, true);

    // UI references.
    private TextViewWithValidator mUsernameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        // Set up the login form.
        mUsernameView = (TextViewWithValidator) findViewById(R.id.username);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin(id);
                    return true;
                }
                return false;
            }
        });

        findViewById(R.id.login_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin(view.getId());
            }
        });

        findViewById(R.id.facebook_login).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void facebookLogin() {
        ParseFacebookUtils.logInWithReadPermissionsInBackground(this, null, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user == null) {
                    LOG.debug("User cancelled facebook login");
                } else if (user.isNew()) {
                    LOG.debug("User is new and just got registered");
                    redirectToRegistration();
                } else {
                    LOG.debug("User is logged in");
                    redirectToMainPage();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (MyApplication.FACEBOOK_LOGIN_REQUEST_CODE == requestCode) {
            ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void redirectToRegistration() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void redirectToMainPage() {
//        Intent intent = new Intent(this, HomePageActivity.class);
//        startActivity(intent);
    }

    /**
     * This method is for Facebook Track App Installs and App Opens
     */
    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    /**
     * This method is for Facebook Track App Deactivates
     */
    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    private void attemptLogin(int id) {
        // Store values at the time of the login attempt.
        final String username = mUsernameView.getText().toString();
        final String password = mPasswordView.getText().toString();

        if (ViewValidator.validate(id)) {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);

            ParseUser.logInInBackground(username, password, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    LOG.debug("error code is: " + e.getCode());
                    if (user == null) {
                        //User does not exist
                        //TODO: show a message
                    } else {
                        LOG.info("User is logged in");
                        redirectToMainPage();
                    }
                    //Have to hide the progress bar
                    showProgress(false);
                }
            });
        }

    }

    private void registerUser(String email, String password) {
        ParseUser user = new ParseUser();
        user.setUsername(email);
        user.setEmail(email);
        user.setPassword(password);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    LOG.debug("user registered manually");
                    //success. TODO: Now we will redirect him to main page.
                } else {
                    if (e.getCode() == ParseException.USERNAME_TAKEN || e.getCode() == ParseException.EMAIL_TAKEN) {
                        mUsernameView.setError(getString(R.string.error_email_exists));
                    } else {
                        LOG.error("Something went wrong in registration of the user", e);
                    }
                }
            }
        });
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}

