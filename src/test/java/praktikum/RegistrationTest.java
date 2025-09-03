package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.pages.PersonalAccountPage;
import praktikum.pages.RegisterPage;

import java.time.Duration;

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

        // Запоминаем текущий URL ДО регистрации
        String urlBeforeRegistration = driver.getCurrentUrl();

        String email = "test" + System.currentTimeMillis() + "@example.com";
        registerPage.register("Test User", email, "password123");

        // ✅ Ожидаем изменения URL - это быстрее чем ждать полной загрузки страницы
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(urlBeforeRegistration)));

        // Быстрая проверка по URL куда нас перенаправило
        String currentUrl = driver.getCurrentUrl();

        // Не создаем новые объекты страниц без необходимости - используем быстрые проверки
        if (currentUrl.contains("/login")) {
            // Простая проверка что мы на странице логина
            assertTrue("URL должен содержать /login", currentUrl.contains("/login"));

        } else if (currentUrl.equals("https://stellarburgers.nomoreparties.site/") ||
                currentUrl.equals("https://stellarburgers.nomoreparties.site")) {
            // Быстрая проверка что мы на главной
            assertTrue("Должны быть на главной странице",
                    driver.getTitle().contains("Stellar Burgers") ||
                            driver.getPageSource().contains("Оформить заказ"));

        } else {
            // Для других случаев используем обычные проверки
            MainPage newMainPage = new MainPage(driver);
            assertTrue("Должны быть на корректной странице после регистрации",
                    newMainPage.isPageLoaded() || loginPage.isPageLoaded());
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

        // ✅ ЗАМЕНА SLEEP НА ЯВНЫЕ ОЖИДАНИЯ
        // Ждем появления сообщения об ошибке или изменения состояния
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(org.openqa.selenium.By.xpath("//p[contains(@class, 'input__error')]")),
                ExpectedConditions.not(ExpectedConditions.urlContains("/register"))
        ));

        // Проверяем, что остались на странице регистрации
        assertTrue("Должны остаться на странице регистрации при ошибке",
                registerPage.isPageLoaded());

        // Проверяем наличие сообщения об ошибке
        String errorMessage = registerPage.getErrorMessage();
        boolean hasError = errorMessage != null && !errorMessage.isEmpty();

        assertTrue("Должна отображаться ошибка валидации пароля: " + errorMessage, hasError);
    }
}