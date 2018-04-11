package se.vidioten.databas.entities;

import se.vidioten.databas.enums.Format;
import se.vidioten.databas.enums.Kategori;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Film")
public class Film {

    private long produktnummer;

    private String namn;

    private String beskrivning;

    private Date utgivningsdatum;

    private Kategori kategori;

    private Format format;

    private Kund kund;

    private List<Uthyrning> uthyrningar;

    protected Film() {

    }

    public Film(String namn, String beskrivning, Date utgivningsdatum, Kategori kategori, Format format, Kund kund) {
        this.namn = namn;
        this.beskrivning = beskrivning;
        this.utgivningsdatum = utgivningsdatum;
        this.kategori = kategori;
        this.format = format;
        this.kund = kund;
    }
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "film")
    public List<Uthyrning> getUthyrningar() {
        return uthyrningar;
    }

    public void setUthyrningar(List<Uthyrning> uthyrningar) {
        this.uthyrningar = uthyrningar;
    }
    @Id
    @GeneratedValue
    public long getProduktnummer() {
        return produktnummer;
    }

    public void setProduktnummer(long produktnummer) {
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

    @ManyToOne
    public Kund getKund() {
        return kund;
    }

    public void setKund(Kund kund) {
        this.kund = kund;
    }
}
