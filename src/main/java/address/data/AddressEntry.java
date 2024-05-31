package address.data;

/**
 * La clase AddressEntry representa una entrada en la libreta de direcciones.
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
     * Constructor por defecto (vacío).
     */
    public AddressEntry() {
    }

    /**
     * Constructor que inicializa todos los campos de una entrada.
     *
     * @param name       El nombre de la persona.
     * @param lastName   El apellido de la persona.
     * @param street     La calle de la dirección.
     * @param city       La ciudad de la dirección.
     * @param state      El estado de la dirección.
     * @param postalCode El código postal de la dirección.
     * @param email      El correo electrónico de la persona.
     * @param phone      El número de teléfono de la persona.
     */
    public AddressEntry(String name, String lastName, String street, String city, String state, int postalCode, String email, String phone) {
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
     * @return El nombre de la persona.
     */
    public String getName() {
        return name;
    }


    /**
     * Establece el nombre de la persona.
     *
     * @param name El nombre de la persona.
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Obtiene el apellido de la persona.
     *
     * @return El apellido de la persona.
     */
    public String getLastName() {
        return lastName;
    }


    /**
     * Establece el apellido de la persona.
     *
     * @param lastName El apellido de la persona.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    /**
     * Obtiene la calle de la dirección.
     *
     * @return La calle de la dirección.
     */
    public String getStreet() {
        return street;
    }


    /**
     * Establece la calle de la dirección.
     *
     * @param street La calle de la dirección.
     */
    public void setStreet(String street) {
        this.street = street;
    }


    /**
     * Obtiene la ciudad de la dirección.
     *
     * @return La ciudad de la dirección.
     */
    public String getCity() {
        return city;
    }


    /**
     * Establece la ciudad de la dirección.
     *
     * @param city La ciudad de la dirección.
     */
    public void setCity(String city) {
        this.city = city;
    }


    /**
     * Obtiene el estado de la dirección.
     *
     * @return El estado de la dirección.
     */
    public String getState() {
        return state;
    }


    /**
     * Establece el estado de la dirección.
     *
     * @param state El estado de la dirección.
     */
    public void setState(String state) {
        this.state = state;
    }


    /**
     * Obtiene el código postal de la dirección.
     *
     * @return El código postal de la dirección.
     */
    public int getPostalCode() {
        return postalCode;
    }


    /**
     * Establece el código postal de la dirección.
     *
     * @param postalCode El código postal de la dirección.
     */
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }


    /**
     * Obtiene el correo electrónico de la persona.
     *
     * @return El correo electrónico de la persona.
     */
    public String getEmail() {
        return email;
    }


    /**
     * Establece el correo electrónico de la persona.
     *
     * @param email El correo electrónico de la persona.
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * Obtiene el número de teléfono de la persona.
     *
     * @return El número de teléfono de la persona.
     */
    public String getPhone() {
        return phone;
    }


    /**
     * Establece el número de teléfono de la persona.
     *
     * @param phone El número de teléfono de la persona.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Devuelve una representación en cadena de la entrada de dirección.
     *
     * @return Una cadena que contiene todos los detalles de la entrada de dirección.
     */
    @Override
    public String toString() {
        return getName() + " " + getLastName() + "\n" + getStreet() + "\n" + getCity()
                + ", " + getState() + ", C.P. " + getPostalCode() + "\n" + getEmail() + "\n" + getPhone();
    }
}
