package voluntarize.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voluntarize.dto.OngDto;
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
    @PostMapping()
    public ResponseEntity<OngViewModel> create(@RequestBody OngRequest request){
        OngDto res = this._ongService.createOng(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.ongToViewModel(res));
    }

    @Operation(summary = "Find ongs", tags = "Ongs")
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
    @GetMapping("/{id}")
    public ResponseEntity<OngViewModel> getOngById(@PathVariable Long id){
        OngDto res = _ongService.findById(id);
        return res != null ? ResponseEntity.ok(this.ongToViewModel(res)) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Update ong by id", tags = "Ongs")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOng(@PathVariable Long id, @RequestBody OngRequest ong){
        boolean res = _ongService.updateOngById(id, ong);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete ong by id", tags = "Ongs")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOng(@PathVariable Long id){
        boolean res = _ongService.deleteOngById(id);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "add categories to ong", tags = "Ongs")
    @PostMapping("{id}/categories")
    public ResponseEntity<Void> addCategories(@PathVariable Long id, @RequestBody Long category){
        _ongService.addCategories(id, category);
        return ResponseEntity.ok().build();
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


}
