package voluntarize.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import voluntarize.entity.Publication;
import voluntarize.request.PublicationRequest;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Operation(summary = "Create publication", tags = "Ong")
    @PostMapping("/publication")
    public ResponseEntity<Publication> create(@RequestBody PublicationRequest request){

    }
}
