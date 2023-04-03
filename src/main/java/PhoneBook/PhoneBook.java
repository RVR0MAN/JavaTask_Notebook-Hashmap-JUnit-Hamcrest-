package PhoneBook;

import java.util.*;

public class PhoneBook {

    private Map<String, List<Contact>> contactByGroup;


    public PhoneBook(Map<String, List<Contact>> contactByGroup) {
        this.contactByGroup = contactByGroup;

    }

    public static boolean existedContactsCheck(String groupName, String phone, List<Contact> checkedGroup) {

        for (Contact i : checkedGroup) {
            if (i.getPhone().equals(phone)) {
                System.out.println("Контакт с номером " + phone + " уже существует под именем " + i.getName() + " в группе " + groupName + "\n");
                return true;
            }
        }
        return false;
    }

    public static void existedGroupCheck(String groupName, Map<String, List<Contact>> contactByGroup) {
        if (contactByGroup.containsKey(groupName)) {
            System.out.println("Группа " + groupName + " уже существует");
        } else {
            contactByGroup.put(groupName, new ArrayList());
            System.out.println("Группа " + groupName + " создана");
        }

    }

    public List<Contact> findByGroup(String groupName) {
        return Collections.unmodifiableList(contactByGroup.get(groupName));

    }

    public Contact findByNumber(String number) {
        Contact searchedContact = new Contact("Контакт с указанным номером не существует", number);
        for (List<Contact> contacts : contactByGroup.values()) {
            for (Contact contact : contacts) {
                if (contact.getPhone().equals(number)) {
                    searchedContact = contact;
                }

            }

        }

        return searchedContact;

    }


}
