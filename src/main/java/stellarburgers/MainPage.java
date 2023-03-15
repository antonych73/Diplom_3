package stellarburgers;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;


import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.support.How.CSS;
import static org.openqa.selenium.support.How.XPATH;

public class MainPage {

    public static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";


    //Локатор кнопки "Войти в аккаунт"
    @FindBy(how = CSS, using = "button_button_size_large__G21Vg,.button_button_type_primary__1O7Bx,.button_button__33qZ0")
    private SelenideElement loginInAccountButton;

    //Локатор кнопки "Личный кабинет"
    @FindBy(how = XPATH, using = ".//p [contains(text(),'Личный Кабинет')]")
    private SelenideElement personalAccountButton;



    //Локатор текста "Соберите бургер"
    @FindBy(how = XPATH, using = ".//h1 [contains(text(),'Соберите бургер')]")
    private SelenideElement textGatherBurger;



    //Локатор выбора списка булок
    @FindBy(how = XPATH, using = ".//span [contains(text(),'Булки')]")
    private SelenideElement bunButton;

    //Локатор выбора списка соусов
    @FindBy(how = XPATH, using = ".//span [contains(text(),'Соусы')]")
    private SelenideElement sauceButton;

    //Локатор выбора списка начинок
    @FindBy(how = XPATH, using = ".//span [contains(text(),'Начинки')]")
    private SelenideElement staffButton;

    //Локатор первого элемента из списка ингридиентов
    @FindBy(how = CSS, using = "div.tab_tab__1SPyG.tab_tab_type_current__2BEPc.pt-4.pr-10.pb-4.pl-10.noselect")
    private SelenideElement currentTab;


    public void clickLoginInAccountButton() {
        loginInAccountButton.shouldBe(visible);
        loginInAccountButton.click();
    }

    public void clickPersonalAccountButton() {
        personalAccountButton.shouldBe(visible);
        personalAccountButton.click();
    }

    public void clickBunButton() {
        bunButton.shouldBe(visible);
        bunButton.shouldBe(enabled).click();
    }

     public void clickSauceButton() {
        sauceButton.shouldBe(visible);
        sauceButton.shouldBe(enabled).click();
    }

    public void clickStaffButton() {
        staffButton.shouldBe(visible);
        staffButton.click();
    }

     public void checkText() {
        assertThat("Text on page is correct",
                textGatherBurger.getText(),
                containsString("Соберите бургер"));
    }

    public void checkPlaceOrderButtonText() {
        assertThat("Text on button is correct",
                loginInAccountButton.getText(),
                containsString("Оформить заказ"));
    }

     public void checkBunText() {
        assertThat("Text of tab is correct",
                currentTab.getText(),
                containsString("Булки"));
    }

    public void checkSauceText() {
        assertThat("Text of tab is correct",
                currentTab.getText(),
                containsString("Соусы"));
    }


    public void checkStufftText() {
        assertThat("Text of tab is correct",
                currentTab.getText(),
                containsString("Начинки"));
    }


}
