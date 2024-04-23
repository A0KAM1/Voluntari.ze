package voluntarize.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voluntarize.entity.Ong;
import voluntarize.request.OngRequest;
import voluntarize.service.OngService;

import java.util.List;

@RestController
@RequestMapping("/ongs")
public class OngController {

    @Autowired
    private OngService _ongService;

    @Operation(summary = "Create a new ong", tags = "Ong")
    @PostMapping
    public ResponseEntity<Ong> create(@RequestBody OngRequest ong){
        return ResponseEntity.status(HttpStatus.CREATED).body(_ongService.create(ong));
    }

    @Operation(summary = "Find ongs", tags = "Ong")
    @GetMapping()
    public ResponseEntity<List<Ong>> findByFilter(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) List<Long> category){
        List<Ong> res = _ongService.findByFilter(keyword, category);
        return res != null ? ResponseEntity.ok(res) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Find ong by id", tags = "Ong")
    @GetMapping("/{id}")
    public ResponseEntity<Ong> findById(@PathVariable Long id){
        Ong res = _ongService.findById(id);
        return res != null ? ResponseEntity.ok(res) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Update ong by id", tags = "Ong")
    @PutMapping("/{id}")
    public ResponseEntity<Ong> update(@PathVariable Long id, @RequestBody Ong ong){
        Ong res = _ongService.update(id, ong);
        return res != null ? ResponseEntity.ok(res) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete ong by id", tags = "Ong")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        boolean res = _ongService.delete(id);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
