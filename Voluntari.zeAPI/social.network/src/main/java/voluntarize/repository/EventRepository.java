package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import voluntarize.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}