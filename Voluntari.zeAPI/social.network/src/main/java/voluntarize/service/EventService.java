package voluntarize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voluntarize.dto.EventDto;
import voluntarize.dto.PostDto;
import voluntarize.entity.*;
import voluntarize.repository.*;
import voluntarize.request.EventRequest;
import voluntarize.service.interfaces.IEventService;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService implements IEventService {
    @Autowired
    private PostRepository _postRepository;
    @Autowired
    private EventRepository _eventRepository;
    @Autowired
    private PictureRepository _pictureRepository;
    @Autowired
    private LikeRepository _likeRepository;
    @Autowired
    private StatusRepository _statusRepository;
    @Autowired
    private OngRepository _ongRepository;
    @Autowired
    private PublicationRepository _publicationRepository;
    @Autowired
    private ParticipantRepository _participantRepository;
    @Autowired
    private VolunteerRepository _volunteerRepository;
    @Autowired
    private PresenceRepository _presenceRepository;

    public PostDto createEvent(EventRequest request){
        Post post = _postRepository.save(this.getPostAttributes(request));
        _eventRepository.save(this.getEventEntity(post, request));
        _pictureRepository.saveAll(this.getListOfPhotos(request.photos, post));
        return this.getPostDto(post);
    };

    public boolean deleteEvent(Long id){
        Optional<Event> event = _eventRepository.findById(id);
        if(event.isPresent()){
            _eventRepository.delete(event.get());








            List<Picture> pictures = _pictureRepository.findByPost(event.get().getPost());
            List<Like> likes = _likeRepository.findByPost(event.get().getPost());
            _pictureRepository.deleteAll(pictures);
            _likeRepository.deleteAll(likes);
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

    public void completeEvent(Long id){
        Event event = _eventRepository.findById(id).orElseThrow();
        event.setStatus(_statusRepository.findById(2L).orElseThrow());
        _eventRepository.save(event);
    }

    public void cancelEvent(Long id){
        Event event = _eventRepository.findById(id).orElseThrow();
        event.setStatus(_statusRepository.findById(3L).orElseThrow());
        _eventRepository.save(event);
    }

    public void confirmParticipation(Long id, Long volunteer){
        Participant participant = _participantRepository.findByVolunteer(_volunteerRepository.findById(volunteer).orElseThrow());
        participant.setPresence(_presenceRepository.findById(2L).orElseThrow());
        _participantRepository.save(participant);
    }

    public void confirmAbsence(Long id, Long volunteer){
        Participant participant = _participantRepository.findByVolunteer(_volunteerRepository.findById(volunteer).orElseThrow());
        participant.setPresence(_presenceRepository.findById(3L).orElseThrow());
        _participantRepository.save(participant);
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
    private EventDto getEventDto(Event event){
        EventDto res = new EventDto();
        res.setId(event.getId());
        res.setDate(event.getDate());
        res.setTime(event.getTime());
        res.setAddress(event.getAddress());
        res.setRequirements(event.getRequirements());
        res.setStatusId(event.getStatus().getId());
        return res;
    }
    private PostDto getPostDto(Post post){
        List<Picture> pictures = _pictureRepository.findByPost(post);
        Optional<Event> event = _eventRepository.findByPost(post);
        Optional<Publication> publication = _publicationRepository.findByPost(post);
        int likes = _likeRepository.findByPost(post).size();

        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setContent(post.getContent());
        dto.setOngId(post.getOng().getId());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUpdatedAt(post.getUpdatedAt());
        publication.ifPresentOrElse(p -> dto.setPublication(p.getId()), () -> dto.setPublication(null));
        event.ifPresentOrElse(p -> dto.setEvent(getEventDto(p)), () -> dto.setEvent(null));
        dto.setPictures(pictures.stream().map(Picture::getUrl).collect(Collectors.toList()));
        dto.setLikes(likes);
        return dto;
    }
}
