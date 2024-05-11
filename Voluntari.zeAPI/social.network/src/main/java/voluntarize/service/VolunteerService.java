package voluntarize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voluntarize.dto.VolunteerDto;
import voluntarize.entity.*;
import voluntarize.repository.*;
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
    @Autowired
    private LikeRepository _likeRepository;
    @Autowired
    private PostRepository _postRepository;
    @Autowired
    private OngRepository _ongRepository;
    @Autowired
    private FollowerRepository _followerRepository;
    @Autowired
    private EventRepository _eventRepository;
    @Autowired
    private ParticipantRepository _participantRepository;
    @Autowired
    private PresenceRepository _presenceRepository;

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

    public boolean update(Long id, VolunteerRequest request){
        Optional<Volunteer> oldVolunteer = _volunteerRepository.findById(id);
        if(oldVolunteer.isPresent()){
            Optional<User> oldUser = _userRepository.findById(oldVolunteer.get().getUser().getId());

            User newUser = this.getUserAttributes(request);
            newUser.setId(oldUser.get().getId());
            Volunteer res = this.volunteerEntity(request, newUser);
            res.setId(id);

            _userRepository.save(newUser);
            _volunteerRepository.save(res);
            return true;
        }
        return false;
    }

    public void likePost(Long id, Long post){
        Like like = new Like();
        _volunteerRepository.findById(id).ifPresent(like::setVolunteer);
        _postRepository.findById(post).ifPresent(like::setPost);

        _likeRepository.save(like);
    }

    public void followOng(Long id, Long ongId){
        Follower follower = new Follower();
        _volunteerRepository.findById(id).ifPresent(follower::setVolunteer);
        _ongRepository.findById(ongId).ifPresent(follower::setOng);
        _followerRepository.save(follower);
    }

    public void subscribeToEvent(Long id, Long event){
        Participant participate = new Participant();
        participate.setVolunteer(_volunteerRepository.findById(id).orElseThrow());
        participate.setEvent(_eventRepository.findById(event).orElseThrow());
        participate.setPresence(_presenceRepository.findById(1L).orElseThrow());
        _participantRepository.save(participate);
    }

    public void abandonEvent(Long id, Long event){
        Participant participant = _participantRepository.findByEvent(_eventRepository.findById(event).orElseThrow());
        participant.setPresence(_presenceRepository.findById(4L).orElseThrow());
        _participantRepository.save(participant);
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
