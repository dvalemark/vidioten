package se.vidioten.databas.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class KundForm {
    @NotNull
    @Size(min = 11, max = 11)
    private String personnummer;
    @NotNull
    @Size(min = 5, max = 50)
    private String namn;
    @NotNull
    @Size(min = 5, max = 50)
    private String address;
    @NotNull
    @Size(min = 9, max = 10)
    private String telefonnummer;
    @NotNull
    @Size(min = 5, max = 50)
    private String epost;
    @NotNull
    @Size(min = 6, max = 6)
    private String postnummer;
    @NotNull
    @Size(min = 2, max = 50)
    private String stad;
    @NotNull
    @Size(min = 2, max = 50)
    private String land;

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
}
