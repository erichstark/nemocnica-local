package sk.stuba.fei.team.local.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@XmlRootElement
public class OpeningHours implements Serializable {
    private static final long serialVersionUID = 7144402491802676227L;
    private Long id;
    private String date;
    private Long reservationMorningFrom;
    private Long reservationMorningTo;
    private Long reservationFrom;
    private Long reservationTo;
    private Office office;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column
    public Long getReservationFrom() {
        return reservationFrom;
    }

    public void setReservationFrom(Long reservationFrom) {
        this.reservationFrom = reservationFrom;
    }

    @Column
    public Long getReservationTo() {
        return reservationTo;
    }

    public void setReservationTo(Long reservationTo) {
        this.reservationTo = reservationTo;
    }

    @ManyToOne
    @JoinColumn(name = "office", nullable = false)
    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    @Column
    public Long getReservationMorningFrom() {
        return reservationMorningFrom;
    }

    public void setReservationMorningFrom(Long reservationMorningFrom) {
        this.reservationMorningFrom = reservationMorningFrom;
    }

    @Column
    public Long getReservationMorningTo() {
        return reservationMorningTo;
    }

    public void setReservationMorningTo(Long reservationMorningTo) {
        this.reservationMorningTo = reservationMorningTo;
    }

}
