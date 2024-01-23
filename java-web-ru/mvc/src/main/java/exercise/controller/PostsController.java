package exercise.controller;

import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.function.Function;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.dto.posts.BuildPostPage;
import exercise.dto.posts.EditPostPage;
import exercise.util.NamedRoutes;

import io.javalin.http.Context;
import io.javalin.validation.ValidationException;
import io.javalin.http.NotFoundResponse;
import java.util.function.Consumer;

public class PostsController {

    public static void build(Context ctx) {
        var page = new BuildPostPage();
        ctx.render("posts/build.jte", Collections.singletonMap("page", page));
    }

    public static void showEditPage(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();

        PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new EditPostPage();
        ctx.render("posts/edit.jte", Collections.singletonMap("page", page));
    }

    private static void handlePostData(Context ctx, Consumer<Void> onSuccess, Consumer<ValidationException> onError) {
        try {
            var name = ctx.formParamAsClass("name", String.class)
                    .check(value -> value.length() >= 2, "Название не должно быть короче двух символов")
                    .get();

            var body = ctx.formParamAsClass("body", String.class)
                    .check(value -> value.length() >= 10, "Пост должен быть не короче 10 символов")
                    .get();

            var post = new Post(name, body);

            PostRepository.save(post);

            onSuccess.accept(null);
        } catch (ValidationException e) {
            onError.accept(e);
        }
    }

    public static void create(Context ctx) {
        Consumer<Void> onSuccessCreation = result -> {
            ctx.redirect(NamedRoutes.postsPath());
        };

        Consumer<ValidationException> onError = error -> {
            var name = ctx.formParam("name");
            var body = ctx.formParam("body");
            var page = new BuildPostPage(name, body, error.getErrors());
            ctx.render("posts/build.jte", Collections.singletonMap("page", page)).status(422);
        };

        PostsController.handlePostData(ctx, onSuccessCreation, onError);
    }

    public static void index(Context ctx) {
        var posts = PostRepository.getEntities();
        var postPage = new PostsPage(posts);
        ctx.render("posts/index.jte", Collections.singletonMap("page", postPage));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
            .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", Collections.singletonMap("page", page));
    }

    // BEGIN
    public static void edit(Context ctx) {
        Consumer<Void> onSuccessCreation = result -> {
            ctx.redirect(NamedRoutes.postsPath());
        };

        Consumer<ValidationException> onError = error -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var name = ctx.formParam("name");
            var body = ctx.formParam("body");
            var page = new EditPostPage(name, body, id, error.getErrors());
            ctx.render("posts/edit.jte", Collections.singletonMap("page", page)).status(422);
        };

        PostsController.handlePostData(ctx, onSuccessCreation, onError);
    }
    // END
}
