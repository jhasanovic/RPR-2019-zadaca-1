package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.List;

public class Kviz {
    String naziv;
    List<Pitanje> pitanja;
    SistemBodovanja sistemBodovanja;

    public Kviz(String naziv, SistemBodovanja sistemBodovanja) {
        this.naziv = naziv;
        this.sistemBodovanja = sistemBodovanja;
        pitanja=new ArrayList<Pitanje>();
    }

    public String getNaziv() {
        return naziv;
    }

    public List<Pitanje> getPitanja() {
        return pitanja;
    }

    public SistemBodovanja getSistemBodovanja() {
        return sistemBodovanja;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setPitanja(List<Pitanje> pitanja) {
        this.pitanja = pitanja;
    }

    public void setSistemBodovanja(SistemBodovanja sistemBodovanja) {
        this.sistemBodovanja = sistemBodovanja;
    }

    public void dodajPitanje(Pitanje pitanje) {
        for (Pitanje p : pitanja) {
            if (p.getTekst().equalsIgnoreCase(pitanje.getTekst()))
                throw new IllegalArgumentException("Ne možete dodati pitanje sa tekstom koji već postoji");
        }
        pitanja.add(pitanje);
    }
}
