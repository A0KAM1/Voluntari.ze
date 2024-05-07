package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import voluntarize.entity.Event;
import voluntarize.entity.Post;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    public Optional<Event> findByPost(Post post);
}
