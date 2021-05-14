package football;

import org.apache.jena.sparql.function.library.print;

public class Personne {

    private String uri;
    private String label;
    private String dateNaissance;
    private String nationalite;

    public Personne() {
    }

    public Personne(String uri, String label, String dateNaissance, String nationalite) {

        this.setDateNaissance(dateNaissance);
        this.setNationalite(nationalite);
        this.setUri(uri);
        this.setLabel(label);

    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

}
