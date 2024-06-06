package voluntarize.service.interfaces;

import voluntarize.dto.OngDto;
import voluntarize.dto.VolunteerDto;
import voluntarize.request.VolunteerRequest;

import java.util.List;

public interface IVolunteerService {
    VolunteerDto createVolunteer(VolunteerRequest request);
    List<VolunteerDto> findAll();
    VolunteerDto findById(Long id);
    boolean delete(Long id);
    boolean update(Long id, VolunteerRequest request);
    void likePost(Long volunteerId, Long postId);
    void followOng(Long volunteerId, Long ongId);
    void donate(Long id, float amount);
    List<OngDto> seeMyFollows(Long id);
}
