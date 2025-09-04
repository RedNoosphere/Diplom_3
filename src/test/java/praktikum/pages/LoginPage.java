package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

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
    public LoginPage setEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
        return this;
    }

    @Step("Ввод пароля")
    public LoginPage setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    @Step("Клик по кнопке 'Войти'")
    public MainPage clickLoginButton() {
        waitUntilClickable(loginButton);
        loginButton.click();
        return new MainPage(driver).waitUntilPageIsLoaded();
    }

    @Step("Клик по ссылке 'Зарегистрироваться'")
    public RegisterPage clickRegisterLink() {
        waitUntilClickable(registerLink);
        registerLink.click();
        return new RegisterPage(driver);
    }

    @Step("Клик по ссылке 'Восстановить пароль'")
    public ForgotPasswordPage clickForgotPasswordLink() {
        waitUntilClickable(forgotPasswordLink);
        forgotPasswordLink.click();
        return new ForgotPasswordPage(driver);
    }

    @Step("Выполнение входа с email: {email} и переход на главную страницу")
    public MainPage login(String email, String password) {
        setEmail(email);
        setPassword(password);
        return clickLoginButton();
    }

    @Step("Ожидание загрузки страницы входа")
    public LoginPage waitUntilPageIsLoaded() {
        waitUntilVisible(loginHeader);
        return this;
    }

    @Step("Проверка загрузки страницы входа")
    public boolean isPageLoaded() {
        return isElementVisible(loginHeader) && driver.getCurrentUrl().contains("/login");
    }

    @Step("Получение текущего URL")
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}