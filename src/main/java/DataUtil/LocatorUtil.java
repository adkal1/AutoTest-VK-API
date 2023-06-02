package DataUtil;

import org.openqa.selenium.By;

public class LocatorUtil {

    public static By getPostLocator(int ownerId, int idPost) {
        return By.xpath(String.format("//div[@id='wpt%d_%d']", ownerId, idPost));
    }

    public static By getPhotoLocator(int mediaId) {
        return By.xpath(String.format("//a[contains(@href, '%d')]", mediaId));
    }

    public static By getLikeLocator(int ownerId, int idPost) {
        return By.xpath(String.format("//div[contains(@class,'%d_%d')]//div[contains(@class, 'PostBottomActionContainer')]", ownerId, idPost));
    }

}
