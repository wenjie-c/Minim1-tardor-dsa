package edu.upc.backend.llibreria;

import edu.upc.backend.models.Lector;
import edu.upc.backend.models.Llibre;
import edu.upc.backend.models.Prestec;
import edu.upc.backend.util.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NameNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;

public class BibliotecaManager implements Llibreria
{

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(BibliotecaManager.class);
    private static BibliotecaManager _instance;

    PilaDeLlibres llibres;
    LectorDB lectors;
    HashMap<String, Llibre> cataleg;
    HashMap<String, Prestec> prestecs;


    public static BibliotecaManager getInstance()
    {
        if(_instance == null)
        {
            _instance = new BibliotecaManager();
        }
        return _instance;
    }

    private BibliotecaManager()
    {
        log.info("S'inicialitza BibliotecaManager.");
        llibres = new PilaDeLlibres();
        lectors = new LectorDB();
        cataleg = new HashMap<String, Llibre>();
        prestecs = new HashMap<String, Prestec>();
    }


    /* Format data: 2007-12-03 */
    @Override
    public void addLector(String id, String nom, String cognoms, String dni, String naixement, String direccio) {
        lectors.addlector(id,nom,cognoms,dni,naixement,direccio);
        log.info("Lector afegit a la taula.");
    }

    @Override
    public void addLlibre(String id, String isbn, String titul, String editorial, String publicacio, String edicio, String autor, String tematica) {
        llibres.push(id,isbn,titul,editorial,publicacio,edicio,autor,tematica);
        log.info("Llibre afegit a la pila.");
    }

    @Override
    public void catalogarLlibre() {
        try
        {
            Llibre top = llibres.pop();
            Llibre res = cataleg.get(top.getTitul());

            if(res == null) // ja existeix el llibre?
            {
                cataleg.put(top.getTitul(),top);
                log.info(String.format("No existeix el llibre amb id %s , s'afegeix a la taula.",top.getId()));
            }
            else
            {
                res.incrementaQuantitat();
                log.info(String.format("El llibre amb id %d ha incrementat en un exemplar.",top.getId()));
            }
        }
        catch (Exception e) {log.error(e.getMessage()); }
    }

    /* Format data: 2007-12-03 */
    @Override
    public void prestar(String id, String idLector, String titulLlibre, String dataFinal) throws Exception {
        Prestec p = prestecs.get(id);
        Llibre l = cataleg.get(titulLlibre);
        if(l == null)
        {
            log.error("Aquest titul de llibre no existeix!");
            throw new Exception("Aquest titul de llibre no existeix!");
        }
        if(p != null)
        {
            log.error("Aquest Id de prestec ja existeix!");
            throw new Exception("Aquest Id de prestec ja existeix!");
        }
        prestecs.put(id, new Prestec(id, idLector, titulLlibre,
                LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE), dataFinal));

        l.decrementaQuantitat();
        log.info("S'ha tramitat amb exit el prestec.");
    }


    public Prestec[] getPrestects() {
        return prestecs.values().toArray(new Prestec[prestecs.size()]);
    }
    @Override
    public Prestec[] getPrestects(String idLector)
    {
        LinkedList<Prestec> buffer = new LinkedList<Prestec>();
        for(Prestec p : prestecs.values())
        {
            if(p.getIdLector().equals(idLector)) buffer.add(p);
        }
        return buffer.toArray(new Prestec[buffer.size()]);
    }
    // --- Algunes funcions de facade per simplificar ---
    public void prestar(String nomLector, String titulObra, String dataFinal) throws Exception {
        String id = RandomUtils.getId();
        Lector l = lectors.getByNom(nomLector);
        Llibre l2 = cataleg.get(titulObra);
        prestar(id,l.getId(),titulObra,dataFinal);
    }

    /*
        public Llibre getLlibreByTitul(String titul) throws NameNotFoundException {
        for(Llibre l : cataleg.values())
        {
            if(l.getTitul().equals(titul)) return l;
        }

        throw new NameNotFoundException("llibre amb titul " + titul + " no s'ha trobat.");
    }
    */
    public Integer lectorSize() {return lectors.size();}
    public Integer pilaSize() {return llibres.size();}
    public Integer catalegSize() {return cataleg.size();}
    public String consultarCataleg()
    {
        String res = "";
        for(Llibre l : cataleg.values())
        {
            res += String.format("Llibre amb id %s, titul %s, de la editorial %s, edicio %s, de l'autor %s, publicat en %s té disponible %d exemplars.\n",
                    l.getId(),l.getTitul(),l.getEditorial(),l.getEdicio(),l.getAutor(),l.getPublicacio(),l.getQuantitat());
        }
        return res;
    }
}
