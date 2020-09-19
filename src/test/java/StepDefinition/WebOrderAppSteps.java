package StepDefinition;

import Pages.OrderPage;
import Pages.WebOrderLoginPage;
import Pages.WebOrdersHomePages;
import Utilities.CommonUtils;
import Utilities.Driver;
import Utilities.ExcelUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WebOrderAppSteps {
    WebDriver driver = Driver.getDriver("chrome");
    WebOrderLoginPage webOrderLoginPage = new WebOrderLoginPage();
    WebOrdersHomePages webOrdersHomePages = new WebOrdersHomePages();
    OrderPage orderPage = new OrderPage();

    @Given("User navigate to WedOrders application")
    public void user_navigate_to_wed_orders_application() {
        driver.get(CommonUtils.getProperty("WebOrderURL"));
    }

    @When("User provides username {string} and password {string}")
    public void user_provides_username_and_password(String username, String password) {
        webOrderLoginPage.logIn(username, password);
    }

    @Then("User validates that application {string} logged in")
    public void user_validates_that_application_logged_in(String condition) {
        if (condition.equalsIgnoreCase("is")) {
            String excpectedTitle = "Web Orders";
            String actualTitle = driver.getTitle();
            Assert.assertEquals("Actual Title: " + actualTitle + "" +
                    " Didn't match  with expected Title: " + excpectedTitle, excpectedTitle, actualTitle);
        } else if (condition.equalsIgnoreCase("is not")) {
            String expectedErrorMessage = "Invalid Login or Password.";
            String actualErrorMessage = webOrderLoginPage.errorMessage.getText();
            Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
        }
    }

    @When("User click on Order part")
    public void user_click_on_order_part() {
        webOrdersHomePages.orderPart.click();
    }

    @When("User adds new order with data")
    public void user_adds_new_order_with_data(DataTable dataTable) {
        List<Map<String, Object>> data = dataTable.asMaps(String.class, Object.class);
        orderPage.quantityBox.clear();
        orderPage.quantityBox.sendKeys(data.get(0).get("Quantity").toString());
        orderPage.customerNameBox.sendKeys(data.get(0).get("Customer name").toString());
        orderPage.streetBox.sendKeys(data.get(0).get("Street").toString());
        orderPage.cityBox.sendKeys(data.get(0).get("City").toString());
        orderPage.stateBox.sendKeys(data.get(0).get("State").toString());
        orderPage.zipBox.sendKeys(data.get(0).get("Zip").toString());
        orderPage.VisaCardBox.click();
        orderPage.cardNumBox.sendKeys(data.get(0).get("Card Nr").toString());
        orderPage.expireDate.sendKeys(data.get(0).get("Expire Date").toString());
    }

    @Then("User click on Process button and validates {string} message")
    public void user_click_on_process_button_and_validates_message(String success) {
        orderPage.processButton.click();
        String actualMessage = orderPage.verifyOrderCreated.getText();
        Assert.assertEquals(success, actualMessage);
    }

    @When("User click View All Orders parts")
    public void user_click_view_all_orders_parts() {
        webOrdersHomePages.viewAllOrder.click();
    }

    @Then("User created order is added to list with data")
    public void user_created_order_is_added_to_list_with_data(DataTable dataTable) {
        List<Map<String, Object>> data = dataTable.asMaps(String.class, Object.class);
        Assert.assertEquals(data.get(0).get("Customer name"), webOrdersHomePages.firstRowData.get(1).getText());
        Assert.assertEquals(data.get(0).get("Quantity"), webOrdersHomePages.firstRowData.get(3).getText());
        Assert.assertEquals(data.get(0).get("Street"), webOrdersHomePages.firstRowData.get(5).getText());
        Assert.assertEquals(data.get(0).get("City"), webOrdersHomePages.firstRowData.get(6).getText());
        Assert.assertEquals(data.get(0).get("State"), webOrdersHomePages.firstRowData.get(7).getText());
        Assert.assertEquals(data.get(0).get("Zip"), webOrdersHomePages.firstRowData.get(8).getText());
        Assert.assertEquals(data.get(0).get("Card Nr"), webOrdersHomePages.firstRowData.get(10).getText());
        Assert.assertEquals(data.get(0).get("Expire Date"), webOrdersHomePages.firstRowData.get(11).getText());
    }

    @Then("User validates headers with {string} excel file expected result")
    public void user_validates_headers_with_excel_file_expected_result(String excelFile) {
        ExcelUtils.openExcelFile(excelFile, "Sheet1");
        String expectedResult = ExcelUtils.getValue(1, 4);
        System.out.println(expectedResult);
        String[] results = expectedResult.split("\n");
        System.out.println(Arrays.toString(results));
        Assert.assertEquals(results[1], orderPage.productLabel.getText());
        Assert.assertEquals(results[2], orderPage.quantityLabel.getText());
        Assert.assertEquals(results[3], orderPage.pricePerUnitLabel.getText());
        Assert.assertEquals(results[4], orderPage.discountLabel.getText());
        Assert.assertEquals(results[5], orderPage.totalLabel.getText());
    }

    @Then("User updates {string} with {string}")
    public void user_updates_with(String string, String string2) {
        ExcelUtils.setValue(1, 6, string2);
    }

    @When("User creates all order from {string} excel file")
    public void user_creates_all_order_from_excel_file(String fileName) throws InterruptedException {
//        ExcelUtils.close();
        int lastRow = ExcelUtils.openExcelFile("TestData2", "Sheet1").getLastRowNum();
        System.out.println(lastRow);

        List<List<String>> excelData = new ArrayList<>();

        for (int i = 1; i < lastRow; i++) {
            List<String> rowData = ExcelUtils.getRowValues(i);
            excelData.add(rowData);

        }
        for (int i = 0; i < excelData.size(); i++) {
            orderPage.quantityBox.clear();
            orderPage.quantityBox.sendKeys(excelData.get(i).get(0));
            orderPage.customerNameBox.sendKeys(excelData.get(i).get(1));
            orderPage.streetBox.sendKeys(excelData.get(1).get(2));
            orderPage.cityBox.sendKeys(excelData.get(i).get(3));
            orderPage.stateBox.sendKeys(excelData.get(i).get(4));
            orderPage.zipBox.sendKeys((excelData.get(1).get(5)).substring(0, excelData.get(i).get(5).indexOf('.')));
            orderPage.VisaCardBox.click();
            orderPage.cardNumBox.sendKeys((excelData.get(1).get(6)).substring(0, excelData.get(i).get(6).indexOf('.')));
            orderPage.expireDate.sendKeys(excelData.get(i).get(7));
            orderPage.processButton.click();
            Thread.sleep(5000);
        }
    }

    @Then("User validates that orders from {string} excel file is created")
    public void user_validates_that_orders_from_excel_file_is_created(String string) {
    }
}