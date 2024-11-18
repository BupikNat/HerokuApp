import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FramesTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkFrame() {
        driver.get("https://the-internet.herokuapp.com/frames");
        //Нажать на ссылку iFrame
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/ul/li[2]/a")).click();
        //Закрыть всплывающее окно, нажать на "крестик" - возможно лишний шаг
        driver.findElement(By.xpath("/html/body/div[4]/div/div/button")).click();
        //переключиться на iframe
        driver.switchTo().frame(driver.findElement(By.id("mce_0_ifr")));
        //Проверка текста внутри ifram
        WebElement frame = driver.findElement(By.xpath("//p[text()='Your content goes here.']"));
        String frameText = frame.getText();
        Assert.assertEquals(frameText, "Your content goes here.");
        driver.switchTo().defaultContent();
    }

    //Закрыть браузер
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
