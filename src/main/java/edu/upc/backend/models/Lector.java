package edu.upc.backend.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Lector {
    String id;
    String nom;
    String cognoms;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNaixement() {
        return naixement;
    }

    /* Format: 2007-12-03 */
    public void setNaixement(String naixement) {
        this.naixement = naixement;
    }

    public String getDireccio() {
        return direccio;
    }

    public void setDireccio(String direccio) {
        this.direccio = direccio;
    }

    String dni;
    String naixement;
    String direccio;


    public Lector() {}
    public Lector(String id, String nom, String cognoms, String dni, String naixement, String direccio)
    {
        setId(id);
        setNom(nom);
        setCognoms(cognoms);
        setDni(dni);
        setNaixement(naixement);
        setDireccio(direccio);
    }
}
