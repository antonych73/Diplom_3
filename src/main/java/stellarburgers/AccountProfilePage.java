package stellarburgers;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.visible;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.support.How.CSS;
import static org.openqa.selenium.support.How.XPATH;

public class AccountProfilePage {

    public final static String ACCOUNT_PROFILE_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";



    //Локатор кнопки "Профиль"
    @FindBy(how = CSS, using = "a.Account_link__2ETsJ.text.text_type_main-medium.text_color_inactive.Account_link_active__2opc9")
    private SelenideElement profileButton;

    //Локатор кнопки "Конструктор"
    @FindBy(how = CSS, using = "p.AppHeader_header__linkText__3q_va.ml-2")
    private SelenideElement constructorButton;

    //Локатор логотипа StellarBurgers
    @FindBy(how = XPATH, using = ".//div[@class='AppHeader_header__logo__2D0X2']/a")
    private SelenideElement stellarBurgersLogo;

    //Локатор кнопки "История заказов"
    @FindBy(how = CSS, using = "a.Account_link__2ETsJ.text.text_type_main-medium.text_color_inactive")
    private SelenideElement orderHistoryButton;



    //Локатор кнопки "Сохранить"
    @FindBy(how = CSS, using = "button.button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_medium__3zxIa")
    private SelenideElement saveButton;

    //Локатор кнопки "Отмена"
    @FindBy(how = CSS, using = "button.button_button__33qZ0.button_button_type_secondary__3-tsA.button_button_size_medium__3zxIa")
    private SelenideElement cancelButton;

    //Локатор кнопки "Выход"
    @FindBy(how = CSS, using = "button.Account_button__14Yp3.text.text_type_main-medium.text_color_inactive")
    private SelenideElement logoutButton;



    //Локатор поля "Имя"
    @FindBy(how = XPATH, using = ".//li[1]/div/div/input")
    private SelenideElement nameField;

    //Локатор лейбла поля "Email"
    @FindBy(how = XPATH, using = ".//li[2]/div/div/input")
    private SelenideElement emailField;

    //Локатор поля "Пароль"
    @FindBy(how = XPATH, using = ".//li[3]/div/div/input")
    private SelenideElement passwordField;

    public AccountProfilePage() {
    }


    public void clickLogoutButton() {
        logoutButton.shouldBe(visible);
        logoutButton.click();
    }

    public void clickConstructorButton() {
        constructorButton.shouldBe(visible);
        constructorButton.click();
    }

    public void clickStellarBurgersLogo() {
        stellarBurgersLogo.shouldBe(visible);
        stellarBurgersLogo.click();
    }

    public void checkElementsIsVisible() {
        nameField.shouldBe(visible);
        emailField.shouldBe(visible);
        passwordField.shouldBe(visible);

        profileButton.shouldBe(visible);
        orderHistoryButton.shouldBe(visible);
//        logoutButton.shouldBe(visible);

        cancelButton.shouldBe(visible);
        saveButton.shouldBe(visible);
    }


    public void checkAccountCredentialsFieldsValue(User user) {
        assertThat("User name is correct",
                nameField.getValue(),
                containsString(user.getName()));
        assertThat("User email is correct",
                emailField.getValue(),
                containsString((user.getEmail()).toLowerCase()));
    }
}
