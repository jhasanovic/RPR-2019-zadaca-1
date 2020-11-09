package ba.unsa.etf.rpr;

import java.util.*;

public class Pitanje {
    private String tekst;
    private double brojPoena;
    private Map<String,Odgovor> odgovori; //ID pitanja je key polje mape odgovori

    public Pitanje(String tekst, double brojPoena) {
        this.tekst = tekst;
        this.brojPoena = brojPoena;
        odgovori= new HashMap<>();
    }
    public String getTekst() {
        return tekst;
    }

    public double getBrojPoena() {
        return brojPoena;
    }

    public Map<String,Odgovor> getOdgovori() {
        return odgovori;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public void setBrojPoena(double brojPoena) {
        this.brojPoena = brojPoena;
    }

    public void setOdgovori(Map<String,Odgovor> odgovori) {
        this.odgovori = odgovori;
    }


    public void dodajOdgovor(String id, String tekst, boolean tacno) {
        if(odgovori.containsKey(id))throw new IllegalArgumentException("Id odgovora mora biti jedinstven");
        odgovori.put(id,new Odgovor(tekst,tacno));
    }

    public void obrisiOdgovor(String id) {
        if(!odgovori.containsKey(id)) throw new IllegalArgumentException("Odgovor s ovim id-em ne postoji");
        odgovori.remove(id);
    }

    public List<Odgovor> dajListuOdgovora() {
        return new ArrayList<>(odgovori.values());
    }

    public double izracunajPoene(List<String> lista, SistemBodovanja sistemBodovanja) {
        double ukupniPoeni=0;

        for(int i=0;i<lista.size();i++){
            for(int j=i+1;j<lista.size();j++) {
                if (lista.get(i).equals(lista.get(j)))
                    throw new IllegalArgumentException("Postoje duplikati među odabranim odgovorima");
            }
        }
        for (String s : lista) {
            if (!odgovori.containsKey(s)) throw new IllegalArgumentException("Odabran je nepostojeći odgovor");
        }
        if(lista.size()==0) return 0; //ako na pitanje nije odgovoreno

        if(sistemBodovanja==SistemBodovanja.BINARNO){
            for (String s : lista) {
                if (!odgovori.get(s).isTacno())//ako nađemo bar jedan netačan
                    return 0;
            }
                int brojTacnih=0;
                for (Map.Entry<String, Odgovor> m : odgovori.entrySet()) {
                    if(m.getValue().isTacno()) brojTacnih++;
                }
                //ako su svi tačni odgovori odabrani
                if(brojTacnih==lista.size()) return brojPoena;
        }

        else if(sistemBodovanja==SistemBodovanja.PARCIJALNO){
            int brojZaokruzenihTacnih=lista.size(),brojTacnih=0,brojOdgovora=0;
            for (String s : lista) {
                if (!odgovori.get(s).isTacno()) return 0;
            }
            for (Map.Entry<String, Odgovor> m : odgovori.entrySet()) {
                if(m.getValue().isTacno()) brojTacnih++;
                brojOdgovora++;
            }
            if(brojTacnih==brojZaokruzenihTacnih) ukupniPoeni=brojPoena; //ako su odabrani svi tačni
            else ukupniPoeni = (brojPoena/brojOdgovora)*brojZaokruzenihTacnih; //ako nisu odabrani svi tačni
        }
        else if(sistemBodovanja==SistemBodovanja.PARCIJALNO_SA_NEGATIVNIM){
            int brojZaokruzenihTacnih=lista.size();
            int brojTacnih=0;
            int brojOdgovora=0;
            for (String s : lista) {
                if (!odgovori.get(s).isTacno()) return -brojPoena / 2; //ako je odabran bar jedan netačan odgovor
            }
            for (Map.Entry<String, Odgovor> m : odgovori.entrySet()) {
                if(m.getValue().isTacno()) brojTacnih++;
                brojOdgovora++;
            }
            if(brojTacnih==brojZaokruzenihTacnih) ukupniPoeni = brojPoena;
            else ukupniPoeni = (brojPoena/brojOdgovora)*brojZaokruzenihTacnih;
        }
        return ukupniPoeni;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(tekst + "(" + brojPoena + "b)\n\t");
        Iterator<Map.Entry<String, Odgovor>> it = odgovori.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Odgovor> m = it.next();
            if(it.hasNext())
            s.append(m.getKey()).append(": ").append(m.getValue().getTekstOdgovora()).append("\n\t");
            else s.append(m.getKey()).append(": ").append(m.getValue().getTekstOdgovora());
        }
        return s.toString();
    }

}
