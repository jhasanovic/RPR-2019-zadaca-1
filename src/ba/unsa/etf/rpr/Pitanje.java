package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.List;

public class Pitanje {
    String tekst;
    double brojPoena;
    List<Odgovor> odgovori;

    public Pitanje(String tekst, double brojPoena) {
        this.tekst = tekst;
        this.brojPoena = brojPoena;
        odgovori=new ArrayList<Odgovor>();
    }

    public String getTekst() {
        return tekst;
    }

    public double getBrojPoena() {
        return brojPoena;
    }

    public List<Odgovor> getOdgovori() {
        return odgovori;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public void setBrojPoena(double brojPoena) {
        this.brojPoena = brojPoena;
    }

    public void setOdgovori(List<Odgovor> odgovori) {
        this.odgovori = odgovori;
    }
}
