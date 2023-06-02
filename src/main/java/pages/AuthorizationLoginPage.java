package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AuthorizationLoginPage extends Form {

    public AuthorizationLoginPage() {
        super(By.className("VkIdForm"), "Login Page");
    }

    private final ITextBox login = getElementFactory().getTextBox(By.id("index_email"), "LoginField");
    private final IButton enterBtn = getElementFactory().getButton(By.xpath("//button[contains(@class, 'VkIdForm__signInButton')]"), "EnterButton");

    public void setLogin(String text) {
        login.clearAndType(text);
    }

    public void clickEnterBtn() {
        enterBtn.clickAndWait();
    }
}
