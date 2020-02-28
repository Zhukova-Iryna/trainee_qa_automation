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
public class SearchPage extends BasePage {
    private ElementsCollection searchResults = $$(By.xpath(".//div[contains(@class, 'product-block type')]"));
    private SelenideElement itemPageButton = $(By.xpath(".//a[contains(@class,'btn-orange')]"));
    private SelenideElement pageBreadcrumbs = $(By.xpath(".//div[@id='page-breadcrumbs']"));
}
