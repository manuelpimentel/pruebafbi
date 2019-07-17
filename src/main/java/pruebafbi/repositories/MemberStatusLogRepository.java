package pruebafbi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pruebafbi.entities.MemberStatusLog;

@Repository
public interface MemberStatusLogRepository extends JpaRepository<MemberStatusLog, Long> {
}
