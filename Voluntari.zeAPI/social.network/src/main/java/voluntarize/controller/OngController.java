package voluntarize.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voluntarize.dto.OngDto;
import voluntarize.entity.Ong;
import voluntarize.entity.Publication;
import voluntarize.request.OngRequest;
import voluntarize.request.PublicationRequest;
import voluntarize.service.OngService;
import voluntarize.viewModel.OngSearchViewModel;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ongs")
public class OngController {

    @Autowired
    private OngService _ongService;

    @Operation(summary = "Create a new ong", tags = "Ong")
    @PostMapping
    public ResponseEntity<Ong> create(@RequestBody OngRequest request){
        boolean res = this._ongService.createOng(request);
        return res != false ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Find ongs", tags = "Ong")
    @GetMapping()
    public ResponseEntity<List<OngSearchViewModel>> searchOngs(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) List<Long> category){
        List<OngDto> ongs = _ongService.searchOngs(keyword, category);
        List<OngSearchViewModel> res = ongs.stream().map((o) -> ongToViewModel(o)).collect(Collectors.toList());
        return res != null ? ResponseEntity.ok(res) : ResponseEntity.noContent().build();
    }

    @Operation(summary = "Find ong by id", tags = "Ong")
    @GetMapping("/{id}")
    public ResponseEntity<OngDto> getOngById(@PathVariable Long id){
        OngDto res = _ongService.findById(id);
        return res != null ? ResponseEntity.ok(res) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Update ong by id", tags = "Ong")
    @PutMapping("/{id}")
    public ResponseEntity<Ong> updateOng(@PathVariable Long id, @RequestBody OngRequest ong){
        Ong res = _ongService.updateOngById(id, ong);
        return res != null ? ResponseEntity.ok(res) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete ong by id", tags = "Ong")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOng(@PathVariable Long id){
        boolean res = _ongService.deleteOngById(id);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

//    @Operation(summary = "Create publication", tags = "Ong")
//    @PostMapping("/post/publication")
//    public ResponseEntity<Publication> createPublication(@RequestBody PublicationRequest request){
//        _ongService.createPublication(request);
//    }

    private OngSearchViewModel ongToViewModel(OngDto ong){
        OngSearchViewModel res = new OngSearchViewModel();
        res.setId(ong.getId());
        res.setName(ong.getName());
        res.setUsername(ong.getUsername());
        res.setProfilePicture(ong.getProfilePicture());
        return res;
    }

}
