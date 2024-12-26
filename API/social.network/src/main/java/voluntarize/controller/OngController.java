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
import voluntarize.request.OngRequest;
import voluntarize.service.OngService;
import voluntarize.viewModel.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ongs")
@CrossOrigin
public class OngController {

    @Autowired
    private OngService _ongService;

    @Operation(summary = "Create a new ong", tags = "Ongs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ong successfully created"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
            @ApiResponse(responseCode = "400", description = "BadRequest")
    })
    @PostMapping()
    public ResponseEntity<OngViewModel> create(@RequestBody OngRequest request){
        OngDto res = this._ongService.createOng(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.ongToViewModel(res));
    }

    @Operation(summary = "Find ongs", tags = "Ongs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found data"),
            @ApiResponse(responseCode = "204", description = "Successfully found empty"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping()
    public ResponseEntity<List<OngSearchViewModel>> searchOngs(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) List<Long> category){

        List<OngDto> ongs = this._ongService.searchOngs(keyword, category);
        if(ongs != null){
            List<OngSearchViewModel> res = ongs.stream().map(this::ongToSearchViewModel)
                                            .collect(Collectors.toList());
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Find ong by id", tags = "Ongs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found ong"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OngViewModel> getOngById(@PathVariable Long id){
        OngDto res = _ongService.findById(id);
        return res != null ? ResponseEntity.ok(this.ongToViewModel(res)) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Update ong by id", tags = "Ongs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated ong"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{me}")
    public ResponseEntity<Void> updateOng(@PathVariable Long me, @RequestBody OngRequest ong){
        boolean res = _ongService.updateOngById(me, ong);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete ong by id", tags = "Ongs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted ong"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{me}")
    public ResponseEntity<Void> deleteOng(@PathVariable Long me){
        boolean res = _ongService.deleteOngById(me);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "add categories to ong", tags = "Ongs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added category to Ong"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("{me}/categories")
    public ResponseEntity<Void> addCategories(@PathVariable Long me, @RequestParam Long category){
        boolean res = _ongService.addCategories(me, category);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "view my followers", tags = "Ongs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found data"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "204", description = "Successfully found empty"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("{me}/followers")
    public ResponseEntity<List<FollowersViewModel>> getMyFollowers(@PathVariable Long me){
        List<VolunteerDto> res = _ongService.followers(me);
        return ResponseEntity.ok(res.stream().map(this::getFollowerViewModel).collect(Collectors.toList()));
    }

    private OngSearchViewModel ongToSearchViewModel(OngDto dto){
        OngSearchViewModel res = new OngSearchViewModel();
        res.setId(dto.getOngId());
        res.setUserId(dto.getUserId());
        res.setName(dto.getName());
        res.setUsername(dto.getUsername());
        res.setProfilePicture(dto.getProfilePicture());
        return res;
    }
    private OngViewModel ongToViewModel(OngDto dto){
        OngViewModel res = new OngViewModel();
        res.setUserId(dto.getUserId());
        res.setOngId(dto.getOngId());
        res.setGovernmentCode(dto.getGovernmentCode());
        res.setAddress(dto.getAddress());
        res.setQrCode(dto.getQrCode());
        res.setName(dto.getName());
        res.setUsername(dto.getUsername());
        res.setEmail(dto.getEmail());
        res.setDescription(dto.getDescription());
        res.setPhoneNumber(dto.getPhoneNumber());
        res.setProfilePicture(dto.getProfilePicture());
        res.setCity(dto.getCity());
        res.setState(dto.getState());
        res.setCountry(dto.getCountry());
        return res;
    }
    private FollowersViewModel getFollowerViewModel(VolunteerDto dto){
        FollowersViewModel res = new FollowersViewModel();
        res.setUser(dto.getUserId());
        res.setVolunteer(dto.getId());
        res.setName(dto.getName());
        res.setLastName(dto.getLastName());
        return res;
    }


}
