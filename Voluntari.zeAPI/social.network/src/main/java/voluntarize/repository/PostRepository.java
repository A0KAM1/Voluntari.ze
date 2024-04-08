package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import voluntarize.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
