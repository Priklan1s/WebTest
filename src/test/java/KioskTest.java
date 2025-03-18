
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
public class KioskTest {

    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(KioskTest.class);
    private String baseUrl;
    @BeforeAll
    public static void webDriverInstall() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("headless");


    }
    @BeforeEach
    public void webDriverStart() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        baseUrl = System.getProperty("base.url");

    }

    @Test
    public void name(){
        driver.get(baseUrl);
        WebElement textInput = driver.findElement(By.id("openModalBtn"));
        boolean textInputDisplayed = textInput.isDisplayed();
        if (textInputDisplayed){
            logger.warn("Модальное окно отображено");
        }
        else {
            driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(100));
        }
        textInput.click();
        WebElement element = driver.findElement(By.className("modal-content"));
        boolean status = element.isDisplayed();
        logger.warn("Статус отображения определён");
        System.out.println(status);

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