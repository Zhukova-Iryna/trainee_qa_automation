package price.ua.tests;

import org.testng.annotations.Test;
import price.ua.base.BaseTest;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.open;

public class UserAccountTest extends BaseTest {
    @Test
    public void checkHobbyChosenSave() {
        open("https://price.ua/account/userpanel/profile");
        userAccountPage.loginEmailInput().setValue("bu9ira@gmail.com");
        userAccountPage.loginPasswordInput().setValue("test2010");
        userAccountPage.loginSubmit().click();
        userAccountPage.saveButton().scrollTo();
        userAccountPage.saveButton().click();
        userAccountPage.successMessage().should(appear);
    }
}
