package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PersonalAccountPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//p[text()='Профиль']")
    private WebElement profileSection;

    @FindBy(how = How.XPATH, using = "//h2[text()='Профиль']")
    private WebElement profileHeader;

    // ✅ ДОБАВЛЯЕМ: Локатор для кнопки выхода (если есть)
    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private WebElement logoutButton;

    public PersonalAccountPage(WebDriver driver) {
        super(driver);
    }

    // ✅ ОПТИМИЗИРУЕМ: Добавляем явное ожидание перед проверкой
    @Step("Проверка отображения раздела 'Профиль'")
    public boolean isProfileSectionDisplayed() {
        try {
            waitUntilVisible(profileSection);
            return profileSection.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ ОПТИМИЗИРУЕМ: Используем wait из BasePage
    @Step("Ожидание загрузки страницы личного кабинета")
    public PersonalAccountPage waitUntilPageIsLoaded() {
        waitUntilVisible(profileHeader);
        return this;
    }

    // ✅ ОПТИМИЗИРУЕМ: Упрощаем метод проверки
    @Step("Проверка загрузки страницы личного кабинета")
    public boolean isPageLoaded() {
        return isElementVisible(profileHeader) && driver.getCurrentUrl().contains("/account/profile");
    }

    // ✅ ДОБАВЛЯЕМ: Метод для выхода (если нужен в тестах)
    @Step("Выход из аккаунта")
    public LoginPage clickLogout() {
        if (logoutButton != null) {
            waitUntilClickable(logoutButton);
            logoutButton.click();
            return new LoginPage(driver).waitUntilPageIsLoaded();
        }
        throw new RuntimeException("Кнопка выхода не найдена на странице");
    }

    // ✅ ДОБАВЛЯЕМ: Метод для получения заголовка профиля
    @Step("Получение заголовка профиля")
    public String getProfileHeaderText() {
        waitUntilVisible(profileHeader);
        return profileHeader.getText();
    }
}