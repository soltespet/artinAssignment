package tests;

import base.TestBase;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class thirdAssignment extends TestBase {

    static SelenideElement basket = $(byId("basket"));
    SelenideElement continueWithPaymentButton = $(byId("blockBtnRight"));
    static SelenideElement basketItemCounter = $("span.count");

    @Test
    public void testEmptyBasket() {
        closeCookies();
        basket.click();
        closeCookies();

        new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(4))
                .until(ExpectedConditions.urlContains("Order1.htm"));

        basketItemCounter.shouldNotBe(visible);
        continueWithPaymentButton.shouldNotBe(visible);

        $(byText("Som taký prázdny...")).shouldBe(visible);
    }
}
