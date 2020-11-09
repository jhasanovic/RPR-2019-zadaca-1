package ba.unsa.etf.rpr;

import java.util.*;

public class Kviz {
    private String naziv;
    private List<Pitanje> pitanja;
    private SistemBodovanja sistemBodovanja;
    

    public Kviz(String naziv, SistemBodovanja sistemBodovanja) {
        this.naziv = naziv;
        this.sistemBodovanja = sistemBodovanja;
        pitanja= new ArrayList<>();
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
        StringBuilder s;
        if(sistemBodovanja.name().equals("BINARNO")) s = new StringBuilder("Kviz \"" + getNaziv() + "\" boduje se binarno" + ". Pitanja:\n");
        else if(sistemBodovanja.name().equals("PARCIJALNO")) s = new StringBuilder("Kviz \"" + getNaziv() + "\" boduje se parcijalno" + ". Pitanja:\n");
        else s = new StringBuilder("Kviz \"" + getNaziv() + "\" boduje se parcijalno sa negativnim bodovima" + ". Pitanja:\n");

        for(int i=0;i<pitanja.size();i++){
            s.append(String.format("%d. ", i + 1));
        s.append(pitanja.get(i).getTekst()).append("(").append(pitanja.get(i).getBrojPoena()).append("b)\n\t");
            Iterator<Map.Entry<String, Odgovor>> it = pitanja.get(i).getOdgovori().entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<String, Odgovor> m = it.next();
                s.append(m.getKey()).append(": ");
                if(i!=pitanja.size()-1) {
                    if(it.hasNext()) {
                        if (m.getValue().isTacno())
                            s.append(m.getValue().getTekstOdgovora()).append("(T)\n\t");
                        else s.append(m.getValue().getTekstOdgovora()).append("\n\t");
                    }
                    else{
                        if(m.getValue().isTacno())
                            s.append(m.getValue().getTekstOdgovora()).append("(T)\n\n");
                        else s.append(m.getValue().getTekstOdgovora()).append("\n\n");
                    }
                }
                else {
                    if(it.hasNext()) {
                        if (m.getValue().isTacno())
                            s.append(m.getValue().getTekstOdgovora()).append("(T)\n\t");
                        else s.append(m.getValue().getTekstOdgovora()).append("\n\t");
                    }
                    else{
                        if(m.getValue().isTacno())
                            s.append(m.getValue().getTekstOdgovora()).append("(T)");
                        else s.append(m.getValue().getTekstOdgovora());
                    }
                }
            }
        }
        return s.toString();
    }

    public RezultatKviza predajKviz(Map<Pitanje, ArrayList<String>> zaokruzeniOdgovori) {
        //zaokruzeni odgovori sadrzi samo pitanja koja su odgovorena
        //dodati neodgovorena
        Map<Pitanje,Double> pomocna= new HashMap<>();
        double sumaPoena=0;
        for (Pitanje pitanje : pitanja) {//provjeriti je li svako pitanja u mapi zaokruzeniOdgovori
            if (!zaokruzeniOdgovori.containsKey(pitanje)) {//ako nije dodati sa nula bodova
                pomocna.put(pitanje, (double) 0);
            } else {//pitanje jeste u zaokruzenim odgovorima
                for (Map.Entry<Pitanje, ArrayList<String>> entry : zaokruzeniOdgovori.entrySet()) {
                    if (entry.getKey().getTekst().equals(pitanje.getTekst())) {
                        double brPoenaNaPitanju = pitanje.izracunajPoene(entry.getValue(), sistemBodovanja);
                        sumaPoena = sumaPoena + brPoenaNaPitanju;
                        pomocna.put(pitanje, brPoenaNaPitanju);
                        break;
                    }
                }
            }
        }

        RezultatKviza rezultat=new RezultatKviza(this);
        rezultat.setTotal(sumaPoena);
        rezultat.setBodovi(pomocna);
        return rezultat;
    }

}
