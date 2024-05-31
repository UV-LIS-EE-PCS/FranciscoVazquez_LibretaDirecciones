import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import address.data.AddressBook;
import address.data.AddressEntry;

/**
 * La clase AddressEntryTest realiza pruebas unitarias para la clase AddressEntry.
 */
public class AddressEntryTest {


    /**
     * Prueba el constructor de la clase AddressEntry con parámetros.
     * Verifica que todos los campos se inicializan correctamente.
     */
    @Test
    public void testAddressEntryConstructor() {
        AddressEntry entry = new AddressEntry("Henry", "Vazquez", "Melchor Ocampo", "Acayucan", "Ver", 96039, "vazquezhenry62@gmail.com", "9241026972");
        assertEquals("Henry", entry.getName());
        assertEquals("Vazquez", entry.getLastName());
        assertEquals("Melchor Ocampo", entry.getStreet());
        assertEquals("Acayucan", entry.getCity());
        assertEquals("Ver", entry.getState());
        assertEquals(96039, entry.getPostalCode());
        assertEquals("vazquezhenry62@gmail.com", entry.getEmail());
        assertEquals("9241026972", entry.getPhone());
    }

    /**
     * Prueba el método setName() y getName() de la clase AddressEntry.
     * Verifica que el nombre se establece y se obtiene correctamente.
     */
    @Test
    public void testGetSetName() {
        AddressEntry entry = new AddressEntry();
        entry.setName("Henry");
        assertEquals("Henry", entry.getName());
    }


    /**
     * Prueba el método setLastName() y getLastName() de la clase AddressEntry.
     * Verifica que el apellido se establece y se obtiene correctamente.
     */
    @Test
    public void testGetSetLastName() {
        AddressEntry entry = new AddressEntry();
        entry.setLastName("Vazquez");
        assertEquals("Vazquez", entry.getLastName());
    }


    /**
     * Prueba el método setStreet() y getStreet() de la clase AddressEntry.
     * Verifica que la calle se establece y se obtiene correctamente.
     */
    @Test
    public void testGetSetStreet() {
        AddressEntry entry = new AddressEntry();
        entry.setStreet("Melchor");
        assertEquals("Melchor", entry.getStreet());
    }

    /**
     * Prueba el método setCity() y getCity() de la clase AddressEntry.
     * Verifica que la ciudad se establece y se obtiene correctamente.
     */
    @Test
    public void testGetSetCity() {
        AddressEntry entry = new AddressEntry();
        entry.setCity("Acayucan");
        assertEquals("Acayucan", entry.getCity());
    }


    /**
     * Prueba el método setState() y getState() de la clase AddressEntry.
     * Verifica que el estado se establece y se obtiene correctamente.
     */
    @Test
    public void testGetSetState() {
        AddressEntry entry = new AddressEntry();
        entry.setState("Ver");
        assertEquals("Ver", entry.getState());
    }


    /**
     * Prueba el método setPostalCode() y getPostalCode() de la clase AddressEntry.
     * Verifica que el código postal se establece y se obtiene correctamente.
     */
    @Test
    public void testGetSetPostalCode() {
        AddressEntry entry = new AddressEntry();
        entry.setPostalCode(96039);
        assertEquals(96039, entry.getPostalCode());
    }


    /**
     * Prueba el método setEmail() y getEmail() de la clase AddressEntry.
     * Verifica que el correo electrónico se establece y se obtiene correctamente.
     */
    @Test
    public void testGetSetEmail() {
        AddressEntry entry = new AddressEntry();
        entry.setEmail("vazquezhenry26@gmail.com");
        assertEquals("vazquezhenry26@gmail.com", entry.getEmail());
    }


    /**
     * Prueba el método setPhone() y getPhone() de la clase AddressEntry.
     * Verifica que el número de teléfono se establece y se obtiene correctamente.
     */
    @Test
    public void testGetSetPhone() {
        AddressEntry entry = new AddressEntry();
        entry.setPhone("9241026972");
        assertEquals("9241026972", entry.getPhone());
    }

    /**
     * Prueba el método toString() de la clase AddressEntry.
     * Verifica que la representación en cadena de la entrada de dirección es correcta.
     */
    @Test
    public void testToString() {
        AddressEntry entry = new AddressEntry("Henry", "Vazquez", "Melchor Ocampo", "Acayucan", "Ver", 62704, "vazquezhenry62@gmail.com", "1234567890");
        String expected = "Henry Vazquez\nMelchor Ocampo\nAcayucan, Ver, C.P. 62704\nvazquezhenry62@gmail.com\n1234567890";
        assertEquals(expected, entry.toString());
    }
}
