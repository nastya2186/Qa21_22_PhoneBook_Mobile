package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddNewContactsTests extends AppiumConfig {

    @BeforeClass
    public void preCondition(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder()
                        .email("liza19@gmail.com")
                        .password("Maksliza1914#")
                        .build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");

    }

    @Test
    public void createNewContactSuccess(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Simon")
                .lastName("Wow"+i)
                .email("wow"+i+"@gmail.com")
                .phone("77887666"+i)
                .address("NY")
                .description("Friend")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(),contact.getLastName());




    }

    @Test
    public void createNewContactSuccessReq(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Mark")
                .lastName("Wow"+i)
                .email("wow"+i+"@gmail.com")
                .phone("77447666"+i)
                .address("NY")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(),contact.getLastName());

    }

    @Test
    public void createContactWithEmptyName(){
        Contact contact = Contact.builder()

                .lastName("Wow")
                .email("wow@gmail.com")
                .phone("77447666999")
                .address("NY")
                .description("Empty Name")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorContainsText("{name=must not be blank}");

    }


    @AfterClass
    public void postCondition(){
        new ContactListScreen(driver).logout();
    }


}
