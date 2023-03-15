package stellarburgers;

import io.qameta.allure.Description;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static com.codeborne.selenide.Selenide.open;
import static stellarburgers.MainPage.MAIN_PAGE_URL;

@RunWith(Parameterized.class)
public class TabsSwitchInConstructorPositiveTest extends Settings {

    private MainPage mainPage;

    public TabsSwitchInConstructorPositiveTest(String browser) {
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
    }


    @Test
    @Description("Test tab Sauce of switch in constructor ")
    public void testConstructorTabsSwitchSaucePositive() {

        checkUrlPage(MAIN_PAGE_URL);
        mainPage.checkText();
        mainPage.clickSauceButton();
        mainPage.checkSauceText();
    }

    @Test
    @Description("Test tab Staff of switch in constructor ")
    public void testConstructorTabsSwitchStaffPositive() {

        checkUrlPage(MAIN_PAGE_URL);
        mainPage.checkText();
        mainPage.clickStaffButton();
        mainPage.checkStufftText();
    }

    @Test
    @Description("Test tab Bun of switch in constructor ")
    public void testConstructorTabsSwitchBunPositive() {

        checkUrlPage(MAIN_PAGE_URL);
        mainPage.checkText();
        mainPage.clickSauceButton();
        mainPage.clickBunButton();
        mainPage.checkBunText();
    }
}
