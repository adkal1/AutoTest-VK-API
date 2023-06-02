package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import static DataUtil.LocatorUtil.*;

public class MyPage extends Form {
    private final int ownerId;
    private final int idPost;

    public MyPage(int ownerId, int idPost) {
        super(By.className("WallLegacy"), "MyPage");
        this.ownerId = ownerId;
        this.idPost = idPost;
    }

    private final ILabel authorPostName = getElementFactory().getLabel(By.xpath("//a[@class='author']"), "AuthorPostName");
    private final ILabel ownerPageName = getElementFactory().getLabel(By.id("owner_page_name"), "OwnerPageName");
    private final ILabel commentName = getElementFactory().getLabel(By.xpath("//div[@class='reply_author']/a"), "CommentName");
    private final IButton repliesListBtn = getElementFactory().getButton(By.xpath("//span[@class='js-replies_next_label']"), "RepliesList");


    public String getTextWallPost() {
        return getPostLabel().getText();
    }

    public boolean isDisplayedPost() {
        return getPostLabel().state().isDisplayed();
    }

    public void clickLikeBtn() {
        getLikeBtn().clickAndWait();
    }

    public boolean isDisplayedPhoto(int mediaId) {
        return photoElement(mediaId).state().isDisplayed();
    }

    public String getTextAuthorPostName() {
        return authorPostName.getText();
    }

    public String getOwnerPageName() {
        return ownerPageName.getText();
    }

    public String getCommentName() {
        return commentName.getText();
    }

    public void clickRepliesListBtn() {
        repliesListBtn.clickAndWait();
    }

    private ILabel getPostLabel() {
        return getElementFactory().getLabel(getPostLocator(ownerId, idPost), "WallPost");
    }

    private IButton getLikeBtn() {
        return getElementFactory().getButton(getLikeLocator(ownerId, idPost), "LikeButton");
    }

    private ILabel photoElement(int mediaId) {
        return getElementFactory().getLabel(getPhotoLocator(mediaId), "PhotoPost");
    }

}
