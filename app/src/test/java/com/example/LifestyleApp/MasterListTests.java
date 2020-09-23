package com.example.LifestyleApp;

import android.os.Build;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P) // Value of Build.VERSION_CODES.P is 28
public class MasterListTests {

    private Login login;
    private MasterList masterList;


    @Before
    public void setup() {

      //  LoginTests loginTests = new LoginTests();

        //loginTests.signUpUser();

        masterList = Robolectric.setupActivity(MasterList.class);

    }
//
//    @Test
//    public void homeShouldNotBeNull() throws Exception
//    {
//        assertNotNull( master_list );
//    }
//
////    @Test
////    public void validateBMITextView() {
////
////        TextView bmiTextView = (TextView) master_list.findViewById(R.id.bmiLabelTextView);
////        assertTrue("TextView contains incorrect text",
////                "Hello world!".equals(bmiTextView.getText().toString()));
////    }
}