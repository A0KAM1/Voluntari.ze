package voluntarize.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voluntarize.dto.VolunteerDto;
import voluntarize.request.VolunteerRequest;
import voluntarize.service.VolunteerService;
import voluntarize.viewModel.VolunteerViewModel;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/volunteers")
public class VolunteerController {

    @Autowired
    private VolunteerService _volunteerService;

    @Operation(summary = "create a new volunteer", tags = "Volunteer")
    @PostMapping
    public ResponseEntity<VolunteerViewModel> create(@RequestBody VolunteerRequest request){
        VolunteerDto res = _volunteerService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.volunteerToViewModel(res));
    }

    @Operation(summary = "find all volunteers", tags = "Volunteer")
    @GetMapping
    public ResponseEntity<List<VolunteerViewModel>> findAll(){
        List<VolunteerDto> res = _volunteerService.findAll();
        return res != null ? ResponseEntity.ok(res.stream().map(this::volunteerToViewModel).collect(Collectors.toList())) : ResponseEntity.noContent().build();
    }

    @Operation(summary = "update volunteer by id", tags = "Volunteer")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody VolunteerRequest request){
        boolean res = _volunteerService.update(id, request);
        return  res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "like publication", tags = "Volunteer")
    @PostMapping("/{id}/{post}")
    public ResponseEntity<Void> likePublication(@PathVariable Long id, @PathVariable Long post){
        _volunteerService.likePost(id, post);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "follow an ong", tags = "Volunteer")
    @PostMapping("/{me}/{ong}")
    public ResponseEntity<Void> followAnOng(@PathVariable Long me, @PathVariable Long ong){
        _volunteerService.followOng(me, ong);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "subscribe to event", tags = "Volunteer")
    @PostMapping("/{me}/{event}/subscribe")
    public ResponseEntity<Void> subscribeToEvent(@PathVariable Long me, @PathVariable Long event){
        _volunteerService.subscribeToEvent(me, event);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "abandon event", tags = "Volunteer")
    @PostMapping("/{me}/{event}/abandon")
    public ResponseEntity<Void> abandonEvent(@PathVariable Long me, @PathVariable Long event){
        _volunteerService.abandonEvent(me, event);
        return ResponseEntity.ok().build();
    }

    private VolunteerViewModel volunteerToViewModel(VolunteerDto volunteer){
        VolunteerViewModel res = new VolunteerViewModel();
        res.setId(volunteer.getId());
        res.setName(volunteer.getName());
        res.setEmail(volunteer.getEmail());
        res.setUsername(volunteer.getUsername());
        res.setDescription(volunteer.getDescription());
        res.setPhoneNumber(volunteer.getPhoneNumber());
        res.setProfilePicture(volunteer.getProfilePicture());
        res.setCity(volunteer.getCity());
        res.setState(volunteer.getState());
        res.setCountry(volunteer.getCountry());
        res.setCpf(volunteer.getCpf());
        res.setBirthday(volunteer.getBirthday());
        res.setLevel(volunteer.getLevel());
        return res;
    }

}
