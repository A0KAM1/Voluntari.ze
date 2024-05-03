package voluntarize.service.interfaces;

import voluntarize.dto.EventDto;
import voluntarize.dto.OngDto;
import voluntarize.dto.PublicationDto;
import voluntarize.request.EventRequest;
import voluntarize.request.OngRequest;
import voluntarize.request.PublicationRequest;

import java.util.List;

public interface OngServiceInterface {
    OngDto createOng(OngRequest request);
    List<OngDto> searchOngs(String keyword, List<Long> category);
    OngDto findById(Long id);
    boolean deleteOngById(Long id);
    OngDto updateOngById(Long id, OngRequest request);
    PublicationDto createPublication(PublicationRequest request);
    boolean deletePublication(Long id);
    boolean updatePublication(Long id, PublicationRequest request);
    EventDto createEvent(EventRequest request);
    boolean deleteEvent(Long id);
    boolean updateEvent(Long id, EventRequest request);
}
