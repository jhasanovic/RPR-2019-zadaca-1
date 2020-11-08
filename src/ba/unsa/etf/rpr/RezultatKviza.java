package ba.unsa.etf.rpr;

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

};
