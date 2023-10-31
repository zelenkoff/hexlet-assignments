package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;
import java.util.Objects;

// BEGIN
import io.javalin.http.NotFoundResponse;
// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/companies/{id}", ctx -> {
            String id = ctx.pathParam("id");

            for (Map<String, String> company : COMPANIES) {
                if (Objects.equals(company.get("id"), id)) {
                    ctx.json(company);
                    return;
                }
            }

            ctx.status(404);
            ctx.result("Company not found");
        });
        // END

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
