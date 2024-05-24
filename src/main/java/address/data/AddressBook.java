package address.data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddressBook {
    private List<AddressEntry> addressEntries;

    // Constructor
    public AddressBook() {
        this.addressEntries = new ArrayList<>();
    }

    // Methods
    public void addEntry(AddressEntry entry) {
        addressEntries.add(entry);
    }


    public void removeEntry(AddressEntry entry) {
        addressEntries.remove(entry);
    }


    //This method finds entries that match the lastname insert
    public List<AddressEntry> searchEntry(String lastName) {
        List<AddressEntry> result = new ArrayList<>();
        for (AddressEntry entry : addressEntries) {
            if (entry.getLastName().startsWith(lastName)) {
                result.add(entry);
            }
        }
        return result;
    }

    // This method print results of the previous method in a more readble format
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


    //Muestra todas las entradas ordenadas por el Apellido
    public void printEntriesSortedByLastName() {
        Collections.sort(addressEntries, (a1, a2) -> a1.getLastName().compareTo(a2.getLastName()));
        int count = 1;
        for (AddressEntry entry : addressEntries) {
            System.out.println("\n"+ count + "." + entry);
            count++;
        }
    }

    public List<AddressEntry> getAddressEntries(){
        return addressEntries;
    }
}

