# Proyecto Libreta de Direcciones en Java

Este proyecto implementa una libreta de direcciones simple en Java. Permite a los usuarios agregar, eliminar, buscar y mostrar entradas de direcciones, además de cargar datos desde un archivo.
**(Para una información más detallada revise la wiki del repositorio).**

## Estructura del Proyecto

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




