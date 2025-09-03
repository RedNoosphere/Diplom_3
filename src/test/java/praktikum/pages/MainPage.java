package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Клик по кнопке 'Личный Кабинет'")
    public void clickPersonalAccountButton() {
        personalAccountButton.click();
    }

    @Step("Клик по разделу 'Булки'")
    public void clickBunsSection() {
        bunsSection.click();
    }

    @Step("Клик по разделу 'Соусы'")
    public void clickSaucesSection() {
        saucesSection.click();
    }

    @Step("Клик по разделу 'Начинки'")
    public void clickFillingsSection() {
        fillingsSection.click();
    }

    @Step("Получение текста активного раздела")
    public String getActiveSectionText() {
        return activeSection.getText();
    }

    @Step("Получение элемента активного раздела")
    public WebElement getActiveSectionElement() {
        return activeSection;
    }

    @Step("Проверка загрузки главной страницы")
    public boolean isPageLoaded() {
        try {
            // Ждем появления либо кнопки входа (для неавторизованного пользователя),
            // либо кнопки оформления заказа (для авторизованного)
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.or(
                            ExpectedConditions.visibilityOf(loginButton),
                            ExpectedConditions.visibilityOf(placeOrderButton)
                    ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверка авторизации пользователя")
    public boolean isUserLoggedIn() {
        try {
            // Проверяем несколько признаков того, что пользователь залогинен:

            // 1. Кнопка "Оформить заказ"
            boolean hasOrderButton = placeOrderButton.isDisplayed();

            // 2. Измененный текст в личном кабинете (если есть)
            boolean personalAccountTextChanged = false;
            try {
                personalAccountTextChanged = !personalAccountButton.getText().equals("Личный Кабинет");
            } catch (Exception e) {
                // Игнорируем, если элемент не найден
            }

            // 3. URL может содержать признак авторизации
            String currentUrl = driver.getCurrentUrl();
            boolean isOnAuthorizedPage = !currentUrl.contains("/login") &&
                    !currentUrl.contains("/register");

            return hasOrderButton || personalAccountTextChanged || isOnAuthorizedPage;
        } catch (Exception e) {
            return false;
        }
    }
}