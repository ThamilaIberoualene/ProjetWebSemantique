package football;

public class EquipeClub extends Equipe {

    private String league;

    public EquipeClub(String uri, String nomEquipe, String pays, String stade, String league) {
        super(uri, nomEquipe, pays, stade);
        this.setLeague(league);
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getLeague() {
        return league;
    }
}
