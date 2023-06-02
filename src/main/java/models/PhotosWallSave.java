package models;

import java.util.List;

public class PhotosWallSave {
    private List<Photo> response;

    public static class Photo {
        private int album_id;
        private long date;
        private int id;
        private int owner_id;
        private String access_key;
        private List<Size> sizes;


        public static class Size {
            private int height;
            private String type;
            private int width;
            private String url;

        }
    }

    public int getId() {
        return response.get(0).id;
    }


}
