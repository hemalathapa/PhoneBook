
package phonebookproject;


import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static phonebookproject.JdbcConnection.conn;

public class Menu
{
    
    Scanner sc=new Scanner(System.in);
    Scanner s=new Scanner(System.in);
    public void option()
    {
        int input;
        do
        {
            System.out.println("""
                               \n
                               Enter 0: Exit 
                               Enter 1:Create a new contact 
                               Enter 2:Retrieve all contact 
                               Enter 3:search a contact 
                               Enter 4:delete a contact 
                               Enter 5:Update First Name of an existing contact
                               Enter 6:Update Last Name of an existing contact
                               Enter 7:Update Phone number of an existing contact 
                               Enter 8:Update address of an exsisting contact
                               Enter 9:Add phone number to an existing contact
                               Enter 10:Add Address to an exsisting contact
                               \n""");
            input=sc.nextInt();
            
            Action ac=new Action();
            
            switch(input)
            {
                case 0:
                    {
                try {
                    conn.close();
                    sc.close();
                    s.close();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                    }
                   
                    break;
                    
                case 1:
                    
                    ac.AddNewContact();
                    break;
                    
                case 2:
                    
                    ac.RetrieveAllContact();
                    break;
                    
                case 3:
                    
                    ac.SearchByName();
                    break;
                    
                case 4:
                    System.out.println("Enter First Name: ");
                    String fn=s.nextLine();
                    System.out.println("Enter Last Name: ");
                    String ln=s.nextLine();
                    ac.DeleteByName(fn,ln);
                    break;
                    
                case 5:
                    ac.UpdateFirstName();
                    break;
                    
                case 6:
                    ac.UpdateLastName();
                    break;
                    
                case 7:
                    ac.UpdateContactByName();
                    break;
                    
                case 8:
                    ac.UpdateAddressByName();
                    break;
                    
                case 9:
                    ac.AddNumberToExsisting();
                    break;
                    
                case 10:
                    ac.AddAddressToExsisting();
                    break;
                    
            }
        }while(input!=0);
        
    }

}