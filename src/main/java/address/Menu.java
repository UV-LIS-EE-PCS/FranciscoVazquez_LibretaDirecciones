package address;

import address.data.AddressBook;
import address.data.AddressEntry;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final AddressBook addressBook;
    private final Scanner scanner;
    private char option;

    // Constructor
    public Menu(AddressBook addressBook) {
        this.addressBook = addressBook;
        this.scanner = new Scanner(System.in);
        this.option = ' ';
    }

    // Metodos
    public void displayMenu(){
        do{
            System.out.println(printMenuOptions());
            getUserOption();
            executeUserOption(option);
        }while(option != 'f');
    }

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

    private void getUserOption(){
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


    public void searchUserEntry() {
        System.out.println("Ingrese el apellido del contacto que desee buscar:");
        String lastNameEntry = scanner.nextLine().trim();
        addressBook.searchEntry(lastNameEntry);
        addressBook.printSearchResults(lastNameEntry);
    }

    public void sortUserEntry() {
        addressBook.printEntriesSortedByLastName();
    }

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

    private void validateEmail(String email) throws Exception {
        if (!email.contains("@")) {
            throw new Exception("El correo electrónico debe contener '@'.");
        }
    }

    private void validatePhone(String phone) throws Exception {
        if (phone.length() != 10) {
            throw new Exception("El número de teléfono debe ser de 10 dígitos.");
        }
    }
}


// Metodo refactorizado sin validaciones
//    private void addUserEntry(){
//        System.out.println("Ingrese los detalles del nuevo registro:");
//        System.out.println("Nombre: ");
//        String name = scanner.nextLine().trim();
//        System.out.print("Apellido: ");
//        String lastName = scanner.nextLine().trim();
//        System.out.print("Calle: ");
//        String street = scanner.nextLine().trim();
//        System.out.print("Ciudad: ");
//        String city = scanner.nextLine().trim();
//        System.out.print("Estado: ");
//        String state = scanner.nextLine().trim();
//        System.out.print("Código Postal: ");
//        int postalCode = scanner.nextInt();
//        scanner.nextLine();
//        System.out.print("Correo Electrónico: ");
//        String email = scanner.nextLine().trim();
//        System.out.print("Teléfono: ");
//        String phone = scanner.nextLine().trim();
//
//        AddressEntry newEntry = new AddressEntry(name, lastName, street, city, state, postalCode, email, phone);
//        addressBook.addEntry(newEntry);
//        System.out.println("Nueva entrada agregada exitosamente :)");
//    }



// Metodo displayMenu antes de ser refactorizado. Observe que estaba muy largo
    /*public void displayMenu() {
        char choice;
        do {
            System.out.println("\nAddress Book Menu:");
            System.out.println("a) Cargar entradas desde un archivo.");
            System.out.println("b) Agregar una nueva entrada.");
            System.out.println("c) Eliminar una entrada.");
            System.out.println("d) Buscar una entrada por apellido.");
            System.out.println("e) Mostrar todas las entradas ordenadas por apellido.");
            System.out.println("f) Salir.");
            System.out.print("Seleccione una opción (a-f): ");
            choice = scanner.next().charAt(0);
            scanner.nextLine(); // consume the newline character

            switch (Character.toLowerCase(choice)) {
                case 'a':
                    // Implementar carga de entradas desde un archivo
                    System.out.println("\nPronta funcionalidad a implementar");
                    break;
                case 'b':
                    System.out.println("Ingrese los detalles del nuevo registro:");
                    System.out.print("Nombre: ");
                    String name = scanner.nextLine();
                    System.out.print("Apellido: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Calle: ");
                    String street = scanner.nextLine();
                    System.out.print("Ciudad: ");
                    String city = scanner.nextLine();
                    System.out.print("Estado: ");
                    String state = scanner.nextLine();
                    System.out.print("Código Postal: ");
                    int postalCode = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Correo Electrónico: ");
                    String email = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    String phone = scanner.nextLine();

                    AddressEntry newEntry = new AddressEntry(name, lastName, street, city, state, postalCode, email, phone);
                    addressBook.addEntry(newEntry);
                    System.out.println("Nueva entrada agregada exitosamente :)");
                    break;
                case 'c':
                    // Implementar eliminar una entrada
                    System.out.println("Ingrese el apellido del registro que desee borrar.");
                    String lastNameDelete = scanner.nextLine();
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

                    break;
                case 'd':
                    // Implementar buscar una entrada por apellido
                    System.out.println("Ingrese el apellido del contacto que desee buscar:");
                    String lastNameEntry = scanner.nextLine();
                    addressBook.searchEntry(lastNameEntry);
                    addressBook.printSearchResults(lastNameEntry);
                    break;
                case 'e':
                    addressBook.displayEntriesSortedByLastName();
                    break;
                case 'f':
                    System.out.println("¡Adiós!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción entre a-f.");
            }
        } while (choice != 'f');
    }*/
