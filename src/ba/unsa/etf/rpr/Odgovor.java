package ba.unsa.etf.rpr;

import java.util.Objects;

public class Odgovor {
    String tekst;
    boolean tacno;

    public Odgovor(String tekst, boolean tacno) {
        this.tekst = tekst;
        this.tacno = tacno;
    }

    public String getTekstOdgovora() {
        return tekst;
    }

    public boolean isTacno() {
        return tacno;
    }

    public void setTekstOdgovora(String tekst) {
        this.tekst = tekst;
    }

    public void setTacno(boolean tacno) {
        this.tacno = tacno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Odgovor odgovor = (Odgovor) o;
        return tacno == odgovor.tacno &&
                Objects.equals(tekst, odgovor.tekst);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tekst, tacno);
    }
}
