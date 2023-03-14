package stellarburgers;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;


import static org.openqa.selenium.support.How.CLASS_NAME;

public class PasswordRecoverPage {

    public final static String PASSWORD_RECOVER_PAGE_URL = "https://stellarburgers.nomoreparties.site/forgot-password";


    //Локатор кнопки "Войти"
    @FindBy(how = CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement loginButton;


    public void clickLoginButton() {
        loginButton.click();
    }
}
