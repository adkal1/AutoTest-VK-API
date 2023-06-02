import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

import models.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AuthorizationLoginPage;
import pages.AuthorizationPasswordPage;
import pages.MenuForm;
import pages.MyPage;

import java.util.UUID;


import static DataUtil.VkApiUtils.*;

public class TestApiVk {
    private final ISettingsFile environment = new JsonSettingsFile("testData.json");
    private final ISettingsFile config = new JsonSettingsFile("settings.json");
    private final AuthorizationLoginPage authorizationLoginPage = new AuthorizationLoginPage();
    private final AuthorizationPasswordPage authorizationPasswordPage = new AuthorizationPasswordPage();
    private final MenuForm menuForm = new MenuForm();

    @Test
    public void testVk() {
        Browser browser = AqualityServices.getBrowser();
        browser.maximize();

        Logger.getInstance().info("Step 1");
        browser.goTo(config.getValue("/url").toString());
        browser.waitForPageToLoad();

        Logger.getInstance().info("Step 2");
        authorizationLoginPage.setLogin(environment.getValue("/login").toString());
        authorizationLoginPage.clickEnterBtn();
        authorizationPasswordPage.setPassword(environment.getValue("/password").toString());
        authorizationPasswordPage.clickNextBtn();

        Logger.getInstance().info("Step 3");
        menuForm.clickMyPage();

        Logger.getInstance().info("Step 4");
        User user = getUser();
        int ownerId = user.getId();
        final String wallText = UUID.randomUUID().toString();

        WallPost wallPost = getWallPost(wallText);
        int idPost = wallPost.getPostId();

        Logger.getInstance().info("Step 5");
        MyPage myPage = new MyPage(ownerId, idPost);
        Assert.assertEquals(wallText, myPage.getTextWallPost(), "Wall post is wrong");
        Assert.assertEquals(myPage.getTextAuthorPostName(), myPage.getOwnerPageName(), "Wall post from the wrong user");

        Logger.getInstance().info("Step 6");
        String newWallText = UUID.randomUUID().toString();
        PhotosWallUploadServer photosWallUploadServer = getPhotosWallUploadServer();
        String urlPhoto = photosWallUploadServer.getUpload_url();

        PhotoPost postPhoto = getPhotoPost(urlPhoto);
        String photo = postPhoto.getPhoto();
        int server = postPhoto.getServer();
        String hash = postPhoto.getHash();

        PhotosWallSave photosWallSave = getPhotosWallSave(server, photo, hash);

        int mediaId = photosWallSave.getId();
        editPost(idPost, ownerId, mediaId, newWallText);

        Logger.getInstance().info("Step 7");
        Assert.assertEquals(newWallText, myPage.getTextWallPost(), "Wall post text has not changed");
        Assert.assertTrue(myPage.isDisplayedPhoto(mediaId), "The image is not uploaded or is not the same");

        Logger.getInstance().info("Step 8");
        final String commentGenerate = UUID.randomUUID().toString();
        CreateComment createComment = getCreateComment(idPost, commentGenerate);

        int commentId = createComment.getCommentId();

        Logger.getInstance().info("Step 9");
        MyPage myPageComment = new MyPage(ownerId, commentId);
        myPage.clickRepliesListBtn();
        Assert.assertEquals(commentGenerate, myPageComment.getTextWallPost(), "Comment is not correct");
        Assert.assertEquals(myPageComment.getCommentName(), myPageComment.getOwnerPageName(), "Comment from the wrong user");

        Logger.getInstance().info("Step 10");
        myPage.clickLikeBtn();

        Logger.getInstance().info("Step 11");
        Like like = getLike(idPost);
        int idLike = like.getLiked();

        Assert.assertEquals(idLike, 1, "The like doesn't appear");
        Assert.assertTrue(myPage.isDisplayedPost(), "The like from the wrong user");

        Logger.getInstance().info("Step 12");
        deletePost(ownerId, idPost);
        Logger.getInstance().info("Step 13");

        Assert.assertFalse(myPage.isDisplayedPost(), "Wall post doesn't delete");

        AqualityServices.getBrowser().quit();
        AqualityServices.setDefaultBrowserFactory();

    }

}
