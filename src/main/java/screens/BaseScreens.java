package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BaseScreens {

    AppiumDriver<MobileElement> driver;

    public BaseScreens(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    public void type(MobileElement element,String text){
        if(text!=null){
            element.click();
            element.clear();
            element.sendKeys(text);
        }
        driver.hideKeyboard();
    }

    public boolean isShouldHave(MobileElement element, String text, int time){
        return new WebDriverWait(driver, time)
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public void checkAlertText(String text){
        Alert alert = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains(text));
        alert.accept();

    }

    public void should(MobileElement element, int time){
        new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(element));
    }
}
