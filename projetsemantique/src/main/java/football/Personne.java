package football;

public class Personne {

    String nom;
    String prenom;
    String age;
    String nationalite;

    public Personne() {
    }

    public Personne(String nom, String prenom, String age, String nationalite) {

        this.setAge(age);
        this.setNationalite(nationalite);
        this.setNom(nom);
        this.setPrenom(prenom);

    }

    public String getNom() {
        return nom;
    }

    public String getAge() {
        return age;
    }

    public String getNationalite() {
        return nationalite;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

}
