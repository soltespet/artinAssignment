package tests;

import base.TestBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static tests.thirdAssignment.*;

public class firstAssignment extends TestBase {

    ElementsCollection boxes = $$x("//div[@id='boxes']/div");
    String[] hrefsofSelectedPhones = new String[3];

    SelenideElement loader = $(".circle-loader-container");
    SelenideElement backToShoppingButton = $(byId("varBBackButton"));
    SelenideElement continueToBasketButton = $(byId("varBToBasketButton"));
    SelenideElement removeItemButton = $(byText("Odobrať tovar"));

    ElementsCollection basketItems = $$(".mainItem");

    @Test
    public void testBuying3MostExpensivePhones() {

        getMeToPhonesPageAndSortThemFromMostExpensive();
        waitForLoader();
        closeVirtualAssistent();

        basketItemCounter.shouldNotBe(visible);

        // add 3 most expensive phones to basket
        for (int i = 1; i < 4; i++) {
            hrefsofSelectedPhones[i - 1] = boxes.get(i - 1).find(".fb").find("a").getAttribute("href");
            boxes.get(i - 1).find(".btnk1").click();
            basketItemCounter.shouldHave(text(String.valueOf(i)));

            if (i == 3) {
                continueToBasketButton.click();
            } else {
                backToShoppingButton.click();
            }
        }

        new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(4))
                .until(ExpectedConditions.urlContains("Order1.htm"));

        int j = 0;
        for (int i = 0; i < 3; i++) {
            basketItems.get(j).shouldHave(attribute("href", hrefsofSelectedPhones[i]));
            j = j + 2;
        }

        //remove first phone in basket
        $(".countMinus").click();

        $(".alzaDialogBody").should(appear);

        sleep(4000);

        removeItemButton.click();

        $(".alzaDialogBody").should(disappear);

        //check if first item was successfully deleted
        for (SelenideElement item : basketItems) {
            item.shouldNotHave(attribute("href", hrefsofSelectedPhones[0]));
        }
    }

    void waitForLoader() {
        loader.should(appear);
        loader.should(disappear);
    }

    void getMeToPhonesPageAndSortThemFromMostExpensive() {
        $(byId("litp18890259")).click();
        $x("//div[@data-testid='category-tiles']/div[@data-testid='category-tile'][1]").click();
        $(byId("tabs")).find("li", 2).click();
    }
}
