package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import voluntarize.entity.Volunteer;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
}
