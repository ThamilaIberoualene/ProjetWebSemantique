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

public class Footballeur extends Personne {

    public static ArrayList<Footballeur> listPlayers = new ArrayList<>();

    private String poste;
    private EquipeNationale equipeNal;
    private EquipeClub equipeClub;
    private int numMaillot;

    public Footballeur() {
    }

    public Footballeur(String uri, String label, String dateNaissance, String nationalite, String poste) {

        super(uri, label, dateNaissance, nationalite);
        this.setPoste(poste);

    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getPoste() {
        return poste;
    }

    public void setEquipeClub(EquipeClub equipeClub) {
        this.equipeClub = equipeClub;
    }

    public EquipeClub getEquipeClub() {
        return equipeClub;
    }

    public void setEquipeNal(EquipeNationale equipeNal) {
        this.equipeNal = equipeNal;
    }

    public EquipeNationale getEquipeNal() {
        return equipeNal;
    }

    public void setNumMaillot(int numMaillot) {
        this.numMaillot = numMaillot;
    }

    public int getNumMaillot() {
        return numMaillot;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return getNationalite() + " " + getLabel() + " " + getPoste();
    }

    public static void instaceConstructor(String req) {

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
                RDFNode uri = sol.get("player");
                RDFNode name = sol.get("name");
                RDFNode posteLbl = sol.get("posteLbl");
                RDFNode dateBirth = sol.get("dob");
                RDFNode nationalite = sol.get("nationaliteLbl");

                Footballeur player = new Footballeur(uri.visitWith(aVisitor).toString(),
                        name.visitWith(aVisitor).toString(), dateBirth.visitWith(aVisitor).toString(),
                        nationalite.visitWith(aVisitor).toString(), posteLbl.visitWith(aVisitor).toString());

                Footballeur.listPlayers.add(player);
            }

        } catch (

        Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

    }

}
