package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.pages.RegisterPage;

import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация")
    public void testSuccessfulRegistration() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginButton();

        assertTrue("Должны быть на странице логина", loginPage.isPageLoaded());

        RegisterPage registerPage = loginPage.clickRegisterLink();
        assertTrue("Должны быть на странице регистрации", registerPage.isPageLoaded());

        String email = "test" + System.currentTimeMillis() + "@example.com";
        LoginPage loginPageAfterRegistration = registerPage.register("Test User", email, "password123");

        assertTrue("Должны быть на странице логина после успешной регистрации",
                loginPageAfterRegistration.isPageLoaded());
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля")
    public void testInvalidPasswordError() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginButton();

        assertTrue("Должны быть на странице логина", loginPage.isPageLoaded());

        RegisterPage registerPage = loginPage.clickRegisterLink();
        assertTrue("Должны быть на странице регистрации", registerPage.isPageLoaded());

        // ✅ ИСПРАВЛЕНИЕ: Используем метод с явным ожиданием ошибки
        registerPage.registerWithError("Test User", "test@example.com", "123");

        // ✅ ИСПРАВЛЕНИЕ: Явно ждем появления ошибки
        assertTrue("Должны остаться на странице регистрации при ошибке",
                registerPage.isPageLoaded());

        assertTrue("Должна отображаться ошибка валидации пароля",
                registerPage.isErrorMessageDisplayed());
    }
}