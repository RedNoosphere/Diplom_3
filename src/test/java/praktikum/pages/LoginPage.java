package praktikum.pages;

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

    public void setEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickRegisterLink() {
        registerLink.click();
    }

    public void clickForgotPasswordLink() {
        forgotPasswordLink.click();
    }

    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }

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