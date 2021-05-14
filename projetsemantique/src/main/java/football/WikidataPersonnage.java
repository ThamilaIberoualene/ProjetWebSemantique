package projet;

import java.io.FileOutputStream;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

public class WikidataPersonnage

{
	public static final String NL = System.getProperty("line.separator");
	public static final String rdf_file = "football.n3";

	public static void main(String[] args) {
		try {
			Model m = ModelFactory.createDefaultModel();
			m.read(rdf_file);
			String a_ns = m.getNsPrefixURI("sport");
			Resource hugo_Loris = m.getResource(a_ns + "Hugo_Lloris");
			Resource coman = m.getResource(a_ns + "kingsley_coman");
			Resource rabiot = m.getResource(a_ns + "adrien_rabiot");
			Resource hernandez = m.getResource(a_ns + "lucas_hernandez");
			Resource digne = m.getResource(a_ns + "lucas_digne");
			Resource thuram = m.getResource(a_ns + "marcus_thuram");
			Resource pres_kimpembe = m.getResource(a_ns + "presnel_kimpembe");
			
			
			
			
			Property personnage = m.createProperty(a_ns + "personnage");
			m.setNsPrefix("wd", "http://www.wikidata.org/entity/");
			m.setNsPrefix("wdt", "http://www.wikidata.org/prop/direct/");
			String sparqlService = "https://query.wikidata.org/sparql";

			String prolog2 = "PREFIX rdfs: <" + RDFS.getURI() + ">";
			String prolog1 = "PREFIX schema: <http://schema.org/>";
			String prolog3 = "PREFIX wd: <http://www.wikidata.org/entity/>";
			String prolog4 = "PREFIX wdt: <http://www.wikidata.org/prop/direct/>";

			String rdq = prolog1 + NL + prolog2 + NL + prolog3 + NL + prolog4 + NL
					+ "SELECT ?s ?label ?o ?label_s WHERE { bind(wd:Q937857 as ?s) "
					+ " ?s rdfs:label ?label ;  wdt:P31 ?o . "
					+ " ?o rdfs:label ?label_s filter(lang(?label) ='fr' && lang(?label_s) ='fr') }";

			Query query = QueryFactory.create(rdq);
			QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlService, query);
			Resource cartoon = null;
			Resource cartoon_type = null;

			ResultSet results = qexec.execSelect();
			for (; results.hasNext();) {
				QuerySolution sol = results.next();
				cartoon = (Resource) sol.get("?s");
				cartoon_type = (Resource) sol.get("?o");
				Literal label = (Literal) sol.get("?label");
				Literal label_s = (Literal) sol.get("?label_s");
				
				m.add(hugo_Loris, personnage, cartoon);
				m.add(coman, personnage, cartoon);
				m.add(rabiot, personnage, cartoon);
				m.add(hernandez, personnage, cartoon);
				m.add(digne, personnage, cartoon);
				m.add(thuram, personnage, cartoon);
				m.add(pres_kimpembe, personnage, cartoon);
				m.add(cartoon, RDF.type, cartoon_type);
				m.add(cartoon, RDFS.label, label);
				m.add(cartoon_type, RDFS.label, label_s);
				
			}

			qexec.close();
			m.write(System.out, "N3");
			FileOutputStream outStream = new FileOutputStream("footBallAndWikidata.n3"); //
			m.write(outStream, "N3");
			outStream.close();
		} catch (Exception e) {
			System.out.println("failure" + e);
		} finally {
		}
	}
}