package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AuthorizationPasswordPage extends Form {
    private final IButton nextBtn = getElementFactory().getButton(By.xpath("//span[@class='vkuiButton__in']"), "Next Button");
    private final ITextBox password = getElementFactory().getTextBox(By.xpath("//input[@name='password']"), "Password Field");

    public AuthorizationPasswordPage() {
        super(By.className("vkc__EnterPasswordNoUserInfo__content"), "Password Form");
    }

    public void setPassword(String text) {
        password.clearAndType(text);
    }

    public void clickNextBtn() {
        nextBtn.clickAndWait();
    }
}
