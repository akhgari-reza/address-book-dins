package com.dins.hw.addressbook.addressbookrestservice.repository;

import com.dins.hw.addressbook.addressbookrestservice.model.Contact;
import com.dins.hw.addressbook.addressbookrestservice.model.User;
import java.util.List;

public interface UserRepository {

    /**
     *
     * @return
     */
    public List<User> findAll();

    /**
     *
     * @param id
     * @return
     */
    public User findUserByID(String id);

    /**
     *
     * @param firstName
     * @return
     */
    public List<User> findUserByFirstName(String firstName);

    /**
     *
     * @param firstName
     * @return
     */
    public List<User> findUserByLastName(String firstName);

    /**
     *
     * @param id
     * @return
     */
    public User deleteUserById(String id);

    /**
     *
     * @param user
     * @return
     */
    public User createUser(User user);


    /**
     *
     * @param userId
     * @return
     */
    public List<Contact> findAllContactOfSelectedUser(String userId);

    /**
     *
     * @param userId
     * @param contactId
     * @return
     */
    public Contact findContactByIdOfSelectedUser(String userId , String contactId);

    /**
     *
     * @param userId
     * @param contact
     * @return
     */
    public Contact createNewContactForSelectedUser(String userId , Contact contact);

    /**
     *
     * @param userId
     * @param contactId
     * @return
     */
    public Contact deleteContactOfSelectedUser(String userId , String contactId);

    /**
     *
     * @param userId
     * @param contactId
     * @param contact
     * @return
     */
    public Contact updateContactOfSelectedUser(String userId , String contactId , Contact contact);

    /**
     *
     * @param phoneNumber
     * @return
     */
    public Contact findContactByPhoneNumber(String phoneNumber);
}
