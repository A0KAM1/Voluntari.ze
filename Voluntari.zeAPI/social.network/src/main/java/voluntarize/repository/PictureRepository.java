package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import voluntarize.entity.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
