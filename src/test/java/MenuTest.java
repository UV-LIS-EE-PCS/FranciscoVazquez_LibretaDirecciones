import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import address.Menu;
import static org.junit.jupiter.api.Assertions.*;
import address.data.AddressBook;



/**
 * La clase MenuTest realiza pruebas unitarias para la clase Menu.
 */
public class MenuTest {

    private AddressBook addressBook;
    private Menu menu;


    /**
     * Configura una nueva instancia de AddressBook y Menu antes de cada prueba.
     */
    @BeforeEach
    public void setUp() {
        addressBook = new AddressBook();
        menu = new Menu(addressBook);
    }


    /**
     * Prueba el método printMenuOptions() de la clase Menu.
     * Verifica que las opciones del menú se impriman correctamente.
     */
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