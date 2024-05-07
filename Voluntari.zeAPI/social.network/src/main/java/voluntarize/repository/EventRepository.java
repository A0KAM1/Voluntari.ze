package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import voluntarize.entity.Event;
import voluntarize.entity.Post;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    public List<Event> findByPost(Post post);
}
