package voluntarize.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voluntarize.entity.Ong;
import voluntarize.service.OngService;

@RestController
@RequestMapping("/ongs")
public class OngController {

    @Autowired
    private OngService _ongService;

    @Operation(summary = "create a new ong", tags = "Ong")
    @PostMapping
    public ResponseEntity<Ong> create(@RequestBody Ong ong){
        return ResponseEntity.status(HttpStatus.CREATED).body(_ongService.create(ong));
    }

    @Operation(summary = "get all ongs", tags = "Ong")
    @GetMapping
    public ResponseEntity<List<Ong>> findAll(){
        return ResponseEntity.status.(HttpStatus.ok).body(_ongService.findAll());
    }
}
