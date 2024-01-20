package exercise.controller;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void post(Long id, Context ctx) {
        try {
            var post = PostRepository.find(id);

            if (post.isEmpty()) {
                throw new NotFoundResponse("Parameter Type cannot be null");
            }

            var page = new PostPage(post.get());

            ctx.render("posts/show.jte", Collections.singletonMap("page", page));
        } catch (NullPointerException e) {
            ctx.status(404);
            ctx.result("Page not found");
        }
    }

    public static void posts(Context ctx) {
        var currentPage = Integer.parseInt(Objects.requireNonNullElse(ctx.queryParam("page"), "1"));

        var start = (currentPage - 1) * 5;
        var end = start + 4;

        System.out.println(start);
        System.out.println(end);
        System.out.println(PostRepository.getEntities());

        List<Post> posts = PostRepository.getEntities().subList(start, end + 1);
        var page = new PostsPage(posts, currentPage);

        ctx.render("posts/index.jte", Collections.singletonMap("page", page));
    }
    // END
}
