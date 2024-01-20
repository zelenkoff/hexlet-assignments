@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostsPage

@param PostsPage page

@if(page.getPosts().isEmpty())
    <p>Пока не добавлено ни одного поста</p>
@else
    @for(var post : page.getPosts())
        <div>
            <h2><a href="${NamedRoutes.postPath(Long.toString(post.getId()))}">${post.getName()}</a></h2>
            <p>${post.getBody()}</p>
        </div>
    @endfor

    @if(page.getCurrentPage() == 1)
        <a href="${NamedRoutes.postsPath(String.valueOf(page.getCurrentPage() + 1))}">Вперед</a>
    @else
        <a href="${NamedRoutes.postsPath(String.valueOf(page.getCurrentPage() - 1))}">Назад</a>
        <a href="${NamedRoutes.postsPath(String.valueOf(page.getCurrentPage() + 1))}">Вперед</a>
    @endif
@endif


