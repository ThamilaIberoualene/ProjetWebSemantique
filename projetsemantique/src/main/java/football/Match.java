package football;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;

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
    private String label;
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

    public Match(String uri, String label, String dateMatch, Competition competition, Arbitre arbitre,
            String lieuMatch) {
        this.setArbitre(arbitre);
        this.setCompetition(competition);
        this.setDateMatch(dateMatch);
        this.setLieuMatch(lieuMatch);
        this.setUri(uri);
        this.setLabel(label);
    }

    public Match(String lieuMatch, String dateMatch, Equipe equipeLocal, Equipe equipeVisitant) {

        this.setDateMatch(dateMatch);
        this.setEquipeLocal(equipeLocal);
        this.setEquipeVisitant(equipeVisitant);
        this.setLieuMatch(lieuMatch);

    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
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

    public static boolean containsURI(String uri) {

        return Match.matchList.stream().anyMatch(e -> uri.equals(e.getUri()));
    };

    public static Match getElemByURI(String uri) {

        return Match.matchList.stream().filter(a -> uri.equals(a.getUri())).findFirst().orElse(null);

    };

    public static void addElem(Match e) {

        if (!Match.containsURI(e.getUri())) {
            Match.matchList.add(e);
        } else {
            System.out.println("Match déjà ajouté");
        }
    };

    public static void instanceConstructor(String req, String compURI) {

        String sparqlService = "http://query.wikidata.org/sparql";

        Query query = QueryFactory.create(req);
        QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlService, query);
        query.serialize(new IndentedWriter(System.out, true));
        System.out.println();

        try {
            // ResultSet rs = qexec.execSelect();
            // ResultSetFormatter.out(System.out, rs, query);
            Iterator<QuerySolution> result = qexec.execSelect();

            RDFVisitor aVisitor = new Un_Visiteur();

            for (; result.hasNext();) {

                QuerySolution sol = result.next();
                RDFNode uri = sol.get("match");
                RDFNode label = sol.get("matchLbl");
                RDFNode arbitre = sol.get("referee");
                RDFNode stade = sol.get("stadiumLbl");
                RDFNode date = sol.get("date");
                Competition comp = Competition.getElemByURI(compURI);
                Arbitre referee;

                if (Arbitre.containsURI(arbitre.visitWith(aVisitor).toString())) {

                    referee = Arbitre.getElemByURI(arbitre.visitWith(aVisitor).toString());

                } else {

                    referee = new Arbitre(arbitre.visitWith(aVisitor).toString());
                    Arbitre.addReferee(referee);

                }

                Match match = new Match(uri.visitWith(aVisitor).toString(), date.visitWith(aVisitor).toString(),
                        label.visitWith(aVisitor).toString(), comp, referee, stade.visitWith(aVisitor).toString());
                Match.addElem(match);

            }

        } catch (

        Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

    }
}
