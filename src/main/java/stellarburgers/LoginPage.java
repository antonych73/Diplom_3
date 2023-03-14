package stellarburgers;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.visible;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.support.How.CLASS_NAME;
import static org.openqa.selenium.support.How.XPATH;

public class LoginPage {

    public final static String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    //Локатор поля "Email"
    @FindBy(how = XPATH, using = ".//fieldset[1]/div/div/input")
    private SelenideElement emailField;

    //Локатор поля "Пароль"
    @FindBy(how = XPATH, using = ".//fieldset[2]/div/div/input")
    private SelenideElement passwordField;

    //Локатор текста "Вход"
    @FindBy(how = CLASS_NAME, using = "Auth_login__3hAey")
    private SelenideElement textOnLoginPage;

    //Локатор кнопки "Войти"
    @FindBy(how = CLASS_NAME, using = "button_button__33qZ0")
    private SelenideElement loginButton;

    //Локатор кнопки "Восстановить пароль"
    @FindBy(how = CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement passwordRecoverButton;

    //Локатор кнопки "Зарегестрироваться"
    @FindBy(how = CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement registerButton;




    public void fillEmailField(String email) {
        emailField.click();
        emailField.clear();
        emailField.setValue(email);
    }

    public void fillPasswordField(String password) {
        passwordField.click();
        passwordField.clear();
        passwordField.setValue(password);
    }

    public void fillLoginFields(User user) {
        fillEmailField(user.getEmail());
        fillPasswordField(user.getPassword());
    }

    public void login(User user) {
        fillEmailField(user.getEmail());
        fillPasswordField(user.getPassword());
        loginButton.click();
    }


    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickRegisterButton() {
        registerButton.click();
    }

    public void checkText() {
        assertThat("Text on page is correct",
                textOnLoginPage.getText(),
                startsWith("Вход"));
    }

    public void checkElementsIsVisible() {
        emailField.shouldBe(visible);
        passwordField.shouldBe(visible);
        checkText();
    }
}
