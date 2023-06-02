package models;

public class PhotosWallUploadServer {
    Params response;

    public static class Params {
        int album_id;
        String upload_url;
        String user_id;
    }

    public int getAlbum_id() {
        return response.album_id;
    }

    public String getUpload_url() {
        return response.upload_url;
    }

    public String getUser_id() {
        return response.user_id;
    }
}
