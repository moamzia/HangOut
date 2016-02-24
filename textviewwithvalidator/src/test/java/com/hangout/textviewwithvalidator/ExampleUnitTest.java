package com.hangout.textviewwithvalidator;

import com.hangout.textviewwithvalidator.rules.field.type.UsernameFieldType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.junit.Assert.assertTrue;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {

        UsernameFieldType username = new UsernameFieldType();
        assertTrue(username.isValid("sfjkvh-k_"));
    }

}