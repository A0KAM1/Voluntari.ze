package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import voluntarize.entity.Ong;
import voluntarize.entity.Volunteer;

import java.util.List;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    @Query(value = "SELECT f.volunteer FROM Follower f WHERE f.ong = :ong")
    public List<Volunteer> findByOng(@Param("ong") Ong ong);
}
