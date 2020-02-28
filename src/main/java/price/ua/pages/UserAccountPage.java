package price.ua.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.openqa.selenium.By;
import price.ua.base.BasePage;

import static com.codeborne.selenide.Selenide.$;

@Accessors(fluent = true)
@Getter
public class UserAccountPage extends BasePage {
    private SelenideElement loginEmailInput = $(By.xpath(".//input[@id='LoginForm_username']"));
    private SelenideElement loginPasswordInput = $(By.xpath(".//input[@id='LoginForm_password']"));
    private SelenideElement loginSubmit = $(By.xpath(".//a[contains(@class,'ga_user_login_page_enter_click')]"));
    private SelenideElement checkBoxTourismHobby = $(By.xpath(".//input[@id='UserHobbies0']"));
    private SelenideElement checkBoxMusicHobby = $(By.xpath(".//input[@id='UserHobbies1']"));
    private SelenideElement saveButton = $(By.xpath(".//button[@type='submit']"));
    private SelenideElement logoutLink = $(By.xpath(".//a[@class='i-logout']"));
    private SelenideElement accountIcon = $(By.xpath(".//a[@id='header-user-link']/span"));
    private SelenideElement successMessage = $(By.xpath(".//div[contains(@class,'alert-success')]"));
}
