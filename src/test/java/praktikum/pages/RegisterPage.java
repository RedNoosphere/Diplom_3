package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends BasePage {

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

    @Step("Ввод имени: {name}")
    public void setName(String name) {
        nameField.clear();
        nameField.sendKeys(name);
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

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickRegisterButton() {
        registerButton.click();
    }

    @Step("Клик по ссылке 'Войти'")
    public void clickLoginLink() {
        loginLink.click();
    }

    @Step("Получение текста ошибки")
    public String getErrorMessage() {
        try {
            return errorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }

    @Step("Регистрация пользователя с именем: {name}, email: {email}")
    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    @Step("Проверка загрузки страницы регистрации")
    public boolean isPageLoaded() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOf(registerHeader));
            return driver.getCurrentUrl().contains("/register") && registerHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Получение значения поля 'Имя'")
    public String getNameFieldValue() {
        return nameField.getAttribute("value");
    }

    @Step("Получение значения поля 'Email'")
    public String getEmailFieldValue() {
        return emailField.getAttribute("value");
    }

    @Step("Получение значения поля 'Пароль'")
    public String getPasswordFieldValue() {
        return passwordField.getAttribute("value");
    }
}