package edu.upc.backend.models;

import java.security.PublicKey;

public class Llibre {
    String id;
    String ISBN;
    String titul;
    String editorial;
    String autor;

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    int quantitat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitul() {
        return titul;
    }

    public void setTitul(String titul) {
        this.titul = titul;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getPublicacio() {
        return publicacio;
    }

    public void setPublicacio(String publicacio) {
        this.publicacio = publicacio;
    }

    public String getEdicio() {
        return edicio;
    }

    public void setEdicio(String edicio) {
        this.edicio = edicio;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    String publicacio;
    String edicio;
    String tematica;

    public void incrementaQuantitat() {quantitat++;}
    public void decrementaQuantitat()
    {
        if(quantitat == 0) throw new ArrayIndexOutOfBoundsException("N'hi ha 0 exemplars d'aquest llibre.");
        quantitat--;
    }

    public Llibre() {}
    public Llibre(String id, String isbn, String titul, String editorial,
                  String publicacio, String edicio, String autor, String tematica)
    {
        setId(id);
        setISBN(isbn);
        setTitul(titul);
        setEditorial(editorial);
        setPublicacio(publicacio);
        setEdicio(edicio);
        setAutor(autor);
        setTematica(tematica);
        quantitat = 1;
    }

}
