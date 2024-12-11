package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import voluntarize.entity.Follower;

public interface FollowerRepository extends JpaRepository<Follower, Long> {
}
