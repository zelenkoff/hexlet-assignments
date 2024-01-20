package exercise.util;

import java.util.Optional;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    // BEGIN
    public static String postsPath() {
        return "/posts";
    }
    public static String postsPath(String page) {
        return "/posts?page=" + page;
    }

    public static String postPath(String id) {
        return "/posts/" + id;
    }
    // END
}
