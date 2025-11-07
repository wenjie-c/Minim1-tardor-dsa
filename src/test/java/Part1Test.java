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
    public static String[][] booksData1 = {
            {"JV7d", "The Steam House", "Forgotten Books", "First Edition", "1880", "978-1605062234", "Jules Verne", "Adventures"},
            {"JV4a", "The Mysterious Island", "Barnes & Noble Classics", "First Edition", "1874", "978-1435149408", "Jules Verne", "Adventures"},
            {"JV1", "Journey to the Center of the Earth", "Dover Publications", "First Edition", "1864", "978-0486268685", "Jules Verne", "Adventures"},
            {"JV3", "Around the World in Eighty Days", "CreateSpace", "First Edition", "1872", "978-1516887907", "Jules Verne", "Adventures"},
            {"JV4c", "The Mysterious Island", "Barnes & Noble Classics", "First Edition", "1874", "978-1435149408", "Jules Verne", "Adventures"},
            {"JV8", "The Begum's Fortune", "BiblioBazaar", "First Edition", "1879", "978-1103325575", "Jules Verne", "Adventures"},
            {"JV7c", "The Steam House", "Forgotten Books", "First Edition", "1880", "978-1605062234", "Jules Verne", "Adventures"},
            {"JV5", "The Adventures of Captain Hatteras", "Wordsworth Editions", "First Edition", "1866", "978-1853260257", "Jules Verne", "Adventures"},
            {"JV2b", "Twenty Thousand Leagues Under the Sea", "Signet Classics", "First Edition", "1870", "978-0451530960", "Jules Verne", "Adventures"},
            {"JV2c", "Twenty Thousand Leagues Under the Sea", "Signet Classics", "First Edition", "1870", "978-0451530960", "Jules Verne", "Adventures"},
            // numStack: 0
            {"JV2a", "Twenty Thousand Leagues Under the Sea", "Signet Classics", "First Edition", "1870", "978-0451530960", "Jules Verne", "Adventures"},
            {"JV6", "From the Earth to the Moon", "Oxford University Press", "First Edition", "1865", "978-0199538474", "Jules Verne", "Adventures"},
            {"JV7a", "The Steam House", "Forgotten Books", "First Edition", "1880", "978-1605062234", "Jules Verne", "Adventures"},
            {"JV4b", "The Mysterious Island", "Barnes & Noble Classics", "First Edition", "1874", "978-1435149408", "Jules Verne", "Adventures"},
            {"JV7b", "The Steam House", "Forgotten Books", "First Edition", "1880", "978-1605062234", "Jules Verne", "Adventures"}
    };

    @Before
    public void setUp() throws Exception{
        biblioteca = BibliotecaManager.getInstance();
        for(int i = 0; i < booksData1.length; i++)
        {
            biblioteca.addLlibre(booksData1[i][0],booksData1[i][5],booksData1[i][1],
                    booksData1[i][2],booksData1[i][4],booksData1[i][3],booksData1[i][6],booksData1[i][7]);
        }

        log.info(String.format("raw = %d i pila = %d",(int)booksData1.length, (int)biblioteca.pilaSize()));
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
        Assert.assertEquals(booksData1.length, (int)biblioteca.pilaSize());
    }

    @Test
    public void testCatalogar() throws Exception {
        while(biblioteca.pilaSize() != 0)
        {
            biblioteca.catalogarLlibre();
        }

        log.info(String.format("N'hi han %d llibres en el cataleg.", biblioteca.catalegSize()));

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

        biblioteca.prestar("Wenjie", "Twenty Thousand Leagues Under the Sea","2025-12-1");
        biblioteca.prestar("Wenjie", "The Steam House","2025-12-1");
        biblioteca.prestar("Wenjie", "Journey to the Center of the Earth","2025-12-1");
        biblioteca.prestar("Toni", "The Mysterious Island","2030-3-1");

        log.info(biblioteca.consultarCataleg());

    }
}
