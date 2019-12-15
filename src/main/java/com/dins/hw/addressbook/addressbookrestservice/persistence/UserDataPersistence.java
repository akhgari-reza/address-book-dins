package com.dins.hw.addressbook.addressbookrestservice.persistence;

import com.dins.hw.addressbook.addressbookrestservice.annotation.UserRepositoryAnnotation;
import com.dins.hw.addressbook.addressbookrestservice.model.Contact;
import com.dins.hw.addressbook.addressbookrestservice.model.User;
import com.dins.hw.addressbook.addressbookrestservice.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@UserRepositoryAnnotation
public class UserDataPersistence implements UserRepository {

    // Persist list of users in Memory to test working
    //In a practical real implementation, data is retrieved
    // from RDBMS or Non-SQL DB using JPA or Hibernate ORM
    private final List<User> userListPersistence = new ArrayList<>();

    /**
     *
     */
    public UserDataPersistence() {
        //Insert data
        this.createSampleUsersInstance();
    }

    /**
     *
     * @return
     */
    @Override
    public List<User> findAll() {
        return this.userListPersistence;
    }


    /**
     *
     * @param id
     * @return
     */
    @Override
    public User findUserByID(String id) {
        return this.userListPersistence
                .stream().filter(user->user.getId().equals(id)).findFirst().orElse(null);
    }

    /**
     *
     * @param firstName
     * @return
     */
    @Override
    public List<User> findUserByFirstName(String firstName) {
        return this.userListPersistence
                .stream()
                .filter(user->user.getFirstName().contains(firstName))
                .collect(Collectors.toList());
    }

    /**
     *
     * @param lastName
     * @return
     */
    @Override
    public List<User> findUserByLastName(String lastName) {

        return this.userListPersistence.stream().filter(user -> user.getLstName().contains(lastName))
                .collect(Collectors.toList());
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public User deleteUserById(String id) {
        //this.userListPersistence.removeIf(user->user.getId().equals(id));
        User user = this.findUserByID(id);
        this.userListPersistence.remove(user);
        return user;
    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public User createUser(User user) {
        this.userListPersistence.add(user);
        return user;
    }


    /**
     *
     * @param userId
     * @return
     */
    @Override
    public List<Contact> findAllContactOfSelectedUser(String userId)
    {
        return this.findUserByID(userId).getContactList();
    }

    /**
     *
     * @param userId
     * @param contactId
     * @return
     */
    @Override
    public Contact findContactByIdOfSelectedUser(String userId, String contactId)
    {
        User selectedUser = this.findUserByID(userId);
        return selectedUser.getContactList()
                .stream()
                .filter(contact -> contact.getId().equals(contactId))
                .findAny()
                .orElse(null);
    }

    /**
     *
     * @param userId
     * @param contact
     * @return
     */
    @Override
    public Contact createNewContactForSelectedUser(String userId, Contact contact)
    {
        User user = this.findUserByID(userId);
        List<Contact> contactList = new LinkedList<>(user.getContactList());
        contactList.add(contact);
        user.setContactList(contactList);
        return contact;
    }

    /**
     *
     * @param userId
     * @param contactId
     * @return
     */
    @Override
    public Contact deleteContactOfSelectedUser(String userId , String contactId)
    {
        User user = this.findUserByID(userId);
        Contact contact = this.findContactByIdOfSelectedUser(userId , contactId);
        List<Contact> contactList = new LinkedList<>(user.getContactList());
        contactList.remove(this.findContactByIdOfSelectedUser(userId , contactId));
        return contact;
    }

    /**
     *
     * @param userId
     * @param contactId
     * @param contact
     * @return
     */
    @Override
    public Contact updateContactOfSelectedUser(String userId, String contactId , Contact contact)
    {
        Contact currentContact = this.findContactByIdOfSelectedUser(userId , contactId);
        currentContact.setFirstName(contact.getFirstName());
        currentContact.setLastName(contact.getLastName());
        currentContact.setPhoneNumbers(contact.getPhoneNumbers());
        return currentContact;
    }

    /**
     *
     * @param phoneNumber
     * @return
     */
    @Override
    public Contact findContactByPhoneNumber(String phoneNumber)
    {
        for (User user: this.userListPersistence) {
            List<Contact> contactList = user.getContactList();
            for (Contact contact : contactList) {
                List<String> numberList = contact.getPhoneNumbers();
                if (numberList.contains(phoneNumber))
                {
                    return contact;
                }
            }
        }
        return null;
    }


    //---------------------------------------------------------------------------------------------
    /*
     *
     *
     *
     *  Sample Persistence to work with collection of Users and Contacts
     *
     *
     */
    private void createSampleUsersInstance()
    {
        Contact contact1 = new Contact("050" , "Sina" , "Berberich",
                Arrays.asList("+497722210193","+498551563300","+496628448066"));
        Contact contact2 = new Contact("060" , "Vivien" , "Holzwarth",
                Arrays.asList("+496423302901"));
        Contact contact3 = new Contact("070" , "Maren" , "Röbbeln",
                Arrays.asList("+490384269689"));
        Contact contact4 = new Contact("080" , "Josef" , "Wesener",
                Arrays.asList("+496772386235" , "+49397799769"));

        User user1 = new User("001" , "Erhard" , "Stickel" ,
                Arrays.asList(contact1, contact2 , contact3));
        User user2 = new User("002" , "Pauline" , "Zimmer" ,
                Arrays.asList(contact3));
        User user3 = new User("003" , "Lydia" , "Löhnig" ,
                Arrays.asList(contact2 , contact4));

        userListPersistence.add(user1);
        userListPersistence.add(user2);
        userListPersistence.add(user3);
    }
}
