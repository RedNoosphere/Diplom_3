package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import praktikum.pages.MainPage;

import static org.junit.Assert.assertEquals;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("Проверка функциональности перехода к разделу 'Булки' в конструкторе бургеров")
    public void testNavigateToBunsSection() {
        MainPage mainPage = new MainPage(driver);

        // ✅ ИСПРАВЛЕНИЕ: Используем методы Page Object вместо явных ожиданий
        mainPage.clickSaucesSection()
                .waitUntilSectionActive("Соусы")
                .clickBunsSection()
                .waitUntilSectionActive("Булки");

        String activeSection = mainPage.getActiveSectionText();
        assertEquals("Должен быть активен раздел 'Булки'", "Булки", activeSection);
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Проверка функциональности перехода к разделу 'Соусы' в конструкторе бургеров")
    public void testNavigateToSaucesSection() {
        MainPage mainPage = new MainPage(driver);

        // ✅ ИСПРАВЛЕНИЕ: Используем методы Page Object
        mainPage.clickSaucesSection()
                .waitUntilSectionActive("Соусы");

        String activeSection = mainPage.getActiveSectionText();
        assertEquals("Должен быть активен раздел 'Соусы'", "Соусы", activeSection);
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Проверка функциональности перехода к разделу 'Начинки' в конструкторе бургеров")
    public void testNavigateToFillingsSection() {
        MainPage mainPage = new MainPage(driver);

        // ✅ ИСПРАВЛЕНИЕ: Используем методы Page Object
        mainPage.clickFillingsSection()
                .waitUntilSectionActive("Начинки");

        String activeSection = mainPage.getActiveSectionText();
        assertEquals("Должен быть активен раздел 'Начинки'", "Начинки", activeSection);
    }
}