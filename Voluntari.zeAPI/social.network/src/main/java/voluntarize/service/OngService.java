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

import javax.swing.text.html.Option;
import java.sql.Time;
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
    @Autowired
    private StatusRepository _statusRepository;

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
        Optional<Ong> res = _ongRepository.findById(id);
        if(res.isPresent()) return this.ongToDto(res.get());
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

    public boolean updateOngById(Long id, OngRequest request){
        Optional<Ong> oldOng = _ongRepository.findById(id);
        if(oldOng.isPresent()){
            User newUser = this.getUserAttributes(request);
            newUser.setId(oldOng.get().getUser().getId());
            Ong res = this.ongToEntity(request, newUser);
            res.setId(id);

            _userRepository.save(newUser);
            _ongRepository.save(res);
            return true;
        }
        return false;
    }

    public PublicationDto createPublication (PublicationRequest request){
        Post post = _postRepository.save(this.getPostAttributesFromPublication(request));
        Publication res = _publicationRepository.save(this.toPublicationEntity(post));
        _pictureRepository.saveAll(this.getListOfPhotos(request.photos, post));
        return this.publicationToDto(res, request.photos);
    }

    public boolean deletePublication(Long id){
        Optional<Publication> publication = _publicationRepository.findById(id);
        if(publication.isPresent()){
            _publicationRepository.delete(publication.get());
            List<Picture> pictures = _pictureRepository.findByPost(publication.get().getPost());
            _pictureRepository.deleteAll(pictures);
            _postRepository.delete(publication.get().getPost());
            return true;
        }
        return false;
    };

    public boolean updatePublication(Long id, PublicationRequest request){
        Optional<Publication> oldPublication = _publicationRepository.findById(id);
        if(oldPublication.isPresent()){
            Post newPost = this.getPostAttributesFromPublication(request);
            newPost.setId(oldPublication.get().getPost().getId());
            Publication res = this.toPublicationEntity(newPost);
            res.setId(id);
            List<Picture> oldPictures = _pictureRepository.findByPost(oldPublication.get().getPost());
            _pictureRepository.deleteAll(oldPictures);

            _pictureRepository.saveAll(this.getListOfPhotos(request.photos, newPost));
            _postRepository.save(newPost);
            _publicationRepository.save(res);
            return true;
        }
        return false;
    };

    public EventDto createEvent(EventRequest request){
        Post post = _postRepository.save(this.getPostAttributes(request));
        Event res = _eventRepository.save(this.getEventEntity(post, request));
        _pictureRepository.saveAll(this.getListOfPhotos(request.photos, post));
        return this.eventToDto(res, request.photos);
    };

    public boolean deleteEvent(Long id){
        Optional<Event> event = _eventRepository.findById(id);
        if(event.isPresent()){
            _eventRepository.delete(event.get());
            List<Picture> pictures = _pictureRepository.findByPost(event.get().getPost());
            _pictureRepository.deleteAll(pictures);
            _postRepository.delete(event.get().getPost());
            return true;
        }
        return false;
    };

    public boolean updateEvent(Long id, EventRequest request){
        Optional<Event> oldEvent = _eventRepository.findById(id);
        if(oldEvent.isPresent()){
            Post newPost = this.getPostAttributes(request);
            newPost.setId(oldEvent.get().getPost().getId());
            Event res = this.getEventEntity(newPost, request);
            res.setId(id);
            List<Picture> oldPictures = _pictureRepository.findByPost(oldEvent.get().getPost());
            _pictureRepository.deleteAll(oldPictures);

            _pictureRepository.saveAll(this.getListOfPhotos(request.photos, newPost));
            _postRepository.save(newPost);
            _eventRepository.save(res);
            return true;
        }
        return false;
    };

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
    private Post getPostAttributesFromPublication(PublicationRequest request){
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
    private PublicationDto publicationToDto(Publication publication, List<String> pictures){
        PublicationDto res = new PublicationDto();
        res.setId(publication.getId());
        res.setPhotos(pictures);
        res.setDescription(publication.getPost().getContent());
        res.setOngId(publication.getPost().getOng().getId());
        res.setPostId(publication.getPost().getId());
        return res;
    }
    private Post getPostAttributes(EventRequest request){
        Optional<Ong> ong = this._ongRepository.findById(request.ongId);
        Post res = new Post();

        res.setOng(ong.get());
        res.setContent(request.description);
        return res;
    }
    private Event getEventEntity(Post post, EventRequest request){
        Optional<Status> status = _statusRepository.findById(1L);
        Event res = new Event();
        res.setDate(request.date);
        res.setTime(Time.valueOf(request.time));
        res.setAddress(request.address);
        res.setRequirements(request.requirements);
        res.setStatus(status.get());
        res.setPost(post);
        return res;
    }
    private EventDto eventToDto(Event event, List<String> pictures){
        EventDto res = new EventDto();
        res.setId(event.getId());
        res.setDate(event.getDate());
        res.setTime(event.getTime());
        res.setAddress(event.getAddress());
        res.setRequirements(event.getRequirements());
        res.setDescription(event.getPost().getContent());
        res.setStatusId(event.getStatus().getId());
        res.setPostId(event.getPost().getId());
        res.setPhotos(pictures);
        return res;
    }

}
