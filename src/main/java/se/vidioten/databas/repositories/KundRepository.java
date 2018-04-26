package se.vidioten.databas.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.vidioten.databas.entities.Kund;

import java.util.List;

@Repository
public interface KundRepository extends JpaRepository<Kund,String> {

    Kund findByPersonnummer(String personnummer);
    List<Kund> findByNamn(String personnummer);
}
