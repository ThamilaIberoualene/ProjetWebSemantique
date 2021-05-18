package football;

import java.util.ArrayList;

public class Arbitre extends Personne {

    public static ArrayList<Arbitre> refereeList = new ArrayList<>();

    public Arbitre(String uri) {
        this.setUri(uri);
    }

    public Arbitre(String uri, String label, String dateNaissance, String nationalite) {
        super(uri, label, dateNaissance, nationalite);
    }

    public static void addReferee(Arbitre referee) {

        if (Arbitre.containsURI(referee.getUri())) {
            System.out.println("Arbitre déjà ajouté");
        } else {
            Arbitre.refereeList.add(referee);
        }

    }

    public static boolean containsURI(String uri) {

        for (Arbitre a : Arbitre.refereeList) {

            if (a.getUri().equals(uri)) {
                return true;
            }

        }
        return false;

    }

    public static Arbitre getElemByURI(String uri) {

        return Arbitre.refereeList.stream().filter(a -> uri.equals(a.getUri())).findFirst().orElse(null);

    }
}
