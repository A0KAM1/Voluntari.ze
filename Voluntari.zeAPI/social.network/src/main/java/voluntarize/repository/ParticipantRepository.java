package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import voluntarize.entity.Event;
import voluntarize.entity.Participant;
import voluntarize.entity.Volunteer;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    public Participant findByVolunteer(Volunteer id);
    public Participant findByEvent(Event id);
}
