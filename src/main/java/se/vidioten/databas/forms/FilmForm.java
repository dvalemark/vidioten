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
    @Size(min = 5, max = 200)
    private String beskrivning;
    @NotNull
    private Date utgivningsdatum;
    private Kategori kategori;
    private Format format;
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

    public Date getUtgivningsdatum() {
        return utgivningsdatum;
    }

    public void setUtgivningsdatum(Date utgivningsdatum) {
        this.utgivningsdatum = utgivningsdatum;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public Kund getKund() {
        return kund;
    }

    public void setKund(Kund kund) {
        this.kund = kund;
    }
}
