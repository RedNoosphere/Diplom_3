package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import praktikum.api.UserAPI;
import praktikum.model.User;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected User testUser;

    @Before
    @DisplayName("Инициализация браузера и создание тестового пользователя")
    public void setUp() {
        try {
            // Создаем тестового пользователя через API
            testUser = UserAPI.createTestUser();
            System.out.println("Создан тестовый пользователь: " + testUser.getEmail());

            // Инициализируем браузер
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-notifications");
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://stellarburgers.nomoreparties.site");
        } catch (Exception e) {
            // Если что-то пошло не так при инициализации, убедимся что ресурсы освобождены
            if (driver != null) {
                driver.quit();
            }
            // Пытаемся удалить пользователя, если он был создан
            if (testUser != null && testUser.getAccessToken() != null) {
                UserAPI.deleteUser(testUser.getAccessToken());
            }
            throw new RuntimeException("Ошибка при инициализации теста", e);
        }
    }

    @After
    @DisplayName("Закрытие браузера и удаление тестового пользователя")
    public void tearDown() {
        try {
            // Всегда закрываем браузер, даже если тест упал
            if (driver != null) {
                driver.quit();
                driver = null; // Помечаем как закрытый
            }
        } catch (Exception e) {
            System.err.println("Ошибка при закрытии браузера: " + e.getMessage());
        }

        try {
            // Удаляем тестового пользователя
            if (testUser != null && testUser.getAccessToken() != null) {
                UserAPI.deleteUser(testUser.getAccessToken());
                System.out.println("Удален тестовый пользователь: " + testUser.getEmail());
                testUser = null; // Помечаем как удаленного
            }
        } catch (Exception e) {
            System.err.println("Ошибка при удалении пользователя: " + e.getMessage());
        }
    }
}