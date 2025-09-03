package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private WebElement loginLink;

    @FindBy(how = How.XPATH, using = "//h2[text()='Восстановление пароля']")
    private WebElement forgotPasswordHeader;

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик на ссылку 'Войти' на странице восстановления пароля")
    public void clickLoginLink() {
        loginLink.click();
    }

    @Step("Проверка загрузки страницы восстановления пароля")
    public boolean isPageLoaded() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOf(forgotPasswordHeader));
            return driver.getCurrentUrl().contains("/forgot-password");
        } catch (Exception e) {
            return false;
        }
    }
}