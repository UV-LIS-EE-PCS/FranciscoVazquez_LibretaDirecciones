package address.data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * La clase AddressBook representa una libreta de direcciones que contiene una lista de entradas de direcciones.
 */
public class AddressBook {
    private List<AddressEntry> addressEntries;


    /**
     * Constructor por defecto que inicializa una libreta de direcciones vacía.
     */
    public AddressBook() {
        this.addressEntries = new ArrayList<>();
    }

    /**
     * Agrega una nueva entrada a la libreta de direcciones.
     *
     * @param entry La entrada de dirección a agregar.
     */
    public void addEntry(AddressEntry entry) {
        addressEntries.add(entry);
    }


    /**
     * Elimina una entrada de la libreta de direcciones.
     *
     * @param entry La entrada de dirección a eliminar.
     */
    public void removeEntry(AddressEntry entry) {
        addressEntries.remove(entry);
    }


    /**
     * Busca entradas en la libreta de direcciones por apellido.
     *
     * @param lastName El apellido a buscar.
     * @return Una lista de entradas que coinciden con el apellido buscado.
     */
    public List<AddressEntry> searchEntry(String lastName) {
        List<AddressEntry> result = new ArrayList<>();
        for (AddressEntry entry : addressEntries) {
            if (entry.getLastName().startsWith(lastName)) {
                result.add(entry);
            }
        }
        return result;
    }

    /**
     * Imprime los resultados de búsqueda en la consola.
     *
     * @param lastName El apellido buscado.
     */
    public void printSearchResults(String lastName){
        List<AddressEntry> results = searchEntry(lastName);
        int count = 1;
        if(results.isEmpty()){
            System.out.println("No se encontró ningun registro con el apellido " + lastName + ".");
        }else{
            for(AddressEntry entry : results){
                System.out.println("\n" + count +". Nombre: " + entry.getName());
                System.out.println("Apellido: " + entry.getLastName());
                System.out.println("Calle: " + entry.getStreet());
                System.out.println("Ciudad: " + entry.getCity());
                System.out.println("Estado: " + entry.getState());
                System.out.println("Código Postal: " + entry.getPostalCode());
                System.out.println("Correo Electrónico: " + entry.getEmail());
                System.out.println("Teléfono: " + entry.getPhone());

                count++;
            }
        }
    }

    /**
     * Imprime todas las entradas de la libreta de direcciones ordenadas por apellido.
     */
    public void printEntriesSortedByLastName() {
        if(addressEntries.isEmpty()){
            System.out.println("No hay ningun registro para mostrar");
        }else {
            Collections.sort(addressEntries, (a1, a2) -> a1.getLastName().compareTo(a2.getLastName()));
            int count = 1;
            for (AddressEntry entry : addressEntries) {
                System.out.println("\n" + count + "." + entry);
                count++;
            }
        }
    }

}

