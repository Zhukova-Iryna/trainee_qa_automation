package price.ua.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.openqa.selenium.By;
import price.ua.base.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Accessors(fluent = true)
@Getter
public class ItemPage extends BasePage {
    private SelenideElement priceOffersTab = $(By.xpath("(.//a[contains(@class,'ga_mdl_tab_price')])[2]"));
    private SelenideElement sortingList = $(By.xpath(".//a[@class='ga_cat_sort link active']"));
    private SelenideElement ascendingPriceSorting = $(By.xpath(".//a[@data-wa-event-label='From_cheap_to_expensive']"));
    private ElementsCollection priceOffers = $$(By.xpath(".//div[contains(@id,'pricelineAnchor')]"));
    private ElementsCollection priceFromPriceOffers = $$(By.xpath(".//div[contains(@id,'pricelineAnchor')]//span[@class='price']"));
}
