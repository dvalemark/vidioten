package se.vidioten.databas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.vidioten.databas.entities.Film;
import se.vidioten.databas.entities.Kund;


import java.util.List;


@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    Page<Film> findAllByKategoriOrderByProduktnummerDesc(String kategori, Pageable pageable);
    List<Film> findAllByKund_Personnummer(String personnummer);
    Page<Film> findByProduktnummerOrNamn(Long produknummer, String namn, Pageable pageable);
    Film findByProduktnummer(Long produknummer);

}
