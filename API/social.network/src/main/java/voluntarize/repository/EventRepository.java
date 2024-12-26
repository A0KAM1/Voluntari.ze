package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import voluntarize.entity.Event;
import voluntarize.entity.Ong;
import voluntarize.entity.Volunteer;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query(value = "SELECT p.event FROM Post p WHERE p.ong = :id")
    public List<Event> findByOng(@Param("id") Ong id);
    @Query(value = "SELECT p.event FROM Participant p WHERE p.volunteer = :id")
    public List<Event> findByVolunteer(@Param("id") Volunteer id);
}
