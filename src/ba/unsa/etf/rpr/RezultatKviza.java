package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.Map;

public class RezultatKviza {
    Kviz kviz;
    double total;
    Map<Pitanje,Double> bodovi;

        public RezultatKviza(Kviz kviz) {
            this.kviz = kviz;
            total=0;
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

    @Override
    public String toString() {
        String s=String.format("Na kvizu "+kviz.getNaziv()+ " ostvarili ste ukupno %.1f poena. Raspored po pitanjima:\n",getTotal());
        for (Map.Entry<Pitanje, Double> m : bodovi.entrySet()) {
            s=s+m.getKey();
            s=s+String.format(" - %.1f",m.getValue());
            s=s+"b\n";
        }
        return s;
    }

};
