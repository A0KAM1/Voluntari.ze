package voluntarize.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voluntarize.dto.EventDto;
import voluntarize.dto.PostDto;
import voluntarize.request.PostRequest;
import voluntarize.service.PostService;
import voluntarize.viewModel.EventViewModel;
import voluntarize.viewModel.PostViewModel;

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
    public ResponseEntity<PostViewModel> createPublication(@RequestBody PostRequest request){
        PostDto res = _postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.getPostViewModel(res));
    }

    @Operation(summary = "delete publication by id", tags = "Posts")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable Long id){
        boolean res = _postService.deletePost(id);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "update publication by id", tags = "Posts")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePublication(@PathVariable Long id, @RequestBody PostRequest request){
        boolean res = _postService.updatePost(id, request);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "get posts by ong", tags = "Posts")
    @GetMapping("/{me}")
    public ResponseEntity<List<PostViewModel>> getPosts(@PathVariable Long me){
        List<PostDto> posts = _postService.getPostsByUser(me);
        if(posts != null){
            List<PostViewModel> res = posts.stream().map(this::getPostViewModel)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "get all posts", tags = "Posts")
    @GetMapping()
    public ResponseEntity<List<PostViewModel>> loadFeed(){
        List<PostDto> posts = _postService.getPosts();
        if(posts != null){
            List<PostViewModel> res = posts.stream().map(this::getPostViewModel)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.noContent().build();
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
        res.setOngId(dto.getOngId());
        res.setContent(dto.getContent());
        res.setPictures(dto.getPictures());
        if(dto.getEvent() != null) res.setEvent(getEventViewModel(dto.getEvent()));
        res.setCreatedAt(dto.getCreatedAt());
        res.setUpdatedAt(dto.getUpdatedAt());
        res.setLikes(dto.getLikes());
        return res;
    }

}
