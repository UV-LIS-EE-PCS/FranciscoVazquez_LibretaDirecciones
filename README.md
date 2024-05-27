# Proyecto Libreta de Direcciones en Java

Este proyecto implementa una libreta de direcciones simple en Java. Permite a los usuarios agregar, eliminar, buscar y mostrar entradas de direcciones, además de cargar datos desde un archivo.
(Para una información más detallada revise la wiki del repositorio).

## _Estructura del Proyecto

El proyecto está organizado en los siguientes paquetes y clases:

### Paquete `address`

- **`Menu`**: Maneja la interacción con el usuario a través de un menú de consola.
- **`AddressBookAplication`**: Clase principal que inicia la aplicación.


### Paquete `address.data`

- **`AddressEntry`**: Representa una entrada en la libreta de direcciones.
- **`AddressBook`**: Administra una colección de entradas de direcciones.

### Paquete `test`

- **`AddressBookTest`**: Prueba las operaciones del libro de direcciones, como agregar y eliminar entradas.
- **`AddressEntryTest`**: Verifica que los metodos de la clase AddressEntry funcionen correctamente.
- **`MenuTest`**: Asegura que el menú de opciones de despliegue correctamente._

## Características

1. **Cargar entradas desde un archivo**: Permite cargar entradas desde un archivo de texto con formato específico (lineal).
2. **Agregar una nueva entrada**: Permite agregar una nueva entrada a la libreta de direcciones.
3. **Eliminar una entrada**: Permite eliminar una entrada existente.
4. **Buscar una entrada por apellido**: Permite buscar entradas por apellido.
5. **Mostrar todas las entradas ordenadas por apellido**: Muestra todas las entradas ordenadas alfabéticamente por apellido.


## Javadoc

### `Menu:`
```java
package address;

import address.data.AddressBook;
import address.data.AddressEntry;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
* Esta clase representa un menú de consola para interactuar con una libreta de direcciones.
* Permite a los usuarios cargar entradas desde un archivo, agregar nuevas entradas,
* eliminar entradas, buscar entradas por apellido, mostrar todas las entradas ordenadas
* por apellido y salir del programa.
  */
  public class Menu {
  private final AddressBook addressBook;
  private final Scanner scanner;
  private char option;

  /**
    * Constructor que inicializa la libreta de direcciones y el escáner.
    *
    * @param addressBook la libreta de direcciones que se manejará
      */
      public Menu(AddressBook addressBook) {
        this.addressBook = addressBook;
        this.scanner = new Scanner(System.in);
        this.option = ' ';
      }

  /**
    * Muestra el menú y maneja la interacción con el usuario.
      */
      public void displayMenu() {
        do {
            System.out.println(printMenuOptions());
            getUserOption();
            executeUserOption(option);
        } while (option != 'f');
      }

  /**
    * Devuelve las opciones del menú en forma de cadena.
    *
    * @return una cadena que representa las opciones del menú
      */
      public String printMenuOptions() {
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
    * Obtiene la opción del usuario desde la entrada estándar.
      */
      private void getUserOption() {
        while (true) {
          String input = scanner.nextLine().trim();
           if (!input.isEmpty()) {
              option = input.charAt(0);
              break; // Salir del bucle si se ingresó una opción válida
           } else{
              System.out.println("Por favor, ingrese una opción válida.");
           }
        }
      }

  /**
    * Ejecuta la acción correspondiente a la opción del usuario.
    *
    * @param option la opción del usuario
      */
      public void executeUserOption(char option) {
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
    * Solicita al usuario que ingrese los detalles de una nueva entrada y la agrega a la libreta de direcciones.
      */
      public void addUserEntry() {
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
        } catch (Exception e) {
            if (e.getMessage() != null) {
                System.out.println("Ha ocurrido un error de entrada: " + e.getMessage());
                displayMenu();
            } else {
                System.out.println("Ha ocurrido un error de entrada: El código postal debe de ser de tipo numérico.");
            }
        }
     }

  /**
    * Solicita al usuario que ingrese el apellido de la entrada que desea eliminar y la elimina de la libreta de direcciones.
      */
      public void deleteUserEntry() {
        System.out.println("Ingrese el apellido del registro que desee borrar.");
        String lastNameDelete = scanner.nextLine().trim();
        List<AddressEntry> result = addressBook.searchEntry(lastNameDelete);
        addressBook.printSearchResults(lastNameDelete);

        System.out.println("\nIngresa el numero del registro a eliminar: ");
        int recordToDelete = scanner.nextInt();

        if (recordToDelete >= 1 && recordToDelete <= result.size()) {
            AddressEntry entryToDelete = result.get(recordToDelete - 1);
            addressBook.removeEntry(entryToDelete);
            System.out.println("El registro ha sido eliminado de manera correcta");
        } else {
            System.out.println("Numero de registro no valido :(");
        }
      }

  /**
    * Solicita al usuario que ingrese el apellido de la entrada que desea buscar y muestra los resultados de la búsqueda.
      */
      public void searchUserEntry() {
        System.out.println("Ingrese el apellido del contacto que desee buscar:");
        String lastNameEntry = scanner.nextLine().trim();
        addressBook.searchEntry(lastNameEntry);
        addressBook.printSearchResults(lastNameEntry);
      }

  /**
    * Muestra todas las entradas de la libreta de direcciones ordenadas por apellido.
      */
      public void sortUserEntry() {
        addressBook.printEntriesSortedByLastName();
      }

  /**
    * Carga entradas desde un archivo de texto y las agrega a la libreta de direcciones.
    * El archivo debe tener las entradas separadas por comas.
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
        } catch (Exception e) {
            System.out.println("Error al leer Email: " + e.getMessage());
        }
      }

  /**
    * Valida que un correo electrónico contenga un '@'.
    *
    * @param email el correo electrónico a validar
    * @throws Exception si el correo electrónico no contiene un '@'
      */
      private void validateEmail(String email) throws Exception {
        if (!email.contains("@")) {
            throw new Exception("El correo electrónico debe contener '@'.");
        }
      }

  /**
    * Valida que un número de teléfono tenga 10 dígitos.
    *
    * @param phone el número de teléfono a validar
    * @throws Exception si el número de teléfono no tiene 10 dígitos
      */
      private void validatePhone(String phone) throws Exception {
        if (phone.length() != 10) {
            throw new Exception("El número de teléfono debe ser de 10 dígitos.");
        }
      }
    }
```

### `AddressBook:`
```java
/**
 * Esta clase representa una libreta de direcciones que contiene varias entradas de direcciones.
 * Permite agregar, eliminar, buscar y mostrar entradas, así como también mostrar todas las entradas ordenadas por apellido.
 */
package address.data;
    
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddressBook {
    private List<AddressEntry> addressEntries;

    /**
     * Constructor de la clase AddressBook.
     * Inicializa la lista de entradas de direcciones.
     */
    public AddressBook() {
        this.addressEntries = new ArrayList<>();
    }

    /**
     * Agrega una nueva entrada de dirección a la libreta de direcciones.
     *
     * @param entry la entrada de dirección que se va a agregar
     */
    public void addEntry(AddressEntry entry) {
        addressEntries.add(entry);
    }

    /**
     * Elimina una entrada de dirección de la libreta de direcciones.
     *
     * @param entry la entrada de dirección que se va a eliminar
     */
    public void removeEntry(AddressEntry entry) {
        addressEntries.remove(entry);
    }

    /**
     * Busca entradas de dirección que coincidan con el apellido insertado.
     *
     * @param lastName el apellido para buscar entradas de dirección
     * @return una lista de entradas de dirección que coinciden con el apellido proporcionado
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
     * Imprime los resultados de la búsqueda en un formato legible.
     *
     * @param lastName el apellido para imprimir los resultados de la búsqueda
     */
    public void printSearchResults(String lastName) {
        List<AddressEntry> results = searchEntry(lastName);
        int count = 1;
        if (results.isEmpty()) {
            System.out.println("No se encontró ningún registro con el apellido " + lastName + ".");
        } else {
            for (AddressEntry entry : results) {
                System.out.println("\n" + count + ". Nombre: " + entry.getName());
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
     * Muestra todas las entradas ordenadas por apellido.
     */
    public void printEntriesSortedByLastName() {
        Collections.sort(addressEntries, (a1, a2) -> a1.getLastName().compareTo(a2.getLastName()));
        int count = 1;
        for (AddressEntry entry : addressEntries) {
            System.out.println("\n" + count + "." + entry);
            count++;
        }
    }
}
```

### `AddressEntry`
```java
/**
 * Esta clase representa una entrada de dirección en una libreta de direcciones.
 * Contiene información sobre el nombre, apellido, dirección, ciudad, estado, código postal, correo electrónico y teléfono de una persona.
 */
public class AddressEntry {
    private String name;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private int postalCode;
    private String email;
    private String phone;

    /**
     * Constructor vacío de la clase AddressEntry.
     */
    public AddressEntry(){
    }

    /**
     * Constructor de la clase AddressEntry que inicializa todos los campos de la entrada de dirección.
     *
     * @param name el nombre de la persona
     * @param lastName el apellido de la persona
     * @param street la calle de la dirección
     * @param city la ciudad de la dirección
     * @param state el estado de la dirección
     * @param postalCode el código postal de la dirección
     * @param email el correo electrónico de la persona
     * @param phone el número de teléfono de la persona
     */
    public AddressEntry(String name,String lastName, String street, String city, String state, int postalCode, String email, String phone){
        this.name = name;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Obtiene el nombre de la persona.
     *
     * @return el nombre de la persona
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre de la persona.
     *
     * @param name el nombre de la persona
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el apellido de la persona.
     *
     * @return el apellido de la persona
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Establece el apellido de la persona.
     *
     * @param lastName el apellido de la persona
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Obtiene la calle de la dirección.
     *
     * @return la calle de la dirección
     */
    public String getStreet() {
        return street;
    }

    /**
     * Establece la calle de la dirección.
     *
     * @param street la calle de la dirección
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Obtiene la ciudad de la dirección.
     *
     * @return la ciudad de la dirección
     */
    public String getCity() {
        return city;
    }

    /**
     * Establece la ciudad de la dirección.
     *
     * @param city la ciudad de la dirección
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Obtiene el estado de la dirección.
     *
     * @return el estado de la dirección
     */
    public String getState() {
        return state;
    }

    /**
     * Establece el estado de la dirección.
     *
     * @param state el estado de la dirección
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Obtiene el código postal de la dirección.
     *
     * @return el código postal de la dirección
     */
    public int getPostalCode() {
        return postalCode;
    }

    /**
     * Establece el código postal de la dirección.
     *
     * @param postalCode el código postal de la dirección
     */
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Obtiene el correo electrónico de la persona.
     *
     * @return el correo electrónico de la persona
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico de la persona.
     *
     * @param email el correo electrónico de la persona
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el número de teléfono de la persona.
     *
     * @return el número de teléfono de la persona
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Establece el número de teléfono de la persona.
     *
     * @param phone el número de teléfono de la persona
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Devuelve una representación de cadena de la entrada de dirección.
     *
     * @return una cadena que representa la entrada de dirección
     */
    @Override
    public String toString() {
        return getName() + " " + getLastName() + "\n" + getStreet() + "\n" + getCity()
                + ", " + getState() + ", C.P. " + getPostalCode() + "\n" + getEmail() + "\n" + getPhone();
    }
}
```
### `AddressBookAplication`
```java
/**
 * Esta clase representa la aplicación principal para la gestión de una libreta de direcciones.
 * Crea una instancia de la clase AddressBook y del menú, y luego muestra el menú para que el usuario interactúe con la libreta de direcciones.
 */
public class AddressBookAplication {
    /**
     * El método principal que inicia la aplicación.
     * Crea una instancia de AddressBook y Menu, y muestra el menú para que el usuario interactúe con la libreta de direcciones.
     */
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook(); // Crea una instancia de la libreta de direcciones
        Menu menu = new Menu(addressBook); // Crea una instancia del menú y pasa la libreta de direcciones como argumento
        menu.displayMenu(); // Muestra el menú para que el usuario interactúe con la libreta de direcciones
    }
}

```