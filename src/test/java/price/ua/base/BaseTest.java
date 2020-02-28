package price.ua.base;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeTest;
import price.ua.pages.ItemPage;
import price.ua.pages.MainPage;
import price.ua.pages.SearchPage;
import price.ua.pages.UserAccountPage;

public abstract class BaseTest {
    protected MainPage mainPage = new MainPage();
    protected SearchPage searchPage = new SearchPage();
    protected ItemPage itemPage = new ItemPage();
    protected UserAccountPage userAccountPage = new UserAccountPage();

    @BeforeTest
    public void setupBrowser() {
        Configuration.browser = "chrome";
        System.setProperty("webdriver.chrome.driver", "C:/Users/IRYNA/IdeaProjects/qa_automatuin_trainee_selenide/src/main/resources/drivers/chromedriver.exe");
    }
}
