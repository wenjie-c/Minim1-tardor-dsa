import edu.upc.backend.llibreria.BibliotecaManager;
import edu.upc.backend.models.Prestec;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Part1Test {

    private static final Logger log = Logger.getLogger(Part1Test.class);

    BibliotecaManager biblioteca;


    @Before
    public void setUp() throws Exception{
        biblioteca = BibliotecaManager.getInstance();


        log.info(String.format("raw = %d i pila = %d",(int)BibliotecaManager.booksData1.length, (int)biblioteca.pilaSize()));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddLector() throws Exception {
        /*
        c = a + b;
        Assert.assertEquals(11, c);
        log.info(String.format("c equals %d", c));
        */

        biblioteca.addLector("aleatori1","Wenjie", "Chen Pan","dgsuauygoudas","2002-06-22","Viladecans");
        biblioteca.addLector("aleatori2","Toni", "Oller Arcas", "2e812hehu21","1980-01-01","Barcelona");

        Assert.assertEquals(2, (int)biblioteca.lectorSize());

    }

    @Test
    public void testPila() throws Exception {
        Assert.assertEquals(BibliotecaManager.booksData1.length, (int)biblioteca.pilaSize());
    }

    @Test
    public void testCatalogar() throws Exception {
        while(biblioteca.pilaSize() != 0)
        {
            biblioteca.catalogarLlibre();
        }

        log.info(String.format("N'hi han %d llibres en el cataleg.", biblioteca.catalegSize()));

        log.info(biblioteca.pilaSize());

        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                biblioteca.catalogarLlibre() );

    }

    @Test
    public void testPrestar() throws Exception {

        biblioteca.addLector("aleatori1","Wenjie", "Chen Pan","dgsuauygoudas","2002-06-22","Viladecans");
        biblioteca.addLector("aleatori2","Toni", "Oller Arcas", "2e812hehu21","1980-01-01","Barcelona");
        while(biblioteca.pilaSize() != 0)
        {
            biblioteca.catalogarLlibre();
        }

        biblioteca.prestar("Wenjie", "Twenty Thousand Leagues Under the Sea","2025-12-1");
        biblioteca.prestar("Wenjie", "The Steam House","2025-12-1");
        biblioteca.prestar("Wenjie", "Journey to the Center of the Earth","2025-12-1");
        biblioteca.prestar("Toni", "The Mysterious Island","2030-3-1");

        Prestec[] prestecs1 = biblioteca.getPrestects("aleatori1");
        Assert.assertEquals(3,prestecs1.length);
        for(int i = 0; i < prestecs1.length; i++)
        {
            log.info(String.format("El lector amb id %s te el un llibre prestat amb titul %s des del dia %s fins a %s",
                    prestecs1[i].getIdLector(), prestecs1[i].getIdLlibre(),prestecs1[i].getDataInici(),prestecs1[i].getDataFinal()));
        }

    }

    @Test
    public void testConsultaCataleg() throws Exception {
        biblioteca.addLector("aleatori1","Wenjie", "Chen Pan","dgsuauygoudas","2002-06-22","Viladecans");
        biblioteca.addLector("aleatori2","Toni", "Oller Arcas", "2e812hehu21","1980-01-01","Barcelona");
        while(biblioteca.pilaSize() != 0)
        {
            biblioteca.catalogarLlibre();
        }

        /*
        biblioteca.prestar("Wenjie", "Twenty Thousand Leagues Under the Sea","2025-12-1");
        biblioteca.prestar("Wenjie", "The Steam House","2025-12-1");
        biblioteca.prestar("Wenjie", "Journey to the Center of the Earth","2025-12-1");
        biblioteca.prestar("Toni", "The Mysterious Island","2030-3-1");
         */

        log.info(biblioteca.consultarCataleg());

    }
}
