package voluntarize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voluntarize.dto.EventDto;
import voluntarize.dto.PostDto;
import voluntarize.entity.*;
import voluntarize.repository.*;
import voluntarize.request.PostRequest;
import voluntarize.service.interfaces.IPostService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {
    @Autowired
    private PostRepository _postRepository;
    @Autowired
    private PictureRepository _pictureRepository;
    @Autowired
    private LikeRepository _likeRepository;
    @Autowired
    private OngRepository _ongRepository;

    public PostDto createPost (PostRequest request){
        Post post = _postRepository.save(this.getPostEntity(request));
        _pictureRepository.saveAll(this.getListOfPhotos(request.photos, post));
        return this.getPostDto(post);
    }

    public boolean deletePost(Long id){
        Optional<Post> post = _postRepository.findById(id);
        if(post.isPresent()){
            List<Picture> pics = _pictureRepository.findByPost(post.get());
            List<Like> likes = _likeRepository.findByPost(post.get());
            _pictureRepository.deleteAll(pics);
            _likeRepository.deleteAll(likes);
            _postRepository.delete(post.get());
            return true;
        }
        return false;
    };

    public boolean updatePost(Long id, PostRequest request){
        Optional<Post> oldPost = _postRepository.findById(id);
        if(oldPost.isPresent()){
            List<Picture> oldPictures = _pictureRepository.findByPost(oldPost.get());
            _pictureRepository.deleteAll(oldPictures);

            Post newPost = this.getPostEntity(request);
            newPost.setId(id);

            _pictureRepository.saveAll(this.getListOfPhotos(request.photos, newPost));
            _postRepository.save(newPost);
            return true;
        }
        return false;
    };

    public List<PostDto> getPostsByUser(Long id){
        Ong ong = _ongRepository.findById(id).orElseThrow();
        List<Post> res = _postRepository.findByOng(ong);

        return res.stream().map(this::getPostDto).collect(Collectors.toList());
    };

    public List<PostDto> getPosts(){
        List<Post> res = _postRepository.getFeed();
        return res.stream().map(this::getPostDto).collect(Collectors.toList());
    }


    private Post getPostEntity(PostRequest request){
        Ong ong = this._ongRepository.findById(request.ongId).orElseThrow();
        Post res = new Post();

        res.setOng(ong);
        res.setContent(request.description);
        return res;
    }
    private List<Picture> getListOfPhotos(List<String> photos, Post post){
        List<Picture> res = new ArrayList<>();
        photos.forEach(picture -> {
            Picture pic = new Picture();
            pic.setUrl(picture);
            pic.setPost(post);
            res.add(pic);
        });
        return res;
    }
    private EventDto getEventDto(Event event){
        EventDto res = new EventDto();
        res.setId(event.getId());
        res.setDate(event.getDate());
        res.setTime(event.getTime());
        res.setAddress(event.getAddress());
        res.setRequirements(event.getRequirements());
        res.setStatusId(event.getStatus().getId());
        return res;
    }
    private PostDto getPostDto(Post post){
        List<Picture> pictures = _pictureRepository.findByPost(post);
        int likes = _likeRepository.findByPost(post).size();

        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setContent(post.getContent());
        dto.setOngId(post.getOng().getId());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUpdatedAt(post.getUpdatedAt());
        if(post.getEvent() != null) dto.setEvent(getEventDto(post.getEvent()));
        dto.setPictures(pictures.stream().map(Picture::getUrl).collect(Collectors.toList()));
        dto.setLikes(likes);
        return dto;
    }
}
