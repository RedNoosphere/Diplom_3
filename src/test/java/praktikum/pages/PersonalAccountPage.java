package praktikum.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//p[text()='Профиль']")
    private WebElement profileSection;

    @FindBy(how = How.XPATH, using = "//h2[text()='Профиль']")
    private WebElement profileHeader;

    public PersonalAccountPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProfileSectionDisplayed() {
        try {
            return profileSection.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPageLoaded() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOf(profileHeader));
            return driver.getCurrentUrl().contains("/account/profile");
        } catch (Exception e) {
            return false;
        }
    }
}