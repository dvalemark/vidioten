package se.vidioten.databas.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.vidioten.databas.entities.Kund;

@Repository
public interface KundRepository extends JpaRepository<Kund,String> {

    Kund findByPersonnummer(String personnummer);

}
