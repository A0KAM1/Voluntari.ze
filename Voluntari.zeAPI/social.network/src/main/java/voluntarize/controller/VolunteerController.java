package voluntarize.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voluntarize.dto.VolunteerDto;
import voluntarize.entity.Volunteer;
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

    @Operation(summary = "Find all volunteers", tags = "Volunteer")
    @GetMapping
    public ResponseEntity<List<VolunteerViewModel>> findAll(){
        List<VolunteerDto> res = _volunteerService.findAll();
        return res != null ? ResponseEntity.ok(res.stream().map(this::volunteerToViewModel).collect(Collectors.toList())) : ResponseEntity.noContent().build();
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
