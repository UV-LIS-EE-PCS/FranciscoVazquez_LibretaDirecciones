package address;

import address.data.AddressBook;
import address.data.AddressEntry;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


/**
 * La clase Menu proporciona una interfaz para interactuar con el AddressBook.
 */
public class Menu {
    private final AddressBook addressBook;
    private final Scanner scanner;
    private char option;

    /**
     * Constructor de la clase Menu.
     *
     * @param addressBook El libro de direcciones con el que interactuará el menú.
     */
    public Menu(AddressBook addressBook) {
        this.addressBook = addressBook;
        this.scanner = new Scanner(System.in);
        this.option = ' ';
    }


    /**
     * Muestra el menú y ejecuta la opción seleccionada por el usuario.
     */
    public void displayMenu(){
        do{
            System.out.println(printMenuOptions());
            getUserOption();
            executeUserOption(option);
        }while(option != 'f');
    }


    /**
     * Devuelve las opciones del menú en forma de cadena.
     *
     * @return Las opciones del menú.
     */
    public String printMenuOptions(){
        return "\nAddress Book Menu:\n" +
                "a) Cargar entradas desde un archivo.\n" +
                "b) Agregar una nueva entrada.\n" +
                "c) Eliminar una entrada.\n" +
                "d) Buscar una entrada por apellido.\n" +
                "e) Mostrar todas las entradas ordenadas por apellido.\n" +
                "f) Salir.\n" +
                "Seleccione una opción (a-f): ";
    }


    /**
     * Obtiene la opción del usuario y la almacena en el campo 'option'.
     */
    public void getUserOption(){
        while (true) {
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                option = input.charAt(0);
                break; // Salir del bucle si se ingresó una opción válida
            } else {
                System.out.println("Por favor, ingrese una opción válida.");
            }
        }
    }


    /**
     * Ejecuta la opción seleccionada por el usuario.
     *
     * @param option La opción seleccionada por el usuario.
     */
    public void executeUserOption(char option){
        switch (Character.toLowerCase(option)) {
            case 'a' -> loadEntriesFromFile(); // Metodo para leer archivos de texto por consola
            case 'b' -> addUserEntry(); // Registrar nuevo registro de entrada
            case 'c' -> deleteUserEntry(); // Eliminar entrada
            case 'd' -> searchUserEntry(); // Buscar entrada por apellido
            case 'e' -> sortUserEntry(); // Ordenar las entradas del usuario
            case 'f' -> System.out.println("Adios! :)"); //Salir
            default -> System.out.println("Error de entrada. Por favor ingrese una opcion (a-f)");
        }
    }


    /**
     * Agrega una nueva entrada del usuario al libro de direcciones.
     */
    public void addUserEntry(){
        try {
            System.out.println("Ingrese los detalles del nuevo registro:");
            System.out.print("Nombre: ");
            String name = scanner.nextLine().trim();
            System.out.print("Apellido: ");
            String lastName = scanner.nextLine().trim();
            System.out.print("Calle: ");
            String street = scanner.nextLine().trim();
            System.out.print("Ciudad: ");
            String city = scanner.nextLine().trim();
            System.out.print("Estado: ");
            String state = scanner.nextLine().trim();
            System.out.print("Código Postal: ");
            int postalCode = scanner.nextInt();
            scanner.nextLine(); //Consume el caracter de salto de linea que queda en el buffer
            System.out.print("Correo Electrónico: ");
            String email = scanner.nextLine().trim();
            System.out.print("Teléfono: ");
            String phone = scanner.nextLine().trim();

            validateEmail(email);
            validatePhone(phone);

            AddressEntry newEntry = new AddressEntry(name, lastName, street, city, state, postalCode, email, phone);
            addressBook.addEntry(newEntry);
            System.out.println("Nueva entrada agregada exitosamente :)");
        }catch (Exception e){
            if(e.getMessage() != null){
                System.out.println("Ha ocurrido un error de entrada: " + e.getMessage());
                displayMenu();
            }else{
                System.out.println("Ha ocurrido un error de entrada: El código postal debe de ser de tipo numérico." );
            }
        }
    }


    /**
     * Elimina una entrada del usuario del libro de direcciones.
     */
    public void deleteUserEntry(){
        System.out.println("Ingrese el apellido del registro que desee borrar.");
        String lastNameDelete = scanner.nextLine().trim();
        List<AddressEntry> result = addressBook.searchEntry(lastNameDelete);
        addressBook.printSearchResults(lastNameDelete);

        System.out.println("\nIngresa el numero del registro a eliminar: ");
        int recordToDelete = scanner.nextInt();

        if(recordToDelete >=1 && recordToDelete<=result.size()){
            AddressEntry entryToDelete = result.get(recordToDelete-1);
            addressBook.removeEntry(entryToDelete);
            System.out.println("El registro ha sido eliminado de manera correcta");
        }else{
            System.out.println("Numero de registro no valido :(");
        }
    }


    /**
     * Busca una entrada del usuario en el libro de direcciones por apellido.
     */
    public void searchUserEntry() {
        System.out.println("Ingrese el apellido del contacto que desee buscar:");
        String lastNameEntry = scanner.nextLine().trim();
        addressBook.searchEntry(lastNameEntry);
        addressBook.printSearchResults(lastNameEntry);
    }


    /**
     * Muestra todas las entradas del ususario ordenadas por apellido.
     */
    public void sortUserEntry() {
        addressBook.printEntriesSortedByLastName();
    }


    /**
     * Carga entradas desde un archivo.
     */
    public void loadEntriesFromFile() {
        System.out.println("El archivo debe de tener la información separada por comas: Nombre, Apellido, Calle, etc.");
        System.out.println("Ingrese el nombre del archivo (con extensión .txt): ");
        String fileName = scanner.nextLine().trim();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 8) {
                    System.out.println("Formato de línea incorrecto: " + line);
                    continue;
                }
                String name = parts[0].trim();
                String lastName = parts[1].trim();
                String street = parts[2].trim();
                String city = parts[3].trim();
                String state = parts[4].trim();
                int postalCode = Integer.parseInt(parts[5].trim());
                String email = parts[6].trim();
                String phone = parts[7].trim();

                validateEmail(email);
                validatePhone(phone);

                AddressEntry entry = new AddressEntry(name, lastName, street, city, state, postalCode, email, phone);
                addressBook.addEntry(entry);
            }
            System.out.println("Entradas cargadas correctamente desde el archivo " + fileName);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error al procesar el código postal: " + e.getMessage());
        } catch(Exception e){
            System.out.println("Error al leer Email: " + e.getMessage());
        }
    }

    /**
     * Valida el formato del correo electrónico.
     *
     * @param email El correo electrónico a validar.
     * @throws Exception Si el correo electrónico no contiene '@'.
     */
    private void validateEmail(String email) throws Exception {
        if (!email.contains("@")) {
            throw new Exception("El correo electrónico debe contener '@'.");
        }
    }

    /**
     * Valida el formato del número de teléfono.
     *
     * @param phone El número de teléfono a validar.
     * @throws Exception Si el número de teléfono no tiene 10 dígitos.
     */
    private void validatePhone(String phone) throws Exception {
        if (phone.length() != 10) {
            throw new Exception("El número de teléfono debe ser de 10 dígitos.");
        }
    }
}
