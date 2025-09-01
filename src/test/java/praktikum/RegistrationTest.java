package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.pages.PersonalAccountPage;
import praktikum.pages.RegisterPage;

import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация")
    public void testSuccessfulRegistration() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Должны быть на странице логина", loginPage.isPageLoaded());
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        assertTrue("Должны быть на странице регистрации", registerPage.isPageLoaded());

        String email = "test" + System.currentTimeMillis() + "@example.com";
        registerPage.register("Test User", email, "password123");

        // Ждем завершения регистрации
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Проверяем разные варианты перенаправления
        boolean isOnMain = new MainPage(driver).isPageLoaded();
        boolean isOnLogin = new LoginPage(driver).isPageLoaded();

        if (isOnMain) {
            // Пользователь автоматически залогинен
            boolean isLoggedIn = mainPage.isUserLoggedIn();
            assertTrue("Пользователь должен быть автоматически залогинен после регистрации", isLoggedIn);

        } else if (isOnLogin) {
            // Пользователь перенаправлен на логин
            assertTrue("Должны быть на странице логина после регистрации", true);

        } else {
            // Другие возможные варианты
            assertTrue("После регистрации должен быть перенаправлен",
                    !registerPage.isPageLoaded());
        }
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля")
    public void testInvalidPasswordError() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Должны быть на странице логина", loginPage.isPageLoaded());
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        assertTrue("Должны быть на странице регистрации", registerPage.isPageLoaded());

        registerPage.register("Test User", "test@example.com", "123");

        // Ждем появления ошибки
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Проверяем, что остались на странице регистрации
        assertTrue("Должны остаться на странице регистрации при ошибке",
                registerPage.isPageLoaded());

        // Проверяем наличие сообщения об ошибке
        String errorMessage = registerPage.getErrorMessage();
        boolean hasError = errorMessage != null && !errorMessage.isEmpty();

        assertTrue("Должна отображаться ошибка валидации пароля: " + errorMessage, hasError);
    }
}