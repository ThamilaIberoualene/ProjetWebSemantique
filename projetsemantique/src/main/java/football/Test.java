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
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.RDFVisitor;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.XSD;

public class Test {

    static final String football_ns = "football";
    static final String football_uri = "https://www.wikidata.org/wiki/Q2736";

    public static final String NL = System.getProperty("line.separator");

    public static void main(String[] args) {

        Model m = ModelFactory.createDefaultModel();
        String sparqlService = "http://query.wikidata.org/sparql";

        m.setNsPrefix(football_ns, football_uri);
        m.setNsPrefix("rdf", RDF.getURI());
        m.setNsPrefix("rdfs", RDFS.getURI());
        m.setNsPrefix("xsd", XSD.getURI());
        m.setNsPrefix("wd", "http://www.wikidata.org/entity/"); // Entity
        m.setNsPrefix("wdt", "http://www.wikidata.org/prop/direct/"); // Property
        m.setNsPrefix("p", "http://www.wikidata.org/prop/"); // Property
        m.setNsPrefix("pq", "http://www.wikidata.org/prop/qualifier/"); // Property Qualifier
        m.setNsPrefix("ps", "http://www.wikidata.org/prop/statement/"); // Property Statement

        Resource personne = m.createResource(football_ns + "Personne");
        m.add(personne, RDFS.subClassOf, RDFS.Class);

        Resource footballeur = m.createResource(football_ns + "Footballeur");
        m.add(footballeur, RDFS.subClassOf, personne);

        Property nom = m.createProperty(football_ns + "nom");
        Property prenom = m.createProperty(football_ns + "prenom");
        Property dateNaissance = m.createProperty(football_ns + "dateBirth");
        Property nationalite = m.createProperty(football_ns + "nationalite");
        Property poste = m.createProperty(football_ns + "poste");
        Property numeroMaillot = m.createProperty(football_ns + "numeroMaillot");

        String prolog1 = "PREFIX " + football_ns + ": <" + football_uri + ">";
        String prolog2 = "PREFIX rdf: <" + RDF.getURI() + ">";
        String prolog3 = "PREFIX rdfs: <" + RDFS.getURI() + ">";
        String prolog4 = "PREFIX wd: <" + m.getNsPrefixURI("wd") + ">";
        String prolog5 = "PREFIX wdt: <" + m.getNsPrefixURI("wdt") + ">";
        String prolog6 = "PREFIX p: <" + m.getNsPrefixURI("p") + ">";
        String prolog7 = "PREFIX ps: <" + m.getNsPrefixURI("ps") + ">";
        String prolog8 = "PREFIX pq: <" + m.getNsPrefixURI("pq") + ">";

        String rdq = prolog2 + NL + prolog3 + NL + prolog4 + NL + prolog5 + NL + prolog6 + NL + prolog7 + NL + prolog8
                + "SELECT ?player ?name ?posteLbl ?dob WHERE { ?player wdt:P54 wd:Q47774 ;"
                + "wdt:P569 ?dob ; wdt:P413 ?poste ; rdfs:label ?name ; p:P54 ?statement . ?statement ps:P54 wd:Q47774;"
                + "pq:P580 ?from . FILTER (lang(?name) = 'fr') . ?poste rdfs:label ?posteLbl . FILTER(lang(?posteLbl) = 'fr') ."
                + "OPTIONAL { ?statement pq:P582 ?until } . FILTER(!( BOUND( ?until) )) } ORDER BY ?name";

        // + "SELECT ?player ?from WHERE { ?player wdt:P54 wd:Q47774 ;"
        // + "p:P54 ?stat . ?stat ps:P54 wd:Q47774 ; pq:P580 ?from }";
        // + "pq:P580 ?from . FILTER (lang(?name) = 'fr') ."
        // + "OPTIONAL { ?statement pq:P582 ?until } . FILTER(!( BOUND( ?until) )) }
        // ORDER BY ?name";

        // SELECT ?player ?name ?natname ?dob
        // WHERE {?player wdt:P54 wd:Q47774;
        // wdt:P27 ?nationalite;
        // wdt:P569 ?dob;
        // rdfs:label ?name FILTER (lang(?name) = 'fr') .
        // ?nationalite rdfs:label ?natname FILTER(lang(?natname) = 'fr') .
        // MINUS {?player wdt:P54 wd:Q47774 ;
        // p:P54 [ ps:P54 wd:Q47774 ; pq:P582 ?fin] }}

        Query query = QueryFactory.create(rdq);
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
                RDFNode player = sol.get("player");
                RDFNode name = sol.get("name");
                RDFNode posteLbl = sol.get("posteLbl");
                RDFNode dateBirth = sol.get("dob");

                System.out.println();
                System.out.println(player.visitWith(aVisitor) + " ");
                System.out.println(name.visitWith(aVisitor) + " ");
                System.out.println(posteLbl.visitWith(aVisitor) + " ");
                System.out.println(dateBirth.visitWith(aVisitor) + " ");

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

    }

}
