package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import voluntarize.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
