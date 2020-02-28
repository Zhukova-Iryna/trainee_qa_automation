package price.ua.tests;

import com.codeborne.selenide.ElementsCollection;
import org.testng.annotations.Test;
import price.ua.base.BaseTest;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SearchTest extends BaseTest {
    @Test
    public void checkSearchComplete() {
        open("https://price.ua/");
        mainPage.searchFieldInput().setValue("Samsung A50").pressEnter();
        searchPage.pageBreadcrumbs().shouldHave(text("поиск"));
    }

    @Test
    public void checkRelevantSearchResults() {
        open("https://price.ua/");
        mainPage.searchFieldInput().setValue("Samsung A50").pressEnter();
        searchPage.searchResults().filterBy(text("Samsung A50")).shouldHave(sizeGreaterThan(0));
    }

    @Test
    public void checkSortingPricesInAscendingOrder() {
        open("https://price.ua/search/?q=Samsung+A50");
        searchPage.itemPageButton().click();
        sleep(2000);
        itemPage.priceOffersTab().click();
        sleep(500);
        itemPage.sortingList().click();
        itemPage.ascendingPriceSorting().click();
        sleep(1000);
        ElementsCollection elementsWithDefaultAscendingSorting = itemPage.priceFromPriceOffers();//.forEach(element -> elements.add(Integer.parseInt(element.getText().replaceAll("\\D", ""))));//List<Integer> defaultAscendingSortingByPrice = new ArrayList<>();
        elementsWithDefaultAscendingSorting.forEach(element -> element.getText().replaceAll("\\D", ""));
        ElementsCollection elementsAfterAscendingSorting = elementsWithDefaultAscendingSorting;
        elementsAfterAscendingSorting.texts().sort(String::compareTo);
        elementsWithDefaultAscendingSorting.shouldBe(exactTexts(elementsAfterAscendingSorting.texts()));
    }
}
