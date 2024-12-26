package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import voluntarize.entity.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {
}
