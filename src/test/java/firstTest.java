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


public class firstTest {

    static WebDriver driver;
    String textToEnter = "ОТУС";
    private static final Logger logger = LogManager.getLogger(firstTest.class);


    @BeforeAll
    public static void webDriverInstall(){
        WebDriverManager.chromedriver().setup();
    }


    @Test
    public void name(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.get("https://otus.home.kartushin.su/training.html");
        logger.warn("Я тут");
        WebElement textInput = driver.findElement(By.id("textInput"));
        textInput.sendKeys(textToEnter);
        String enteredText = textInput.getAttribute("value");
        System.out.println(enteredText);
    }
    @AfterAll
    public static void close() {
        driver.quit();
        logger.warn("WebDriver закрыт.");
    }
}
