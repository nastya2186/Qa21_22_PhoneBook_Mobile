package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTest extends AppiumConfig {
@Test
    public void loginSuccess(){
//       boolean result =  new SplashScreen(driver)
//                .checkCurrentVersion("Version 1.0.0")
               boolean result = new AuthenticationScreen(driver)
                       .fillEmail("liza19@gmail.com")
                .fillPassword("Maksliza1914#")
               .submitLogin()
                .isActivityTitleDisplayed("Contact list");
        Assert.assertTrue(result);
    }
@Test
    public void loginSuccessModel(){
//    boolean result = new SplashScreen(driver)
//            .checkCurrentVersion("Version 1.0.0")
    boolean result = new AuthenticationScreen(driver)
            .fillLoginRegistrationForm(Auth.builder()
                    .email("liza19@gmail.com")
                    .password("Maksliza1914#").build())
            .submitLogin()
            .isActivityTitleDisplayed("Contact list");
    Assert.assertTrue(result);

    }


    @Test
    public void loginSuccessModel1(){
 Assert.assertTrue(new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder()
                        .email("liza19@gmail.com")
                        .password("Maksliza1914#").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list"));

    }

    @Test
    public void loginWrongEmail(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder()
                        .email("liza19gmail.com")
                        .password("Maksliza1914#").build())
                .submitLoginNegative()
                .isErrorMessageHasText("Login or Password incorrect");

    }



    @Test
    public void loginWrongPassword(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder()
                        .email("liza19@gmail.com")
                        .password("Maksliza").build())
                .submitLoginNegative()
                .isErrorMessageHasText("Login or Password incorrect");

    }
    @AfterMethod
    public void postCondition(){
    new ContactListScreen(driver).logout();
    }

}
