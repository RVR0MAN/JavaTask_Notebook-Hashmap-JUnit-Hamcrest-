import PhoneBook.Contact;
import PhoneBook.PhoneBook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class PhoneBookTest {

    Map<String, List<Contact>> contactByGroupTest;
    PhoneBook sut;
    List<Contact> contacts;

    String firstGroupName = "First Group";
    String secondGroupName = "Second Group";
    String firstPersonName = "First person";
    String firstPersonNumber = "8(495)123-45-01";
    String secondPersonName = "Second person";
    String secondPersonNumber = "8(495)123-45-02";
    String thirdPersonName = "Third person";
    String thirdPersonNumber = "8(495)123-45-03";


    @BeforeEach
    public void beforeEach() {
        contactByGroupTest = new HashMap<>();
        sut = new PhoneBook(contactByGroupTest);
        contacts = new ArrayList<Contact>();
    }

    @AfterEach
    public void afterEach() {
        contactByGroupTest = null;
        sut = null;
        contacts = null;
    }


    @Test
    public void findByGroup_searchListOfContactsByCreatedGroup_contactsFound() {
        //given
        contacts.add(new Contact(firstPersonName, firstPersonNumber));
        contacts.add(new Contact(secondPersonName, secondPersonNumber));
        contacts.add(new Contact(thirdPersonName, thirdPersonNumber));
        contactByGroupTest.put(firstGroupName, contacts);

        List<Contact> expected = Collections.unmodifiableList(contactByGroupTest.get(firstGroupName));

        //when
        var result = sut.findByGroup(firstGroupName);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void findByGroup_findContactsByNonExistentGroup_throwException() {
        //given
        contacts.add(new Contact(thirdPersonName, thirdPersonNumber));
        contactByGroupTest.put(firstGroupName, contacts);

        String notExistedGroupName = "Spam";

        Class<NullPointerException> expected = NullPointerException.class;

        //when
        Executable executable = () -> sut.findByGroup(notExistedGroupName);

        //then
        Assertions.assertThrows(expected, executable);
    }


    @Test
    public void findByNumber_searchNameOfContactByNumber_nameFound() {

        //given
        contacts.add(new Contact(firstPersonName, firstPersonNumber));
        contacts.add(new Contact(secondPersonName, secondPersonNumber));
        contacts.add(new Contact(thirdPersonName, thirdPersonNumber));
        contactByGroupTest.put(firstGroupName, contacts);


        String expected = firstPersonName;

        //when
        var result = sut.findByNumber(firstPersonNumber).getName();

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void existedContactCheck_matchCheck_true() {
        //given
        contacts.add(new Contact(firstPersonName, firstPersonNumber));
        boolean expected = true;

        //when
        boolean result = sut.existedContactsCheck(firstGroupName, firstPersonNumber, contacts);

        //then
        assertThat(expected, is(equalTo(result)));
    }


    @Test
    public void existedGroupCheck_matchCheck_false(){

        //given
        contactByGroupTest.put(firstGroupName, new ArrayList());

        //when
        sut.existedGroupCheck(secondGroupName, contactByGroupTest);

        //then
        MatcherAssert.assertThat(contactByGroupTest, Matchers.<String>hasKey(secondGroupName));

    }


}
