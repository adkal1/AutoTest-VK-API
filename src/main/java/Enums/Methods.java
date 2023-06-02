package Enums;

public enum Methods {
    POST("wall.post"),
    EDIT("wall.edit"),
    DELETE("wall.delete"),
    COMMENT("wall.createComment"),
    PHOTOSERVER("photos.getWallUploadServer"),
    PHOTOSAVE("photos.saveWallPhoto"),
    LIKE("likes.isLiked"),
    USER("users.get");

    private final String value;

    Methods(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
