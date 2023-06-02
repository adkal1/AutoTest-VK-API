package models;

import java.util.List;

public class User {
    public List<UserParams> response;

    public static class UserParams {
        public int id;
        public String first_name;
        public String last_name;
        public String bdate;
    }

    public int getId() {
        return response.get(0).id;
    }

    public String getFirst_name() {
        return response.get(0).first_name;
    }

    public String getLast_name() {
        return response.get(0).last_name;
    }

    public String getBdate() {
        return response.get(0).bdate;
    }
}
