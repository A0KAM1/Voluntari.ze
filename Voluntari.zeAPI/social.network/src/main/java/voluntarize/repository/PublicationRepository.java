package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import voluntarize.entity.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
}
