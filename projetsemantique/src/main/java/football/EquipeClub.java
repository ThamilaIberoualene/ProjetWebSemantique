package football;

public class EquipeClub extends Equipe {

    private String league;

    public EquipeClub(String uri, String nomEquipe, String pays, String stade, String league, Entraineur coach) {
        super(uri, nomEquipe, pays, stade, coach);
        this.setLeague(league);
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getLeague() {
        return league;
    }
}
