package com.hangout.textviewwithvalidator.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

import com.hangout.textviewwithvalidator.R;
import com.hangout.textviewwithvalidator.rules.FieldRequiredRule;
import com.hangout.textviewwithvalidator.rules.MaxLengthAllowedRule;
import com.moamzia.validator.ViewValidator;

/**
 * This View has a validator that can be used with it. Its attributes are the validation attributes
 * that can be defined on the view XML itself statically.
 * Then {@link ViewValidator} should be used to perform the validation
 * whenever necessary.
 * <p/>
 * Created by Amin on 27-Jan-16.
 */
public class TextViewWithValidator extends AutoCompleteTextView {
    private Integer triggeringViewId;

    public TextViewWithValidator(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TextViewWithValidator, 0, 0);
        try {
            triggeringViewId = typedArray.getResourceId(R.styleable.TextViewWithValidator_triggeringViewId, -1);
            if (triggeringViewId == -1) {
                triggeringViewId = null;
            }

            int maxLength = typedArray.getInt(R.styleable.TextViewWithValidator_maxLengthAllowed, -1);
            int maxLengthErrorMessageRID = typedArray.getResourceId(R.styleable.TextViewWithValidator_maxLengthAllowed_errorMessage, MaxLengthAllowedRule.DEFAULT_ERROR_RESOURCE_ID);
            //Put the validation rule only if it's set in layout.
            if (maxLength > 0) {
                ViewValidator.addViewAndItsValidationRules(this, triggeringViewId, MaxLengthAllowedRule.class, maxLength, maxLengthErrorMessageRID);
            }

            boolean required = typedArray.getBoolean(R.styleable.TextViewWithValidator_required, false);
            int requiredErrorMessageRID = typedArray.getResourceId(R.styleable.TextViewWithValidator_required_errorMessage, FieldRequiredRule.DEFAULT_ERROR_RESOURCE_ID);

            if (required) {
                ViewValidator.addViewAndItsValidationRules(this, triggeringViewId, FieldRequiredRule.class, required, requiredErrorMessageRID);
            }
        } finally {
            typedArray.recycle();
        }
    }

    public void setMaxLengthAllowed(int maxLengthAllowed, Integer errorText) {
        ViewValidator.addViewAndItsValidationRules(this, triggeringViewId, MaxLengthAllowedRule.class, maxLengthAllowed, errorText);
    }
}
