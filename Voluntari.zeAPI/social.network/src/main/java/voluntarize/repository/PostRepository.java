package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import voluntarize.entity.Event;
import voluntarize.entity.Ong;
import voluntarize.entity.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    public List<Post> findByOng(Ong ong);
    public Post findByEvent(Event event);

    @Query(value = "SELECT p FROM Post p ORDER BY createdAt DESC")
    public List<Post> getFeed();
}
