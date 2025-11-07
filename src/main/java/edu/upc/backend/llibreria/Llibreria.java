package edu.upc.backend.llibreria;

import edu.upc.backend.models.Lector;
import edu.upc.backend.models.Llibre;
import edu.upc.backend.models.Prestec;

public interface Llibreria {
    /* Afegir un nou lector */
    public void addLector(String id, String nom, String cognoms, String dni, String naixement, String direccio);
    /* Emmagatzemar un llibre */
    public void addLlibre(String id, String isbn, String titul, String editorial,
                          String publicacio, String edicio, String autor, String tematica);
    /* Catalogar un llibre */
    public void catalogarLlibre();
    /* Prestar un llibre, s'intueix que la data d'inici del prestec es avui.*/
    public void prestar(String id, String idLector, String titulLlibre , String dataFinal) throws Exception;
    /* Consultar tots els préstecs que ha realitzat un lector */
    public Prestec[] getPrestects(String idLector);
}
