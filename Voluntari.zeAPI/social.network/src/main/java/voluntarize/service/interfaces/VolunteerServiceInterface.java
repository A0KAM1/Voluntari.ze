package voluntarize.service.interfaces;

import voluntarize.dto.VolunteerDto;
import voluntarize.request.VolunteerRequest;

public interface VolunteerServiceInterface {
    boolean createVolunteer(VolunteerRequest request);
    boolean updateVolunteer(Long id, VolunteerRequest request);
    boolean deleteVolunteer(Long id);
    VolunteerDto getById(Long id);
}
