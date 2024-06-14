package voluntarize.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "BadRequest"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping()
    public ResponseEntity<PostViewModel> createEvent(@RequestBody EventRequest request){
        PostDto res = _eventService.createEvent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.getPostViewModel(res));
    }

    @Operation(summary = "delete event by id", tags = "Events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Deleted"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id){
        boolean res = _eventService.deleteEvent(id);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "update event by id", tags = "Events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Updated"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEvent(@PathVariable Long id, @RequestBody EventRequest request){
        boolean res = _eventService.updateEvent(id, request);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "complete event", tags = "Events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Updated"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/{id}/complete")
    public ResponseEntity<Void> completeEvent(@PathVariable Long id){
        _eventService.completeEvent(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "cancel event", tags = "Events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Updated"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelEvent(@PathVariable Long id){
        _eventService.cancelEvent(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "confirm volunteer participation", tags = "Events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Updated"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/{id}/participants/{volunteer}/present")
    public ResponseEntity<Void> confirmParticipation(@PathVariable Long id, @PathVariable Long volunteer){
        _eventService.confirmParticipation(id, volunteer);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "confirm volunteer absence", tags = "Events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Updated"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/{id}/participants/{volunteer}/absent")
    public ResponseEntity<Void> confirmAbsence(@PathVariable Long id, @PathVariable Long volunteer){
        _eventService.confirmAbsence(id, volunteer);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "subscribe to event", tags = "Events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Updated"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/{id}/{volunteer}/subscribe")
    public ResponseEntity<Void> subscribeToEvent(@PathVariable Long id, @PathVariable Long volunteer){
        _eventService.subscribeToEvent(id, volunteer);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "abandon event", tags = "Events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Updated"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/{id}/{volunteer}/abandon")
    public ResponseEntity<Void> abandonEvent(@PathVariable Long id, @PathVariable Long volunteer){
        _eventService.abandonEvent(id, volunteer);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "view event participants", tags = "Events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Found Data"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{id}/participants")
    public ResponseEntity<List<ParticipantsViewModel>> getParticipantsList(@PathVariable Long id){
        List<ParticipantsDto> res = _eventService.getListOfParticipants(id);
        return ResponseEntity.ok(res.stream().map(this::getParticipantViewModel).collect(Collectors.toList()));
    }

    @Operation(summary = "view my events", tags = "Events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Found Data"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{ong}")
    public ResponseEntity<List<EventViewModel>> viewMyEvents(@PathVariable Long ong){
        List<EventDto> res = _eventService.getMyEvents(ong);
        return ResponseEntity.ok(res.stream().map(this::getEventViewModel).collect(Collectors.toList()));
    }

    @Operation(summary = "view my subscriptions", tags = "Events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Found Data"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{volunteer}/subscriptions")
    public ResponseEntity<List<EventViewModel>> viewMySubscriptions(@PathVariable Long volunteer){
        List<EventDto> res = _eventService.getMySubscriptions(volunteer);
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
