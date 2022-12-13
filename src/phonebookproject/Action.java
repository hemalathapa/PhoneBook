
package phonebookproject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static phonebookproject.JdbcConnection.conn;

public class Action implements IAction
{
    Scanner s=new Scanner(System.in);
    Scanner sc=new Scanner(System.in);
    
    
    @Override
    public void RetrieveAllContact() {
        try {
           //Create a Statement
            Statement stm = conn.createStatement();
            
            //Execute Query and Get Result set
            ResultSet rs =stm.executeQuery("SELECT p.people_id,p.fname,p.lname,p.email,p.relationship,p.birthday,c.phone ,c.numtype,a.addresstype,a.address,a.city,a.state,a.postal_code, a.country FROM people p INNER JOIN contact c ON p.people_id= c.people_id INNER JOIN address a ON p.people_id  = a.people_id order by fname;");
            
            System.out.println("Id    FirstName    LastName        Email      Relationship        Birthday         Phone           NumType           AddType          Address            City           State          Postal Code           Country ");
            
            //Loop to print each record
            while(rs.next())
            {
                System.out.print(rs.getInt("people_id")+"       ");
                System.out.print(rs.getString("fname")+"        ");
                System.out.print(rs.getString("lname")+"        ");
                System.out.print(rs.getString("email")+"        ");
                System.out.print(rs.getString("relationship")+"         ");
                System.out.print(rs.getString("birthday")+"         ");
                System.out.print(rs.getString("phone")+"        ");
                System.out.print(rs.getString("numtype")+"          ");
                System.out.print(rs.getString("addresstype")+"          ");
                System.out.print(rs.getString("address")+"         ");
                System.out.print(rs.getString("city")+"         ");
                System.out.print(rs.getString("state")+"        ");
                System.out.print(rs.getString("postal_code")+"        ");
                System.out.println(rs.getString("country")+"        ");
            }
            
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
    
    @Override
    public void  AddNewContact()
    {
        try {
            
            
            
            //Query to create a new data into all 3 tables
            String query1="START TRANSACTION;insert into people(fname,lname,email,relationship,birthday) values(?,?,?,?,?);insert into contact(people_id,numtype,phone) values(@people_id,?,?);insert into address(people_id,addresstype,address,city,state,postal_code,country) values(@people_id,?,?,?,?,?,?);COMMIT;";
            String query2="select(SELECT max(people_id)+1 from people) INTO @people_id;";
            //create Statement
            PreparedStatement stm1=conn.prepareStatement(query1);
            Statement stm2=conn.createStatement();
            stm2.executeQuery(query2);
            
            String fname,lname,phone,email,relationship,birthday,numtype,addresstype,address,city,state,postal_code,country;
            
            System.out.println("Enter First Name * : ");
            fname=s.nextLine();
            
            System.out.println("Enter Last Name * : ");
            lname=s.nextLine();
            
            System.out.println("Enter Email: ");
            email=s.nextLine();
            
            System.out.println("Enter relationship: ");
            relationship=s.nextLine();
            
            System.out.println("Enter Birthday: ");
            birthday=s.nextLine();
            
            System.out.println("Enter Number Category * : ");
            numtype=s.nextLine();
            
            System.out.println("Enter Phone Number* : ");
            phone=s.nextLine();
            
            System.out.println("Enter Address Category * : ");
            addresstype=s.nextLine();
            
            System.out.println("Enter Address * : ");
            address=s.nextLine();
            
            System.out.println("Enter City: ");
            city=s.nextLine();
            
            System.out.println("Enter State: ");
            state=s.nextLine();
            
            System.out.println("Enter Postal Code * : ");
            postal_code=s.nextLine();
            
            System.out.println("Enter Country: ");
            country=s.nextLine();
            
           
            
            stm1.setString(1, fname);
            stm1.setString(2, lname);
            stm1.setString(3, email);
            stm1.setString(4, relationship);
            stm1.setString(5, birthday);
            
            stm1.setString(6, numtype);
            stm1.setString(7,phone);
            
            stm1.setString(8, addresstype);
            stm1.setString(9, address);
            stm1.setString(10, city);
            stm1.setString(11, state);
            stm1.setString(12, postal_code);
            stm1.setString(13, country);
            
            
            
            
            stm1.executeUpdate();
            
            stm1.close();
            stm2.close();
            
            System.out.println("Contact Saved !");  
            System.out.println("\n");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    //SearchByName Method
    @Override
    public void SearchByName()
    {
        try {
            
            System.out.println("Enter First Name : ");
            String fn=s.nextLine();
            
          
            //Create a Statement 
            String query="SELECT p.people_id as id,p.fname,p.lname,p.email,p.relationship,p.birthday,c.phone as phone ,c.numtype,a.addresstype,a.address,a.city,a.state,a.postal_code, a.country FROM people p  JOIN contact as c ON p.people_id= c.people_id JOIN address as a ON c.people_id  = a.people_id where p.fname like CONCAT( '%',?,'%') ";
            PreparedStatement stm= conn.prepareStatement(query);
            
            
            stm.setString(1,fn);
            
            
            ResultSet rs=stm.executeQuery();
           
            
            System.out.println("Id  FirstName   LastName    Email   Relationship    Birthday    Phone   NumType     AddType     Address     City       State    Postal Code       Country ");
            
            while (rs.next()) {
                
                System.out.print(rs.getInt("id")+"     ");
                System.out.print(rs.getString("fname")+"     ");
                System.out.print(rs.getString("lname")+"     ");
                System.out.print(rs.getString("email")+"     ");
                System.out.print(rs.getString("relationship")+"     ");
                System.out.print(rs.getString("birthday")+"     ");
                System.out.print(rs.getString("phone")+"     ");
                System.out.print(rs.getString("numtype")+"     ");
                System.out.print(rs.getString("addresstype")+"     ");
                System.out.print(rs.getString("address")+"     ");
                System.out.print(rs.getString("city")+"     ");
                System.out.print(rs.getString("state")+"     ");
                System.out.print(rs.getString("postal_code")+"     ");
                System.out.println(rs.getString("country")+"     ");
        }
            
            stm.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
 
    //DeleteByName
    @Override
    public void DeleteByName(String fn,String ln)
    {
        
        
        try {
            
            String query="DELETE FROM people WHERE fname=? and lname=?";
            PreparedStatement stm= conn.prepareStatement(query);
            stm.setString(1,fn);
            stm.setString(2, ln);
            
            stm.executeUpdate();
            
            System.out.println("Contact deleted successfully!");
            System.out.println("\n");
            
            
            stm.close();
            
            //ResultSet rms=stm.executeUpdate("");
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public void UpdateFirstName() 
    {
        try {
            System.out.println("Enter the First Name: ");
            String fn=s.nextLine();
            
            System.out.println("Enter the Last Name: ");
            String ln=s.nextLine();
            
            System.out.println("Enter New First Name");
            String nfn=s.nextLine();
            
            String query="update people set fname=? where people.fname=? and people.lname=? ";
            
            PreparedStatement stm =conn.prepareStatement(query);
            
            stm.setString(1, nfn);
            stm.setString(2, fn);
            stm.setString(3, ln);
            
            stm.executeUpdate();
            
            System.out.println("Updated Successfully !");  
            System.out.println("\n");
            
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void UpdateLastName() 
    {
        try {
            System.out.println("Enter the First Name: ");
            String fn=s.nextLine();
            
            System.out.println("Enter the Last Name: ");
            String ln=s.nextLine();
            
            System.out.println("Enter New Last Name");
            String nln=s.nextLine();
            
            String query="update people set lname=? where people.fname=? and people.lname=? ";
            
            PreparedStatement stm =conn.prepareStatement(query);
            
            stm.setString(1, nln);
            stm.setString(2, fn);
            stm.setString(3, ln);
            
            stm.executeUpdate();
            
            System.out.println("Updated Successfully !");  
            System.out.println("\n");
            
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public void UpdateContactByName() 
    {
        try {
            System.out.println("Enter First name: ");
            String fn=s.nextLine();
            
            String query1="select p.fname,p.lname,c.numtype,c.phone,c.contact_id from people p join contact c on p.people_id=c.people_id where p.fname=? ";
            PreparedStatement stm1=conn.prepareStatement(query1);
            
            stm1.setString(1, fn);
            ResultSet rs1=stm1.executeQuery();
            System.out.println("First Name      Last Name        NumType     Phone      Contact ID");
            while(rs1.next())
            {
              
                System.out.print(rs1.getString("fname")+"   ");
                System.out.print(rs1.getString("lname")+"   ");
                System.out.print(rs1.getString("numtype")+"   ");
                System.out.print(rs1.getString("phone")+"   ");   
                System.out.println(rs1.getInt("contact_id")+"   ");
            }
            
            System.out.println("\n");
           
            System.out.println("Enter the contact id to be updated: ");
            int c_id=sc.nextInt();
            System.out.println("Enter the new phone number:");
            String nphone=s.nextLine();
            System.out.println("Enter the Number Type:");
            String nnumtype=s.nextLine();
            
            
            
            String query2="UPDATE contact as c SET c.phone=?,c.numtype=? WHERE c.contact_id=?;";
            PreparedStatement stm2=conn.prepareStatement(query2);
            stm2.setString(1, nphone);
            stm2.setString(2, nnumtype);
            
            stm2.setInt(3, c_id);
            
            stm2.executeUpdate();
            
            System.out.println("Contact Updated Successfully !");
            
            stm2.close();
            stm1.close();   
            
        } catch (SQLException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void UpdateAddressByName() 
    {
        try {
            System.out.println("Enter First Name: ");
            String fn=s.nextLine();
            System.out.println("Enter Last Name:");
            String ln=s.nextLine();
            
            
            String query1="select p.fname,p.lname,a.addresstype,a.address,a.city,a.state,a.country,a.postal_code,a.address_id from people p join address a on p.people_id=a.people_id where (p.fname=? and p.lname=?) ";
            PreparedStatement stm1=conn.prepareStatement(query1);
            
            stm1.setString(1, fn);
            stm1.setString(2, ln);
            ResultSet rs1=stm1.executeQuery();
            System.out.println("First Name      Last Name        AddresssType     Address      City     State   Country  postal code   Address id");
            while(rs1.next())
            {
              
                System.out.print(rs1.getString("fname")+"   ");
                System.out.print(rs1.getString("lname")+"   ");
                System.out.print(rs1.getString("addresstype")+"   ");
                System.out.print(rs1.getString("address")+"   ");   
                System.out.print(rs1.getString("city")+"   ");
                System.out.print(rs1.getString("state")+"   "); 
                System.out.print(rs1.getString("country")+"   ");   
                System.out.print(rs1.getString("postal_code")+"   ");   
                System.out.println(rs1.getInt("address_id")+"   ");
            }
            System.out.println("\n");
           
            System.out.println("Enter the address id to be updated: ");
            int a_id=sc.nextInt();
            System.out.println("Enter the new address * :");
            String naddress=s.nextLine();
            System.out.println("Enter the address Type * :");
            String naddresstype=s.nextLine();
            System.out.println("Enter the city:");
            String ncity=s.nextLine();
            System.out.println("Enter the state:");
            String nstate=s.nextLine();
            System.out.println("Enter the country:");
            String ncountry=s.nextLine();
            System.out.println("Enter the postal code * :");
            String npostal_code=s.nextLine();
            
            
            
            
            String query2="UPDATE address as a SET a.addresstype=?,a.address=?,a.city=?,a.state=?,a.country=?,a.postal_code=? WHERE a.a_id=?;";
            PreparedStatement stm2=conn.prepareStatement(query2);
            stm2.setString(1, naddresstype);
            stm2.setString(2, naddress); 
            stm2.setString(3, ncity);
            stm2.setString(4, nstate);
            stm2.setString(5, ncountry);
            stm2.setString(6, npostal_code);
            stm2.setInt(7, a_id);
            
            
            stm2.executeUpdate();
            
            System.out.println("Contact Updated Successfully !");
            System.out.println("\n");
            
            stm2.close();
        } catch (SQLException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    
    
    @Override
    public void AddNumberToExsisting() {
        try {
            System.out.println("Enter First Name: ");
            String fn=s.nextLine();
            System.out.println("Enter Last Name: ");
            String ln=s.nextLine();
            
            String query1="select (select p.people_id from people p where p.fname=? and p.lname=?) into @people_id";
            
            PreparedStatement stm1=conn.prepareStatement(query1);
            
            stm1.setString(1, fn);
            stm1.setString(2, ln);
            
            stm1.executeQuery();
            
            System.out.println("Enter the new number: ");
            String nnum=s.nextLine();
            System.out.println("Enter number type: ");
            String nnumtype=s.nextLine();
            
            String query2="insert into contact(people_id,numtype,phone) values(@people_id,?,?)";
            PreparedStatement stm2=conn.prepareStatement(query2);
            
            stm2.setString(1, nnumtype);
            stm2.setString(2, nnum);
            
            
            stm2.executeUpdate();
            
            System.out.println("Number Added Succussfully !");
            System.out.println("\n");
            
            stm2.close();
            stm1.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }
       

    }

    @Override
    public void AddAddressToExsisting() 
    {
        try {
            System.out.println("Enter First Name: ");
            String fn=s.nextLine();
            System.out.println("Enter Last Name: ");
            String ln=s.nextLine();
            
            String query1="select (select p.people_id from people p where p.fname=? and p.lname=?) into @people_id";
            
            PreparedStatement stm1=conn.prepareStatement(query1);
            
            stm1.setString(1, fn);
            stm1.setString(2, ln);
            
            stm1.executeQuery();
            
            System.out.println("Enter the new address *: ");
            String naddress=s.nextLine();
            System.out.println("Enter address type *: ");
            String naddresstype=s.nextLine();
            System.out.println("Enter the city:");
            String ncity=s.nextLine();
            System.out.println("Enter the state:");
            String nstate=s.nextLine();
            System.out.println("Enter the country:");
            String ncountry=s.nextLine();
            System.out.println("Enter the postal code * :");
            String npostal_code=s.nextLine();
            
            
            String query2="insert into address(people_id,addresstype,address,city,state,country,postal_code) values(@people_id,?,?,?,?,?,?)";
            PreparedStatement stm2=conn.prepareStatement(query2);
            
            stm2.setString(1, naddresstype);
            stm2.setString(2, naddress);
            stm2.setString(3, ncity);
            stm2.setString(4, nstate);
            stm2.setString(5, ncountry);
            stm2.setString(6, npostal_code);
            
            
            stm2.executeUpdate();
            
            System.out.println("Address Added Succussfully !");
            System.out.println("\n");
            
            stm2.close();
            stm1.close();
        } catch (SQLException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
       
}
    
    
    
    

