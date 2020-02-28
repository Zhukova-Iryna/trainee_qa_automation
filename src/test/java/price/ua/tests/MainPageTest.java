package price.ua.tests;

import org.testng.annotations.Test;
import price.ua.base.BaseTest;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class MainPageTest extends BaseTest {
    @Test
    public void checkPositiveLoginScenario() {
        open("https://price.ua/");
        sleep(2000);
        mainPage.loginLink().click();
        mainPage.emailField().setValue("bu9ira@gmail.com");
        mainPage.passwordField().setValue("test2010");
        mainPage.submitLoginButton().click();
        sleep(1000);
        mainPage.loginLink().shouldNotHave(text("Вход"));
    }

    @Test
    public void checkNegativeLoginScenario() {
        open("https://price.ua/");
        sleep(3000);
        mainPage.loginLink().click();
        mainPage.emailField().setValue("bu9ira@gmail.com");
        mainPage.passwordField().setValue("test2020");
        mainPage.submitLoginButton().click();
        mainPage.errorMessage().should(appear);
    }

    @Test
    public void checkPositiveRegistrationScenario() {
        open("https://price.ua/");
        sleep(3000);
        mainPage.loginLink().click();
        mainPage.registrationTab().click();
        mainPage.regEmailField().setValue("User4@gmail.com");
        mainPage.regPasswordField().setValue("User3456789");
        mainPage.submitRegistrationButton().click();
        mainPage.successRegistrationMessage().should(appear);
    }

    @Test
    public void checkNegativeRegistrationScenario() {
        open("https://price.ua/");
        sleep(3000);
        mainPage.loginLink().click();
        mainPage.registrationTab().click();
        mainPage.regEmailField().setValue("User4gmail.com");
        mainPage.regPasswordField().setValue("User3456789");
        mainPage.submitRegistrationButton().click();
        mainPage.errorMessage().should(appear);
    }

    @Test
    public void checkUserSuccessfullyLogout() {
        open("https://price.ua/");
        sleep(3000);
        mainPage.loginLink().click();
        mainPage.emailField().setValue("bu9ira@gmail.com");
        mainPage.passwordField().setValue("test2010");
        mainPage.submitLoginButton().click();
        sleep(2000);
        mainPage.userAccount().click();
        mainPage.logoutLink().click();
        sleep(1000);
        mainPage.logoutLink().shouldHave(text("Вход"));
    }
}
