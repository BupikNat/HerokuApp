import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class DropDownTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkDropDown() {
        //Открыть страницу и поместить в коллекцию WebElement все options, которые находятся в теге select (весь dropdown)
        driver.get("http://the-internet.herokuapp.com/dropdown");
        WebElement dropDown = driver.findElement(By.id("dropdown"));
        Select select = new Select(dropDown);
        List<WebElement> options = select.getOptions();

        //Проверить что элементы options имеют соответствующие названия
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(options.get(0).getText(), "Please select an option");
        softAssert.assertEquals(options.get(1).getText(), "Option 1");
        softAssert.assertEquals(options.get(2).getText(), "Option 2");

        //Выбираем элемент option1 и проверяем что он выбран
        select.selectByVisibleText("Option 1");
        softAssert.assertTrue(select.getFirstSelectedOption().isSelected());

        select.selectByVisibleText("Option 2");
        softAssert.assertTrue(select.getFirstSelectedOption().isSelected());

        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
