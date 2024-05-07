package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import voluntarize.entity.Picture;
import voluntarize.entity.Post;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Long> {
    public List<Picture> findByPost(Post post);
}
