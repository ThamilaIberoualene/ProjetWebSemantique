package football;

public class Equipe {

    private String uri;
    private String nomEquipe;
    private String pays;
    private String stade;

    public Equipe() {
    }

    public Equipe(String uri, String nomEquipe, String pays, String stade) {

        this.setUri(uri);
        this.setNomEquipe(nomEquipe);
        this.setPays(pays);
        this.setStade(stade);
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
