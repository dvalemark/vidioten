package se.vidioten.databas.entities;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Film")
public class Film {

    private long produktnummer;

    private String namn;

    private String beskrivning;

    private String utgivningsdatum;

    private String kategori;

    private String format;

    private String bild;

    private String imdb;

    private Kund kund;

    private List<Uthyrning> uthyrningar;

    protected Film() {

    }

    public Film(String namn, String beskrivning, String utgivningsdatum, String kategori, String format) {
        this.namn = namn;
        this.beskrivning = beskrivning;
        this.utgivningsdatum = utgivningsdatum;
        this.kategori = kategori;
        this.format = format;
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

    @ManyToOne
    public Kund getKund() {
        return kund;
    }

    public void setKund(Kund kund) {
        this.kund = kund;
    }

    public String getBild() {
        return bild;
    }

    public void setBild(String bild) {
        this.bild = bild;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }
}
