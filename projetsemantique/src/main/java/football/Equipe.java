package football;

import java.util.regex.Matcher;

public class Equipe {

    private String uri;
    private String nomEquipe;
    private String pays;
    private String stade;
    private Entraineur coach;

    public Equipe() {
    }

    public Equipe(String uri, String nomEquipe, String pays, String stade, Entraineur coach) {

        this.setUri(uri);
        this.setNomEquipe(nomEquipe);
        this.setPays(pays);
        this.setStade(stade);
        this.setCoach(coach);
    }

    public void setCoach(Entraineur coach) {
        this.coach = coach;
    }

    public Entraineur getCoach() {
        return coach;
    }

    public void setStade(String stade) {
        this.stade = stade;
    }

    public String getStade() {
        return stade;
    }

    public void setUri(String uri) {

        this.uri = uri;
    }

    public String getUri() {
        return uri;
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
