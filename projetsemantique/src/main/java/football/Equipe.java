package football;

public class Equipe {

    private String nomEquipe;
    private String pays;

    public Equipe() {
    }

    public Equipe(String nomEquipe, String pays) {

        this.setNomEquipe(nomEquipe);
        this.setPays(pays);
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public String getPays() {
        return pays;
    }

}
