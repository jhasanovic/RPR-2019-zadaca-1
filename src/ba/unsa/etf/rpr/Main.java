package ba.unsa.etf.rpr;

import java.util.*;

public class Main {
    private static Kviz kviz;
    private static List<Pitanje> pitanja;
    static void postavka() {
        Pitanje pitanje1 = new Pitanje("Koje od navedenih tvrdnji su tačne za interfejse u Javi?", 2);
        pitanje1.dodajOdgovor("a", "Interfejsi nemaju atribute i sve metode su apstraktne", true);
        pitanje1.dodajOdgovor("b", "Višestruko nasljeđivanje interfejsa nije dozvoljeno", false);
        pitanje1.dodajOdgovor("c", "Interfejs može sadržavati prave konstante", true);
        pitanje1.dodajOdgovor("d", "Metode interfejsa se moraju označiti sa abstract", false);

        Pitanje pitanje2 = new Pitanje("Ako klasa nasljeđuje dva interfejsa sa identičnom metodom, od kojih je u jednom interfejsu metoda default: ", 2);
        pitanje2.dodajOdgovor("a", "Klasa će preuzeti default implementaciju metode", false);
        pitanje2.dodajOdgovor("b", "Klasa će preuzeti metodu interfejsa koji je prvi naveden iza ključne riječi implements", false);
        pitanje2.dodajOdgovor("c", "Default implementacija metode neće biti preuzeta, nego klasa mora ponuditi vlastitu verziju metode", true);

        Pitanje pitanje3 = new Pitanje("Za šta od navedenog se ključna riječ final ne koristi? ", 2);
        pitanje3.dodajOdgovor("a", "Za definisanje konstanti", false);
        pitanje3.dodajOdgovor("b", "Za pozivanje metoda bazne klase iz izvedene klase", true);
        pitanje3.dodajOdgovor("c", "Za metode koje ne trebaju moći biti naslijeđene", false);
        pitanje3.dodajOdgovor("d", "Za označavanje interfejsa koji ne sadrže niti jednu metodu", true);

        Pitanje pitanje4 = new Pitanje("Koja od navedenih klasa izvedenih iz List je \"thread-safe\"? ", 2);
        pitanje4.dodajOdgovor("a", "Vector", true);
        pitanje4.dodajOdgovor("b", "ArrayList", false);
        pitanje4.dodajOdgovor("c", "LinkedList", false);

        Pitanje pitanje5 = new Pitanje("Koje od navedenih tvrdnji vrijede za mape u Javi?", 2);
        pitanje5.dodajOdgovor("a", "Mapa može sadržavati identične vrijednosti pridružene različitim ključevima", true);
        pitanje5.dodajOdgovor("b", "TreeMap je implementacija mape koja uvijek zadržava poredak elemenata u redoslijedu njihovog dodavanja", false);
        pitanje5.dodajOdgovor("c", "HashMap je za razliku od HashTable sinhronizovana i \"thread-safe\"", false);
        pitanje5.dodajOdgovor("d", "Metoda get(k) daje objekat pridružen ključu k", true);

        pitanja = new ArrayList<>();
        pitanja.add(pitanje1);
        pitanja.add(pitanje2);
        pitanja.add(pitanje3);
        pitanja.add(pitanje4);
        pitanja.add(pitanje5);
        kviz = new Kviz("Kviz Java", SistemBodovanja.PARCIJALNO);
        pitanja.forEach(pitanje -> kviz.dodajPitanje(pitanje));
    }

    public static void igrajKviz (Kviz k){
    postavka();

        System.out.println("Dobrodošli u "+kviz.getNaziv());
        System.out.println("Kviz se sastoji od pet pitanja koja ukupno nose 10 bodova.");
        System.out.println("Na pitanja odgovarate unosom slova koje odgovara željenom pitanju. Nakon svakog unosa pritisnite ENTER.");
        System.out.println("Odgovor na pitanje završava se unosom \"0\". Moguće je i ne odgovoriti na ponuđeno pitanje. Sretno!\n\n");
        Scanner ulaz=new Scanner(System.in);
        Map<Pitanje, ArrayList<String>> zaokruzeniOdgovori=new HashMap<>();
        // u mapu dodajem samo odgovorena pitanja
        for (Pitanje pitanje : pitanja) {
            System.out.println(pitanje.toString());
            ArrayList<String> lista = new ArrayList<>();
            char unos;
            do {
                unos = ulaz.next().charAt(0);
                if (unos == 'a' || unos == 'b' || unos == 'c' || unos == 'd') {
                    lista.add(String.valueOf(unos));
                }
            } while (unos != '0');
            if (!lista.isEmpty())
                zaokruzeniOdgovori.put(pitanje, lista);
        }
        RezultatKviza rezultat=kviz.predajKviz(zaokruzeniOdgovori);
        System.out.println(rezultat.toString());

    }
    public static void main(String[] args) {
    igrajKviz(kviz);
    }
}
