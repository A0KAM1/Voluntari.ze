package voluntarize.service.interfaces;

import voluntarize.dto.OngDto;
import voluntarize.dto.PostDto;
import voluntarize.request.EventRequest;
import voluntarize.request.OngRequest;
import voluntarize.request.PublicationRequest;

import java.util.List;

public interface OngServiceInterface {
    OngDto createOng(OngRequest request);
    List<OngDto> searchOngs(String keyword, List<Long> category);
    OngDto findById(Long id);
    boolean deleteOngById(Long id);
    boolean updateOngById(Long id, OngRequest request);
    PostDto createPublication(PublicationRequest request);
    boolean deletePublication(Long id);
    boolean updatePublication(Long id, PublicationRequest request);
    PostDto createEvent(EventRequest request);
    boolean deleteEvent(Long id);
    boolean updateEvent(Long id, EventRequest request);
    List<PostDto> getPosts(Long id);

}
