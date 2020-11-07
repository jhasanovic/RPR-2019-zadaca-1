package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Override
    public String toString() {
        String s = null;
        //izlistati cijelu listu
        for(int i=0;i<pitanja.size();i++){
        s=s+(i+1)+pitanja.get(i).getTekst()+"("+pitanja.get(i).getBrojPoena()+"b)\n";
        //prodjemo kroz mapu sa odgovorima
            for (Map.Entry<String, Odgovor> m : pitanja.get(i).odgovori.entrySet()) {
                s = s + m.getKey()+": ";
                if(m.getValue().isTacno())
                    s=s+m.getValue().getTekstOdgovora()+"(T)\n";
                else s=s+m.getValue().getTekstOdgovora();
            }
        }
        return s;
    }
}
