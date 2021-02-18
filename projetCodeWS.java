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
	
	static final String football = "http://www.ex.fr/sport#";
	public static void main(String args[]) {

		try {
			Model m = ModelFactory.createDefaultModel();
			m.setNsPrefix("football", football);
			m.setNsPrefix("rdf", RDF.getURI());
			m.setNsPrefix("rdfs", RDFS.getURI());
			
			//super-Class
			//Personnage
			Resource personnage = m.createResource(football + "Personnage");
			m.add(personnage, RDFS.subClassOf,RDFS.Class);
			//Match
			Resource match = m.createResource(football + "Match");
			m.add(personnage, RDFS.subClassOf,RDFS.Class);
			//Poste
			Resource poste = m.createResource(football + "Poste");
			m.add(personnage, RDFS.subClassOf,RDFS.Class);
			//Equipe
			Resource equipe = m.createResource(football + "Equipe");
			m.add(equipe, RDFS.subClassOf,RDFS.Class);
			
			//class
			//Competition
			Resource competition = m.createResource(football + "Competition");
			m.add(competition, RDFS.subClassOf,RDFS.Class);
			//TituOuRemplacant
			Resource tituouremplacant = m.createResource(football + "TituOuRemplacant");
			m.add(tituouremplacant, RDFS.subClassOf,RDFS.Class);
			
			
			//Sous-class
			//Footballer
			Resource footballeur = m.createResource(football + "Footballeur");
			footballeur.addProperty(RDFS.subClassOf, personnage);
			//Arbitre
			Resource arbitre = m.createResource(football + "Arbitre");
			arbitre.addProperty(RDFS.subClassOf, personnage);
			//Entraineur
			Resource entraineur = m.createResource(football + "Entraineur");
			entraineur.addProperty(RDFS.subClassOf, personnage);
			//EquipeClub
			Resource equipeClub = m.createResource(football + "EquipeClub");
			equipeClub.addProperty(RDFS.subClassOf, equipe);
			//EquipeNational
			Resource equipeNational = m.createResource(football + "EquipeNational");
			equipeNational.addProperty(RDFS.subClassOf, equipe);
			//MatchAmical
			Resource matchAmical = m.createResource(football + "MatchAmical");
			matchAmical.addProperty(RDFS.subClassOf, match);
			//MatchCompetition
			Resource matchCompetition = m.createResource(football + "MatchCompetition");
			matchCompetition.addProperty(RDFS.subClassOf, match);
			//MilieuDeTerrain
			Resource milieuDeTerrain = m.createResource(football + "MilieuDeTerrain");
			milieuDeTerrain.addProperty(RDFS.subClassOf, poste);
			//Attaquant
			Resource attaquant = m.createResource(football + "Attaquant");
			attaquant.addProperty(RDFS.subClassOf, poste);
			//Defenseur
			Resource defenseur = m.createResource(football + "Defenseur");
			defenseur.addProperty(RDFS.subClassOf, poste);
			//GardienDeBut
			Resource gardienDeBut = m.createResource(football + "GardienDeBut");
			gardienDeBut.addProperty(RDFS.subClassOf, poste);
			
			
		 	//Propriete(attribut) (pas specifique a une class)
			//personnage
			Property nom = m.createProperty(football + "nom");
			Property prenom = m.createProperty(football + "prenom");
			Property age = m.createProperty(football + "age");
			Property nationalite = m.createProperty(football + "nationalite");
			//Equipe
			Property nomEquipe = m.createProperty(football + "nomEquipe");
			Property pays = m.createProperty(football + "pays");
			//EquipeClub
			Property paysECI = m.createProperty(football + "paysECI");
			//Match
			Property dateMatch = m.createProperty(football + "dateMatch");
			Property lieuMatch = m.createProperty(football + "lieuMatch");
			Property nomAdversaire = m.createProperty(football + "nomAdversaire");
			//Competition
			Property dateDebut = m.createProperty(football + "dateDebut");
			Property dateFin = m.createProperty(football + "dateFin");
			Property nomC = m.createProperty(football + "nomC");
			//Footballer
			Property numeroMaillot = m.createProperty(football + "numeroMaillot");
			//TituOuRemplacant(boolean)
			Property titulaire = m.createProperty(football + "titulaire");
			
			//Propriete(role)
			Property appartient = m.createProperty(football + "appartient");
			Property entrainerPar = m.createProperty(football + "entrainerPar");
			Property arbitrerPar = m.createProperty(football + "arbitrerPar");
			Property faitPartie = m.createProperty(football + "faitPartie");
			
			
			
			
			//Instance
			Resource hugo_Loris = m.createResource(football + "Hugo_Loris");
			hugo_Loris.addProperty(RDF.type, footballeur);
			hugo_Loris.addProperty(RDFS.label, "Hugo_Loris");
			
			Resource equipeDeFrance = m.createResource(football + "EquipeDeFrance");
			equipeDeFrance.addProperty(RDF.type, equipeNational);
			equipeDeFrance.addProperty(RDFS.label, "EquipeDeFrance");
			hugo_Loris.addProperty(appartient,equipeDeFrance);
			
			Resource didier_Deschamps = m.createResource(football + "Didier_Deschamps");
			didier_Deschamps.addProperty(RDF.type, entraineur);
			didier_Deschamps.addProperty(RDFS.label, "Didier_Deschamps");
			equipeDeFrance.addProperty(entrainerPar,didier_Deschamps);
			
			Resource pierluigi_Collina = m.createResource(football + "Pierluigi_Collina");
			pierluigi_Collina.addProperty(RDF.type, arbitre);
			pierluigi_Collina.addProperty(RDFS.label, "Pierluigi_Collina");
			
			Resource finalCoupeDuMonde2018 = m.createResource(football + "FinalCoupeDuMonde2018");
			finalCoupeDuMonde2018.addProperty(RDF.type, matchCompetition);
			finalCoupeDuMonde2018.addProperty(RDFS.label, "FinalCoupeDuMonde2018");
			finalCoupeDuMonde2018.addProperty(arbitrerPar,pierluigi_Collina);
			
			Resource coupeDuMonde2018 = m.createResource(football + "CoupeDuMonde2018");
			coupeDuMonde2018.addProperty(RDF.type, competition);
			coupeDuMonde2018.addProperty(RDFS.label, "CoupeDuMonde2018");
			finalCoupeDuMonde2018.addProperty(faitPartie,coupeDuMonde2018);
			
			
			
			
			
			
			
		
			
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
