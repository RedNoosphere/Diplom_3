package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends BasePage {

    // Исправляем локаторы - используем более специфичные селекторы
    @FindBy(how = How.XPATH, using = "//label[text()='Имя']/following-sibling::input")
    private WebElement nameField;

    @FindBy(how = How.XPATH, using = "//label[text()='Email']/following-sibling::input")
    private WebElement emailField;

    @FindBy(how = How.XPATH, using = "//input[@type='password']")
    private WebElement passwordField;

    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private WebElement registerButton;

    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private WebElement loginLink;

    @FindBy(how = How.XPATH, using = "//p[contains(@class, 'input__error')]")
    private WebElement errorMessage;

    @FindBy(how = How.XPATH, using = "//h2[text()='Регистрация']")
    private WebElement registerHeader;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void setName(String name) {
        nameField.clear();
        nameField.sendKeys(name);
    }

    public void setEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickRegisterButton() {
        registerButton.click();
    }

    public void clickLoginLink() {
        loginLink.click();
    }

    public String getErrorMessage() {
        try {
            return errorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    public boolean isPageLoaded() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOf(registerHeader));
            return driver.getCurrentUrl().contains("/register") && registerHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Добавляем метод для отладки - какие значения введены в поля
    public String getNameFieldValue() {
        return nameField.getAttribute("value");
    }

    public String getEmailFieldValue() {
        return emailField.getAttribute("value");
    }

    public String getPasswordFieldValue() {
        return passwordField.getAttribute("value");
    }
}