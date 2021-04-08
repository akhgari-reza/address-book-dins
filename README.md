Build :   `mvn clean package`

---------------------



# REST Results:
* Get the list of all users: 

`(GET) http://localhost:8080/dins/users/`

* Find user by id:

`(GET) http://localhost:8080/dins/users/searchById/{id}/`

* Find user by the first name or part of it:

`(GET) http://localhost:8080/dins/users/searchByFirstName/{firstName}/`

* Find user by the last name or part of it:

`(GET) http://localhost:8080/dins/users/searchByLastName/{lastName}/`

* Add a new user to the list:

`(POST) http://localhost:8080/dins/users/addNewUser/`

* Remove a user from the list:

`(DELETE) http://localhost:8080/dins/users/deleteUserById/{id}`

* Get all contacts in the address book of a selected user by id:

`(GET) http://localhost:8080/dins/contacts/findByUser/{userId}/`

* Find a specific contact in the address book of a selected user by id:

`(GET) http://localhost:8080/dins/contacts/findById/{userId}/{contactId}/`

* Add a new contact to the address book of a selected user by id:

`(POST) http://localhost:8080/dins/contacts/{userId}/new/`

* Remove a contact from the address book of a selected user by id:

`(DELETE) http://localhost:8080/dins/contacts/delete/{userId}/{contactId}/`

* Update a contact in the address book of a selected user by id:

`(UPDATE) http://localhost:8080/dins/contacts/update/{userId}/{contactId}/`

* Find a specific contact by phone number:

`(GET) http://localhost:8080/dins/contacts/findByNumber/{phoneNumber}/`

