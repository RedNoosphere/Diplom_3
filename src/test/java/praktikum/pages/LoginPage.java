package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//input[@name='name']")
    private WebElement emailField;

    @FindBy(how = How.XPATH, using = "//input[@name='Пароль']")
    private WebElement passwordField;

    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    private WebElement loginButton;

    @FindBy(how = How.XPATH, using = "//a[text()='Зарегистрироваться']")
    private WebElement registerLink;

    @FindBy(how = How.XPATH, using = "//a[text()='Восстановить пароль']")
    private WebElement forgotPasswordLink;

    @FindBy(how = How.XPATH, using = "//h2[text()='Вход']")
    private WebElement loginHeader;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод email: {email}")
    public void setEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    @Step("Ввод пароля")
    public void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Клик по ссылке 'Зарегистрироваться'")
    public void clickRegisterLink() {
        registerLink.click();
    }

    @Step("Клик по ссылке 'Восстановить пароль'")
    public void clickForgotPasswordLink() {
        forgotPasswordLink.click();
    }

    @Step("Выполнение входа с email: {email}")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }

    @Step("Проверка загрузки страницы входа")
    public boolean isPageLoaded() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOf(loginHeader));
            return driver.getCurrentUrl().contains("/login") && loginHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}