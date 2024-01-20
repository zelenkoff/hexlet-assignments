package exercise.dto.posts;

import java.util.List;
import exercise.model.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;


// BEGIN
@Getter
@AllArgsConstructor
public class PostsPage {
    private List<Post> posts;
    private Integer currentPage;
}
// END


