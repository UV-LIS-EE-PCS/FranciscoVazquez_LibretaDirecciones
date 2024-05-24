import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import address.data.AddressBook;
import address.data.AddressEntry;

public class AddressEntryTest {

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

    @Test
    public void testToString() {
        AddressEntry entry = new AddressEntry("Henry", "Vazquez", "Melchor Ocampo", "Acayucan", "Ver", 62704, "vazquezhenry62@gmail.com", "1234567890");
        String expected = "Henry Vazquez\nMelchor Ocampo\nAcayucan, Ver, C.P. 62704\nvazquezhenry62@gmail.com\n1234567890";
        assertEquals(expected, entry.toString());
    }
}
