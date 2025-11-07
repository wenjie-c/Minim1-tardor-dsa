package edu.upc.backend.llibreria;

import edu.upc.backend.models.Lector;
import io.swagger.models.auth.In;

import javax.naming.NameNotFoundException;
import java.util.HashMap;

public class LectorDB {
    HashMap<String, Lector> _db;
    public LectorDB()
    {
        _db = new HashMap<String, Lector>();
    }

    public void addlector(String id, String nom, String cognoms, String dni, String naixement, String direccio)
    {
        Lector res = _db.get(id);
        if(res == null)
        {
            // No existe por lo que lo añadimos
            _db.put(id,new Lector(id,nom,cognoms,dni,naixement,direccio));
        }
        else
        {
            // Existe entonces solamente modificamos
            res.setNom(nom);
            res.setCognoms(cognoms);
            res.setDni(dni);
            res.setNaixement(naixement);
            res.setDireccio(direccio);
        }
    }

    public Lector get(String id) throws NameNotFoundException {
        Lector res = _db.get(id);
        if(res == null) throw new NameNotFoundException("Aquest lector amb aquest id no existeix.");
        return res;
    }

    public Lector getByNom(String nom) throws NameNotFoundException {
        for(Lector l : _db.values())
        {
            if(l.getNom().equals(nom)) return l;
        }

        throw new NameNotFoundException("Lector amb nom " + nom + " no s'ha trobat.");
    }

    public Integer size() { return _db.size();}

}
