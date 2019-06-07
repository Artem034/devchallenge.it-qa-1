package it.devchallenge.pageFragments;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class HeaderFragment extends BaseFragment {


    @Step("Open menu: {firstMenuItem}->{secondMenuItem}->{thirdMenuItem}")
    public void openMenu(String firstMenuItem, String secondMenuItem, String thirdMenuItem) {
        $x(".//span[@class='ui-menuitem-text' and text() = '"+firstMenuItem+"']").hover();
        $x(".//span[@class='ui-menuitem-text' and text() = '"+secondMenuItem+"']").hover();
        $x(".//span[@class='ui-menuitem-text' and text() = '"+thirdMenuItem+"']").click();
    }

    @Step("Open menu: {firstMenuItem}->{secondMenuItem}")
    public void openMenu(String firstMenuItem, String secondMenuItem) {
        $x(".//span[@class='ui-menuitem-text' and text() = '"+firstMenuItem+"']").hover();
        $x(".//span[@class='ui-menuitem-text' and text() = '"+secondMenuItem+"']").click();
    }
}
