package football;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.jena.atlas.io.IndentedWriter;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.RDFVisitor;

public class Match {

    public static ArrayList<Match> matchList = new ArrayList<>();

    private String uri;
    private String lieuMatch;
    private String dateMatch;
    private Equipe equipeLocal;
    private Equipe equipeVisitant;
    private Competition competition;
    private Arbitre arbitre;

    public Match() {
    }

    public Match(String uri) {
        this.setUri(uri);
    }

    public Match(String lieuMatch, String dateMatch, Equipe equipeLocal, Equipe equipeVisitant) {

        this.setDateMatch(dateMatch);
        this.setEquipeLocal(equipeLocal);
        this.setEquipeVisitant(equipeVisitant);
        this.setLieuMatch(lieuMatch);

    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setArbitre(Arbitre arbitre) {
        this.arbitre = arbitre;
    }

    public Arbitre getArbitre() {
        return arbitre;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setDateMatch(String dateMatch) {
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

    public String getDateMatch() {
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
