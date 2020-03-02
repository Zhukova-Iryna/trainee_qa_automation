package price.ua.base;

import price.ua.pages.ItemPage;
import price.ua.pages.MainPage;
import price.ua.pages.SearchPage;
import price.ua.pages.UserAccountPage;

public abstract class BaseTest {
    protected MainPage mainPage = new MainPage();
    protected SearchPage searchPage = new SearchPage();
    protected ItemPage itemPage = new ItemPage();
    protected UserAccountPage userAccountPage = new UserAccountPage();
}
