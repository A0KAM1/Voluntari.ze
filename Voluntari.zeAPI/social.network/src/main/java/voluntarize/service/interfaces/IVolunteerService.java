package voluntarize.service.interfaces;

import voluntarize.dto.VolunteerDto;
import voluntarize.request.VolunteerRequest;

public interface IVolunteerService {
    boolean createVolunteer(VolunteerRequest request);
    boolean updateVolunteer(Long id, VolunteerRequest request);
    boolean deleteVolunteer(Long id);
    VolunteerDto getById(Long id);
    void likePost(Long volunteerId, Long postId);
    void followOng(Long volunteerId, Long ongId);
    void donate(Long id, float amount);
}
