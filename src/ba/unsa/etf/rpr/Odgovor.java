package ba.unsa.etf.rpr;

public class Odgovor {
    String tekst;
    boolean tacno;

    public Odgovor(String tekst, boolean tacno) {
        this.tekst = tekst;
        this.tacno = tacno;
    }

    public String getTekst() {
        return tekst;
    }

    public boolean isTacno() {
        return tacno;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public void setTacno(boolean tacno) {
        this.tacno = tacno;
    }
}
