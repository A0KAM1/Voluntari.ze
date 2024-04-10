package voluntarize.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voluntarize.entity.Volunteer;
import voluntarize.service.VolunteerService;

@RestController
@RequestMapping("/volunteers")
public class VolunteerController {

    @Autowired
    private VolunteerService _volunteerService;

    @Operation(summary = "create a new volunteer", tags = "Volunteer")
    @PostMapping
    public ResponseEntity<Volunteer> create(@RequestBody Volunteer volunteer){
        return ResponseEntity.status(HttpStatus.CREATED).body(_volunteerService.create(volunteer));
    }

}
