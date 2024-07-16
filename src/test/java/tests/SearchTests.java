package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;

@DisplayName("Search tests")
public class SearchTests extends TestBase {

    @Test
    @Tag("android")
    @DisplayName("Find article by specific name test")
    void successfulSearchTest() {
        step("Tap on Skip button", () -> {
            $(id("fragment_onboarding_skip_button")).click();
        });
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }


    @Test
    @Tag("android")
    @DisplayName("Open article test")
    void successfulArticleOpeningTest() {
        step("Tap on Skip button", () -> {
            $(id("fragment_onboarding_skip_button")).click();
        });
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Java");
        });
        step("Open an article", () ->
                $(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .click());
        step("Verify that article has mentioned text", () ->
                $(className("android.widget.TextView")).shouldHave(text("Java")));
    }
}
