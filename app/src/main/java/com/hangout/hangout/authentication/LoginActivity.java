package com.hangout.hangout.authentication;

import android.accounts.AccountManager;
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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.api.GoogleApiClient;
import com.hangout.hangout.MyApplication;
import com.hangout.hangout.exceptions.Logger;
import com.hangout.textviewwithvalidator.views.TextViewWithValidator;
import com.moamzia.validator.ViewValidator;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.hangout.hangout.R;
import java.util.Arrays;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private static final Logger LOG = Logger.getLogger(LoginActivity.class, true);

    // UI references.
    private TextViewWithValidator mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private static final int REQUEST_CODE_EMAIL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (TextViewWithValidator) findViewById(R.id.email);

        //This is to suggest email address of the Android user logged in OS
        Intent intent = AccountPicker.newChooseAccountIntent(null, null,
                new String[]{GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE}, false, null, null, null, null);
        startActivityForResult(intent, REQUEST_CODE_EMAIL);

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

        findViewById(R.id.email_sign_in_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin(view.getId());
//                ViewValidator.validate();
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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void facebookLogin() {
        ParseFacebookUtils.logInWithReadPermissionsInBackground(this, null, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user == null) {
                    LOG.debug("User cancelled facebook login");
                } else if (user.isNew()) {
                    LOG.debug("User is registered");
                    //TODO: user is new. So go through the first preference setup
                } else {
                    LOG.debug("User is logged in");
                    //TODO: user is logged in and it's not the first time, so redirect to main page
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_EMAIL && resultCode == RESULT_OK) {
            //This means that the result is coming from AccountPicker. Now we have logged in user's email
            String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);

            addEmailsToAutoComplete(accountName);
        } else if (MyApplication.FACEBOOK_LOGIN_REQUEST_CODE == requestCode) {
            ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
        }
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

    public void redirectToRegister(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    private void attemptLogin(int id) {
        // Reset errors.
//        mEmailView.setError(null);
//        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();

//        boolean cancel = false;
//        View focusView = null;

        // Check for a valid password, if the user entered one.
//        if (TextUtils.isEmpty(password)) {
//            mPasswordView.setError(getString(R.string.error_field_required));
//            focusView = mPasswordView;
//            cancel = true;
//        } else if (!isPasswordValid(password)) {
//            mPasswordView.setError(getString(R.string.error_invalid_password));
//            focusView = mPasswordView;
//            cancel = true;
//        }

        // Check for a valid email address.
//        if (TextUtils.isEmpty(email)) {
//            mEmailView.setError(getString(R.string.error_field_required));
//            focusView = mEmailView;
//            cancel = true;
//        } else if (!isEmailValid(email)) {
//            mEmailView.setError(getString(R.string.error_invalid_email));
//            focusView = mEmailView;
//            cancel = true;
//        }
//
//        if (cancel) {
//            // There was an error; don't attempt login and focus the first
//            // form field with an error.
//            focusView.requestFocus();
        if(ViewValidator.validate(id)) {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);

            ParseUser.logInInBackground(email, password, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    LOG.debug("error code is: " + e.getCode());
                    if (user == null) {
                        //User does not exist
                        //TODO: show a message
                    } else {
                        LOG.info("User is logged in");
                        //TODO: User is loggedin. Redirect to main activity
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
                        mEmailView.setError(getString(R.string.error_email_exists));
                    } else {
                        LOG.error("Something went wrong in registration of the user", e);
                    }
                }
            }
        });
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    /**
     * Password is valid if its length is more than 6
     *
     * @param password
     * @return
     */
    private boolean isPasswordValid(String password) {
        return password.length() > 6;
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

    /**
     * Adds the String passed to ArrayAdapter to add it to the dropdown, so user can select if wanted to.
     *
     * @param emailAddressCollection
     */
    private void addEmailsToAutoComplete(String emailAddressCollection) {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, Arrays.asList(new String[]{emailAddressCollection}));

        mEmailView.setAdapter(adapter);
    }

//    @Override
//    public void onFacebookLogin(AccessToken accessToken) {
//        LOG.debug("FACEBOOK IN DA HOUSE " + accessToken.getUserId());//TODO need to change this
//
////        ParseUser.
//    }
}

