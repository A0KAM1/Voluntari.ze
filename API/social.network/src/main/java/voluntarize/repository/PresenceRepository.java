package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import voluntarize.entity.Presence;

public interface PresenceRepository extends JpaRepository<Presence, Long> {
}
