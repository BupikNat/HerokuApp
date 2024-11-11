import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class InputsTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkInput() {
        driver.get("http://the-internet.herokuapp.com/inputs");

        WebElement input = driver.findElement(By.tagName("input"));

        //Нажать на стрелку "вверх"
        input.click();
        new Actions(driver).keyDown(Keys.ARROW_UP).perform();

        //Проверить, что в поле появилось число 1
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(input.getAttribute("value"), "1", "Некорректное число, ожидаемое число - 1");

        //Нажать на стрелку "вниз" и проверить, что 1 поменялось на 0
        input.click();
        new Actions(driver).keyDown(Keys.ARROW_DOWN).perform();
        softAssert.assertEquals(input.getAttribute("value"), "0", "Некорректное число, ожидаемое число - 0");

        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}