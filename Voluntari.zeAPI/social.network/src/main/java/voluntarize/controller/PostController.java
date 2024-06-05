package voluntarize.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voluntarize.dto.PostDto;
import voluntarize.request.PublicationRequest;
import voluntarize.service.PostService;
import voluntarize.viewModel.EventViewModel;
import voluntarize.viewModel.PostViewModel;
import voluntarize.viewModel.PublicationViewModel;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {
    @Autowired
    private PostService _postService;

    @Operation(summary = "Create publication", tags = "Posts")
    @PostMapping()
    public ResponseEntity<PublicationViewModel> createPublication(@RequestBody PublicationRequest request){
        PostDto res = _postService.createPublication(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.getPublicationViewModel(res));
    }

    @Operation(summary = "delete publication by id", tags = "Posts")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable Long id){
        boolean res = _postService.deletePublication(id);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "update publication by id", tags = "Posts")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePublication(@PathVariable Long id, @RequestBody PublicationRequest request){
        boolean res = _postService.updatePublication(id, request);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "get posts by ong", tags = "Posts")
    @GetMapping("/{me}")
    public ResponseEntity<List<PostViewModel>> getPosts(@PathVariable Long me){
        List<PostDto> posts = _postService.getPosts(me);
        if(posts != null){
            List<PostViewModel> res = posts.stream().map(this::getPostViewModel)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.noContent().build();
    }

    private PublicationViewModel getPublicationViewModel(PostDto dto){
        PublicationViewModel res = new PublicationViewModel();
        res.setId(dto.getPublication());
        res.setDescription(dto.getContent());
        res.setPictures(dto.getPictures());
        return res;
    }
    private EventViewModel getEventViewModel(PostDto dto){
        EventViewModel res = new EventViewModel();
        res.setId(dto.getEvent().getId());
        res.setDescription(dto.getContent());
        res.setPictures(dto.getPictures());
        res.setDate(dto.getEvent().getDate());
        res.setTime(dto.getEvent().getTime());
        res.setAddress(dto.getEvent().getAddress());
        res.setRequirements(dto.getEvent().getRequirements());
        res.setStatusId(dto.getEvent().getStatusId());
        return res;
    }
    private PostViewModel getPostViewModel(PostDto dto){
        PostViewModel res = new PostViewModel();
        res.setId(dto.getId());
        res.setOngId(dto.getOngId());
        if(dto.getPublication() != null) res.setPublication(getPublicationViewModel(dto));
        if(dto.getEvent() != null) res.setEvent(getEventViewModel(dto));
        res.setCreatedAt(dto.getCreatedAt());
        res.setUpdatedAt(dto.getUpdatedAt());
        res.setLikes(dto.getLikes());
        return res;
    }

}
