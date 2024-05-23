package address;

import address.data.AddressBook;

public class AddressBookAplication {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Menu menu = new Menu(addressBook);
        menu.displayMenu();
    }
}