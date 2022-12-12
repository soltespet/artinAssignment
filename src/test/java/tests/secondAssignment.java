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

public class secondAssignment extends TestBase {

    SelenideElement searchInput = $(byId("edtSearch"));
    SelenideElement searchButton = $(byId("btnSearch"));

    @Test
    public void testSearch(){
        closeCookies();
        searchInput.setValue("Samsung");
        searchButton.click();
        new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(4))
                .until(ExpectedConditions.urlContains("samsung"));
        $(".mainContent").find(byText("Samsung")).shouldBe(visible);
    }
}
