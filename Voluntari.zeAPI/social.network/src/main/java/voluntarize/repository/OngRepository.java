package voluntarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import voluntarize.entity.Ong;

import java.util.List;

public interface OngRepository extends JpaRepository<Ong, Long> {
    @Query("SELECT t.ong FROM Tag t " +
            "INNER JOIN t.category c " +
            "INNER JOIN t.ong.user u " +
            "WHERE (:keyword IS NULL OR u.name LIKE %:keyword%) " +
            "OR (:keyword IS NULL OR u.username LIKE %:keyword%) " +
            "OR (:category IS NULL OR c.id IN (:category))")
    List<Ong> findByFilter(@Param("keyword") String keyword,
                           @Param("category") List<Long> category);

}
