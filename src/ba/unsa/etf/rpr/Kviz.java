package ba.unsa.etf.rpr;

import java.util.*;

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
        String s;
        if(sistemBodovanja.name()=="BINARNO") s="Kviz \""+getNaziv()+"\" boduje se binarno"+". Pitanja:\n";
        else if(sistemBodovanja.name()=="PARCIJALNO") s="Kviz \""+getNaziv()+"\" boduje se parcijalno"+". Pitanja:\n";
        else s="Kviz \""+getNaziv()+"\" boduje se parcijalno sa negativnim bodovima"+". Pitanja:\n";

        for(int i=0;i<pitanja.size();i++){
            s=s+String.format("%d. ",i+1);
        s=s+pitanja.get(i).getTekst()+"("+pitanja.get(i).getBrojPoena()+"b)\n\t";
            Iterator<Map.Entry<String, Odgovor>> it = pitanja.get(i).odgovori.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<String, Odgovor> m = it.next();
                s = s + m.getKey()+": ";
                if(i!=pitanja.size()-1) {
                    if(it.hasNext()) {
                        if (m.getValue().isTacno())
                            s = s + m.getValue().getTekstOdgovora() + "(T)\n\t";
                        else s = s + m.getValue().getTekstOdgovora() + "\n\t";
                    }
                    else{
                        if(m.getValue().isTacno())
                            s = s + m.getValue().getTekstOdgovora() + "(T)\n\n";
                        else s=s+m.getValue().getTekstOdgovora()+"\n\n";
                    }
                }
                else {
                    if(it.hasNext()) {
                        if (m.getValue().isTacno())
                            s = s + m.getValue().getTekstOdgovora() + "(T)\n\t";
                        else s = s + m.getValue().getTekstOdgovora() + "\n\t";
                    }
                    else{
                        if(m.getValue().isTacno())
                            s = s + m.getValue().getTekstOdgovora() + "(T)";
                        else s=s+m.getValue().getTekstOdgovora();
                    }
                }
            }
        }
        return s;
    }

    public RezultatKviza predajKviz(Map<Pitanje, ArrayList<String>> zaokruzeniOdgovori) {
        //zaokruzeni odgovori sadrzi samo pitanja koja su odgovorena
        //dodati neodgovorena
        Map<Pitanje,Double> pomocna=new HashMap<Pitanje,Double>();
        double sumaPoena=0;
        int i=0;
        for(i=0;i<pitanja.size();i++){//provjeriti je li svako pitanja u mapi zaokruzeniOdgovori
            if(!zaokruzeniOdgovori.containsKey(pitanja.get(i))){//ako nije dodati sa nula bodova
                pomocna.put(pitanja.get(i),(double)0);
            }
            else{//pitanje jeste u zaokruzenim odgovorima
                Iterator<Map.Entry<Pitanje, ArrayList<String>>> it = zaokruzeniOdgovori.entrySet().iterator();
                while(it.hasNext()){
                    Map.Entry<Pitanje, ArrayList<String>> entry=it.next();
                    if(entry.getKey().getTekst().equals(pitanja.get(i).getTekst())){
                        double brPoenaNaPitanju=pitanja.get(i).izracunajPoene(entry.getValue(), sistemBodovanja);
                        sumaPoena = sumaPoena + brPoenaNaPitanju;
                        pomocna.put(pitanja.get(i), brPoenaNaPitanju);
                        break;
                    }
                }
            }
        }

        RezultatKviza rezultat=new RezultatKviza(this);
        rezultat.setTotal(sumaPoena);
        rezultat.bodovi=pomocna;
        return rezultat;
    }

}
