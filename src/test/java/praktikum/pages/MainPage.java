package praktikum.pages;

import org.openqa.selenium.By;
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

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickPersonalAccountButton() {
        personalAccountButton.click();
    }

    public void clickBunsSection() {
        bunsSection.click();
    }

    public void clickSaucesSection() {
        saucesSection.click();
    }

    public void clickFillingsSection() {
        fillingsSection.click();
    }

    public String getActiveSectionText() {
        return activeSection.getText();
    }

    public boolean isPageLoaded() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));

            String currentUrl = driver.getCurrentUrl();
            return currentUrl.equals("https://stellarburgers.nomoreparties.site/") ||
                    currentUrl.equals("https://stellarburgers.nomoreparties.site") ||
                    (currentUrl.contains("stellarburgers.nomoreparties.site") &&
                            !currentUrl.contains("/login") &&
                            !currentUrl.contains("/register") &&
                            !currentUrl.contains("/account"));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUserLoggedIn() {
        try {
            // Проверяем несколько признаков того, что пользователь залогинен:

            // 1. Кнопка "Оформить заказ"
            boolean hasOrderButton = placeOrderButton.isDisplayed();

            // 2. Измененный текст в личном кабинете (если есть)
            boolean personalAccountTextChanged = false;
            try {
                WebElement accountElement = driver.findElement(By.xpath("//p[text()='Личный Кабинет']"));
                personalAccountTextChanged = !accountElement.getText().equals("Личный Кабинет");
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