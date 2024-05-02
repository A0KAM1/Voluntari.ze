package voluntarize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voluntarize.dto.EventDto;
import voluntarize.dto.OngDto;
import voluntarize.dto.PublicationDto;
import voluntarize.entity.*;
import voluntarize.repository.*;
import voluntarize.request.EventRequest;
import voluntarize.request.OngRequest;
import voluntarize.request.PublicationRequest;
import voluntarize.service.interfaces.OngServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OngService implements OngServiceInterface {

    @Autowired
    private OngRepository _ongRepository;
    @Autowired
    private UserRepository _userRepository;
    @Autowired
    private PostRepository _postRepository;
    @Autowired
    private PublicationRepository _publicationRepository;
    @Autowired
    private EventRepository _eventRepository;
    @Autowired
    private PictureRepository _pictureRepository;

    public OngDto createOng(OngRequest ong){
        User user = _userRepository.save(this.getUserAttributes(ong));
        Ong res = _ongRepository.save(this.ongToEntity(ong, user));
        return this.ongToDto(res);
    }

    public List<OngDto> searchOngs(String keyword, List<Long> category){
        List<Ong> res = _ongRepository.findByFilter(keyword, category);
        return res.stream().map(this::ongToDto).collect(Collectors.toList());
    }

    public OngDto findById(Long id){
        Optional<Ong> ong = _ongRepository.findById(id);
        //OngDto res = this.ongToDto(ong.get());
        if(ong.isPresent()) return this.ongToDto(ong.get());
        return null;
    }

    public boolean deleteOngById(Long id){
        OngDto res = this.findById(id);
        if(res != null) {
            _ongRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public OngDto updateOngById(Long id, OngRequest request){
        User user = this.getUserAttributes(request);
        Ong ong = this.ongToEntity(request, user);
        ong.setId(id);
        Ong res = _ongRepository.save(ong);
        return this.ongToDto(res);
    }

    public PublicationDto createPublication (PublicationRequest request){
        Post post = _postRepository.save(this.getPostAttributes(request));
        Publication res = _publicationRepository.save(this.toPublicationEntity(post));
        List<Picture> pictures = _pictureRepository.saveAll(this.getListOfPhotos(request.photos, post));
        return this.publicationToDto(res, pictures);
    }

    public boolean deletePublication(Long id){return false;};
    public boolean updatePublication(Long id, PublicationRequest request){return false;};
    public EventDto createEvent(EventRequest request){return null;};
    public boolean deleteEvent(Long id){return false;};
    public boolean updateEvent(Long id, EventRequest request){return false;};

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
    private Ong ongToEntity(OngRequest request, User user){
        Ong res = new Ong();
        res.setAddress(request.address);
        res.setGovernmentCode(request.cnpj);
        res.setUser(user);
        res.setQrCode(request.qrCode);
        return res;
    }
    private OngDto ongToDto(Ong ong){
        OngDto res = new OngDto();
        res.setId(ong.getId());
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
    private Post getPostAttributes(PublicationRequest request){
        Optional<Ong> ong = this._ongRepository.findById(request.ongId);
        Post res = new Post();

        res.setOng(ong.get());
        res.setContent(request.description);
        return res;
    }
    private Publication toPublicationEntity(Post post){
        Publication res = new Publication();
        res.setPost(post);
        return res;
    }
    private List<Picture> getListOfPhotos(List<String> photos, Post post){
        List<Picture> res = new ArrayList<>();
        photos.forEach(picture -> {
            Picture pic = new Picture();
            pic.setUrl(picture);
            pic.setPost(post);
            res.add(pic);
        });
        return res;
    }
    private PublicationDto publicationToDto(Publication publication, List<Picture> pictures){
        PublicationDto res = new PublicationDto();
        res.setId(publication.getId());
        res.setPhotos(pictures);
        res.setDescription(publication.getPost().getContent());
        res.setOngId(publication.getPost().getOng().getId());
        return res;
    }

}
