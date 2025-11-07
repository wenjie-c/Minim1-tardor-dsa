package edu.upc.backend.models;

import java.security.PublicKey;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Prestec {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdLector() {
        return idLector;
    }

    public void setIdLector(String idLector) {
        this.idLector = idLector;
    }

    public String getIdLlibre() {
        return idLlibre;
    }

    public void setIdLlibre(String idLlibre) {
        this.idLlibre = idLlibre;
    }

    public String getDataInici() {
        //return dataInici.format(DateTimeFormatter.ISO_LOCAL_DATE);
        return dataInici;
    }

    public void setDataInici(String dataInici) {
        //this.dataInici = LocalTime.parse(dataInici);
        this.dataInici = dataInici;
    }

    public String getDataFinal() {
        //return dataFinal.format(DateTimeFormatter.ISO_LOCAL_DATE);
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        //this.dataFinal = LocalTime.parse(dataFinal);
        this.dataFinal = dataFinal;
    }

    public Boolean getTramit() {
        return tramit;
    }

    public void setTramit(Boolean tramit) {
        this.tramit = tramit;
    }

    String id;
    String idLector;
    String idLlibre;
    //LocalTime dataInici;
    //LocalTime dataFinal;
    String dataInici;
    String dataFinal;
    Boolean tramit;

    public void finalitzar()
    {
        tramit = false;
        // I es decrementaria un exemplar, pero no des d'aqui.
    }

    public Prestec() {}
    public Prestec(String id, String idLector,String idLlibre ,String dataInici, String dataFinal)
    {
        setId(id);
        setIdLector(idLector);
        setIdLlibre(idLlibre);
        setDataInici(dataInici);
        setDataFinal(dataFinal);
        tramit = true;
    }

}
