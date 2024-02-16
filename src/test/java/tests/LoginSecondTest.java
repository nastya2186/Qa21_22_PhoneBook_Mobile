package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;

public class LoginSecondTest extends AppiumConfig {

    @Test
    public void loginSuccess(){
        new AuthenticationScreen(driver)
                .fillEmail("liza19@gmail.com")
                .fillPassword("Maksliza1914#")
                .submitLogin()
                .isAccountOpened()
                .logout();
    }


    @Test
    public void loginWrongEmail(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder()
                        .email("liza19@gmail.com")
                        .password("Maksliza1914#").build())
                .submitLoginNegative()
                .isErrorMessageHasText("Login or Password incorrect");

    }

}
