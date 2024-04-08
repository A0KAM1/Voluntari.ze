package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import voluntarize.entity.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
