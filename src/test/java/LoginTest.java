import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {


    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(LoginTest.class);
    private String base_url = "https://otus.home.kartushin.su/training.html";

    @BeforeAll
    public static void webDriverInstall() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    public void webDriverStart() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void name() {
        var email = "test@test.ru";
        var name = "tes1";
        driver.get(base_url);
        WebElement textInput = driver.findElement(By.id("name"));
        textInput.sendKeys(name);
        WebElement element = driver.findElement(By.id("email"));
        element.sendKeys(email);
        WebElement button = driver.findElement(By.xpath("//*[@id=\"sampleForm\"]/button"));
        button.click();
        System.out.println(element);
        WebElement updateForm = driver.findElement(By.id("messageBox"));
        String expectedMessage = updateForm.getText();
        String expectedText = "Форма отправлена с именем: " + name + " и email: " + email;
        System.out.println(expectedMessage);
        if (expectedMessage.equals(expectedText)) {
            System.out.println("Текст соответствует ожидаемому.");
        } else {
            System.out.println("Текст не соответствует ожидаемому. Ожидаемый: " + expectedText + ", Фактический: " + expectedMessage);
        }

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