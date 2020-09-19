package Pages;

import Utilities.CommonUtils;
import Utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebOrderLoginPage {
    WebDriver driver = Driver.getDriver(CommonUtils.getProperty("browser"));

    public WebOrderLoginPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "ctl00_MainContent_username")
    public WebElement username;

    @FindBy(id = "ctl00_MainContent_password")
    public WebElement password;

    @FindBy(id = "ctl00_MainContent_login_button")
    public WebElement loginButton;
    @FindBy(id = "ctl00_MainContent_status")
    public WebElement errorMessage;

    public void logIn(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        loginButton.click();
    }
}
