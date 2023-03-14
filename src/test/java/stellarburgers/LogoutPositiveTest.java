package stellarburgers;

import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static stellarburgers.AccountProfilePage.ACCOUNT_PROFILE_PAGE_URL;
import static stellarburgers.LoginPage.LOGIN_PAGE_URL;
import static stellarburgers.MainPage.MAIN_PAGE_URL;
import static stellarburgers.User.getCorrectUser;

@RunWith(Parameterized.class)
public class LogoutPositiveTest extends Settings {

    private MainPage mainPage;
    private LoginPage loginPage;
    private AccountProfilePage accountProfilePage;

    public LogoutPositiveTest(String browser) {
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

        loginPage = open(LOGIN_PAGE_URL, LoginPage.class);
        mainPage = page(MainPage.class);
        accountProfilePage = page(AccountProfilePage.class);

        loginPage.login(user);
    }

    @After
    public void tearDownUser() {
        super.deleteUser(accessToken, userMethods);
    }

    @Test
    @Description("Test of logout on account/profile page")
    public void testTransitionToConstructorPagePositive() {

        checkUrlPage(MAIN_PAGE_URL);
        mainPage.clickPersonalAccountButton();

        checkUrlPage(ACCOUNT_PROFILE_PAGE_URL);
        accountProfilePage.clickLogoutButton();

        checkUrlPage(LOGIN_PAGE_URL);
        loginPage.checkElementsIsVisible();
    }
}
