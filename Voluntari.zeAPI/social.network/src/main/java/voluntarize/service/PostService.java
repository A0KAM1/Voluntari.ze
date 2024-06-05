package voluntarize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voluntarize.dto.EventDto;
import voluntarize.dto.PostDto;
import voluntarize.entity.*;
import voluntarize.repository.*;
import voluntarize.request.PublicationRequest;
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
    private PublicationRepository _publicationRepository;
    @Autowired
    private PictureRepository _pictureRepository;
    @Autowired
    private LikeRepository _likeRepository;
    @Autowired
    private OngRepository _ongRepository;
    @Autowired
    private EventRepository _eventRepository;

    public PostDto createPublication (PublicationRequest request){
        Post post = _postRepository.save(this.getPostAttributesFromPublication(request));
        _publicationRepository.save(this.getPublicationEntity(post));
        _pictureRepository.saveAll(this.getListOfPhotos(request.photos, post));
        return this.getPostDto(post);
    }

    public boolean deletePublication(Long id){
        Optional<Publication> publication = _publicationRepository.findById(id);
        if(publication.isPresent()){
            _publicationRepository.delete(publication.get());
            List<Picture> pictures = _pictureRepository.findByPost(publication.get().getPost());
            List<Like> likes = _likeRepository.findByPost(publication.get().getPost());
            _pictureRepository.deleteAll(pictures);
            _likeRepository.deleteAll(likes);
            _postRepository.delete(publication.get().getPost());
            return true;
        }
        return false;
    };

    public boolean updatePublication(Long id, PublicationRequest request){
        Optional<Publication> oldPublication = _publicationRepository.findById(id);
        if(oldPublication.isPresent()){
            Post newPost = this.getPostAttributesFromPublication(request);
            newPost.setId(oldPublication.get().getPost().getId());
            Publication res = this.getPublicationEntity(newPost);
            res.setId(id);
            List<Picture> oldPictures = _pictureRepository.findByPost(oldPublication.get().getPost());
            _pictureRepository.deleteAll(oldPictures);

            _pictureRepository.saveAll(this.getListOfPhotos(request.photos, newPost));
            _postRepository.save(newPost);
            _publicationRepository.save(res);
            return true;
        }
        return false;
    };

    public List<PostDto> getPosts(Long id){
        Ong ong = _ongRepository.findById(id).orElseThrow();
        List<Post> res = _postRepository.findByOng(ong);

        return res.stream().map(this::getPostDto).collect(Collectors.toList());
    }

    private Post getPostAttributesFromPublication(PublicationRequest request){
        Optional<Ong> ong = this._ongRepository.findById(request.ongId);
        Post res = new Post();

        res.setOng(ong.get());
        res.setContent(request.description);
        return res;
    }
    private Publication getPublicationEntity(Post post){
        Publication res = new Publication();
        res.setPost(post);
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
        EventDto event = getEventDto(post.getEvent());

        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setContent(post.getContent());
        dto.setOngId(post.getOng().getId());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUpdatedAt(post.getUpdatedAt());
        dto.setEvent(event);
        dto.setPictures(pictures.stream().map(Picture::getUrl).collect(Collectors.toList()));
        dto.setLikes(likes);
        return dto;
    }
}
