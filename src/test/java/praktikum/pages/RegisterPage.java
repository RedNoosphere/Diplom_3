package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

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
    public RegisterPage setName(String name) {
        waitUntilVisible(nameField);
        nameField.clear();
        nameField.sendKeys(name);
        return this;
    }

    @Step("Ввод email: {email}")
    public RegisterPage setEmail(String email) {
        waitUntilVisible(emailField);
        emailField.clear();
        emailField.sendKeys(email);
        return this;
    }

    @Step("Ввод пароля")
    public RegisterPage setPassword(String password) {
        waitUntilVisible(passwordField);
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public LoginPage clickRegisterButton() {
        waitUntilClickable(registerButton);
        registerButton.click();
        return new LoginPage(driver).waitUntilPageIsLoaded();
    }

    @Step("Клик по кнопке 'Зарегистрироваться' без перехода")
    public RegisterPage clickRegisterButtonStay() {
        waitUntilClickable(registerButton);
        registerButton.click();
        return this;
    }

    @Step("Клик по ссылке 'Войти'")
    public LoginPage clickLoginLink() {
        waitUntilClickable(loginLink);
        loginLink.click();
        return new LoginPage(driver).waitUntilPageIsLoaded();
    }

    @Step("Получение текста ошибки")
    public String getErrorMessage() {
        try {
            waitUntilVisible(errorMessage);
            return errorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }

    @Step("Проверка отображения ошибки")
    public boolean isErrorMessageDisplayed() {
        return isElementVisible(errorMessage);
    }

    @Step("Ожидание появления сообщения об ошибке")
    public RegisterPage waitUntilErrorMessageVisible() {
        waitUntilVisible(errorMessage);
        return this;
    }

    @Step("Регистрация пользователя с именем: {name}, email: {email}")
    public LoginPage register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        return clickRegisterButton();
    }

    @Step("Попытка регистрации с некорректными данными: {email}")
    public RegisterPage registerWithError(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButtonStay();
        waitUntilErrorMessageVisible();
        return this;
    }

    @Step("Ожидание загрузки страницы регистрации")
    public RegisterPage waitUntilPageIsLoaded() {
        waitUntilVisible(registerHeader);
        return this;
    }

    @Step("Проверка загрузки страницы регистрации")
    public boolean isPageLoaded() {
        return isElementVisible(registerHeader) && driver.getCurrentUrl().contains("/register");
    }

    @Step("Получение значения поля 'Имя'")
    public String getNameFieldValue() {
        waitUntilVisible(nameField);
        return nameField.getAttribute("value");
    }

    @Step("Получение значения поля 'Email'")
    public String getEmailFieldValue() {
        waitUntilVisible(emailField);
        return emailField.getAttribute("value");
    }

    @Step("Получение значения поля 'Пароль'")
    public String getPasswordFieldValue() {
        waitUntilVisible(passwordField);
        return passwordField.getAttribute("value");
    }

    @Step("Очистка всех полей формы")
    public RegisterPage clearAllFields() {
        waitUntilVisible(nameField);
        nameField.clear();
        emailField.clear();
        passwordField.clear();
        return this;
    }

    @Step("Проверка активности кнопки регистрации")
    public boolean isRegisterButtonEnabled() {
        waitUntilVisible(registerButton);
        return registerButton.isEnabled();
    }

    @Step("Проверка наличия всех обязательных полей")
    public boolean areAllFieldsPresent() {
        return isElementVisible(nameField) &&
                isElementVisible(emailField) &&
                isElementVisible(passwordField) &&
                isElementVisible(registerButton);
    }

    @Step("Заполнение формы регистрации: {name}, {email}")
    public RegisterPage fillRegistrationForm(String name, String email, String password) {
        return setName(name)
                .setEmail(email)
                .setPassword(password);
    }

    @Step("Проверка, что кнопка регистрации кликабельна")
    public boolean isRegisterButtonClickable() {
        try {
            waitUntilClickable(registerButton);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}