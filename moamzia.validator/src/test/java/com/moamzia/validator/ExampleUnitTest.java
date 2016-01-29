package com.moamzia.validator;

import android.content.Context;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    @Mock
    Context context;

    @Test
    public void addition_isCorrect() throws Exception {
        TextView myView = new TextView(context);
        ViewValidator.addViewAndItsValidationRules(myView, MyCustomRule.class, 10, R.string.test_error_text);
        assertEquals(4, 2 + 2);

        ViewValidator.addViewAndItsValidationRules(myView, MyCustomRule.class, 25, R.string.test_error_text);
    }

}