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

public class Competition {

    public static ArrayList<Competition> compList = new ArrayList<>();

    private String uri;
    private String nomComp;
    private String pays;
    private String dateDebut;
    private String dateFin;

    public Competition() {
    }

    public Competition(String uri, String pays, String nomComp, String dateDebut, String dateFin) {
        this.setDateDebut(dateDebut);
        this.setDateFin(dateFin);
        this.setNomComp(nomComp);
        this.setPays(pays);
        this.setUri(uri);

    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getPays() {
        return pays;
    }

    public String getUri() {
        return uri;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public void setNomComp(String nomComp) {
        this.nomComp = nomComp;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public String getNomComp() {
        return nomComp;
    }

    public static void instaceConstructor(String req, String uri) {

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
                RDFNode compName = sol.get("compName");
                RDFNode country = sol.get("countryLbl");
                RDFNode dateFin = sol.get("until");
                RDFNode dateDebut = sol.get("from");
                RDFNode finalMatch = sol.get("finalMatch");

                Competition comp = new Competition(uri, country.toString(), compName.toString(), dateDebut.toString(),
                        dateFin.toString());

                Match finalWordCup2018 = new Match(finalMatch.toString());
                Match.matchList.add(finalWordCup2018);

                Competition.compList.add(comp);
            }

        } catch (

        Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

    }

}
