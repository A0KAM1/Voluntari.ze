package voluntarize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voluntarize.dto.EventDto;
import voluntarize.dto.ParticipantsDto;
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
    private ParticipantRepository _participantRepository;
    @Autowired
    private VolunteerRepository _volunteerRepository;
    @Autowired
    private PresenceRepository _presenceRepository;

    public PostDto createEvent(EventRequest request){
        Event event = _eventRepository.save(this.getEventEntity(request));
        Post post = this.getPostAttributes(request);
        post.setEvent(event);
        Post res = _postRepository.save(post);
        _pictureRepository.saveAll(this.getListOfPhotos(request.photos, res));
        return this.getPostDto(res);
    };

    public boolean deleteEvent(Long id){
        Optional<Event> event = _eventRepository.findById(id);
        if(event.isPresent()){
            List<Participant> participants = _participantRepository.findByEvent(event.get());
            Post post = _postRepository.findByEvent(event.get());
            List<Picture> pics = _pictureRepository.findByPost(post);
            List<Like> likes = _likeRepository.findByPost(post);
            _participantRepository.deleteAll(participants);
            _pictureRepository.deleteAll(pics);
            _likeRepository.deleteAll(likes);
            _postRepository.delete(post);
            _eventRepository.delete(event.get());
            return true;
        }
        return false;
    };

    public boolean updateEvent(Long id, EventRequest request){
        Optional<Event> oldEvent = _eventRepository.findById(id);
        if(oldEvent.isPresent()){
            Event res = getEventEntity(request);
            res.setId(id);
            _eventRepository.save(res);

            Post post = _postRepository.findByEvent(oldEvent.get());
            post.setContent(request.description);
            post.setEvent(res);
            _postRepository.save(post);

            List<Picture> pics = _pictureRepository.findByPost(post);
            _pictureRepository.deleteAll(pics);
            _pictureRepository.saveAll(getListOfPhotos(request.photos, post));
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
        Participant participant = _participantRepository.findParticipation(
                _volunteerRepository.findById(volunteer).orElseThrow(),
                _eventRepository.findById(id).orElseThrow()
        );
        participant.setPresence(_presenceRepository.findById(2L).orElseThrow());
        _participantRepository.save(participant);
    }

    public void confirmAbsence(Long id, Long volunteer){
        Participant participant = _participantRepository.findParticipation(
                _volunteerRepository.findById(volunteer).orElseThrow(),
                _eventRepository.findById(id).orElseThrow()
        );
        participant.setPresence(_presenceRepository.findById(3L).orElseThrow());
        _participantRepository.save(participant);
    }

    public void subscribeToEvent(Long id, Long volunteer){
        Volunteer person = _volunteerRepository.findById(volunteer).orElseThrow();
        Event event = _eventRepository.findById(id).orElseThrow();
        Presence presenceId = _presenceRepository.findById(1L).orElseThrow();
        Participant participate = new Participant();
        participate.setVolunteer(person);
        participate.setEvent(event);
        participate.setPresence(presenceId);
        _participantRepository.save(participate);
    }

    public void abandonEvent(Long id, Long volunteer){
        Participant participant = _participantRepository.findParticipation(
                _volunteerRepository.findById(volunteer).orElseThrow(),
                _eventRepository.findById(id).orElseThrow()
        );
        participant.setPresence(_presenceRepository.findById(4L).orElseThrow());
        _participantRepository.save(participant);
    }

    public List<ParticipantsDto> getListOfParticipants(Long id){
        Event event = _eventRepository.findById(id).orElseThrow();
        List<Participant> res = _participantRepository.findByEvent(event);

        return res.stream().map(this::getParticipantsListDto).collect(Collectors.toList());
    }

    public List<EventDto> getMyEvents(Long id){
        List<Event> res = _eventRepository.findByOng(_ongRepository.findById(id).orElseThrow());
        return res.stream().map(this::getEventDto).collect(Collectors.toList());
    }

    public List<EventDto> getMySubscriptions(Long id){
        List<Event> res = _eventRepository.findByVolunteer(_volunteerRepository.findById(id).orElseThrow());
        return res.stream().map(this::getEventDto).collect(Collectors.toList());
    }

    private Post getPostAttributes(EventRequest request){
        Optional<Ong> ong = this._ongRepository.findById(request.ongId);
        Post res = new Post();

        res.setContent(request.description);
        res.setOng(ong.orElseThrow());
        return res;
    }
    private Event getEventEntity(EventRequest request){
        Event res = new Event();
        res.setDate(request.date);
        res.setTime(Time.valueOf(request.time));
        res.setAddress(request.address);
        res.setRequirements(request.requirements);
        res.setStatus(_statusRepository.findById(1L).orElseThrow());
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
        int likes = _likeRepository.findByPost(post).size();

        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setContent(post.getContent());
        dto.setOngId(post.getOng().getId());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUpdatedAt(post.getUpdatedAt());
        if(post.getEvent() != null) dto.setEvent(getEventDto(post.getEvent()));
        dto.setPictures(pictures.stream().map(Picture::getUrl).collect(Collectors.toList()));
        dto.setLikes(likes);
        return dto;
    }
    private ParticipantsDto getParticipantsListDto(Participant participant){
        ParticipantsDto dto = new ParticipantsDto();
        dto.setName(participant.getVolunteer().getUser().getName());
        dto.setLastName(participant.getVolunteer().getLastName());
        dto.setPresence(participant.getPresence().getName());
        return dto;
    }
}
