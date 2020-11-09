package ba.unsa.etf.rpr;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RezultatKviza {
    private Kviz kviz;
    private double total;
    private Map<Pitanje,Double> bodovi;

        public RezultatKviza(Kviz kviz) {
            this.kviz = kviz;
            total=0;
            bodovi= new HashMap<>();
        }

        public Kviz getKviz() {
            return kviz;
        }

        public double getTotal() {
            return total;
        }

        public void setKviz(Kviz kviz) {
            this.kviz = kviz;
        }

        public void setTotal(double total) {
            this.total = total;
        }

    public Map<Pitanje,Double> getBodovi() {
            return bodovi;
    }

    public void setBodovi(Map<Pitanje, Double> bodovi) {
        this.bodovi = bodovi;
    }

    @Override
    public String toString() {
        StringBuilder s= new StringBuilder(String.format("Na kvizu \"" + kviz.getNaziv() + "\" ostvarili ste ukupno %.1f poena. Raspored po pitanjima:\n", getTotal()));
        Iterator<Map.Entry<Pitanje, Double>> it = bodovi.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Pitanje, Double> m = it.next();
            s.append(m.getKey().getTekst());
            s.append(String.format(" - %.1f", m.getValue()));
            if(it.hasNext()) {
                s.append("b\n");
            }
            else s.append("b");
        }
        return s.toString();
    }

};
