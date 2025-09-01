package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import praktikum.pages.MainPage;

import static org.junit.Assert.assertEquals;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    public void testNavigateToBunsSection() {
        MainPage mainPage = new MainPage(driver);

        // Сначала переходим в другой раздел
        mainPage.clickSaucesSection();
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        mainPage.clickBunsSection();
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        String activeSection = mainPage.getActiveSectionText();
        assertEquals("Должен быть активен раздел 'Булки'", "Булки", activeSection);
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    public void testNavigateToSaucesSection() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickSaucesSection();
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        String activeSection = mainPage.getActiveSectionText();
        assertEquals("Должен быть активен раздел 'Соусы'", "Соусы", activeSection);
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    public void testNavigateToFillingsSection() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickFillingsSection();
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        String activeSection = mainPage.getActiveSectionText();
        assertEquals("Должен быть активен раздел 'Начинки'", "Начинки", activeSection);
    }
}