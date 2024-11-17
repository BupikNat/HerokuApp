import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class FileUploadTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkUploadingOfFiles() {
        driver.get("https://the-internet.herokuapp.com/upload");
        //Путь к файлу
        File file = new File("src/test/resources/111");
        //Передать файл в загрузку и нажать на кнопку выбора файла
        //Загрузить файл
        driver.findElement(By.id("file-upload")).sendKeys(file.getAbsolutePath());
        driver.findElement(By.id("file-submit")).click();
        //Проверка что файл занрузился
        String uploadedFile = driver.findElement(By.xpath("//h3[text()='File Uploaded!']")).getText();
        Assert.assertEquals(uploadedFile, "File Uploaded!");
    }

    //Закрыть браузер
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
