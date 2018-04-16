package se.vidioten.databas.entities;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "Uthyrning")
public class Uthyrning {


    private long uthyrningsID;

    private Kund kund;

    private Film film;

    private Date uthyrningsdatum;

    private Date senasteInlamning;

    private Date inlamningsdatum;


    protected Uthyrning() {

    }

    public Uthyrning(Kund kund, Film film, Date uthyrningsdatum, Date senasteInlamning) {
        this.kund = kund;
        this.film = film;
        this.uthyrningsdatum = uthyrningsdatum;
        this.senasteInlamning = senasteInlamning;
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

    public Date getInlamningsdatum() {
        return inlamningsdatum;
    }

    public void setInlamningsdatum(Date inlamningsdatum) {
        this.inlamningsdatum = inlamningsdatum;
    }

    public Date getSenasteInlamning() {
        return senasteInlamning;
    }

    public void setSenasteInlamning(Date senasteInlamning) {
        this.senasteInlamning = senasteInlamning;
    }

    public boolean checkIfLate() {
        Date uthyrningsdatum = this.uthyrningsdatum;
        Date today = Date.valueOf(LocalDate.now());
        long days = ChronoUnit.DAYS.between(uthyrningsdatum.toLocalDate(), today.toLocalDate());
        System.out.println(days);
        if (days > 1 && inlamningsdatum == null) {
            return true;
        } else {
            return false;
        }
    }
}
