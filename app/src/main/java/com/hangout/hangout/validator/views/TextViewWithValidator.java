package com.hangout.hangout.validator.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

import com.hangout.hangout.R;

/**
 * Created by Amin on 27-Jan-16.
 */
public class TextViewWithValidator extends AutoCompleteTextView {
    private int maxLengthAllowed;
    private boolean required;

    public TextViewWithValidator(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TextViewWithValidator, 0, 0);
        try {
            maxLengthAllowed = typedArray.getInt(R.styleable.TextViewWithValidator_maxLengthAllowed, 5000);
            required = typedArray.getBoolean(R.styleable.TextViewWithValidator_required, false);
        } finally {
            typedArray.recycle();
        }
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public int getMaxLengthAllowed() {
        return maxLengthAllowed;
    }

    public void setMaxLengthAllowed(int maxLengthAllowed) {
        this.maxLengthAllowed = maxLengthAllowed;
    }
}
