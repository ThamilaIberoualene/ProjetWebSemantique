package football;

import java.util.Iterator;

import org.apache.jena.atlas.io.IndentedWriter;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.RDFVisitor;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

public class Req_Sparql {

    public static final String NL = System.getProperty("line.separator");

    public static void main(String[] args) {

        String sparqlService = "http://query.wikidata.org/sparql";

        Model m = ModelFactory.createDefaultModel();
        m.read("football.n3");

        String football_ns = m.getNsPrefixURI("football");
        String prolog4 = "PREFIX wd: <" + m.getNsPrefixURI("wd") + ">";
        String prolog5 = "PREFIX wdt: <" + m.getNsPrefixURI("wdt") + ">";
        String prolog3 = "PREFIX rdfs: <" + RDFS.getURI() + ">";

        // String rdq = prolog1 + NL + prolog2 + NL + prolog3
        // + "SELECT ?s ?name WHERE { ?s rdf:type football:Attaquant ; rdfs:label ?name
        // }";

        // String rdq = prolog1 + NL + prolog2 + NL + prolog3 + NL + prolog4 + NL +
        // prolog5
        // + "SELECT ?s WHERE {?s wdt:P54 wd:Q47774}";

        String rdq = prolog4 + NL + prolog5 + NL + prolog3
                + "SELECT ?s ?name  WHERE {?s wdt:P54 wd:Q47774 ; rdfs:label ?name FILTER (lang(?name) = 'fr')}";

        // SELECT ?player ?name ?natname ?dob
        // WHERE {?player wdt:P54 wd:Q47774;
        // wdt:P27 ?nationalite;
        // wdt:P569 ?dob;
        // rdfs:label ?name FILTER (lang(?name) = 'fr') .
        // ?nationalite rdfs:label ?natname FILTER(lang(?natname) = 'fr') .
        // MINUS {?player wdt:P54 wd:Q47774 ;
        // p:P54 [ ps:P54 wd:Q47774 ; pq:P582 ?fin] }}

        Query query = QueryFactory.create(rdq);

        // QueryExecution qexec = QueryExecutionFactory.create(query, m);
        QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlService, query);

        query.serialize(new IndentedWriter(System.out, true));
        System.out.println();
        try {

            ResultSet rs = qexec.execSelect();
            ResultSetFormatter.out(System.out, rs, query);

        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
        }

        finally {
            qexec.close();
        }
    }

}
