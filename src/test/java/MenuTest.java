import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import address.Menu;
import static org.junit.jupiter.api.Assertions.*;
import address.data.AddressBook;


public class MenuTest {

    private AddressBook addressBook;
    private Menu menu;

    @BeforeEach
    public void setUp() {
        addressBook = new AddressBook();
        menu = new Menu(addressBook);
    }


    @Test
    public void testPrintMenuOptions() {

        Menu menu = new Menu(null); // No necesitamos una AddressBook para esta prueba

        String expectedOutput = "\nAddress Book Menu:\n" +
                "a) Cargar entradas desde un archivo.\n" +
                "b) Agregar una nueva entrada.\n" +
                "c) Eliminar una entrada.\n" +
                "d) Buscar una entrada por apellido.\n" +
                "e) Mostrar todas las entradas ordenadas por apellido.\n" +
                "f) Salir.\n" +
                "Seleccione una opción (a-f): ";

        System.out.println( menu.printMenuOptions());
        String testMenu = menu.printMenuOptions();

        assertEquals(expectedOutput, testMenu);
    }

}