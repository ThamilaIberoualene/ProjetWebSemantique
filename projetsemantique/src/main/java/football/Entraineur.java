package football;

import java.util.ArrayList;

public class Entraineur extends Personne {

    public static ArrayList<Entraineur> coachList = new ArrayList<>();

    public Entraineur() {
    }

    public Entraineur(String uri) {
        this.setUri(uri);
    }

    public Entraineur(String uri, String label, String dateNaissance, String nationalite) {
        super(uri, label, dateNaissance, nationalite);
    }

    public static boolean containsURI(String uri) {

        return Entraineur.coachList.stream().anyMatch(e -> uri.equals(e.getUri()));
    };

    public static Entraineur getElemByURI(String uri) {

        return Entraineur.coachList.stream().filter(a -> uri.equals(a.getUri())).findFirst().orElse(null);

    };

    public static void addElem(Entraineur e) {

        if (!Entraineur.containsURI(e.getUri())) {
            Entraineur.coachList.add(e);
        } else {
            System.out.println("Entraineur déjà ajouté");
        }
    };
}
