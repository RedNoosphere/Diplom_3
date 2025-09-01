package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import praktikum.utils.UserGenerator;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected UserGenerator.User testUser;

    @Before
    @DisplayName("Инициализация браузера и создание тестового пользователя")
    public void setUp() {
        // Создаем тестового пользователя через API
        testUser = UserGenerator.createTestUser();
        System.out.println("Создан тестовый пользователь: " + testUser.email);

        // Инициализируем браузер
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://stellarburgers.nomoreparties.site");
    }

    @After
    @DisplayName("Закрытие браузера и удаление тестового пользователя")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        // Удаляем тестового пользователя
        if (testUser != null) {
            UserGenerator.deleteUser(testUser.accessToken);
            System.out.println("Удален тестовый пользователь: " + testUser.email);
        }
    }
}