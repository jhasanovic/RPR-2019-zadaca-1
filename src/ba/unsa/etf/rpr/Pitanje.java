package ba.unsa.etf.rpr;

import java.util.*;

public class Pitanje {
    String tekst;
    double brojPoena;
    Map<String,Odgovor> odgovori;

    public Pitanje(String tekst, double brojPoena) {
        this.tekst = tekst;
        this.brojPoena = brojPoena;
        odgovori=new HashMap<String,Odgovor>();
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
        if(odgovori.containsKey(id)==false) throw new IllegalArgumentException("Odgovor s ovim id-em ne postoji");
        odgovori.remove(id);
    }

    public List<Odgovor> dajListuOdgovora() {
        List<Odgovor> lista=new ArrayList<Odgovor>(odgovori.values());
        return lista;
    }

    public double izracunajPoene(List<String> lista, SistemBodovanja sistemBodovanja) {
        double ukupniPoeni=0;

        for(int i=0;i<lista.size();i++){
            for(int j=i+1;j<lista.size();j++) {
                if (lista.get(i).equals(lista.get(j)))
                    throw new IllegalArgumentException("Postoje duplikati među odabranim odgovorima");
            }
        }
        for(int i=0;i<lista.size();i++) {
            if(odgovori.containsKey(lista.get(i))==false) throw new IllegalArgumentException("Odabran je nepostojeći odgovor");
        }

        if(sistemBodovanja==SistemBodovanja.BINARNO){
            if(lista.size()==0) return 0;
            else {
                for (int i = 0; i < lista.size(); i++) {
                    if(!odgovori.get(lista.get(i)).isTacno())
                    return 0;
                }
                int brojTacnih=0;
                for (Map.Entry<String, Odgovor> m : odgovori.entrySet()) {
                    if(m.getValue().isTacno()) brojTacnih++;
                }
                if(brojTacnih==lista.size()) return brojPoena;
            }
        }

        else if(sistemBodovanja==SistemBodovanja.PARCIJALNO){
            int brojZaokruzenihTacnih=0;
            int brojTacnih=0;
            int brojOdgovora=0;
            for (int i = 0; i < lista.size(); i++) {
                if(!odgovori.get(lista.get(i)).isTacno()) return 0;
            }
            for (Map.Entry<String, Odgovor> m : odgovori.entrySet()) {
                if(m.getValue().isTacno()) brojTacnih++;
                brojOdgovora++;
            }
            brojZaokruzenihTacnih=lista.size();
            if(brojTacnih==brojZaokruzenihTacnih) ukupniPoeni=brojPoena;
            else ukupniPoeni = (brojPoena/brojOdgovora)*brojZaokruzenihTacnih;
        }
        else if(sistemBodovanja==SistemBodovanja.PARCIJALNO_SA_NEGATIVNIM){
            int brojZaokruzenihTacnih=0;
            int brojTacnih=0;
            int brojOdgovora=0;
            for (int i = 0; i < lista.size(); i++) {
                if(!odgovori.get(lista.get(i)).isTacno()) return -brojPoena/2;
            }
            for (Map.Entry<String, Odgovor> m : odgovori.entrySet()) {
                if(m.getValue().isTacno()) brojTacnih++;
                brojOdgovora++;
            }
            brojZaokruzenihTacnih=lista.size();
            if(brojTacnih==brojZaokruzenihTacnih) ukupniPoeni = brojPoena;
            else ukupniPoeni = (brojPoena/brojOdgovora)*brojZaokruzenihTacnih;
        }
        return ukupniPoeni;
    }

    @Override
    public String toString() {
        String s = tekst +"("+brojPoena+"b)\n\t";
        Iterator<Map.Entry<String, Odgovor>> it = odgovori.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Odgovor> m = it.next();
            if(it.hasNext())
            s=s+m.getKey()+": "+m.getValue().getTekstOdgovora()+"\n\t";
            else s=s+m.getKey()+": "+m.getValue().getTekstOdgovora();
        }
        return s;
    }
}
