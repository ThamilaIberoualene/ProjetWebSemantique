package projet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

public class Partie1 {
	
	static final String sport_ns = "http://www.ex.fr/sport#";
	public static void main(String args[]) {

		try {
			Model m = ModelFactory.createDefaultModel();
			m.setNsPrefix("sport", sport_ns);
			m.setNsPrefix("rdf", RDF.getURI());
			m.setNsPrefix("rdfs", RDFS.getURI());
			// FootBall
			Resource football = m.createResource(sport_ns + "Football");
			m.add(football, RDFS.subClassOf,RDFS.Class);
			// Ressource Equipe
			Resource equipe = m.createResource(sport_ns + "Equipe");
			// Equipe sous-classe de 
			equipe.addProperty(RDFS.subClassOf, football);
			// Ressource RealMadrid
			Resource realMadrid = m.createResource(sport_ns + "RealMadrid");
			realMadrid.addProperty(RDF.type, equipe);
			realMadrid.addProperty(RDFS.label, "RealMadrid");
			// Propriete pays
			Property pays = m.createProperty(sport_ns + "pays");
			realMadrid.addProperty(pays, m.createLiteral("Espagne", "fr"));
			
			
			//Ressource Competition
			Resource competition = m.createResource(sport_ns + "Competition");
			// Competition sous-classe de 
			competition.addProperty(RDFS.subClassOf, football);
			//Ressource LigueDesChampions
			Resource ligueDesChampions = m.createResource(sport_ns + "LigueDesChampions");
			
			// affichage des triplets
			// N3 (ou TURTLE), N-TRIPLE, RDF/XML, JSON-LD 
			m.write(System.out, "TURTLE");
			
			try { FileOutputStream outStream = new FileOutputStream("Real.n3"); //
			  m.write(outStream, "N3");
			  outStream.close(); } 
			  catch (FileNotFoundException e)
			  {System.out.println("File not found");} catch (IOException e)
			  {System.out.println("IO problem");}
		} catch (Exception e) {
			System.out.println("failure" + e);
		}
	}

}
