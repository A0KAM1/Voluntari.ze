package voluntarize.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("create")
    public ResponseEntity<PostViewModel> createPublication(@RequestBody PostRequest request){
        PostDto res = _postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.getPostViewModel(res));
    }

    @Operation(summary = "delete publication by id", tags = "Posts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Deleted"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable Long id){
        boolean res = _postService.deletePost(id);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "update publication by id", tags = "Posts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePublication(@PathVariable Long id, @RequestBody PostRequest request){
        boolean res = _postService.updatePost(id, request);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "get posts by ong", tags = "Posts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Found Data"),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{ong}")
    public ResponseEntity<List<PostViewModel>> getPosts(@PathVariable Long ong){
        List<PostDto> posts = _postService.getPostsByUser(ong);
        if(posts != null){
            List<PostViewModel> res = posts.stream().map(this::getPostViewModel)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "get all posts", tags = "Posts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Found Data"),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
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

    @Operation(summary = "upload file", tags = "Posts")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadFile(@RequestPart MultipartFile file){
        return ResponseEntity.ok().build();
    };

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
