package se.vidioten.databas.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Uthyrning")
public class Uthyrning {

    private long uthyrningsID;

    private Kund kund;

    private Film film;

    private Date uthyrningsdatum;

    private Date inlämningsdatum;

    protected Uthyrning() {

    }

    public Uthyrning(Kund kund, Film film, Date uthyrningsdatum, Date inlämningsdatum) {
        this.kund = kund;
        this.film = film;
        this.uthyrningsdatum = uthyrningsdatum;
        this.inlämningsdatum = inlämningsdatum;
    }
    @Id
    @GeneratedValue
    public long getUthyrningsID() {
        return uthyrningsID;
    }

    public void setUthyrningsID(long uthyrningsID) {
        this.uthyrningsID = uthyrningsID;
    }

    @ManyToOne
    public Kund getKund() {
        return kund;
    }

    public void setKund(Kund kund) {
        this.kund = kund;
    }

    @ManyToOne
    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Date getUthyrningsdatum() {
        return uthyrningsdatum;
    }

    public void setUthyrningsdatum(Date uthyrningsdatum) {
        this.uthyrningsdatum = uthyrningsdatum;
    }

    public Date getInlämningsdatum() {
        return inlämningsdatum;
    }

    public void setInlämningsdatum(Date inlämningsdatum) {
        this.inlämningsdatum = inlämningsdatum;
    }
}
