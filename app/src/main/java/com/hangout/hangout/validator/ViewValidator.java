package com.hangout.hangout.validator;

import android.content.Context;
import android.text.TextUtils;

import com.hangout.hangout.R;
import com.hangout.hangout.validator.views.TextViewWithValidator;

/**
 * Use this class to validate a {@link TextViewWithValidator} view.
 * <p/>
 * Created by Amin on 13-Jan-16.
 */
public class ViewValidator {

    /**
     * Validates the views passed to this method. Validations are done for all attributes of {@link TextViewWithValidator}.
     * if any of the validations specified not met, an appropriate error message is shown.
     *
     * @param context
     * @param views
     * @return True if all validations pass successfully. False otherwise.
     */
    public static boolean validate(Context context, TextViewWithValidator... views) {
        for (TextViewWithValidator view : views) {
            //Remove the existing errors
            view.setError(null);

            final String text = view.getText().toString();

            //check if maximum number of characters is more than the maximum allowed.
            if (view.getMaxLengthAllowed() < text.length()) {
                view.setError(context.getString(R.string.error_text_too_long));
                return false;
            }
            //check if field is required and it's empty, show an error
            if (view.isRequired() && TextUtils.isEmpty(text)) {
                view.setError(context.getString(R.string.error_field_required));
                return false;
            }
        }
        return true;
    }

}
