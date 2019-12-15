package com.dins.hw.addressbook.addressbookrestservice.controller;


import com.dins.hw.addressbook.addressbookrestservice.annotation.UserRepositoryAnnotation;
import com.dins.hw.addressbook.addressbookrestservice.model.Contact;
import com.dins.hw.addressbook.addressbookrestservice.model.User;
import com.dins.hw.addressbook.addressbookrestservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class AddressBookController {

    /**
     *
     */
    @Autowired
    @UserRepositoryAnnotation
    private UserRepository userRepository;

    /**
     *
     * @return
     */
    @GetMapping("/dins/users/")
    public List<User> getAllUsers()
    {
        return this.userRepository.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/dins/users/searchById/{id}/")
    public User getUserById(@PathVariable String id)
    {
        return this.userRepository.findUserByID(id);
    }

    /**
     *
     * @param firstName
     * @return
     */
    @GetMapping("/dins/users/searchByFirstName/{firstName}/")
    public List<User> getUsersByFirstName(@PathVariable String firstName)
    {
        return this.userRepository.findUserByFirstName(firstName);
    }

    /**
     *
     * @param lastName
     * @return
     */
    @GetMapping("/dins/users/searchByLastName/{lastName}/")
    public List<User> getUsersByLastName(@PathVariable String lastName)
    {
        return this.userRepository.findUserByLastName(lastName);
    }

    /**
     *
     * @param user
     * @return
     */
    @PostMapping("/dins/users/addNewUser/")
    public User addNewUser(@RequestBody User user)
    {
        return this.userRepository.createUser(user);
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/dins/users/deleteUserById/{id}" , method = RequestMethod.DELETE)
    public User deleteUserById(@PathVariable String id)
    {
        return this.userRepository.deleteUserById(id);
    }



    //------------------------------------------------------------------------------------------

    /**
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/dins/contacts/findByUser/{userId}/" , method = RequestMethod.GET)
    public List<Contact> getAllContactBySelectedUser(@PathVariable String userId)
    {
        return this.userRepository.findAllContactOfSelectedUser(userId);
    }


    /**
     *
     * @param phoneNumber
     * @return
     */
    @RequestMapping(value = "/dins/contacts/findByNumber/{phoneNumber}/" , method = RequestMethod.GET)
    public Contact getContactByPhoneNumber(@PathVariable String phoneNumber)
    {
        return this.userRepository.findContactByPhoneNumber(phoneNumber);
    }

    /**
     *
     * @param userId
     * @param contactId
     * @return
     */
    @RequestMapping(value = "/dins/contacts/findById/{userId}/{contactId}/" , method = RequestMethod.GET)
    public Contact getContactById(@PathVariable String userId , @PathVariable String contactId)
    {
        return this.userRepository.findContactByIdOfSelectedUser(userId , contactId);
    }

    /**
     *
     * @param userId
     * @param contact
     * @return
     */
    @RequestMapping(value = "/dins/contacts/{userId}/new/" , method = RequestMethod.POST)
    public Contact createNewContactForSelectedUser(@PathVariable String userId ,
                                                   @RequestBody Contact contact)
    {
        return this.userRepository.createNewContactForSelectedUser(userId , contact);
    }

    /**
     *
     * @param userId
     * @param contactId
     */
    @RequestMapping(value="/dins/contacts/delete/{userId}/{contactId}/" , method = RequestMethod.DELETE)
    public void deleteContactOfSelectedUser(@PathVariable String userId , @PathVariable String contactId)
    {
        this.userRepository.deleteContactOfSelectedUser(userId , contactId);
    }


    /**
     *
     * @param userId
     * @param contactId
     * @param contact
     * @return
     */
    @RequestMapping(value = "/dins/contacts/update/{userId}/{contactId}/" , method = RequestMethod.PUT)
    public Contact updateContactOfSelectedUser(@PathVariable String userId ,
                                               @PathVariable String contactId ,
                                               @RequestBody Contact contact)
    {
        return this.userRepository.updateContactOfSelectedUser(userId , contactId , contact);
    }


}
