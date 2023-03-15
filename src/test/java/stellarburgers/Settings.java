package stellarburgers;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static java.lang.System.out;
import static java.lang.System.setProperty;
import static java.util.concurrent.TimeUnit.SECONDS;

/*
Выделение общих методов в отдельный наследуемый класс
 */
public class Settings {

    String browser;
    WebDriver driver;
    String accessToken;
    UserMethods userMethods;
    User user;


    public void deleteUser(String accessToken, UserMethods userMethods) {
        if (accessToken != null) {
            ValidatableResponse response = userMethods.deleteUser(accessToken.substring(7));
            if (response.extract().statusCode() == 202) {
                out.println("\nUser is deleted\n");
            } else {
                out.println("\nUser was not be deleted\n");
            }
        }
    }

    public String createTokenUser(User user, UserMethods userMethods) {
        return accessToken = userMethods
                .createUser(user)
                .assertThat()
                .statusCode(200)
                .and()
                .extract()
                .path("accessToken");
    }

    public void checkUrlPage(String url) {
        webdriver().shouldHave(url(url));
    }

     public void initChoose(String browser) {
        if (Objects.equals(browser, "Chrome")) {
            chromedriver().setup();
        }
        if (Objects.equals(browser, "Yandex")) {
            setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
        }

        driver = new ChromeDriver();
        setWebDriver(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, SECONDS);
        driver.manage().timeouts().implicitlyWait(15, SECONDS);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.close();
        }

    }
}

