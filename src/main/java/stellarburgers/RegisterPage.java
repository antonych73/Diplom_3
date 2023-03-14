package stellarburgers;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.visible;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.support.How.CLASS_NAME;
import static org.openqa.selenium.support.How.XPATH;

public class RegisterPage {

    public final static String REGISTER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";


    //Локатор кнопки "Зарегистрироваться"
    @FindBy(how = CLASS_NAME, using = "button_button__33qZ0")
    private SelenideElement registerButton;

    //Локатор кнопки "Войти"
    @FindBy(how = CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement loginButton;

    //Локатор поля "Имя"
    @FindBy(how = XPATH, using = ".//fieldset[1]/div/div/input")
    private SelenideElement nameField;

    //Локатор поля "Email"
    @FindBy(how = XPATH, using = ".//fieldset[2]/div/div/input")
    private SelenideElement emailField;

    //Локатор поля "Пароль"
    @FindBy(how = XPATH, using = ".//fieldset[3]/div/div/input")
    private SelenideElement passwordField;

    //Локатор текста "Регистриция"
    @FindBy(how = CLASS_NAME, using = "Auth_login__3hAey")
    private SelenideElement textOnRegisterPage;

    //Локатор текста Ошибки в поле "Пароль"
    @FindBy(how = CLASS_NAME, using = "input__error")
    private SelenideElement passwordFieldErrorText;


    public void clickRegisterButton() {
        registerButton.click();
    }


    public void clickLoginButton() {
        loginButton.click();
    }

    public void fillNameField(String name) {
        nameField.click();
        nameField.clear();
        nameField.setValue(name);
    }

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

    public void fillRegisterFields(User user) {
        fillNameField(user.getName());
        fillEmailField(user.getEmail());
        fillPasswordField(user.getPassword());
    }


    public void checkElementsIsVisible() {
        emailField.shouldBe(visible);
        nameField.shouldBe(visible);
        passwordField.shouldBe(visible);
        registerButton.shouldBe(visible);
        checkText();
    }

    public void checkText() {
        assertThat("Text on page is correct",
                textOnRegisterPage.getText(),
                startsWith("Регистрация"));
    }

    public void checkPasswordFieldErrorText() {
        assertThat("Error text of password field is correct",
                passwordFieldErrorText.getText(),
                startsWith("Некорректный пароль"));
    }
}
