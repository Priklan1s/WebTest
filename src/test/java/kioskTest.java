
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class kioskTest {

    static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(kioskTest.class);

    @BeforeAll
    public static void webDriverInstall(){
        WebDriverManager.chromedriver().setup();

    }


    @Test
    public void name(){
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("headless");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://otus.home.kartushin.su/training.html");
        WebElement textInput = driver.findElement(By.id("openModalBtn"));
        textInput.click();
        WebElement element = driver.findElement(By.className("modal-content"));
        boolean status = element.isDisplayed();
        logger.warn("Статус отображения определён");
        System.out.println(status);

    }
    @AfterAll
    public static void close() {
        driver.quit();
        logger.warn("WebDriver закрыт.");
    }
}