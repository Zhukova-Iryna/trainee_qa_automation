package price.ua.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.openqa.selenium.By;
import price.ua.base.BasePage;

import static com.codeborne.selenide.Selenide.$;

@Accessors(fluent = true)
@Getter
public class MainPage extends BasePage {
    private SelenideElement loginLink = $(By.xpath(".//div[@id='auth-user-block']"));
    private SelenideElement userAccount = $(By.xpath("(.//a[contains(@class,'header-user-link')])[1]"));
    private SelenideElement searchFieldInput = $(By.xpath(".//input[@id='SearchForm_searchPhrase']"));
    private SelenideElement emailField = $(By.xpath(".//input[@id = 'LoginForm_username']"));
    private SelenideElement passwordField = $(By.xpath(".//input[@id = 'login_user_password']"));
    private SelenideElement submitLoginButton = $(By.xpath(".//div[contains(@class,'pt_20')]"));
    private SelenideElement registrationTab = $(By.xpath(".//div[@id = 'go-tab-userregister']"));
    private SelenideElement errorMessage = $(By.xpath(".//div[@class='error-text'][contains(text(),'.')]"));
    private SelenideElement regEmailField = $(By.xpath(".//input[@id = 'RegisterUserFirmForm_user_email']"));
    private SelenideElement regPasswordField = $(By.xpath(".//input[@id = 'user_user_password']"));
    private SelenideElement logoutLink = $(By.xpath(".//a[@class='i-logout']"));
    private SelenideElement submitRegistrationButton = $(By.xpath("//div[contains(@class,'mt_10')]"));
    private SelenideElement successRegistrationMessage = $(By.xpath(".//div[@class='reg-success-text']"));
}
