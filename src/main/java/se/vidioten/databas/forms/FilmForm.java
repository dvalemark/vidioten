package se.vidioten.databas.forms;

import se.vidioten.databas.entities.Kund;
import se.vidioten.databas.enums.Format;
import se.vidioten.databas.enums.Kategori;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

public class FilmForm {
    private Long produktnummer;
    @NotNull
    @Size(min = 5, max = 50)
    private String namn;
    @NotNull
    @Size(min = 5, max = 500)
    private String beskrivning;
    @NotNull
    private String utgivningsdatum;
    private String kategori;
    private String format;
    private Kund kund;

    public Long getProduktnummer() {
        return produktnummer;
    }

    public void setProduktnummer(Long produktnummer) {
        this.produktnummer = produktnummer;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public String getBeskrivning() {
        return beskrivning;
    }

    public void setBeskrivning(String beskrivning) {
        this.beskrivning = beskrivning;
    }

    public String getUtgivningsdatum() {
        return utgivningsdatum;
    }

    public void setUtgivningsdatum(String utgivningsdatum) {
        this.utgivningsdatum = utgivningsdatum;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Kund getKund() {
        return kund;
    }

    public void setKund(Kund kund) {
        this.kund = kund;
    }
}
