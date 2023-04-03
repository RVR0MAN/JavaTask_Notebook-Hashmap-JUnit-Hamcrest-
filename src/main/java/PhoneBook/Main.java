package PhoneBook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {
    public static Map<String, List<Contact>> contactByGroup = new HashMap();
    public static PhoneBook phoneBook = new PhoneBook(contactByGroup);

    public static void main(String[] args) {
        groupsAdministration("GYM");
        groupsAdministration("Work");

        groupsAdministration("Work"); //checked on same groupName


        contactsAdministration("Vasya", "8(495)123-54-34", "GYM");
        contactsAdministration("Vasya", "8(495)123-54-34", "Work");
        contactsAdministration("Petya", "8(495)234-24-17", "GYM");
        contactsAdministration("Petya", "8(495)234-24-17", "Work");
        contactsAdministration("Tanya", "8(495)532-93-12", "Work");

        contactsAdministration("Petya", "8(495)234-24-17", "Work");  //checked on same phone number


        searchByGroup("Work");

        searchByGroup("GYM");

        searchByNumber("8(495)532-93-12");

    }

    public static void groupsAdministration(String groupName) {
        phoneBook.existedGroupCheck(groupName, contactByGroup);
    }

    public static void contactsAdministration(String name, String phone, String groupName) {
        List<Contact> checkedGroup = contactByGroup.get(groupName);
        boolean check = phoneBook.existedContactsCheck(groupName, phone, checkedGroup);
        if (check) {
            return;
        } else {
            contactByGroup.get(groupName).add(new Contact(name, phone));
            System.out.println("Контакт " + name + " добавлен в группу " + groupName);
        }
    }


    public static void searchByGroup(String existGroup) {
        System.out.println("Контакты в группе " + existGroup + ": ");
        for (Contact contactByGroup : phoneBook.findByGroup(existGroup)) {
            System.out.println(contactByGroup.toString());

        }
        System.out.println();
    }


    public static void searchByNumber(String existNumber) {
        System.out.println("Результат поиска по номеру телефона: ");
        System.out.println(phoneBook.findByNumber(existNumber));
    }


}
