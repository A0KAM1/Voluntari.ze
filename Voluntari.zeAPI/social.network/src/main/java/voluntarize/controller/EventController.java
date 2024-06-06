package voluntarize.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voluntarize.dto.EventDto;
import voluntarize.dto.ParticipantsDto;
import voluntarize.dto.PostDto;
import voluntarize.request.EventRequest;
import voluntarize.service.EventService;
import voluntarize.viewModel.EventViewModel;
import voluntarize.viewModel.ParticipantsViewModel;
import voluntarize.viewModel.PostViewModel;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
@CrossOrigin
public class EventController {
    @Autowired
    private EventService _eventService;

    @Operation(summary = "create event", tags = "Events")
    @PostMapping()
    public ResponseEntity<PostViewModel> createEvent(@RequestBody EventRequest request){
        PostDto res = _eventService.createEvent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.getPostViewModel(res));
    }

    @Operation(summary = "delete event by id", tags = "Events")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id){
        boolean res = _eventService.deleteEvent(id);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "update event by id", tags = "Events")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEvent(@PathVariable Long id, @RequestBody EventRequest request){
        boolean res = _eventService.updateEvent(id, request);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "complete event", tags = "Events")
    @PostMapping("/{id}/complete")
    public ResponseEntity<Void> completeEvent(@PathVariable Long id){
        _eventService.completeEvent(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "cancel event", tags = "Events")
    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelEvent(@PathVariable Long id){
        _eventService.cancelEvent(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "confirm volunteer participation", tags = "Events")
    @PostMapping("/{id}/participants/{volunteer}/present")
    public ResponseEntity<Void> confirmParticipation(@PathVariable Long id, @PathVariable Long volunteer){
        _eventService.confirmParticipation(id, volunteer);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "confirm volunteer absence", tags = "Events")
    @PostMapping("/{id}/participants/{volunteer}/absent")
    public ResponseEntity<Void> confirmAbsence(@PathVariable Long id, @PathVariable Long volunteer){
        _eventService.confirmAbsence(id, volunteer);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "subscribe to event", tags = "Events")
    @PostMapping("/{id}/{volunteer}/subscribe")
    public ResponseEntity<Void> subscribeToEvent(@PathVariable Long id, @PathVariable Long volunteer){
        _eventService.subscribeToEvent(volunteer, id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "abandon event", tags = "Events")
    @PostMapping("/{id}/{volunteer}/abandon")
    public ResponseEntity<Void> abandonEvent(@PathVariable Long id, @PathVariable Long volunteer){
        _eventService.abandonEvent(volunteer, id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "view event participants", tags = "Events")
    @GetMapping("/{id}/participants")
    public ResponseEntity<List<ParticipantsViewModel>> getParticipantsList(@PathVariable Long id){
        List<ParticipantsDto> res = _eventService.getListOfParticipants(id);
        return ResponseEntity.ok(res.stream().map(this::getParticipantViewModel).collect(Collectors.toList()));
    }

    @Operation(summary = "view my events", tags = "Events")
    @GetMapping("/{me}")
    public ResponseEntity<List<EventViewModel>> viewMyEvents(@PathVariable Long me){
        List<EventDto> res = _eventService.getMyEvents(me);
        return ResponseEntity.ok(res.stream().map(this::getEventViewModel).collect(Collectors.toList()));
    }

    private EventViewModel getEventViewModel(EventDto dto){
        EventViewModel res = new EventViewModel();
        res.setId(dto.getId());
        res.setDate(dto.getDate());
        res.setTime(dto.getTime());
        res.setAddress(dto.getAddress());
        res.setRequirements(dto.getRequirements());
        res.setStatusId(dto.getStatusId());
        return res;
    }
    private PostViewModel getPostViewModel(PostDto dto){
        PostViewModel res = new PostViewModel();
        res.setId(dto.getId());
        res.setOngId(dto.getOngId());
        res.setContent(dto.getContent());
        res.setPictures(dto.getPictures());
        if(dto.getEvent() != null) res.setEvent(getEventViewModel(dto.getEvent()));
        res.setCreatedAt(dto.getCreatedAt());
        res.setUpdatedAt(dto.getUpdatedAt());
        res.setLikes(dto.getLikes());
        return res;
    }
    private ParticipantsViewModel getParticipantViewModel(ParticipantsDto dto){
        ParticipantsViewModel res = new ParticipantsViewModel();
        res.setName(dto.getName());
        res.setLastName(dto.getLastName());
        res.setPresenceCheck(dto.getPresence());
        return res;
    }
}
