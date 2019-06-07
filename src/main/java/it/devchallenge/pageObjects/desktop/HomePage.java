package it.devchallenge.pageObjects.desktop;

import it.devchallenge.pageFragments.HeaderFragment;
import it.devchallenge.pageObjects.BasePage;

public class HomePage extends BasePage {

    public static final String HOME_PAGE_URL = "http://localhost:8080/article/faces/welcome.xhtml";

    public HeaderFragment headerFragment = new HeaderFragment();

    public class Asserts extends BasePage.Asserts {

    }
}
