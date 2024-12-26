package voluntarize.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voluntarize.dto.OngDto;
import voluntarize.dto.VolunteerDto;
import voluntarize.request.VolunteerRequest;
import voluntarize.service.VolunteerService;
import voluntarize.viewModel.FollowingViewModel;
import voluntarize.viewModel.VolunteerViewModel;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/volunteers")
@CrossOrigin
public class VolunteerController {

    @Autowired
    private VolunteerService _volunteerService;

    @Operation(summary = "create a new volunteer", tags = "Volunteer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully Created Volunteer"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping
    public ResponseEntity<VolunteerViewModel> create(@RequestBody VolunteerRequest request){
        VolunteerDto res = _volunteerService.createVolunteer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.volunteerToViewModel(res));
    }

    @Operation(summary = "find all volunteers", tags = "Volunteer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Found"),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping
    public ResponseEntity<List<VolunteerViewModel>> findAll(){
        List<VolunteerDto> res = _volunteerService.findAll();
        return res != null ? ResponseEntity.ok(res.stream().map(this::volunteerToViewModel).collect(Collectors.toList())) : ResponseEntity.noContent().build();
    }

    @Operation(summary = "update volunteer by id", tags = "Volunteer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("/{me}")
    public ResponseEntity<Void> update(@PathVariable Long me, @RequestBody VolunteerRequest request){
        boolean res = _volunteerService.update(me, request);
        return  res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "like publication", tags = "Volunteer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully likes"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/{me}/{post}/like")
    public ResponseEntity<Void> likePublication(@PathVariable Long me, @PathVariable Long post){
        _volunteerService.likePost(me, post);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "follow an ong", tags = "Volunteer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Followed"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/{me}/{ong}/follow")
    public ResponseEntity<Void> followAnOng(@PathVariable Long me, @PathVariable Long ong){
        _volunteerService.followOng(me, ong);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "make a donation", tags = "Volunteer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Donated"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("{me}/donations")
    public ResponseEntity<Void> makeADonation(@PathVariable Long me, @RequestBody float amount){
        _volunteerService.donate(me, amount);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "see my follows", tags = "Volunteer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Found Follows"),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("{me}/following")
    public ResponseEntity<List<FollowingViewModel>> follows(@PathVariable Long me){
        List<OngDto> res = _volunteerService.seeMyFollows(me);
        return ResponseEntity.ok(res.stream().map(this::getFollowingViewModel).collect(Collectors.toList()));
    }

    private VolunteerViewModel volunteerToViewModel(VolunteerDto volunteer){
        VolunteerViewModel res = new VolunteerViewModel();
        res.setId(volunteer.getId());
        res.setName(volunteer.getName());
        res.setLastName(volunteer.getLastName());
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
    private FollowingViewModel getFollowingViewModel(OngDto dto){
        FollowingViewModel res = new FollowingViewModel();
        res.setId(dto.getOngId());
        res.setUserId(dto.getUserId());
        res.setName(dto.getName());
        res.setProfilePic(dto.getProfilePicture());
        return res;
    }

}
