package voluntarize.service.interfaces;

import voluntarize.dto.PostDto;
import voluntarize.request.PostRequest;

import java.util.List;

public interface IPostService {
    PostDto createPost(PostRequest request);
    boolean deletePost(Long id);
    boolean updatePost(Long id, PostRequest request);
    List<PostDto> getPostsByUser(Long id);
    List<PostDto> getPosts();
}
