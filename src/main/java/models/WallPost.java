package models;

public class WallPost {
    PostId response;

    public static class PostId {
        int post_id;
    }

    public int getPostId() {
        return response.post_id;
    }
}
