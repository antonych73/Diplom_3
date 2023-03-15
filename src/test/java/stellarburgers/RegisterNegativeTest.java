package stellarburgers;


import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static com.codeborne.selenide.Selenide.open;
import static stellarburgers.RegisterPage.REGISTER_PAGE_URL;
import static stellarburgers.User.getWithoutPassword;

@RunWith(Parameterized.class)
public class RegisterNegativeTest extends Settings {

    private RegisterPage registerPage;

    public RegisterNegativeTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getBrowser() {
        return new Object[][]{{"Chrome"}, {"Yandex"}};
    }

    @Before
    @Step("Open register page")
    public void openRegisterPage() {
        super.initChoose(browser);

        user = getWithoutPassword("00000");
        registerPage = open(REGISTER_PAGE_URL, RegisterPage.class);

    }

    @Test
    @Description("Test of login with error in password - negative")
    public void testRegisterNegative() {

        checkUrlPage(REGISTER_PAGE_URL);
        registerPage.checkElementsIsVisible();
        registerPage.fillRegisterFields(user);
        registerPage.clickRegisterButton();
        registerPage.checkPasswordFieldErrorText();

        checkUrlPage(REGISTER_PAGE_URL);   //Cтраница не должна измениться
        registerPage.checkText();
    }
}
