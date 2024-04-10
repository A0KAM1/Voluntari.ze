package voluntarize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voluntarize.entity.Ong;
import voluntarize.entity.Volunteer;
import voluntarize.repository.VolunteerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository _volunteerRepository;

    public Volunteer create(Volunteer volunteer){
        _volunteerRepository.save(volunteer);
        return volunteer;
    }
    public List<Volunteer> findAll(){
        return _volunteerRepository.findAll();
    }

    public Volunteer findById(Long id){
        Optional<Volunteer> res = _volunteerRepository.findById(id);
        if(res.isPresent()) return res.get();
        return null;
    }

    public boolean delete(Long id){
        Volunteer res = this.findById(id);
        if(res != null) {
            _volunteerRepository.delete(res);
            return true;
        }
        return false;
    }

    public Volunteer update(Long id, Volunteer volunteer){
        Volunteer res = this.findById(id);
        if(res != null) return _volunteerRepository.save(volunteer);
        return null;
    }
}
