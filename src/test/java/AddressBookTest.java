//package address.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import address.data.AddressBook;
import address.data.AddressEntry;

public class AddressBookTest {

    private AddressBook addressBook;

    @BeforeEach
    public void setUp() {
        addressBook = new AddressBook();
    }

    @Test
    public void testAddEntry() {
        AddressEntry entry = new AddressEntry("Henry", "Vazquez", "Melchor", "Acayucan", "Ver", 62704, "vazquezhenry26@gmail.com", "1234567890");
        addressBook.addEntry(entry);
        assertEquals(1, addressBook.searchEntry("Vazquez").size());
    }

    @Test
    public void testRemoveEntry() {
        AddressEntry entry = new AddressEntry("Henry", "Vazquez", "Melchor", "Acayucan", "Ver", 62704, "vazquezhenry26@gmail.com", "1234567890");
        addressBook.addEntry(entry);
        addressBook.removeEntry(entry);
        assertTrue(addressBook.searchEntry("Vazquez").isEmpty());
    }

    @Test
    public void testSearchEntry() {
        AddressEntry entry1 = new AddressEntry("Henry", "Vazquez", "Melchor", "Acayucan", "Ver", 96039, "vazquezhenry62@gmail.com", "1234567890");
        AddressEntry entry2 = new AddressEntry("Cecilia", "Vazquez", "5 de mayo", "Sonocusco", "Ver", 62705, "cecyny@gmail.com", "0987654321");
        addressBook.addEntry(entry1);
        addressBook.addEntry(entry2);

        List<AddressEntry> results = addressBook.searchEntry("Vazquez");
        assertEquals(2, results.size());
    }

    @Test
    public void testPrintEntriesSortedByLastName() {
        AddressEntry entry1 = new AddressEntry("Henry", "Vazquez", "Melchor", "Acayucan", "Ver", 96039, "vazquezhenry62@gmail.com", "1234567890");
        AddressEntry entry2 = new AddressEntry("Cecilia", "Vazquez", "5 de mayo", "Sonocusco", "Ver", 62705, "cecyny@gmail.com", "0987654321");
        addressBook.addEntry(entry1);
        addressBook.addEntry(entry2);

        addressBook.printEntriesSortedByLastName();

    }
}
