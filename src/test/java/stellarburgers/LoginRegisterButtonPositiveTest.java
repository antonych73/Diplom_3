package stellarburgers;


import io.qameta.allure.Description;
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
public class LoginRegisterButtonPositiveTest extends Settings {


    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;

    public LoginRegisterButtonPositiveTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getBrowser() {
        return new Object[][]{{"Chrome"}, {"Yandex"}};
    }

    @Before
    public void setup() {
        super.initChoose(browser);

        userMethods = new UserMethods();
        user = getCorrectUser();
        accessToken = createTokenUser(user, userMethods);

        loginPage = page(LoginPage.class);
        mainPage = page(MainPage.class);

        registerPage = open(REGISTER_PAGE_URL, RegisterPage.class);
    }

    @After
    public void tearDownUser() {
        super.deleteUser(accessToken, userMethods);
    }

    @Test
    @Description("Test of login through 'Login' button on register page")
    public void testLoginRegisterButtonPositive() {

        checkUrlPage(REGISTER_PAGE_URL);
        registerPage.clickLoginButton();

        checkUrlPage(LOGIN_PAGE_URL);
        loginPage.checkElementsIsVisible();
        loginPage.fillLoginFields(user);
        loginPage.clickLoginButton();

        checkUrlPage(MAIN_PAGE_URL);
        mainPage.checkText();
        mainPage.checkPlaceOrderButtonText();
    }
}
