import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class FirstTest {

    WebDriver driver;
    private String textToEnter = "ОТУС";
    private static final Logger logger = LogManager.getLogger(FirstTest.class);
    private String baseUrl;

    @BeforeAll
    public static void webDriverInstall() {
        WebDriverManager.chromedriver().setup();

    }
    @BeforeEach
    public void webDriverStart() {
        baseUrl = System.getProperty("base.url");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
    }

    @Test
    public void name() {
        driver.get(baseUrl);
        logger.warn("Я тут");
        WebElement textInput = driver.findElement(By.id("textInput"));
        textInput.sendKeys(textToEnter);
        String enteredText = textInput.getAttribute("value");
        System.out.println(enteredText);
    }

    @AfterEach
    public void close() {
        if (driver != null) { // Проверяем driver на null
            driver.quit();
            logger.warn("WebDriver закрыт.");
        } else {
            logger.warn("WebDriver не был создан.");
        }
    }
}