
package phonebookproject;

public interface IAction 
{
    //retrieves all contact saved in the phonebook along with its contact and address details
    public void RetrieveAllContact();
    
    //add a new contact to the phonebook along with its contact and address details
    public void AddNewContact();
    
    //Searches a contact by its first name 
    public void SearchByName();
    
    //Deletes a contact by its first name and last name
    public void DeleteByName(String fn,String ln);
    
    //Updates the first name of the contact
    public void UpdateFirstName();
    
    //Updates the last name of the contact
    public void UpdateLastName();
    
    //updates the phone number and its type 
    public void UpdateContactByName();
    
    //updates the address details in address table
    public void UpdateAddressByName();
    
    //Adds a phone number to an exsisting contact
    public void AddNumberToExsisting();
    
    //Adds a address details to an exsisting contact
    public void AddAddressToExsisting();
    
    
}
