package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import voluntarize.entity.Ong;

public interface OngRepository extends JpaRepository<Ong, Long> {
}
