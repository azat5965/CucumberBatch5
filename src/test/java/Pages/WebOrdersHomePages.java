package Pages;

import Utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WebOrdersHomePages {
    WebDriver driver = Driver.getDriver("browser");

    public WebOrdersHomePages() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='Process.aspx']")
    public WebElement orderPart;

    @FindBy(xpath = "//a[@href='Default.aspx']")
    public WebElement viewAllOrder;

    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr[2]//td")
    public List<WebElement> firstRowData;
}
