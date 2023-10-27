package exercise;

import io.javalin.Javalin;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
            var userNumber = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
            var per = ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);

            int startIdx = (userNumber - 1) * per;
            int endIdx = Math.min(startIdx + per, USERS.size());

            List<Map<String, String>> selectedUsers = USERS.subList(startIdx, endIdx);

            ctx.json(selectedUsers);
        });
        // END

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
