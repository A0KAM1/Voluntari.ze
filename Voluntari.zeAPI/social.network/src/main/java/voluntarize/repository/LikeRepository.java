package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import voluntarize.entity.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
