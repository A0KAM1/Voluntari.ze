package voluntarize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voluntarize.entity.Volunteer;
import voluntarize.repository.VolunteerRepository;

@Service
public class UserService {

    @Autowired
    private VolunteerRepository _volunteerRepository;

    public Volunteer create(Volunteer volunteer){
        _volunteerRepository.save(volunteer);
        return volunteer;
    }
}
