package voluntarize.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voluntarize.dto.PostDto;
import voluntarize.request.EventRequest;
import voluntarize.service.EventService;
import voluntarize.viewModel.EventViewModel;

@RestController
@RequestMapping("/events")
@CrossOrigin
public class EventController {
    @Autowired
    private EventService _eventService;

    @Operation(summary = "create event", tags = "Posts")
    @PostMapping()
    public ResponseEntity<EventViewModel> createEvent(@RequestBody EventRequest request){
        PostDto res = _eventService.createEvent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.getEventViewModel(res));
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
    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> completeEvent(@PathVariable Long id){
        _eventService.completeEvent(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "cancel event", tags = "Events")
    @PostMapping("/{id}/complete")
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

    private EventViewModel getEventViewModel(PostDto dto){
        EventViewModel res = new EventViewModel();
        res.setId(dto.getEvent().getId());
        res.setDescription(dto.getContent());
        res.setPictures(dto.getPictures());
        res.setDate(dto.getEvent().getDate());
        res.setTime(dto.getEvent().getTime());
        res.setAddress(dto.getEvent().getAddress());
        res.setRequirements(dto.getEvent().getRequirements());
        res.setStatusId(dto.getEvent().getStatusId());
        return res;
    }
}
