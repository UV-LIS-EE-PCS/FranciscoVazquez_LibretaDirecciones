//package address.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import address.data.AddressBook;
import address.data.AddressEntry;

/**
 * La clase AddressBookTest realiza pruebas unitarias para la clase AddressBook.
 */
public class AddressBookTest {

    private AddressBook addressBook;


    /**
     * Configura un AddressBook vacío antes de cada prueba.
     */
    @BeforeEach
    public void setUp() {
        addressBook = new AddressBook();
    }


    /**
     * Prueba el método addEntry() de la clase AddressBook.
     * Verifica que una entrada se agrega correctamente a la libreta de direcciones.
     */
    @Test
    public void testAddEntry() {
        AddressEntry entry = new AddressEntry("Henry", "Vazquez", "Melchor", "Acayucan", "Ver", 62704, "vazquezhenry26@gmail.com", "1234567890");
        addressBook.addEntry(entry);
        assertEquals(1, addressBook.searchEntry("Vazquez").size());
    }


    /**
     * Prueba el método removeEntry() de la clase AddressBook.
     * Verifica que una entrada se elimina correctamente de la libreta de direcciones.
     */

    @Test
    public void testRemoveEntry() {
        AddressEntry entry = new AddressEntry("Henry", "Vazquez", "Melchor", "Acayucan", "Ver", 62704, "vazquezhenry26@gmail.com", "1234567890");
        addressBook.addEntry(entry);
        addressBook.removeEntry(entry);
        assertTrue(addressBook.searchEntry("Vazquez").isEmpty());
    }


    /**
     * Prueba el método searchEntry() de la clase AddressBook.
     * Verifica que se pueden buscar entradas por apellido.
     */
    @Test
    public void testSearchEntry() {
        AddressEntry entry1 = new AddressEntry("Henry", "Vazquez", "Melchor", "Acayucan", "Ver", 96039, "vazquezhenry62@gmail.com", "1234567890");
        AddressEntry entry2 = new AddressEntry("Cecilia", "Vazquez", "5 de mayo", "Sonocusco", "Ver", 62705, "cecyny@gmail.com", "0987654321");
        addressBook.addEntry(entry1);
        addressBook.addEntry(entry2);

        List<AddressEntry> results = addressBook.searchEntry("Vazquez");
        assertEquals(2, results.size());
    }


    /**
     * Prueba el método printEntriesSortedByLastName() de la clase AddressBook.
     * Verifica que las entradas se imprimen ordenadas por apellido.
     */
    @Test
    public void testPrintEntriesSortedByLastName() {
        AddressEntry entry1 = new AddressEntry("Henry", "Vazquez", "Melchor", "Acayucan", "Ver", 96039, "vazquezhenry62@gmail.com", "1234567890");
        AddressEntry entry2 = new AddressEntry("Cecilia", "Vazquez", "5 de mayo", "Sonocusco", "Ver", 62705, "cecyny@gmail.com", "0987654321");
        addressBook.addEntry(entry1);
        addressBook.addEntry(entry2);

        addressBook.printEntriesSortedByLastName();

    }
}
