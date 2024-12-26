package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import voluntarize.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
