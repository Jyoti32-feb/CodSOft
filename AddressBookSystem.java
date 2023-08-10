import java.io.*;
import java.util.*;

class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nPhone Number: " + phoneNumber + "\nEmail Address: " + emailAddress + "\n";
    }
}

class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public boolean removeContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                contacts.remove(contact);
                return true;
            }
        }
        return false;
    }

    public List<Contact> searchContact(String keyword) {
        List<Contact> searchResults = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(contact);
            }
        }
        return searchResults;
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }

    public void saveToFile(String fileName) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(contacts);
        }
    }

    public void loadFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            contacts = (List<Contact>) inputStream.readObject();
        }
    }
}

public class AddressBookSystem {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Address Book System");
            System.out.println("1. Add Contact");
            System.out.println("2. Remove Contact");
            System.out.println("3. Search Contacts");
            System.out.println("4. Display All Contacts");
            System.out.println("5. Save Contacts to File");
            System.out.println("6. Load Contacts from File");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter Email Address: ");
                    String emailAddress = scanner.nextLine();
                    Contact newContact = new Contact(name, phoneNumber, emailAddress);
                    addressBook.addContact(newContact);
                    System.out.println("Contact added.");
                    break;

                case 2:
                    System.out.print("Enter Name to Remove: ");
                    String nameToRemove = scanner.nextLine();
                    if (addressBook.removeContact(nameToRemove)) {
                        System.out.println("Contact removed.");
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Search Keyword: ");
                    String searchKeyword = scanner.nextLine();
                    List<Contact> searchResults = addressBook.searchContact(searchKeyword);
                    if (!searchResults.isEmpty()) {
                        System.out.println("Search Results:");
                        for (Contact contact : searchResults) {
                            System.out.println(contact);
                        }
                    } else {
                        System.out.println("No matching contacts found.");
                    }
                    break;

                case 4:
                    List<Contact> allContacts = addressBook.getAllContacts();
                    if (!allContacts.isEmpty()) {
                        System.out.println("All Contacts:");
                        for (Contact contact : allContacts) {
                            System.out.println(contact);
                        }
                    } else {
                        System.out.println("No contacts in the address book.");
                    }
                    break;

                case 5:
                    try {
                        System.out.print("Enter File Name to Save Contacts: ");
                        String fileName = scanner.nextLine();
                        addressBook.saveToFile(fileName);
                        System.out.println("Contacts saved to file.");
                    } catch (IOException e) {
                        System.out.println("Error saving contacts to file: " + e.getMessage());
                    }
                    break;

                case 6:
                    try {
                        System.out.print("Enter File Name to Load Contacts: ");
                        String fileName = scanner.nextLine();
                        addressBook.loadFromFile(fileName);
                        System.out.println("Contacts loaded from file.");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Error loading contacts from file: " + e.getMessage());
                    }
                    break;

                case 7:
                    System.out.println("Exiting Address Book System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
