@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostPage

@param PostPage page

<div>
    <h1>${page.getPost().getName()}</h1>

    <p>${page.getPost().getBody()}</p>

    <p>${page.getPost().getId()}</p>

    <a href="${NamedRoutes.postsPath()}">Все посты</a>
</div>


