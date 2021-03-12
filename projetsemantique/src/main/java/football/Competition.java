package football;

import java.util.Date;

public class Competition {

    private String nomComp;
    private Date dateDebut;
    private Date dateFin;

    public Competition() {
    }

    public Competition(String nomComp, Date dateDebut, Date dateFin) {
        this.setDateDebut(dateDebut);
        this.setDateFin(dateFin);
        this.setNomComp(nomComp);
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setNomComp(String nomComp) {
        this.nomComp = nomComp;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public String getNomComp() {
        return nomComp;
    }

}
