package address;

import address.data.AddressBook;


/**
 * La clase AddressBookApplication es el punto de entrada de la aplicación de la libreta de direcciones.
 */
public class AddressBookAplication {

    /**
     * El método principal que inicia la aplicación.
     * @param args Los argumentos de línea de comandos (no se utilizan en esta aplicación).
     */
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Menu menu = new Menu(addressBook);
        menu.displayMenu();
    }
}