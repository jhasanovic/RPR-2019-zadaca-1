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
            bodovi=new HashMap<Pitanje,Double>();
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
        String s=String.format("Na kvizu \""+kviz.getNaziv()+ "\" ostvarili ste ukupno %.1f poena. Raspored po pitanjima:\n",getTotal());
        Iterator<Map.Entry<Pitanje, Double>> it = bodovi.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Pitanje, Double> m = it.next();
            s=s+m.getKey().getTekst();
            s=s+String.format(" - %.1f",m.getValue());
            if(it.hasNext()) {
                s=s+"b\n";
            }
            else s=s+"b";
        }
        return s;
    }

};
