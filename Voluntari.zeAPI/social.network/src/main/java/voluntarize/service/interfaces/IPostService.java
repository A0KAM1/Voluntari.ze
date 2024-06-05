package voluntarize.service.interfaces;

import voluntarize.dto.PostDto;
import voluntarize.request.PublicationRequest;

import java.util.List;

public interface IPostService {
    PostDto createPublication(PublicationRequest request);
    boolean deletePublication(Long id);
    boolean updatePublication(Long id, PublicationRequest request);
    List<PostDto> getPosts(Long id);
}
