import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class AddRemoveElementTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkAddRemove() {
        //Открыть страницу и кликнуть на кнопку "Add Element" 2 раза подряд
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        List<WebElement> buttonDelete = driver.findElements(By.xpath("//button[text()='Delete']"));

        //Проверить что добавилось 2 кнопки "Delete" и нажать на вторую кнопку "Delete" (элемент 1 т.к. начинается с 0)
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(buttonDelete.size(), 2, "Количество кнопок Delete не равно 2");
        buttonDelete.get(1).click();

        //Проверить что вторая кнопка удалилась и на странице осталась всего одна кнопка "Delete"
        List<WebElement> buttonDeleteAfterDelete = driver.findElements(By.xpath("//button[text()='Delete']"));
        softAssert.assertEquals(buttonDeleteAfterDelete.size(), 1, "Количество кнопок Delete не равно 1");
        softAssert.assertAll();
    }

    //Закрыть браузер
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
