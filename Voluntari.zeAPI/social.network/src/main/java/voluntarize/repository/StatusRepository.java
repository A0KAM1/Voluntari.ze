package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import voluntarize.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
