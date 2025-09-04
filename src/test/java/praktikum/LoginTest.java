package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import praktikum.pages.ForgotPasswordPage;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.pages.RegisterPage;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной")
    public void testLoginFromMainPage() {
        MainPage mainPageAfterLogin = new MainPage(driver)
                .clickLoginButton()
                .waitUntilPageIsLoaded()
                .login(testUser.getEmail(), testUser.getPassword());

        assertTrue("Должны быть авторизованы", mainPageAfterLogin.isUserLoggedIn());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    public void testLoginFromPersonalAccountButton() {
        MainPage mainPageAfterLogin = new MainPage(driver)
                .clickPersonalAccountButton()
                .waitUntilPageIsLoaded()
                .login(testUser.getEmail(), testUser.getPassword());

        assertTrue("Должны быть авторизованы", mainPageAfterLogin.isUserLoggedIn());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void testLoginFromRegistrationForm() {
        MainPage mainPageAfterLogin = new MainPage(driver)
                .clickLoginButton()
                .clickRegisterLink()
                .waitUntilPageIsLoaded()
                .clickLoginLink()
                .waitUntilPageIsLoaded()
                .login(testUser.getEmail(), testUser.getPassword());

        assertTrue("Должны быть авторизованы", mainPageAfterLogin.isUserLoggedIn());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void testLoginFromForgotPasswordForm() {
        MainPage mainPageAfterLogin = new MainPage(driver)
                .clickLoginButton()
                .clickForgotPasswordLink()
                .waitUntilPageIsLoaded()
                .clickLoginLink()
                .waitUntilPageIsLoaded()
                .login(testUser.getEmail(), testUser.getPassword());

        assertTrue("Должны быть авторизованы", mainPageAfterLogin.isUserLoggedIn());
    }
}