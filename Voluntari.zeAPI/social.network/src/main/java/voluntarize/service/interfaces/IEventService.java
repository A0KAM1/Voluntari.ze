package voluntarize.service.interfaces;

import voluntarize.dto.PostDto;
import voluntarize.request.EventRequest;

public interface IEventService {
    PostDto createEvent(EventRequest request);
    boolean deleteEvent(Long id);
    boolean updateEvent(Long id, EventRequest request);
    void completeEvent(Long id);
    void cancelEvent(Long id);
    void confirmParticipation(Long event, Long volunteer);
    void confirmAbsence(Long event, Long volunteer);
    void subscribeToEvent(Long id, Long event);
    void abandonEvent(Long id, Long event);
}
