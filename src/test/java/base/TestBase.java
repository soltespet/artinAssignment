package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.After;
import org.junit.Before;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    public static final String BASE_URL = "https://www.alza.sk/";

    SelenideElement chat = $(byId("chat-wrapper"));
    SelenideElement cookiesCard = $(".cookies-info__container");

    @Before
    public void setUp() {
        Configuration.headless = false;
        Configuration.holdBrowserOpen = true;

        open(BASE_URL);
        closeCookies();
    }

    @After
    public void tearDown() {
        closeWindow();
    }

    public void closeCookies() {
        if (cookiesCard.isDisplayed()) {
            cookiesCard.find(byText("Rozumiem")).click();
        }
    }

    public void closeVirtualAssistent() {
        if (chat.isDisplayed()) {
            chat.find(byId("vendor-close")).click();
        }

    }
}
