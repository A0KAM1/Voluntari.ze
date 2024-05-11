package voluntarize.service.interfaces;

import voluntarize.dto.VolunteerDto;
import voluntarize.request.VolunteerRequest;

public interface VolunteerServiceInterface {
    boolean createVolunteer(VolunteerRequest request);
    boolean updateVolunteer(Long id, VolunteerRequest request);
    boolean deleteVolunteer(Long id);
    VolunteerDto getById(Long id);
    void likePost(Long volunteerId, Long postId);
    void followOng(Long volunteerId, Long ongId);
    void subscribeToEvent(Long id, Long event);
    void abandonEvent(Long id, Long event);
}
