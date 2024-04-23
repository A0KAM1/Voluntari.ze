package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import voluntarize.entity.Ong;

import java.util.List;

public interface OngRepository extends JpaRepository<Ong, Long> {
    @Query("SELECT t.ong FROM Tag t INNER JOIN t.category c INNER JOIN t.ong.user u WHERE u.name LIKE %?1% OR u.username LIKE %?1% OR c.id IN (?2)")
    List<Ong> findByFilter(String keyword, int category);
}
