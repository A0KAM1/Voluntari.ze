package voluntarize.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voluntarize.entity.Volunteer;
import voluntarize.request.VolunteerRequest;
import voluntarize.service.VolunteerService;

import java.util.List;

@RestController
@RequestMapping("/volunteers")
public class VolunteerController {

    @Autowired
    private VolunteerService _volunteerService;

    @Operation(summary = "create a new volunteer", tags = "Volunteer")
    @PostMapping
    public ResponseEntity<Volunteer> create(@RequestBody VolunteerRequest volunteer){
        return ResponseEntity.status(HttpStatus.CREATED).body(_volunteerService.create(volunteer));
    }

    @Operation(summary = "Find all volunteers", tags = "Volunteer")
    @GetMapping
    public ResponseEntity<List<Volunteer>> findAll(){
        List<Volunteer> res = _volunteerService.findAll();
        return res != null ? ResponseEntity.ok(res) : ResponseEntity.noContent().build();
    }

}
