package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import voluntarize.entity.Ong;
import voluntarize.entity.Volunteer;

import java.util.List;

public interface OngRepository extends JpaRepository<Ong, Long> {
    @Query(value = "SELECT o FROM Ong o " +
            "JOIN o.user u " +
            "LEFT JOIN Tag t ON t.ong = o " +
            "LEFT JOIN t.category c " +
            "WHERE ((:keyword IS NULL) OR (u.name LIKE %:keyword%) OR (u.username LIKE %:keyword%)) " +
            "AND ((:category IS NULL) OR (c.id IN (:category)))")
    List<Ong> findByFilter(@Param("keyword") String keyword,
                           @Param("category") List<Long> category);

    @Query(value = "SELECT f.ong FROM Follower f WHERE f.volunteer = :volunteer")
    public List<Ong> findByVolunteer(@Param("volunteer")Volunteer volunteer);
}
