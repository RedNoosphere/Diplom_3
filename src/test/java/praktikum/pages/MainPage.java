package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private WebElement loginButton;

    @FindBy(how = How.XPATH, using = "//p[text()='Личный Кабинет']")
    private WebElement personalAccountButton;

    @FindBy(how = How.XPATH, using = "//span[text()='Булки']/..")
    private WebElement bunsSection;

    @FindBy(how = How.XPATH, using = "//span[text()='Соусы']/..")
    private WebElement saucesSection;

    @FindBy(how = How.XPATH, using = "//span[text()='Начинки']/..")
    private WebElement fillingsSection;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'tab_tab_type_current')]")
    private WebElement activeSection;

    @FindBy(how = How.XPATH, using = "//button[contains(text(), 'Оформить заказ')]")
    private WebElement placeOrderButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public LoginPage clickLoginButton() {
        waitUntilClickable(loginButton);
        loginButton.click();
        return new LoginPage(driver);
    }

    @Step("Клик по кнопке 'Личный Кабинет'")
    public LoginPage clickPersonalAccountButton() {
        waitUntilClickable(personalAccountButton);
        personalAccountButton.click();
        return new LoginPage(driver);
    }

    @Step("Клик по разделу 'Булки'")
    public MainPage clickBunsSection() {
        waitUntilClickable(bunsSection);
        bunsSection.click();
        return this;
    }

    @Step("Клик по разделу 'Соусы'")
    public MainPage clickSaucesSection() {
        waitUntilClickable(saucesSection);
        saucesSection.click();
        return this;
    }

    @Step("Клик по разделу 'Начинки'")
    public MainPage clickFillingsSection() {
        waitUntilClickable(fillingsSection);
        fillingsSection.click();
        return this;
    }

    @Step("Ожидание активации раздела: {sectionName}")
    public MainPage waitUntilSectionActive(String sectionName) {
        wait.until(ExpectedConditions.textToBePresentInElement(activeSection, sectionName));
        return this;
    }

    @Step("Получение элемента активного раздела")
    public WebElement getActiveSectionElement() {
        return activeSection;
    }

    @Step("Получение текста активного раздела")
    public String getActiveSectionText() {
        waitUntilVisible(activeSection);
        return activeSection.getText();
    }

    @Step("Ожидание загрузки главной страницы")
    public MainPage waitUntilPageIsLoaded() {
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(loginButton),
                ExpectedConditions.visibilityOf(placeOrderButton)
        ));
        return this;
    }

    @Step("Проверка авторизации пользователя")
    public boolean isUserLoggedIn() {
        try {
            return placeOrderButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверка загрузки главной страницы")
    public boolean isPageLoaded() {
        try {
            waitUntilPageIsLoaded();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверка видимости кнопки 'Оформить заказ'")
    public boolean isPlaceOrderButtonVisible() {
        return isElementVisible(placeOrderButton);
    }

    @Step("Проверка видимости кнопки 'Войти в аккаунт'")
    public boolean isLoginButtonVisible() {
        return isElementVisible(loginButton);
    }

    @Step("Получение текущего URL")
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}