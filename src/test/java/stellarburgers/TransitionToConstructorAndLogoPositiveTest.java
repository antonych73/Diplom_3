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
public class TransitionToConstructorAndLogoPositiveTest extends Settings {

    private MainPage mainPage;
    private AccountProfilePage accountProfilePage;

    public TransitionToConstructorAndLogoPositiveTest(String browser) {
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

        LoginPage loginPage = open(LOGIN_PAGE_URL, LoginPage.class);

        mainPage = page(MainPage.class);
        accountProfilePage = page(AccountProfilePage.class);

        checkUrlPage(LOGIN_PAGE_URL);
        loginPage.login(user);

    }

    @After
    public void tearDownUser() {
        super.deleteUser(accessToken, userMethods);
    }

    @Test
    @Description("Test of transition to constructor from account profile page though 'Constructor' button")
    public void testTransitionToConstructorPagePositive() {

        checkUrlPage(MAIN_PAGE_URL);
        mainPage.clickPersonalAccountButton();

        checkUrlPage(ACCOUNT_PROFILE_PAGE_URL);
        accountProfilePage.checkElementsIsVisible();
        accountProfilePage.clickConstructorButton();

        checkUrlPage(MAIN_PAGE_URL);
    }

    @Test
    @Description("Test of transition to constructor from account profile page though 'Stellar Burgers logo'")
    public void testTransitionToStellarBurgersLogoPositive() {

        checkUrlPage(MAIN_PAGE_URL);
        mainPage.clickPersonalAccountButton();

        checkUrlPage(ACCOUNT_PROFILE_PAGE_URL);
        accountProfilePage.checkElementsIsVisible();
        accountProfilePage.clickStellarBurgersLogo();

        checkUrlPage(MAIN_PAGE_URL);
    }
}
