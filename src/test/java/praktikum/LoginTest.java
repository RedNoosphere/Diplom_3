package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import praktikum.pages.ForgotPasswordPage;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.pages.PersonalAccountPage;
import praktikum.pages.RegisterPage;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной")
    public void testLoginFromMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Должны быть на странице логина", loginPage.isPageLoaded());

        loginPage.login(testUser.email, testUser.password);

        // Ждем завершения логина
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Проверяем, что авторизовались
        assertTrue("Должны быть авторизованы", isUserLoggedIn());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    public void testLoginFromPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Должны быть на странице логина", loginPage.isPageLoaded());

        loginPage.login(testUser.email, testUser.password);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        assertTrue("Должны быть авторизованы", isUserLoggedIn());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void testLoginFromRegistrationForm() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Должны быть на странице логина", loginPage.isPageLoaded());
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        assertTrue("Должны быть на странице регистрации", registerPage.isPageLoaded());
        registerPage.clickLoginLink();

        // Возвращаемся на страницу логина
        LoginPage returnedLoginPage = new LoginPage(driver);
        assertTrue("Должны вернуться на страницу логина", returnedLoginPage.isPageLoaded());

        returnedLoginPage.login(testUser.email, testUser.password);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        assertTrue("Должны быть авторизованы", isUserLoggedIn());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void testLoginFromForgotPasswordForm() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Должны быть на странице логина", loginPage.isPageLoaded());
        loginPage.clickForgotPasswordLink();

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        assertTrue("Должны быть на странице восстановления пароля", forgotPasswordPage.isPageLoaded());
        forgotPasswordPage.clickLoginLink();

        // Возвращаемся на страницу логина
        LoginPage returnedLoginPage = new LoginPage(driver);
        assertTrue("Должны вернуться на страницу логина", returnedLoginPage.isPageLoaded());

        returnedLoginPage.login(testUser.email, testUser.password);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        assertTrue("Должны быть авторизованы", isUserLoggedIn());
    }

    private boolean isUserLoggedIn() {
        MainPage mainPage = new MainPage(driver);
        return mainPage.isUserLoggedIn();
    }
}