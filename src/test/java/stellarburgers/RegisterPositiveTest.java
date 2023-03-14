package stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static stellarburgers.LoginPage.LOGIN_PAGE_URL;
import static stellarburgers.MainPage.MAIN_PAGE_URL;
import static stellarburgers.RegisterPage.REGISTER_PAGE_URL;
import static stellarburgers.User.getCorrectUser;

@RunWith(Parameterized.class)
public class RegisterPositiveTest extends Settings {

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;

    public RegisterPositiveTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getBrowser() {
        return new Object[][]{{"Chrome"}, {"Yandex"}};
    }

    @Before
    public void setup() {
        super.initChoose(browser);

        mainPage = open(MAIN_PAGE_URL, MainPage.class);

        loginPage = page(LoginPage.class);
        registerPage = page(RegisterPage.class);

    }

    @After
    public void tearDownUser() {
        super.deleteUser(accessToken, userMethods);
    }

    @Step("Get user accessToken")
    public void getUserAccessToken(User user) {
        userMethods = new UserMethods();
        accessToken = userMethods.loginUser(new UserCredentials(user.getPassword(), user.getEmail())).extract().path("accessToken");
    }

    @Test
    @Description("User has been successfully registered")
    public void testRegisterPositive() {

        checkUrlPage(MAIN_PAGE_URL);
        mainPage.checkText();
        mainPage.clickLoginInAccountButton();

        checkUrlPage(LOGIN_PAGE_URL);
        loginPage.checkText();
        loginPage.clickRegisterButton();

        checkUrlPage(REGISTER_PAGE_URL);
        registerPage.checkElementsIsVisible();
        User user = getCorrectUser();
        registerPage.fillRegisterFields(user);
        registerPage.clickRegisterButton();

        checkUrlPage(LOGIN_PAGE_URL);
        loginPage.checkText();
        loginPage.login(user);

        checkUrlPage(MAIN_PAGE_URL);
        mainPage.checkText();
        mainPage.checkPlaceOrderButtonText();


        getUserAccessToken(user); //токен пользователя для последующего удаления
    }
}
