package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import voluntarize.entity.Ong;
import voluntarize.entity.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    public List<Post> findByOng(Ong ong);
}
