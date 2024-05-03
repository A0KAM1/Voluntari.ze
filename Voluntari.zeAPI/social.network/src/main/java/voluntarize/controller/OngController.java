package voluntarize.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voluntarize.dto.EventDto;
import voluntarize.dto.OngDto;
import voluntarize.dto.PublicationDto;
import voluntarize.entity.Ong;
import voluntarize.entity.Publication;
import voluntarize.request.EventRequest;
import voluntarize.request.OngRequest;
import voluntarize.request.PublicationRequest;
import voluntarize.service.OngService;
import voluntarize.viewModel.EventViewModel;
import voluntarize.viewModel.OngSearchViewModel;
import voluntarize.viewModel.OngViewModel;
import voluntarize.viewModel.PublicationViewModel;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ongs")
public class OngController {

    @Autowired
    private OngService _ongService;

    @Operation(summary = "Create a new ong", tags = "Ong")
    @PostMapping
    public ResponseEntity<OngViewModel> create(@RequestBody OngRequest request){
        OngDto res = this._ongService.createOng(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.ongToViewModel(res));
    }

    @Operation(summary = "Find ongs", tags = "Ong")
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

    @Operation(summary = "Find ong by id", tags = "Ong")
    @GetMapping("/{id}")
    public ResponseEntity<OngViewModel> getOngById(@PathVariable Long id){
        OngDto res = _ongService.findById(id);
        return res != null ? ResponseEntity.ok(this.ongToViewModel(res)) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Update ong by id", tags = "Ong")
    @PutMapping("/{id}")
    public ResponseEntity<OngViewModel> updateOng(@PathVariable Long id, @RequestBody OngRequest ong){
        OngDto exists = _ongService.findById(id);
        if(exists == null) return ResponseEntity.notFound().build();
        OngDto res = _ongService.updateOngById(id, ong);
        return ResponseEntity.ok(this.ongToViewModel(res));
    }

    @Operation(summary = "Delete ong by id", tags = "Ong")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOng(@PathVariable Long id){
        boolean res = _ongService.deleteOngById(id);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create publication", tags = "Ong")
    @PostMapping("/posts/publications")
    public ResponseEntity<PublicationViewModel> createPublication(@RequestBody PublicationRequest request){
        PublicationDto res = _ongService.createPublication(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.publicationToViewModel(res));
    }

    @Operation(summary = "create event", tags = "Ong")
    @PostMapping("/posts/events")
    public ResponseEntity<EventViewModel> createEvent(@RequestBody EventRequest request){
        EventDto res = _ongService.createEvent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.getEventViewModel(res));
    }

    private OngSearchViewModel ongToSearchViewModel(OngDto ong){
        OngSearchViewModel res = new OngSearchViewModel();
        res.setId(ong.getId());
        res.setName(ong.getName());
        res.setUsername(ong.getUsername());
        res.setProfilePicture(ong.getProfilePicture());
        return res;
    }
    private OngViewModel ongToViewModel(OngDto ong){
        OngViewModel res = new OngViewModel();
        res.setId(ong.getId());
        res.setGovernmentCode(ong.getGovernmentCode());
        res.setAddress(ong.getAddress());
        res.setQrCode(ong.getQrCode());
        res.setName(ong.getName());
        res.setUsername(ong.getUsername());
        res.setEmail(ong.getEmail());
        res.setDescription(ong.getDescription());
        res.setPhoneNumber(ong.getPhoneNumber());
        res.setProfilePicture(ong.getProfilePicture());
        res.setCity(ong.getCity());
        res.setState(ong.getState());
        res.setCountry(ong.getCountry());
        return res;
    }
    private PublicationViewModel publicationToViewModel(PublicationDto publication){
        PublicationViewModel res = new PublicationViewModel();
        res.setId(publication.getId());
        res.setPhotos(publication.getPhotos());
        res.setDescription(publication.getDescription());
        res.setOngId(publication.getOngId());
        return res;
    }
    private EventViewModel getEventViewModel(EventDto event){
        EventViewModel res = new EventViewModel();
        res.setDate(event.getDate());
        res.setTime(event.getTime());
        res.setAddress(event.getAddress());
        res.setRequirements(event.getRequirements());
        res.setDescription(event.getDescription());
        res.setStatusId(event.getStatusId());
        res.setPostId(event.getPostId());
        res.setPhotos(event.getPhotos());
        return res;
    }

}
