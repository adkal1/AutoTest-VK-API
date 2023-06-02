package models;

import java.util.List;

public class CreateComment {
    Params response;

    public static class Params {
        int comment_id;
        List<Integer> parent_stack;
    }

    public int getCommentId() {
        return response.comment_id;
    }

}
