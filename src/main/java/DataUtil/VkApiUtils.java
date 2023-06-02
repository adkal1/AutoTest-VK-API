package DataUtil;

import Enums.Methods;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;

import com.mashape.unirest.http.exceptions.UnirestException;
import models.*;

import java.io.File;

public class VkApiUtils {
    private static final Gson gson = new Gson();

    private static final ISettingsFile config = new JsonSettingsFile("settings.json");
    private static final ISettingsFile enviroment = new JsonSettingsFile("testData.json");
    private static final File photo = new File("src/test/resources/image.jpg");

    public static User getUser() {
        StringBuilder urlApi = new StringBuilder(config.getValue("/urlApi").toString());
        urlApi.append(Methods.USER.getValue());
        String response = null;
        try {
            response = Unirest.get(urlApi.toString())
                    .queryString("access_token", enviroment.getValue("/token").toString())
                    .queryString("v", enviroment.getValue("/ver").toString())
                    .asString().getBody();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
        return gson.fromJson(response, User.class);
    }

    public static WallPost getWallPost(String message) {
        StringBuilder urlApi = new StringBuilder(config.getValue("/urlApi").toString());
        urlApi.append(Methods.POST.getValue());
        String response = null;
        try {
            response = Unirest.post(urlApi.toString())
                    .queryString("message", message)
                    .queryString("access_token", enviroment.getValue("/token").toString())
                    .queryString("v", enviroment.getValue("/ver").toString())
                    .asString().getBody();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
        return gson.fromJson(response, WallPost.class);
    }

    public static PhotosWallUploadServer getPhotosWallUploadServer() {

        StringBuilder urlApi = new StringBuilder(config.getValue("/urlApi").toString());
        urlApi.append(Methods.PHOTOSERVER.getValue());
        String response = null;
        try {
            response = Unirest.get(urlApi.toString())
                    .queryString("access_token", enviroment.getValue("/token").toString())
                    .queryString("v", enviroment.getValue("/ver").toString())
                    .asString().getBody();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
        return gson.fromJson(response, PhotosWallUploadServer.class);
    }

    public static PhotoPost getPhotoPost(String uploadUrl) {
        String response = null;
        try {
            response = Unirest.post(uploadUrl)
                    .field("photo", photo)
                    .asString().getBody();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
        return gson.fromJson(response, PhotoPost.class);
    }

    public static PhotosWallSave getPhotosWallSave(int server, String photo, String hash) {
        StringBuilder urlApi = new StringBuilder(config.getValue("/urlApi").toString());
        urlApi.append(Methods.PHOTOSAVE.getValue());
        String response = null;
        try {
            response = Unirest.post(urlApi.toString())
                    .queryString("access_token", enviroment.getValue("/token").toString())
                    .queryString("v", enviroment.getValue("/ver").toString())
                    .queryString("server", Integer.toString(server))
                    .queryString("photo", photo)
                    .queryString("hash", hash)
                    .asString().getBody();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
        return gson.fromJson(response, PhotosWallSave.class);
    }

    public static void editPost(int postId, int ownerId, int mediaId, String message) {
        StringBuilder urlApi = new StringBuilder(config.getValue("/urlApi").toString());
        urlApi.append(Methods.EDIT.getValue());
        try {
            Unirest.post(urlApi.toString())
                    .queryString("access_token", enviroment.getValue("/token").toString())
                    .queryString("post_id", Integer.toString(postId))
                    .queryString("attachments", String.format("photo%s_%s", ownerId, mediaId))
                    .queryString("message", message)
                    .queryString("v", enviroment.getValue("/ver"))
                    .asString();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }

    }

    public static CreateComment getCreateComment(int postId, String message) {
        StringBuilder urlApi = new StringBuilder(config.getValue("/urlApi").toString());
        urlApi.append(Methods.COMMENT.getValue());
        String response = null;
        try {
            response = Unirest.post(urlApi.toString())
                    .queryString("message", message)
                    .queryString("post_id", postId)
                    .queryString("access_token", enviroment.getValue("/token").toString())
                    .queryString("v", enviroment.getValue("/ver").toString())
                    .asString().getBody();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
        return gson.fromJson(response, CreateComment.class);
    }

    public static Like getLike(int postId) {
        StringBuilder urlApi = new StringBuilder(config.getValue("/urlApi").toString());
        urlApi.append(Methods.LIKE.getValue());
        String response = null;
        try {
            response = Unirest.get(urlApi.toString())
                    .queryString("type", "post")
                    .queryString("item_id", postId)
                    .queryString("access_token", enviroment.getValue("/token").toString())
                    .queryString("v", enviroment.getValue("/ver").toString())
                    .asString().getBody();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
        return gson.fromJson(response, Like.class);
    }

    public static void deletePost(int ownerId, int postId) {
        StringBuilder urlApi = new StringBuilder(config.getValue("/urlApi").toString());
        urlApi.append(Methods.DELETE.getValue());
        try {
            Unirest.post(urlApi.toString())
                    .queryString("owner_id", ownerId)
                    .queryString("post_id", postId)
                    .queryString("access_token", enviroment.getValue("/token").toString())
                    .queryString("v", enviroment.getValue("/ver").toString())
                    .asString();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }

    }

}
