package Projet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.SymmetricProperty;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.reasoner.ValidityReport;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.XSD;

public class owl {
	
	// espace de nom du modele
		static final String football_ns = "http://www.ex.fr/football#";
		
		// espace de nom des individus du modele
		static final String individus= "http://www.ex.fr/individus#";
	public static void main(String args[]) {
		OntModel om = ModelFactory.createOntologyModel();
		om.setNsPrefix("football", football_ns);
		om.setNsPrefix("ex", individus);
		om.setNsPrefix("rdf", RDF.getURI());
		om.setNsPrefix("rdfs", RDFS.getURI());
		om.setNsPrefix("xsd", XSD.getURI());
		
		OntClass Equipe = om.createClass(football_ns + "Equipe");
		OntClass EquipeNational = om.createClass(football_ns + "EquipeNational");
		OntClass match = om.createClass(football_ns +" match");
		OntClass Competition = om.createClass(football_ns+ " Competition");
		OntClass personne = om.createClass(football_ns + "perosnne");
		OntClass poste = om.createClass(football_ns + "poste");
		OntClass footballeur = om.createClass(football_ns + "footballeur");
		OntClass Entraineur = om.createClass(football_ns + "Entraineur");
		OntClass Arbitre = om.createClass(football_ns + "Arbitre");
		
		football_ns.addSuperClass(OWL.Class);
		
		
		footballeur.addDisjointWith(Entraineur);
		footballeur.addDisjointWith(Arbitre);
		Entraineur.addDisjointWith(Arbitre);
		
		SymmetricProperty iAvec =
	    om.createSymmetricProperty(footballeur + "ineragitAvec");
		
		
		SymmetricProperty jcontre =
	    om.createSymmetricProperty(Equipe + "	jouecontre");
				
				
		
		//propriété
		DatatypeProperty score = om.createDatatypeProperty(football_ns + "score");
		DatatypeProperty lieu = om.createDatatypeProperty(football_ns+ "lieu");
		ObjectProperty date = om.createObjectProperty(football_ns + "date");
		
		ObjectProperty EquipeParticipe = om.createObjectProperty(football_ns+ "EquipeParticipe");
		
		ObjectProperty aPourRole = om.createObjectProperty(football_ns + "aPourRole");
		
       //individus:
		
		Individual EquipeDeFrance = EquipeNational.createIndividual(individus + "EquipeDeFrance");
		EquipeDeFrance.addProperty(score, om.createTypedLiteral("4", XSD.getURI() + "int"));
		Individual EquipeDeCroatie = EquipeNational.createIndividual(individus + "EquipeDeCroatie");
		EquipeDeCroatie.addProperty(score, om.createTypedLiteral("2", XSD.getURI() + "int"));
		
		
		
		
		om.write(System.out, "N3");

		try { FileOutputStream outStream = new FileOutputStream("footballOwl.n3");
		om.write(outStream, "N3");
		  outStream.close(); } 
		
		  catch (FileNotFoundException e)
		  {System.out.println("File not found");} 
		
		catch (IOException e)
		  {System.out.println("IO problem");
		  }
 }

}
