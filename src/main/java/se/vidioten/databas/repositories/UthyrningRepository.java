package se.vidioten.databas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.vidioten.databas.entities.Uthyrning;

@Repository
public interface UthyrningRepository extends JpaRepository<Uthyrning,Long> {
}
