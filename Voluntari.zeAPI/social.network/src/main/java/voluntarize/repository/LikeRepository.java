package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import voluntarize.entity.Like;
import voluntarize.entity.Post;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    public List<Like> findByPost(Post post);
}
