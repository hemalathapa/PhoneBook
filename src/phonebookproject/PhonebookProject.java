
package phonebookproject;

public class PhonebookProject {

    public static void main(String[] args) 
    {
        JdbcConnection db = new JdbcConnection();
        db.Setup();
        
        Menu m=new Menu();
        m.option();
        
        
        
    }
    
}
