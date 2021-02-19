package football;

import java.util.Iterator;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.RDFVisitor;
import org.apache.jena.vocabulary.RDF;

public class Requetes {

    public static final String NL = System.getProperty("line.separator");

    public static void main(String[] args) {

        Model m = ModelFactory.createDefaultModel();
        m.read("football.n3");

        String football_ns = m.getNsPrefixURI("football");
        String prolog1 = "PREFIX football: <" + football_ns + ">";
        String prolog2 = "PREFIX rdf: <" + RDF.getURI() + ">";

        String rdq = prolog1 + NL + prolog2 + "SELECT ?s WHERE {?s rdf:type football:Attaquant}";

        Query query = QueryFactory.create(rdq);

        QueryExecution qexec = QueryExecutionFactory.create(query, m);

        try {

            Iterator<QuerySolution> result = qexec.execSelect();
            System.out.println("result: " + result.next());
            RDFVisitor aVisitor = new Un_Visiteur();
            for (; result.hasNext();) {

                QuerySolution sol = result.next();
                RDFNode s = sol.get("s");
                System.out.println(s.visitWith(aVisitor) + " ");
            }

            System.out.println("nice");

        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
        }

        finally {
            qexec.close();
        }

    }

}
