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
import static stellarburgers.User.getCorrectUser;

@RunWith(Parameterized.class)
public class LoginAccountLoginButtonPositiveTest extends Settings {

    private MainPage mainPage;
    private LoginPage loginPage;

    public LoginAccountLoginButtonPositiveTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getBrowser() {
        return new Object[][]{{"Chrome"}, {"Yandex"}};
    }

    @Before
    public void setUp() {
        super.initChoose(browser);

        userMethods = new UserMethods();
        user = getCorrectUser();
        accessToken = createTokenUser(user, userMethods);

        loginPage = page(LoginPage.class);

        mainPage = open(MAIN_PAGE_URL, MainPage.class);

    }

    @After
    public void tearDownUser() {
        super.deleteUser(accessToken, userMethods);
    }


    @Test
    @Description("Test of login through 'Login account' button on main page")
    public void testLoginAccountLoginButtonPositive() {

        checkUrlPage(MAIN_PAGE_URL);
        mainPage.checkText();
        mainPage.clickLoginInAccountButton();

        checkUrlPage(LOGIN_PAGE_URL);
        loginPage.checkElementsIsVisible();
        loginPage.fillLoginFields(user);
        loginPage.clickLoginButton();

        checkUrlPage(MAIN_PAGE_URL);
        mainPage.checkText();
        mainPage.checkPlaceOrderButtonText();
    }
}
