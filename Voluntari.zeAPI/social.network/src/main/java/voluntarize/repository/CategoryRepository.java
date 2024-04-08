package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import voluntarize.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
