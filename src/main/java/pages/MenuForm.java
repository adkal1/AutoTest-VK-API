package pages;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MenuForm extends Form {
    private final ILink myPage = getElementFactory().getLink(By.id("l_pr"), "MyPage");

    public MenuForm() {
        super(By.xpath("//nav[@class='side_bar_nav']"), "Menu Form");
    }

    public void clickMyPage() {
        myPage.clickAndWait();
    }
}
