package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotPasswordPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private WebElement loginLink;

    @FindBy(how = How.XPATH, using = "//h2[text()='Восстановление пароля']")
    private WebElement forgotPasswordHeader;

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    // ✅ ИСПРАВЛЕНИЕ: Метод теперь возвращает LoginPage
    @Step("Клик на ссылку 'Войти' на странице восстановления пароля")
    public LoginPage clickLoginLink() {
        waitUntilClickable(loginLink);
        loginLink.click();
        return new LoginPage(driver);
    }

    // ✅ ОПТИМИЗИРУЕМ: Используем wait из BasePage
    @Step("Ожидание загрузки страницы восстановления пароля")
    public ForgotPasswordPage waitUntilPageIsLoaded() {
        waitUntilVisible(forgotPasswordHeader);
        return this;
    }

    // ✅ ОПТИМИЗИРУЕМ: Упрощаем метод проверки
    @Step("Проверка загрузки страницы восстановления пароля")
    public boolean isPageLoaded() {
        return isElementVisible(forgotPasswordHeader) && driver.getCurrentUrl().contains("/forgot-password");
    }
}