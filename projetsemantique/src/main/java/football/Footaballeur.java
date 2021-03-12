package football;

public class Footaballeur extends Personne {

    private String nMaillot;
    private Poste poste;

    public Footaballeur() {
    }

    public Footaballeur(String nom, String prenom, String age, String nationalite, String nMaillot, Poste poste) {
        super(nom, prenom, age, nationalite);
        this.setnMaillot(nMaillot);
        this.setPoste(poste);

    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public void setnMaillot(String nMaillot) {
        this.nMaillot = nMaillot;
    }

}
