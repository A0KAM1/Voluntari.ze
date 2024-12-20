package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import voluntarize.entity.Event;
import voluntarize.entity.Participant;
import voluntarize.entity.Volunteer;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    public List<Participant> findByVolunteer(Volunteer id);
    public List<Participant> findByEvent(Event id);

    @Query(value = "SELECT p FROM Participant p WHERE p.volunteer = :volunteer AND p.event = :event")
    public Participant findParticipation(@Param("volunteer") Volunteer volunteer,
                                         @Param("event") Event event);
}
