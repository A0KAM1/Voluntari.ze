package voluntarize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voluntarize.dto.VolunteerDto;
import voluntarize.entity.Ong;
import voluntarize.entity.User;
import voluntarize.entity.Volunteer;
import voluntarize.repository.UserRepository;
import voluntarize.repository.VolunteerRepository;
import voluntarize.request.OngRequest;
import voluntarize.request.VolunteerRequest;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository _volunteerRepository;
    @Autowired
    private UserRepository _userRepository;

    public VolunteerDto create(VolunteerRequest request){
        User user = _userRepository.save(this.getUserAttributes(request));
        Volunteer res = _volunteerRepository.save(this.volunteerEntity(request, user));
        return this.volunteerToDto(res);
    }

    public List<VolunteerDto> findAll(){
        List<Volunteer> res = _volunteerRepository.findAll();
        return res.stream().map(this::volunteerToDto).collect(Collectors.toList());
    }

    public VolunteerDto findById(Long id){
        Optional<Volunteer> res = _volunteerRepository.findById(id);
        if(res.isPresent()) return this.volunteerToDto(res.get());
        return null;
    }

    public boolean delete(Long id){
        VolunteerDto res = this.findById(id);
        if(res != null) {
            _volunteerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public VolunteerDto update(Long id, VolunteerRequest request){
        Optional<Volunteer> test = _volunteerRepository.findById(id);
        Optional<User> exists = _userRepository.findById(test.get().getUser().getId());

        User user = this.getUserAttributes(request);
        user.setId(exists.get().getId());
        Volunteer volunteer = this.volunteerEntity(request, user);
        volunteer.setId(id);

        _userRepository.save(user);
        Volunteer res = _volunteerRepository.save(volunteer);

        return this.volunteerToDto(res);
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

    private Volunteer volunteerEntity(VolunteerRequest request, User user){
        Volunteer res = new Volunteer();
        res.setCpf(request.cpf);
        res.setLastName(request.lasName);
        res.setUser(user);
        res.setBirthday(request.birthday);
        res.setLevel(1);
        return res;
    }

    private VolunteerDto volunteerToDto(Volunteer volunteer){
        VolunteerDto res = new VolunteerDto();
        res.setId(volunteer.getId());
        res.setName(volunteer.getUser().getName());
        res.setEmail(volunteer.getUser().getEmail());
        res.setUsername(volunteer.getUser().getUsername());
        res.setDescription(volunteer.getUser().getDescription());
        res.setPhoneNumber(volunteer.getUser().getPhoneNumber());
        res.setProfilePicture(volunteer.getUser().getProfilePicture());
        res.setCity(volunteer.getUser().getCity());
        res.setState(volunteer.getUser().getState());
        res.setCountry(volunteer.getUser().getCountry());
        res.setCpf(volunteer.getCpf());
        res.setBirthday(volunteer.getBirthday());
        res.setLevel(volunteer.getLevel());
        return res;
    }
}
