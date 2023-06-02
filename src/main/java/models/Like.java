package models;

public class Like {

    Params response;

    public static class Params {
        int liked;
        int copied;
    }

    public int getLiked() {
        return response.liked;
    }
}
