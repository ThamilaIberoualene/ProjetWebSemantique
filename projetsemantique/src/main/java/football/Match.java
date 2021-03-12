package football;

import java.util.Date;

public class Match {

    private String lieuMatch;
    private Date dateMatch;
    private Equipe equipeLocal;
    private Equipe equipeVisitant;

    public Match() {
    }

    public Match(String lieuMatch, Date dateMatch, Equipe equipeLocal, Equipe equipeVisitant) {

        this.setDateMatch(dateMatch);
        this.setEquipeLocal(equipeLocal);
        this.setEquipeVisitant(equipeVisitant);
        this.setLieuMatch(lieuMatch);

    }

    public void setDateMatch(Date dateMatch) {
        this.dateMatch = dateMatch;
    }

    public void setEquipeLocal(Equipe equipeLocal) {
        this.equipeLocal = equipeLocal;
    }

    public void setEquipeVisitant(Equipe equipeVisitant) {
        this.equipeVisitant = equipeVisitant;
    }

    public void setLieuMatch(String lieuMatch) {
        this.lieuMatch = lieuMatch;
    }

    public Date getDateMatch() {
        return dateMatch;
    }

    public Equipe getEquipeLocal() {
        return equipeLocal;
    }

    public Equipe getEquipeVisitant() {
        return equipeVisitant;
    }

    public String getLieuMatch() {
        return lieuMatch;
    }

}
