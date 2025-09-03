package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.pages.MainPage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("Проверка функциональности перехода к разделу 'Булки' в конструкторе бургеров")
    public void testNavigateToBunsSection() {
        MainPage mainPage = new MainPage(driver);

        // Сначала переходим в другой раздел, чтобы был виден переход
        mainPage.clickSaucesSection();

        // Явное ожидание: ждем, пока раздел "Соусы" станет активным
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.textToBePresentInElement(mainPage.getActiveSectionElement(), "Соусы"));

        mainPage.clickBunsSection();

        // Явное ожидание: ждем, пока раздел "Булки" станет активным
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.textToBePresentInElement(mainPage.getActiveSectionElement(), "Булки"));

        String activeSection = mainPage.getActiveSectionText();
        assertEquals("Должен быть активен раздел 'Булки'", "Булки", activeSection);
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Проверка функциональности перехода к разделу 'Соусы' в конструкторе бургеров")
    public void testNavigateToSaucesSection() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickSaucesSection();

        // Явное ожидание: ждем, пока раздел "Соусы" станет активным
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.textToBePresentInElement(mainPage.getActiveSectionElement(), "Соусы"));

        String activeSection = mainPage.getActiveSectionText();
        assertEquals("Должен быть активен раздел 'Соусы'", "Соусы", activeSection);
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Проверка функциональности перехода к разделу 'Начинки' в конструкторе бургеров")
    public void testNavigateToFillingsSection() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickFillingsSection();

        // Явное ожидание: ждем, пока раздел "Начинки" станет активным
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.textToBePresentInElement(mainPage.getActiveSectionElement(), "Начинки"));

        String activeSection = mainPage.getActiveSectionText();
        assertEquals("Должен быть активен раздел 'Начинки'", "Начинки", activeSection);
    }
}