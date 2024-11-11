import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class CheckBoxesTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkCheckboxes() {
        driver.get("http://the-internet.herokuapp.com/checkboxes");

        //Проверить, что чекбокс №1 НЕ выбран
        SoftAssert softAssert = new SoftAssert();
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("[type=checkbox]"));
        softAssert.assertFalse(checkboxes.get(0).isSelected(), "Чекбокс №_1 выбран");

        //Проверить, что чекбокс №1 выбран
        checkboxes.get(0).click();
        softAssert.assertTrue(checkboxes.get(0).isSelected(), "Чекбокс №_1 НЕ выбран");

        //Проверить, что чекбокс №2 выбран
        softAssert.assertTrue(checkboxes.get(1).isSelected(), "Чекбокс №_2 НЕ выбран");

        //Анчекнуть чекбокс №2 и проверить, что он не выбран
        checkboxes.get(1).click();
        softAssert.assertFalse(checkboxes.get(1).isSelected(), "Чекбокс №_1 выбран");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}