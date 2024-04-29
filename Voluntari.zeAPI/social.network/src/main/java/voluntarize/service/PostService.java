package voluntarize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voluntarize.repository.EventRepository;
import voluntarize.repository.PostRepository;
import voluntarize.repository.PublicationRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository _postRepository;
    @Autowired
    private PublicationRepository _publicationRepository;
    @Autowired
    private EventRepository _eventRepository;

    public Post
}
