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

public class loginTest {


    static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(loginTest.class);


    @BeforeAll
    public static void webDriverInstall() {
        WebDriverManager.chromedriver().setup();

    }


    @Test
    public void name() {
        var email = "test@test.ru";
        var name = "tes1";
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://otus.home.kartushin.su/training.html");
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

    @AfterAll
    public static void close() {
        driver.quit();
        logger.warn("WebDriver закрыт.");
    }
}