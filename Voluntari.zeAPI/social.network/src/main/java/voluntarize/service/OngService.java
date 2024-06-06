package voluntarize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voluntarize.dto.OngDto;
import voluntarize.dto.VolunteerDto;
import voluntarize.entity.*;
import voluntarize.repository.*;
import voluntarize.request.OngRequest;
import voluntarize.service.interfaces.IOngService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OngService implements IOngService {

    @Autowired
    private OngRepository _ongRepository;
    @Autowired
    private UserRepository _userRepository;
    @Autowired
    private TagRepository _tagRepository;
    @Autowired
    private CategoryRepository _categoryRepository;
    @Autowired
    private VolunteerRepository _volunteerRepository;

    public OngDto createOng(OngRequest ong){
        User user = _userRepository.save(this.getUserAttributes(ong));
        Ong res = _ongRepository.save(this.getOngEntity(ong, user));
        return this.getOngDto(res);
    }

    public List<OngDto> searchOngs(String keyword, List<Long> category){
        List<Ong> res = _ongRepository.findByFilter(keyword, category);
        return res.stream().map(this::getOngDto).collect(Collectors.toList());
    }

    public OngDto findById(Long id){
        Optional<Ong> res = _ongRepository.findById(id);
        return res.map(this::getOngDto).orElse(null);
    }

    public boolean deleteOngById(Long id){
        OngDto res = this.findById(id);
        if(res != null) {
            _ongRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateOngById(Long id, OngRequest request){
        Optional<Ong> oldOng = _ongRepository.findById(id);
        if(oldOng.isPresent()){
            User newUser = this.getUserAttributes(request);
            newUser.setId(oldOng.get().getUser().getId());
            Ong res = this.getOngEntity(request, newUser);
            res.setId(id);

            _userRepository.save(newUser);
            _ongRepository.save(res);
            return true;
        }
        return false;
    }

    public void addCategories(Long id, Long category){
        Tag tag = new Tag();
        tag.setOng(_ongRepository.findById(id).orElseThrow());
        tag.setCategory(_categoryRepository.findById(category).orElseThrow());
        _tagRepository.save(tag);
    }

    public List<VolunteerDto> followers(Long id){
        List<Volunteer> res = _volunteerRepository.findByOng(
                _ongRepository.findById(id).orElseThrow()
        );
        return res.stream().map(this::volunteerToDto).collect(Collectors.toList());
    }

    private User getUserAttributes(OngRequest request){
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
    private Ong getOngEntity(OngRequest request, User user){
        Ong res = new Ong();
        res.setAddress(request.address);
        res.setGovernmentCode(request.cnpj);
        res.setUser(user);
        res.setQrCode(request.qrCode);
        return res;
    }
    private OngDto getOngDto(Ong ong){
        OngDto res = new OngDto();
        res.setUserId(ong.getUser().getId());
        res.setOngId(ong.getId());
        res.setGovernmentCode(ong.getGovernmentCode());
        res.setAddress(ong.getAddress());
        res.setQrCode(ong.getQrCode());
        res.setName(ong.getUser().getName());
        res.setUsername(ong.getUser().getUsername());
        res.setEmail(ong.getUser().getEmail());
        res.setDescription(ong.getUser().getDescription());
        res.setPhoneNumber(ong.getUser().getPhoneNumber());
        res.setProfilePicture(ong.getUser().getProfilePicture());
        res.setCity(ong.getUser().getCity());
        res.setState(ong.getUser().getState());
        res.setCountry(ong.getUser().getCountry());

        return res;
    }
    private VolunteerDto volunteerToDto(Volunteer volunteer){
        VolunteerDto res = new VolunteerDto();
        res.setId(volunteer.getId());
        res.setUserId(volunteer.getUser().getId());
        res.setName(volunteer.getUser().getName());
        res.setLastName(volunteer.getLastName());
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