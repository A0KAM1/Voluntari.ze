package voluntarize.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voluntarize.dto.EventDto;
import voluntarize.dto.OngDto;
import voluntarize.dto.PostDto;
import voluntarize.request.EventRequest;
import voluntarize.request.OngRequest;
import voluntarize.request.PublicationRequest;
import voluntarize.service.OngService;
import voluntarize.viewModel.*;

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
    public ResponseEntity<Void> updateOng(@PathVariable Long id, @RequestBody OngRequest ong){
        boolean res = _ongService.updateOngById(id, ong);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete ong by id", tags = "Ong")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOng(@PathVariable Long id){
        boolean res = _ongService.deleteOngById(id);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create publication", tags = "Ong")
    @PostMapping("/posts/publications")
    public ResponseEntity<PostViewModel> createPublication(@RequestBody PublicationRequest request){
        PostDto res = _ongService.createPublication(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.getPostViewModel(res));
    }

    @Operation(summary = "delete publication by id", tags = "Ong")
    @DeleteMapping("/posts/publications/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable Long id){
        boolean res = _ongService.deletePublication(id);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "update publication by id", tags = "Ong")
    @PutMapping("/posts/publications/{id}")
    public ResponseEntity<Void> updatePublication(@PathVariable Long id, @RequestBody PublicationRequest request){
        boolean res = _ongService.updatePublication(id, request);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "create event", tags = "Ong")
    @PostMapping("/posts/events")
    public ResponseEntity<PostViewModel> createEvent(@RequestBody EventRequest request){
        PostDto res = _ongService.createEvent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.getPostViewModel(res));
    }

    @Operation(summary = "delete event by id", tags = "Ong")
    @DeleteMapping("/posts/events/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id){
        boolean res = _ongService.deleteEvent(id);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "update event by id", tags = "Ong")
    @PutMapping("/posts/events/{id}")
    public ResponseEntity<Void> updateEvent(@PathVariable Long id, @RequestBody EventRequest request){
        boolean res = _ongService.updateEvent(id, request);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "get posts by ong", tags = "Ong")
    @GetMapping("/{id}/posts")
    public ResponseEntity<List<PostViewModel>> getPosts(@PathVariable Long id){
        List<PostDto> posts = _ongService.getPosts(id);
        if(posts != null){
            List<PostViewModel> res = posts.stream().map(this::getPostViewModel)
                                        .collect(Collectors.toList());
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.noContent().build();
    }

    private OngSearchViewModel ongToSearchViewModel(OngDto dto){
        OngSearchViewModel res = new OngSearchViewModel();
        res.setId(dto.getId());
        res.setName(dto.getName());
        res.setUsername(dto.getUsername());
        res.setProfilePicture(dto.getProfilePicture());
        return res;
    }
    private OngViewModel ongToViewModel(OngDto dto){
        OngViewModel res = new OngViewModel();
        res.setId(dto.getId());
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
        res.setContent(dto.getContent());
        res.setOngId(dto.getOngId());
        res.setCreatedAt(dto.getCreatedAt());
        res.setUpdatedAt(dto.getUpdatedAt());
        res.setPublication(dto.getPublication());
        res.setEvent(this.getEventViewModel(dto.getEvent()));
        res.setPictures(dto.getPictures());
        res.setLikes(dto.getLikes());
        return res;
    }

}
