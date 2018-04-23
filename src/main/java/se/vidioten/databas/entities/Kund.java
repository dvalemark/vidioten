package se.vidioten.databas.entities;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Kund")
public class Kund {


    private String personnummer;

    private String namn;

    private String address;

    private String telefonnummer;

    private String epost;

    private String postnummer;

    private String stad;

    private String land;

    private List<Film> filmer;

    private List<Uthyrning> uthyrningar;

    public Kund() {

    }

    public Kund(String personnummer, String namn, String address, String telefonnummer, String epost, String postnummer, String stad, String land) {
        this.personnummer = personnummer;
        this.namn = namn;
        this.address = address;
        this.telefonnummer = telefonnummer;
        this.epost = epost;
        this.postnummer = postnummer;
        this.stad = stad;
        this.land = land;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kund")
    public List<Uthyrning> getUthyrningar() {
        return uthyrningar;
    }

    public void setUthyrningar(List<Uthyrning> uthyrningar) {
        this.uthyrningar = uthyrningar;
    }
    @Id
    public String getPersonnummer() {
        return personnummer;
    }

    public void setPersonnummer(String personnummer) {
        this.personnummer = personnummer;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public void setName(String name) {
        this.namn = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public String getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(String postnummer) {
        this.postnummer = postnummer;
    }

    public String getStad() {
        return stad;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }
    @OneToMany(mappedBy = "kund")
    public List<Film> getFilmer() {
        return filmer;
    }

    public void setFilmer(List<Film> filmer) {
        this.filmer = filmer;
    }
}
