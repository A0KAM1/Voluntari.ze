package voluntarize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voluntarize.entity.Ong;
import voluntarize.entity.User;
import voluntarize.entity.Volunteer;
import voluntarize.repository.UserRepository;
import voluntarize.repository.VolunteerRepository;
import voluntarize.request.OngRequest;
import voluntarize.request.VolunteerRequest;

import java.util.List;
import java.util.Optional;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository _volunteerRepository;
    @Autowired
    private UserRepository _userRepository;

    public Volunteer create(VolunteerRequest volunteer){
        User user = this.getUserAttributes(volunteer);
        _userRepository.save(user);
        Volunteer res = this.convertToEntity(volunteer, user);
        _volunteerRepository.save(res);
        return res;
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

    private User getUserAttributes(VolunteerRequest request){
        User res = new User();
        res.setEmail(request.email);
        res.setPassword(request.password);
        res.setUsername(request.username);
        res.setDescription(request.description);
        res.setName(request.name);
        res.setPhoneNumber(request.phoneNumber);
        res.setProfilePicture(request.profilePicture);
        res.setCity(request.city);
        res.setState(request.state);
        res.setCountry(request.country);
        return res;
    }

    private Volunteer convertToEntity(VolunteerRequest request, User user){
        Volunteer res = new Volunteer();
        res.setCpf(request.cpf);
        res.setLastName(request.lasName);
        res.setUser(user);
        res.setBirthday(request.birthday);
        res.setLevel(request.Level);
        return res;
    }
}
