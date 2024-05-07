package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import voluntarize.entity.Post;
import voluntarize.entity.Publication;

import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
    public List<Publication> findByPost(Post post);
}
