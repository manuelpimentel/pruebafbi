package pruebafbi.repositories;

import pruebafbi.entities.MemberBossActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberBossActivityRepository extends JpaRepository<MemberBossActivity, Long> {
}
